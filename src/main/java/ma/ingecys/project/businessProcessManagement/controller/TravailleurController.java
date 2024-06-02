package ma.ingecys.project.businessProcessManagement.controller;

import ma.ingecys.project.businessProcessManagement.bo.Travailleur;
import ma.ingecys.project.businessProcessManagement.service.TravailleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/travailleur")
public class TravailleurController {
    @Autowired
    TravailleurService travailleurService;

    @GetMapping
    public List<Travailleur> getAllTypes(){
        return travailleurService.getAllTravailleurs();
    }
}
