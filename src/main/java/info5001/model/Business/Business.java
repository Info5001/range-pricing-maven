/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5001.model.Business;

import java.util.ArrayList;

import info5001.model.CustomerManagement.ChannelCatalog;
import info5001.model.CustomerManagement.CustomerDirectory;
import info5001.model.CustomerManagement.MarketCatalog;
import info5001.model.MarketingManagement.MarketingPersonDirectory;
import info5001.model.OrderManagement.MasterOrderList;
import info5001.model.Personnel.EmployeeDirectory;
import info5001.model.Personnel.PersonDirectory;
import info5001.model.ProductManagement.Product;
import info5001.model.ProductManagement.ProductCatalog;
import info5001.model.ProductManagement.ProductSummary;
import info5001.model.ProductManagement.ProductsReport;
import info5001.model.ProductManagement.SolutionOfferCatalog;
import info5001.model.SalesManagement.SalesPersonDirectory;
import info5001.model.Supplier.Supplier;
import info5001.model.Supplier.SupplierDirectory;
import info5001.model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory;
    MasterOrderList masterorderlist;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    ChannelCatalog channelcatalog;
    SolutionOfferCatalog solutionoffercatalog;
    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;

    public Business(String n) {
        name = n;
        masterorderlist = new MasterOrderList();
        suppliers = new SupplierDirectory();
        // solutionoffercatalog = new SolutionOfferCatalog();
        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);
        employeedirectory = new EmployeeDirectory(this);

    }

    public int getSalesVolume() {
        return masterorderlist.getSalesVolume();

    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public MarketingPersonDirectory getMarketingPersonDirectory() {
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();

    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n) {

        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); // return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory() {
        return salespersondirectory;
    }

    public MasterOrderList getMasterOrderList() {
        return masterorderlist;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public ProductsReport generateProductSalesReport() {
        ArrayList<Supplier> suppliersList = suppliers.getSupplierList();
        ProductsReport allProductsReport = new ProductsReport();

        for (Supplier s : suppliersList) {
            ProductCatalog pc = s.getProductCatalog();
            for (Product p : pc.getProductList()) {
                allProductsReport.addProductSummary(new ProductSummary(p));
            }
        }

        allProductsReport.sortBySalesVolume();
        return allProductsReport;
    }

    public void printShortInfo() {
        System.out.println("Checking what's inside the business hierarchy.");
        suppliers.printShortInfo();
        customerdirectory.printShortInfo();
        masterorderlist.printShortInfo();
    }

}
