package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Etape;

import java.util.List;

public interface EtapeServiceInterface {
    public List<Etape> getAllEtapes();
    public void save(Etape etape);
    public void update(Etape etape);
    public void delete(Long id);
}
