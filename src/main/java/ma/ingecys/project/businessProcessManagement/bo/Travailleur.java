package ma.ingecys.project.businessProcessManagement.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Travailleur implements Serializable {
    @Id
    private String matricule;
    private String nom;
    private String prenom;
    private String numTel;
    private String email;
    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;
}
