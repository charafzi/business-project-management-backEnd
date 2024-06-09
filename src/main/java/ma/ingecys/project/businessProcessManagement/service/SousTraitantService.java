package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.SousTraitant;
import ma.ingecys.project.businessProcessManagement.dto.SousTraitantDTO;
import ma.ingecys.project.businessProcessManagement.repository.SousTraitantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SousTraitantService implements SousTraitantServiceInterface{
    @Autowired
    private SousTraitantRepository sousTraitantRepository;
    @Override
    public List<SousTraitantDTO> getAllSousTraitant() {
        return sousTraitantRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private SousTraitantDTO toDTO(SousTraitant s){
        return SousTraitantDTO.builder()
                .idUser(s.getIdUser())
                .nom(s.getNom())
                .prenom(s.getPrenom())
                .email(s.getEmail())
                .numTel(s.getNumTel())
                .adresse(s.getAdresse())
                .role("SOUSTRAITANT")
                .build();
    }
}
