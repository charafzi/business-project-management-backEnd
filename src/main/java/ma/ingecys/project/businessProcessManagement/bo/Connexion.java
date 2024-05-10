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
@IdClass(ConnexionID.class)
public class Connexion implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "idEtape_from")
    private Etape from;
    @Id
    @ManyToOne
    @JoinColumn(name = "idEtape_to")
    private Etape to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connexion connexion = (Connexion) o;
        return connexion.from.getIdEtape().equals(from.getIdEtape())
                && connexion.to.getIdEtape().equals(to.getIdEtape());
    }

}
