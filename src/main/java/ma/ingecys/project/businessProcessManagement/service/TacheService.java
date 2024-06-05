package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.*;
import ma.ingecys.project.businessProcessManagement.repository.ProcessusRepository;
import ma.ingecys.project.businessProcessManagement.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;

@Service
public class TacheService implements TacheServiceInterface {
    @Autowired
   private ProcessusRepository processusRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Override
    public Tache saveMainTache(Tache tache, Long idProcessus) {
        Optional<Processus> processus = processusRepository.findById(idProcessus);
        if(processus.isEmpty())
            throw new IllegalStateException("Aucun processus existe evec id = "+idProcessus);
        Processus process = processus.get();
        List<Etape> etapes = process.getEtapes();

        //creation de la tache mère
        Tache mainTache = new Tache();
        mainTache.setObjetTache(tache.getObjetTache());
        mainTache.setDateDebutPrevue(tache.getDateDebutPrevue());
        mainTache.setDateExpiration(tache.getDateExpiration());
        mainTache.setSousTraitant(tache.getSousTraitant());
        mainTache.setTravailleurs(tache.getTravailleurs());
        mainTache.setEtape(null);
        mainTache.setTache_mere(null);

        etapes.sort(new EtapeComparatorOrdre());

        //création des sous taches que chacune sera associé à une étape

        //ces dates seront utilsées pour calculer les dates en basant sur l'ordre (cas des étapes sans précédent)
        List<Tache> sousTaches = new ArrayList<>();
        LocalDateTime dateDebutPrevue = mainTache.getDateDebutPrevue();
        LocalDateTime dateExpiration = mainTache.getDateDebutPrevue();
        //pour stoker date expiration pour chaque tache
        Map<Etape,LocalDateTime> mapEtapeDateExp = new HashMap<>();

        for(Etape etape:etapes){
            Tache sousTache = new Tache();
            sousTache.setTache_mere(mainTache);
            sousTache.setTravailleurs(mainTache.getTravailleurs());
            sousTache.setSousTraitant(mainTache.getSousTraitant());
            sousTache.setStatutEtape(StatutEtape.PAS_ENCORE_COMMENCEE);
            sousTache.setStatutTache(null);
            sousTache.setEtape(etape);

            List<Connexion> connexions = etape.getPrecedents();

            if(connexions.isEmpty()){
                dateDebutPrevue = dateExpiration;
            }
            else{
                List<LocalDateTime> dates = new ArrayList<>();

                for(Connexion con: connexions){
                    LocalDateTime date = mapEtapeDateExp.get(con.getFrom());
                    switch (con.getDelaiAttenteUnite()){
                        case HOUR :
                            date = date.plusHours(con.getDelaiAttente());
                            break;
                        case DAY:
                            date = date.plusDays(con.getDelaiAttente());
                            break;
                        case MONTH:
                            date = date.plusMonths(con.getDelaiAttente());
                            break;
                    }
                    dates.add(date);

                }

                Optional<LocalDateTime> maxDate = dates.stream().max(LocalDateTime::compareTo);
                dateDebutPrevue = maxDate.get();
            }

            switch (etape.getDureeEstimeeUnite()){
                case HOUR :
                    dateExpiration = dateDebutPrevue.plusHours(etape.getDureeEstimee());
                    break;
                case DAY:
                    dateExpiration = dateDebutPrevue.plusDays(etape.getDureeEstimee());
                    break;
                case MONTH:
                    dateExpiration = dateDebutPrevue.plusMonths(etape.getDureeEstimee());
                    break;
            }
            sousTache.setDateDebutPrevue(dateDebutPrevue);
            sousTache.setDateExpiration(dateExpiration);
            mapEtapeDateExp.put(etape,sousTache.getDateExpiration());

            sousTaches.add(sousTache);
        }
        mainTache.setSous_taches(sousTaches);
        return tacheRepository.save(mainTache);
    }

    @Override
    public List<Tache> getAllTachesMeres() {
        List<Tache> taches = tacheRepository.findAll().stream().toList();
        List<Tache> mainTaches = new ArrayList<>();
        for(Tache tache : taches){
            if(tache.getTache_mere()==null){
                //m-à-j pourcentage
                for(Tache t :tache.getSous_taches()){
                    double pourcentage = 0;
                    /**
                     * pourcentage = (Aujourd'hui - date debut effective) / Duree etape
                     **/
                    if(t.getDateDebutEffective() != null){
                        if(LocalDateTime.now().isBefore(t.getDateDebutEffective()))
                            pourcentage = 0;
                        else{
                            int duration = (int) Duration.between(t.getDateDebutEffective(),LocalDateTime.now()).toHours();
                            int dureeEtape = t.getEtape().getDureeEstimee();
                            switch (t.getEtape().getDureeEstimeeUnite()){
                                case HOUR :
                                    break;
                                case DAY:
                                    dureeEtape*=24;
                                    break;
                                case MONTH:
                                    dureeEtape*=24*30;
                                    break;
                            }
                            if(duration>dureeEtape){
                                pourcentage=1;
                            }else{
                                pourcentage = (double) duration/(double) dureeEtape;
                            }
                            System.err.println("********************** Date debut effective"+t.getDateDebutEffective()+", Now = "+LocalDateTime.now()+",Difference : "+duration+", Percentege : "+pourcentage);
                        }
                    }
                    System.err.println("Pourcentage : "+pourcentage);
                    t.setPourcentage(pourcentage*100);
                }
                tacheRepository.save(tache);
                mainTaches.add(tache);
            }
        }
        return mainTaches;
    }

    @Override
    public Tache getMainTache(Long id) {
        Optional<Tache> mainTache = tacheRepository.findById(id);
        if(mainTache.isEmpty())
            throw new IllegalStateException("Aucune tache mère existe avec id = "+id);
        return mainTache.get();
    }

    @Override
    public void deleteTacheMere(Long id) {
        boolean exists = tacheRepository.existsById(id);
        if(!exists)
            throw new IllegalStateException("Aucune tache mère existe avec id = "+id);

        tacheRepository.deleteById(id);
    }

    @Override
    public Processus getProcessByIdMainTache(Long id) {
        Optional<Tache> mainTache = tacheRepository.findById(id);
        if(mainTache.isEmpty())
            throw new IllegalStateException("Aucune tache mère existe avec id = "+id);
        return mainTache.get().getSous_taches().getLast().getEtape().getProcessus();
    }

    @Override
    public void updateSousTache(Tache tache,Long id) {
        Optional<Tache> sousTache = tacheRepository.findById(id);
        if(sousTache.isEmpty())
            throw new IllegalStateException("Aucune Sous-Tache existe avec id = "+tache.getIdTache());
        Tache st = sousTache.get();
        st.setDateDebutEffective(tache.getDateDebutEffective());
        st.setDateFinEffective(tache.getDateFinEffective());
        st.setStatutTache(tache.getStatutTache());
        st.setPriorite(tache.getPriorite());
        st.setPourcentage(tache.getPourcentage());

        tacheRepository.save(st);
    }

    public List<Connexion> getConnexions(){
        Optional<Tache> tache = tacheRepository.findById(160L);
        if(tache.isEmpty())
            return null;
        return tache.get().getSous_taches().get(2).getEtape().getPrecedents();
    }
}
