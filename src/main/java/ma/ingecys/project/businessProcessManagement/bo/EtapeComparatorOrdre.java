package ma.ingecys.project.businessProcessManagement.bo;

import java.util.Comparator;

public class EtapeComparatorOrdre implements Comparator<Etape> {
    @Override
    public int compare(Etape etape1, Etape etape2) {
        return Integer.compare(etape1.getOrdre(), etape2.getOrdre());
    }
}
