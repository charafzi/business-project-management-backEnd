package ma.ingecys.project.businessProcessManagement.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SousTraitant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousTraitant;
    private String nom;
    private String adresse;
    private String tel;
    @OneToMany (mappedBy = "sousTraitant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Tache> taches;
}
