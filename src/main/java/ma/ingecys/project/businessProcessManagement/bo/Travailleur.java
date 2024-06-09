package ma.ingecys.project.businessProcessManagement.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("TRAVAILLEUR")
public class Travailleur extends User implements Serializable {
    @Column(unique = true,nullable = true)
    private String matricule;
    @ManyToMany(mappedBy = "travailleurs")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Tache> taches;

    /*public Long getIdUser() {
        return this.getIdUser();
    }

    public String getNom(){
        return this.getNom();
    }
    public String getPrenom(){
        return this.getPrenom();
    }

    public String getEmail(){
        return this.getEmail();
    }

    public String getNumTel(){
        return this.getNumTel();
    }*/


}
