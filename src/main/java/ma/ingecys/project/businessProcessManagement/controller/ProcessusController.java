package ma.ingecys.project.businessProcessManagement.controller;

import ma.ingecys.project.businessProcessManagement.bo.Etape;
import ma.ingecys.project.businessProcessManagement.bo.Processus;
import ma.ingecys.project.businessProcessManagement.bo.StatutEtape;
import ma.ingecys.project.businessProcessManagement.service.ProcessusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/processus")
public class ProcessusController {
    @Autowired
    private ProcessusService processusService;

    @GetMapping("/allprocess")
    public List<Processus> getAllProcessus(){
        return processusService.getAllprocessus();
    }

    @GetMapping(path = "/process/{id}")
    public Processus getProcessus(@PathVariable Long id){
        return processusService.getProcessById(id);
    }

    @GetMapping(path = "/process/{id}/etapes")
    public List<Etape> getEtapesByProcess(
            @PathVariable Long id
    ){
        return processusService.getProcessById(id).getEtapes();
    }

    @PostMapping()
    public Processus saveProcessus(@RequestBody Processus p
    ){
        return processusService.save(p);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProcessus(@PathVariable Long id){
        processusService.delete(id);
    }

    @PutMapping(path = "/{id}")
    public void updateProcessEtapes(@PathVariable Long id,
                                    @RequestBody List<Etape> etapes){
        processusService.updateProcessEtape(id,etapes);
    }

    @PutMapping(path = "process/{id}")
    public void updateProcess(@PathVariable Long id,
                              @RequestBody Processus p
                              ){
        processusService.updateProcess(id,p);
    }


}
