package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Travailleur;
import ma.ingecys.project.businessProcessManagement.dto.TravailleurDTO;
import ma.ingecys.project.businessProcessManagement.repository.TravailleurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravailleurService implements TravailleurServiceInterface{
    @Autowired
    private TravailleurRepository travailleurRepository;
    @Override
    public List<TravailleurDTO> getAllTravailleurs() {
        return travailleurRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private TravailleurDTO toDTO(Travailleur travailleur) {
        return TravailleurDTO.builder()
                .idUser(travailleur.getIdUser())
                .nom(travailleur.getNom())
                .prenom(travailleur.getPrenom())
                .email(travailleur.getEmail())
                .numTel(travailleur.getNumTel())
                .matricule(travailleur.getMatricule())
                .role("TRAVAILLEUR")
                .build();
    }
}
