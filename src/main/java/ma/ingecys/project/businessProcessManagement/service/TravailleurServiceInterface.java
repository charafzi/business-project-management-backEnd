package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Travailleur;
import ma.ingecys.project.businessProcessManagement.dto.TravailleurDTO;

import java.util.List;

public interface TravailleurServiceInterface {
    List<TravailleurDTO> getAllTravailleurs();
}
