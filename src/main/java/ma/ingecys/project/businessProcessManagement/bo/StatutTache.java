package ma.ingecys.project.businessProcessManagement.bo;

public enum StatutTache{
    EN_COURS{
        public String toString() {
            return "EN_COURS";
        }
    },
    EN_ATTENTE_DE_VALIDATION{
        public String toString() {
            return "EN_ATTENTE_DE_VALIDATION";
        }
    },
    EN_ATTENTE_DU_DELAI{
        public String toString() {
            return "EN_ATTENTE_DU_DELAI";
        }
    },
    TERMINE{
        public String toString() {
            return "TERMINE";
        }
    }
}
