package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Categorie;

import java.util.List;

public interface CategorieServiceInterface {
    public List<Categorie> getAllCategories();
    public Categorie save(Categorie categorie);
    public void delete(Long id);
    public void  update(Long id,Categorie categorie);
}
