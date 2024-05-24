package ma.ingecys.project.businessProcessManagement.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tache implements Serializable {
    @Id
    private Long idTache;
    private String objetTache;
    private int priorite;
    @Enumerated(EnumType.STRING)
    private StatutTache statutTache;
    private LocalDate dateDebutPrevue;
    private LocalDate dateDebutEffective;
    private LocalDate dateFinEffective;
    private LocalDate dateExpiration;
    @OneToMany(mappedBy = "tache")
    private List<Travailleur> travailleurs;
    @ManyToOne
    @JoinColumn(name = "idEtape")
    private Etape etape;
    @ManyToOne
    @JoinColumn(name = "tache_mere_id")
    private Tache tache_mere;
    @OneToMany(mappedBy = "tache_mere",cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Tache> sous_taches;
}
