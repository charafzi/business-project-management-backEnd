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

    @GetMapping("/test")
    public void test(){
        Etape etape1 = new Etape();
        etape1.setOrdre(1);
        etape1.setPourcentage(20);
        etape1.setDureeEstimee(10);
        etape1.setFirst(true);
        etape1.setEnd(false);
        etape1.setValidate(false);
        etape1.setPaid(false);
        etape1.setStatutEtape(StatutEtape.COMMENCEE);
        etape1.setValide_par(null);

        Etape etape2 = new Etape();
        etape2.setOrdre(2);
        etape2.setPourcentage(100);
        etape2.setDureeEstimee(20);
        etape2.setFirst(false);
        etape2.setEnd(true);
        etape2.setValidate(true);
        etape2.setPaid(false);
        etape2.setStatutEtape(StatutEtape.PAS_ENCORE_COMMENCEE);
        etape2.setValide_par(null);

        List<Etape> etapes = new ArrayList<>();
        etapes.add(etape1);
        etapes.add(etape2);
        Processus processus = Processus.builder()
                .nom("Test Processus")
                .description("Test.....")
                .etapes(etapes)
                .build();

        processusService.save(processus);

    }

}
