package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Tache;

import java.util.List;

public interface TacheServiceInterface {
    Tache saveMainTache(Tache tache,Long idProcessus);
    List<Tache> getAllTachesMeres();
    void deleteTacheMere(Long id);
}
