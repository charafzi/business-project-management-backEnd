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
public class Type implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType;
    private String nom;
    @OneToMany(mappedBy = "type")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Etape> etapes;
}
