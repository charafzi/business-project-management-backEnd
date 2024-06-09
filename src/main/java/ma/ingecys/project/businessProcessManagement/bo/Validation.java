package ma.ingecys.project.businessProcessManagement.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ValidationID.class)
public class Validation implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;
    @Id
    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Responsable responsable;
    private String etat;
    private String commentaire;
    private LocalDateTime dateValidation;
}
