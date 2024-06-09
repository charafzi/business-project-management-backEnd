package ma.ingecys.project.businessProcessManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    private String uploadDir = "C:\\Users\\Achra\\Desktop\\PFA\\Implementation\\front-End\\businessProcessManagement\\BPM-project\\src\\assets\\payments";

    public void storeFile(MultipartFile file) throws IOException {
        Path targetLocation = Paths.get(uploadDir).resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetLocation);
    }

    public byte[] getFile(String filename) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(filename);
        return Files.readAllBytes(filePath);
    }
}
