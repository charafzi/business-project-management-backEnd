package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Etape;
import ma.ingecys.project.businessProcessManagement.repository.EtapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapeService implements EtapeServiceInterface{
    @Autowired
    EtapeRepository etapeRepository;
    @Override
    public List<Etape> getAllEtapes() {
        return null;
    }

    @Override
    public void save(Etape etape) {
        etapeRepository.save(etape);
    }

    @Override
    public void update(Etape etape) {

    }

    @Override
    public void delete(Long id) {

    }
}
