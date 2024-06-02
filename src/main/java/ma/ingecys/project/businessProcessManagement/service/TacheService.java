package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.*;
import ma.ingecys.project.businessProcessManagement.repository.ProcessusRepository;
import ma.ingecys.project.businessProcessManagement.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        System.out.println("DATE DEBUT : "+tache.getDateDebutPrevue().toString());
        System.out.println("DATE FIN: "+tache.getDateExpiration().toString());

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
        List<Tache> sousTaches = new ArrayList<>();
        LocalDateTime dateDebutPrevue = mainTache.getDateDebutPrevue();
        LocalDateTime dateExpiration = mainTache.getDateExpiration();


        for(Etape etape:etapes){
            Tache sousTache = new Tache();
            sousTache.setTache_mere(mainTache);
            sousTache.setTravailleurs(mainTache.getTravailleurs());
            sousTache.setSousTraitant(mainTache.getSousTraitant());
            sousTache.setStatutEtape(StatutEtape.PAS_ENCORE_COMMENCEE);
            sousTache.setEtape(etape);
            sousTache.setDateDebutPrevue(dateDebutPrevue);
            sousTache.setDateExpiration(dateExpiration);
            switch (etape.getDureeEstimeeUnite()){
                case HOUR :
                    dateDebutPrevue = dateDebutPrevue.plusHours(etape.getDureeEstimee());
                    dateExpiration = dateExpiration.plusHours(etape.getDureeEstimee());
                    break;
                case DAY:
                    dateDebutPrevue = dateDebutPrevue.plusDays(etape.getDureeEstimee());
                    dateExpiration = dateExpiration.plusDays(etape.getDureeEstimee());
                    break;
                case MONTH:
                    dateDebutPrevue = dateDebutPrevue.plusMonths(etape.getDureeEstimee());
                    dateExpiration = dateExpiration.plusMonths(etape.getDureeEstimee());
                    break;
            }
            System.out.println("DATE AFTER : "+dateDebutPrevue.toString());
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
            if(tache.getTache_mere()==null)
                mainTaches.add(tache);
        }
        return mainTaches;
    }

    @Override
    public void deleteTacheMere(Long id) {
        boolean exists = tacheRepository.existsById(id);
        if(!exists)
            throw new IllegalStateException("Aucune tache existe avec id = "+id);

        tacheRepository.deleteById(id);
    }
}
