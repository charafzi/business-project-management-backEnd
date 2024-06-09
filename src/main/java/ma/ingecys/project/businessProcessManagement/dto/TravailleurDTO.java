package ma.ingecys.project.businessProcessManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravailleurDTO {
    private Long idUser;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;
    private String role;
    private String matricule;
}
