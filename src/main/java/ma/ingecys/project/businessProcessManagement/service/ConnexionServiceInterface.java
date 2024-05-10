package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Connexion;

import java.util.List;

public interface ConnexionServiceInterface {
    public void saveConnexions(List<Connexion> connexions);
    public List<Connexion> getAllConnexionsByProcess(Long id);
    public List<Connexion> getAllConnexionsByEtape(Long id);
    public void updateConnexionsByProcess(Long id,List<Connexion> connexions);
}
