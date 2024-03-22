package info5001.model.Supplier;

import java.util.Comparator;

public class SupplierComparator implements Comparator<Supplier> {

    private boolean isAscending = true;

    public SupplierComparator(boolean isAscending) {
        this.isAscending = isAscending;
    }

    @Override
    public int compare(Supplier o1, Supplier o2) {
        if (isAscending)
            return o1.getName().compareTo(o2.getName());
        else
            return (-1) * o1.getName().compareTo(o2.getName());
    }

}
