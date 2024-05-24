package ma.ingecys.project.businessProcessManagement.repository;

import ma.ingecys.project.businessProcessManagement.bo.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}
