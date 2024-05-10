package ma.ingecys.project.businessProcessManagement.controller;

import ma.ingecys.project.businessProcessManagement.bo.Connexion;
import ma.ingecys.project.businessProcessManagement.service.ConnexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/connexions")
public class ConnexionController {
    @Autowired
    ConnexionService connexionService;
    @GetMapping("/processus/{id}")
    List<Connexion> getConnexionsByProcess(@PathVariable Long id){
        return connexionService.getAllConnexionsByProcess(id);
    }

    @GetMapping("/etape/{id}")
    List<Connexion> getConnexionsByEtape(@PathVariable Long id){
        return connexionService.getAllConnexionsByEtape(id);
    }

    @PutMapping("/processus/{id}")
    void updateConnexionsByProcess(@PathVariable Long id,
                                    @RequestBody List<Connexion> connexions
    ){
        connexionService.updateConnexionsByProcess(id,connexions);
    }
}
