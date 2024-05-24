package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Categorie;
import ma.ingecys.project.businessProcessManagement.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService implements CategorieServiceInterface{
    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository
                .findAll()
                .stream()
                .toList();
    }

    @Override
    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public void delete(Long id) {
        boolean exist = categorieRepository.existsById(id);
        if(!exist)
            throw new IllegalStateException("Aucune categorie existe avec id="+id);
        categorieRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Categorie categorie) {
        Optional<Categorie> cat = categorieRepository.findById(id);
        if(cat.isEmpty())
            throw new IllegalStateException("Aucune categorie existe avec id="+id);
        Categorie c = cat.get();
        c.setNom(categorie.getNom());
        categorieRepository.save(c);
    }
}
