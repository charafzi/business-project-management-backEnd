package ma.ingecys.project.businessProcessManagement.controller;

import ma.ingecys.project.businessProcessManagement.bo.SousTraitant;
import ma.ingecys.project.businessProcessManagement.dto.SousTraitantDTO;
import ma.ingecys.project.businessProcessManagement.service.SousTraitantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sousTraitant")
public class SousTraitantController {
    @Autowired
    SousTraitantService sousTraitantService;

    @GetMapping
    List<SousTraitantDTO> getAllSoustraitants(){

        return sousTraitantService.getAllSousTraitant();
    }
}
