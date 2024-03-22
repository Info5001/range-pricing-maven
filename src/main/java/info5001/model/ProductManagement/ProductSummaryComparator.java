package info5001.model.ProductManagement;

import java.util.Comparator;

public class ProductSummaryComparator implements Comparator<ProductSummary> {

    private boolean isAscending = false;

    public ProductSummaryComparator(boolean isAscending) {
        this.isAscending = isAscending;
    }

    @Override
    public int compare(ProductSummary o1, ProductSummary o2) {
        // TODO Auto-generated method stub
        if (isAscending)
            return Integer.compare(o1.getSalesRevenues(), o2.getSalesRevenues());
        else
            return (-1) * Integer.compare(o1.getSalesRevenues(), o2.getSalesRevenues());
    }

}
