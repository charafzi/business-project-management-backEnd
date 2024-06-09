package ma.ingecys.project.businessProcessManagement.bo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("TEAMLEADER")
public class TeamLeader extends User implements Serializable {
    @Column(unique = true,nullable = true)
    private String matricule;
    @OneToMany(mappedBy = "teamLeader")
    private List<Tache> taches;
}
