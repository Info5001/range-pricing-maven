/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5001.model.ProductManagement;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kal bugrara
 */
public class ProductsReport {

    ArrayList<ProductSummary> productsummarylist;

    public ProductsReport() {
        productsummarylist = new ArrayList();
    }

    public void addProductSummary(ProductSummary ps) {

        productsummarylist.add(ps);
    }

    public ProductSummary getTopProductAboveTarget() {
        ProductSummary currenttopproduct = null;

        for (ProductSummary ps : productsummarylist) {
            if (currenttopproduct == null) {
                currenttopproduct = ps; // initial step
            } else if (ps.getNumberAboveTarget() > currenttopproduct.getNumberAboveTarget()) {
                currenttopproduct = ps; // we have a new higher total
            }

        }
        return currenttopproduct;
    }

    public ArrayList<ProductSummary> getProductsAlwaysAboveTarget() {
        ArrayList<ProductSummary> productsalwaysabovetarget = new ArrayList(); // temp array list

        for (ProductSummary ps : productsummarylist) {
            if (ps.isProductAlwaysAboveTarget() == true) {
                productsalwaysabovetarget.add(ps);
            }
        }

        return productsalwaysabovetarget;
    }

    public void sortBySalesVolume() {
        Collections.sort(productsummarylist, new ProductSummaryComparator(false));
    }

    public void printProductReport() {
        System.out.println("---------------------------------");
        System.out.println("Product Performance Report");

        if (productsummarylist.isEmpty()) {
            System.out.println("No products in the report");
            return;
        }

        System.out.println("Below are product name, actual sales and number of sales above target.");
        for (ProductSummary ps : productsummarylist) {
            int index = productsummarylist.indexOf(ps);
            System.out.print((index + 1) + " ");
            ps.printProductSummary();
        }

    }
}
