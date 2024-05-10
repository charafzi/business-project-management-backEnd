package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Connexion;
import ma.ingecys.project.businessProcessManagement.repository.ConnexionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnexionService implements ConnexionServiceInterface{
    @Autowired
    ConnexionRepository connexionRepository;
    @Override
    public void saveConnexions(List<Connexion> connexions) {

    }

    @Override
    public List<Connexion> getAllConnexionsByProcess(Long id) {
        return connexionRepository.findConnexionsByFromProcessusIdProcessusOrToProcessusIdProcessus(id,id);
    }

    @Override
    public List<Connexion> getAllConnexionsByEtape(Long id) {
        return connexionRepository.findConnexionsByFromIdEtapeOrToIdEtape(id,id);
    }

    @Override
    public void updateConnexionsByProcess(Long id,List<Connexion> connexions) {
        List<Connexion> toRemove = new ArrayList<>();
        List<Connexion> toSave = new ArrayList<>(connexions);

        for(Connexion oldCon : this.getAllConnexionsByProcess(id)){
            boolean found = false;
            for(Connexion newCon : connexions){
                if(oldCon.equals(newCon))
                {
                    found =true;
                    break;
                }
            }
            if(!found)
                toRemove.add(oldCon);
        }

        for(Connexion newCon : connexions){
            boolean found = false;

            for(Connexion oldCon : this.getAllConnexionsByProcess(id))
            {
                if(newCon.equals(oldCon))
                {
                    found= true;
                    break;
                }
            }
            if(!found)
                toSave.add(newCon);
        }

        connexionRepository.deleteAll(toRemove);
        connexionRepository.saveAll(toSave);
    }


}
