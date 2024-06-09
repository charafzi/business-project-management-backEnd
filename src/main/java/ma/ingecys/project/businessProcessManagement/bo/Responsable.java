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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("RESPONSABLE")
public class Responsable extends User implements Serializable {
    @Column(unique = true,nullable = true)
    private String matricule;
    @OneToMany(mappedBy = "responsable")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Validation> validations;
    @OneToMany(mappedBy = "responsable")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Tache> taches;
    @OneToMany(mappedBy = "responsable")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Processus> processuses;
}
