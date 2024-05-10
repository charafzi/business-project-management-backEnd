package ma.ingecys.project.businessProcessManagement.bo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NecessiterID implements Serializable {
    private Long materiel;
    private Long etape;
}
