package ma.ingecys.project.businessProcessManagement.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("SOUSTRAITANT")
public class SousTraitant extends User implements Serializable {
    private String adresse;
    @OneToMany (mappedBy = "sousTraitant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Tache> taches;
}
