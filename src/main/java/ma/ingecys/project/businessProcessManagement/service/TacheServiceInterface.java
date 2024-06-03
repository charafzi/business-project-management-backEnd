package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Processus;
import ma.ingecys.project.businessProcessManagement.bo.Tache;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TacheServiceInterface {
    Tache saveMainTache(Tache tache,Long idProcessus);
    List<Tache> getAllTachesMeres();
    Tache getMainTache(Long id);
    void deleteTacheMere(Long id);
    Processus getProcessByIdMainTache(Long id);
    void updateSousTache(Tache tache,Long id);
}
