package ma.ingecys.project.businessProcessManagement.repository;

import ma.ingecys.project.businessProcessManagement.bo.Connexion;
import ma.ingecys.project.businessProcessManagement.bo.ConnexionID;
import ma.ingecys.project.businessProcessManagement.bo.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnexionRepository extends JpaRepository<Connexion, ConnexionID> {
    List<Connexion> findConnexionsByFromProcessusIdProcessusOrToProcessusIdProcessus(Long from_processus_idProcessus, Long to_processus_idProcessus);
    List<Connexion> findConnexionsByFromIdEtapeOrToIdEtape(Long from_idEtape, Long to_idEtape);
}
