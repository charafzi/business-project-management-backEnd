package ma.ingecys.project.businessProcessManagement.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(NecessiterID.class)
public class Necessiter implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "idEtape")
    private Etape etape;
    @Id
    @ManyToOne
    @JoinColumn(name = "idMateriel")
    private Materiel materiel;
    private int quantite;
}
