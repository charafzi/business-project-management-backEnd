package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Etape;
import ma.ingecys.project.businessProcessManagement.bo.Processus;

import java.util.List;

public interface ProcessusServiceInterface {
    public List<Processus> getAllprocessus();
    public Processus getProcessById(Long id);
    public void save(Processus process);
    public void delete(Long id);
    public void updateProcess(Long id,Processus p);
    public void updateProcessEtape(Long id, List<Etape> etapes);
}
