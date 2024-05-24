package ma.ingecys.project.businessProcessManagement.controller;

import ma.ingecys.project.businessProcessManagement.bo.Categorie;
import ma.ingecys.project.businessProcessManagement.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    CategorieService categorieService;
    @GetMapping("")
    public List<Categorie> getAllCategories(){
        return categorieService.getAllCategories();
    }

    @PostMapping()
    public Categorie saveCategorie(@RequestBody Categorie categorie){
        return categorieService.save(categorie);
    }

    @PutMapping("/{id}")
    public void updateCategorie(@PathVariable Long id,
                                @RequestBody Categorie categorie){
        categorieService.update(id,categorie);
    }

    @DeleteMapping("/{id}")
    public void deleteCategorie(@PathVariable Long id){
        categorieService.delete(id);
    }
}
