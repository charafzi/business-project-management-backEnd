package ma.ingecys.project.businessProcessManagement.service;

import jakarta.transaction.Transactional;
import ma.ingecys.project.businessProcessManagement.bo.Etape;
import ma.ingecys.project.businessProcessManagement.bo.Processus;
import ma.ingecys.project.businessProcessManagement.repository.EtapeRepository;
import ma.ingecys.project.businessProcessManagement.repository.ProcessusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessusService implements ProcessusServiceInterface{
    @Autowired
    private ProcessusRepository processusRepository;
    @Autowired
    private EtapeRepository etapeRepository;
    @Override
    public List<Processus> getAllprocessus() {
        return processusRepository
                .findAll()
                .stream()
                .toList();
    }

    @Override
    public Processus getProcessById(Long id) {
        Optional<Processus> processus= processusRepository.findById(id);
        if (processus.isEmpty())
            throw new IllegalStateException("Aucun processus existe avec Id = "+id);
        return processus.get();
    }

    @Override
    public Processus save(Processus process) {
        return processusRepository.save(process);
    }

    @Override
    public void delete(Long id) {
        boolean exists = processusRepository.existsById(id);
        if(!exists)
            throw new IllegalStateException("Aucun processus existe avec Id = "+id);
        processusRepository.deleteById(id);
    }

    @Override
    public void updateProcess(Long id, Processus p) {
        Optional<Processus> processus = processusRepository.findById(id);

        if(processus.isEmpty())
            throw new IllegalStateException("Aucun processus existe avec Id = "+id);
        Processus process = processus.get();
        process.setNom(p.getNom());
        process.setDescription(p.getDescription());
        process.setNbLignes(p.getNbLignes());
        process.setNbColonnes(p.getNbColonnes());
        processusRepository.save(process);
    }

    @Override
    public void updateProcessEtape(Long id, List<Etape> etapes) {
        Optional<Processus> process = processusRepository.findById(id);

        if(process.isEmpty())
            throw new IllegalStateException("Aucun processus existe avec Id = "+id);
        Processus p = process.get();

        List<Etape> toRemove = new ArrayList<>();

        for(Etape oldEtape : p.getEtapes()){
            boolean found = false;
            for(Etape newEtape: etapes){
                //Etape déjà crée et existe toujours
                if (oldEtape.getIdEtape().equals(newEtape.getIdEtape()))
                {
                    oldEtape.setDescription(newEtape.getDescription());
                    oldEtape.setOrdre(newEtape.getOrdre());
                    oldEtape.setStatutEtape(newEtape.getStatutEtape());
                    oldEtape.setDureeEstimee(newEtape.getDureeEstimee());
                    oldEtape.setDelaiAttente(newEtape.getDelaiAttente());
                    oldEtape.setIndexLigne(newEtape.getIndexLigne());
                    oldEtape.setIndexColonne(newEtape.getIndexColonne());
                    oldEtape.setFirst(newEtape.isFirst());
                    oldEtape.setEnd(newEtape.isEnd());
                    oldEtape.setValidate(newEtape.isValidate());
                    oldEtape.setPaid(newEtape.isPaid());
                    oldEtape.setPourcentage(newEtape.getPourcentage());
                    oldEtape.setCategorie(newEtape.getCategorie());
                    oldEtape.setDureeEstimeeUnite(newEtape.getDureeEstimeeUnite());
                    oldEtape.setDelaiAttenteUnite(newEtape.getDelaiAttenteUnite());
                    found = true;
                    break;
                }
            }
            if(!found)
                toRemove.add(oldEtape);
        }

        p.getEtapes().removeAll(toRemove);
        //ajouter les nouvelles étapes
        for (Etape newEtape : etapes) {
            if (newEtape.getIdEtape().equals(-1L)) {
                //etapeRepository.save(newEtape);
                p.getEtapes().add(newEtape);
            }
        }

        processusRepository.save(p);
    }
}
