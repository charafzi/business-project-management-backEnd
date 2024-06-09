package ma.ingecys.project.businessProcessManagement.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Paiement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;
    private LocalDateTime datePaiement;
    private double total_a_payer;
    private double montantPaye;
    private double reste;
    private String etat;
    //private String justification;
    @Lob
    private byte[] justification;
    @ManyToOne
    @JoinColumn(name = "tache_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Tache tache;
}
