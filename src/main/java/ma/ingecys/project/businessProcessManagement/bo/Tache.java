package ma.ingecys.project.businessProcessManagement.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTache;
    private String objetTache;
    private Priorite priorite;
    @Column(scale = 2)
    private double pourcentage;
    @Enumerated(EnumType.STRING)
    private StatutTache statutTache;
    @Enumerated(EnumType.STRING)
    private StatutEtape statutEtape;
    private LocalDateTime dateDebutPrevue;
    private LocalDateTime dateDebutEffective;
    private LocalDateTime dateFinEffective;
    private LocalDateTime dateExpiration;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "matricule")
    )
    private List<Travailleur> travailleurs;
    @ManyToOne
    @JoinColumn(name = "etape_id")
    private Etape etape;
    @ManyToOne
    @JoinColumn(name = "tache_mere_id")
    @JsonBackReference
    private Tache tache_mere;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "tache_mere_id")
    @JsonManagedReference
    private List<Tache> sous_taches;
    @ManyToOne
    @JoinColumn(name = "sousTraitant_id")
    private SousTraitant sousTraitant;
}
