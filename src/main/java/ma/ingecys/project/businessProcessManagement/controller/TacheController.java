package ma.ingecys.project.businessProcessManagement.controller;

import ma.ingecys.project.businessProcessManagement.bo.Connexion;
import ma.ingecys.project.businessProcessManagement.bo.Processus;
import ma.ingecys.project.businessProcessManagement.bo.Tache;
import ma.ingecys.project.businessProcessManagement.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tache")
public class TacheController {
    @Autowired
    TacheService tacheService;

    @GetMapping
    List<Tache> getAllTachesMeres(){
        return tacheService.getAllTachesMeres();
    }

    @PostMapping("/process/{id}")
    Tache saveMainTache(
            @RequestBody Tache tache,
            @PathVariable Long id){
        return tacheService.saveMainTache(tache,id);
    }

    @DeleteMapping("/{id}")
    void deleteTacheMere(@PathVariable Long id){
        this.tacheService.deleteTacheMere(id);
    }

    @GetMapping("/{id}")
    Tache getMainTache(@PathVariable Long id){
        return tacheService.getMainTache(id);
    }

    @GetMapping("/{id}/process")
    Processus getProcessByIdMainTache(@PathVariable Long id){
        return tacheService.getProcessByIdMainTache(id);
    }

    @PutMapping("/sousTache/{id}")
    void updateSousTache(
            @PathVariable Long id,
            @RequestBody Tache tache
    ){
        tacheService.updateSousTache(tache,id);
    }

    @GetMapping("/test")
    List<Connexion> getConenxions(){
        return tacheService.getConnexions();
    }

}
