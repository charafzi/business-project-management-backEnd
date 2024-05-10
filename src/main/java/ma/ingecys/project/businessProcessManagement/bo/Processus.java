package ma.ingecys.project.businessProcessManagement.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Processus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcessus;
    private String nom;
    private String description;
    private int nbLignes;
    private int nbColonnes;
    @OneToMany(mappedBy = "processus",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Etape> etapes;
}
