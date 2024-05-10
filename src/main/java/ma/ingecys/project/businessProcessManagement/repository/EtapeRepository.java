package ma.ingecys.project.businessProcessManagement.repository;

import ma.ingecys.project.businessProcessManagement.bo.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapeRepository extends JpaRepository<Etape,Long> {
}
