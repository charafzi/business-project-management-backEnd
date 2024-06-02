package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Travailleur;
import ma.ingecys.project.businessProcessManagement.repository.TravailleurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravailleurService implements TravailleurServiceInterface{
    @Autowired
    private TravailleurRepository travailleurRepository;
    @Override
    public List<Travailleur> getAllTravailleurs() {
        return travailleurRepository.findAll()
                .stream()
                .toList();
    }
}
