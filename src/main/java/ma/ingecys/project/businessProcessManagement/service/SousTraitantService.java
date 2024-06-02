package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.SousTraitant;
import ma.ingecys.project.businessProcessManagement.repository.SousTraitantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SousTraitantService implements SousTraitantServiceInterface{
    @Autowired
    private SousTraitantRepository sousTraitantRepository;
    @Override
    public List<SousTraitant> getAllSousTraitant() {
        return sousTraitantRepository.findAll()
                .stream()
                .toList();
    }
}
