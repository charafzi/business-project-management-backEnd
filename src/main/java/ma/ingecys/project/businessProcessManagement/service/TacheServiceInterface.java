package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Paiement;
import ma.ingecys.project.businessProcessManagement.bo.Processus;
import ma.ingecys.project.businessProcessManagement.bo.Tache;
import ma.ingecys.project.businessProcessManagement.bo.Validation;

import java.time.LocalDateTime;
import java.util.List;

public interface TacheServiceInterface {
    Tache saveTacheMere(Tache tache, Long idProcessus);
    List<Tache> getAllTachesMeres();
    Tache getTacheMere(Long id);
    void deleteTacheMere(Long id);
    Processus getProcessByIdTacheMere(Long id);
    void updateSousTache(Tache tache,Long id);
    void updateTacheMere(Long id,Tache tache);
    void updateTacheMereDateExpiration(Long id, LocalDateTime dateExpiration);
    void savePayement(Long id, Paiement paiement);
    List<Paiement> getAllPayments(Long id);
    void saveValidation(Long id, Validation paiement);
    List<Validation> getAllValidations(Long id);
}
