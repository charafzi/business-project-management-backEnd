package ma.ingecys.project.businessProcessManagement.repository;

import ma.ingecys.project.businessProcessManagement.bo.Processus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessusRepository extends JpaRepository<Processus,Long> {
}
