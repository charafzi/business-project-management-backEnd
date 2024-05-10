package ma.ingecys.project.businessProcessManagement.service;

import ma.ingecys.project.businessProcessManagement.bo.Type;
import ma.ingecys.project.businessProcessManagement.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeService implements TypeServiceInterface{
    @Autowired
    TypeRepository typeRepository;
    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll()
                .stream()
                .toList();
    }
}
