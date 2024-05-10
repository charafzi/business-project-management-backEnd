package ma.ingecys.project.businessProcessManagement.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tache extends Etape implements Serializable {
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
}
