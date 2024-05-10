package ma.ingecys.project.businessProcessManagement.controller;

import ma.ingecys.project.businessProcessManagement.bo.Type;
import ma.ingecys.project.businessProcessManagement.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/types")
public class TypeController {
     @Autowired
    TypeService typeService;

    @GetMapping("/alltypes")
    public List<Type> getAllTypes(){
        return typeService.getAllTypes();
    }
}
