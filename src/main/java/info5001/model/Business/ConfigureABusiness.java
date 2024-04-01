/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5001.model.Business;

import java.util.ArrayList;
import java.util.Random;

import com.github.javafaker.Faker;

import info5001.model.Business.Business;
import info5001.model.CustomerManagement.CustomerDirectory;
import info5001.model.CustomerManagement.CustomerProfile;
import info5001.model.MarketingManagement.MarketingPersonDirectory;
import info5001.model.MarketingManagement.MarketingPersonProfile;
import info5001.model.OrderManagement.MasterOrderList;
import info5001.model.OrderManagement.Order;
import info5001.model.OrderManagement.OrderItem;
import info5001.model.Personnel.EmployeeDirectory;
import info5001.model.Personnel.EmployeeProfile;
import info5001.model.Personnel.Person;
import info5001.model.Personnel.PersonDirectory;
import info5001.model.ProductManagement.Product;
import info5001.model.ProductManagement.ProductCatalog;
import info5001.model.SalesManagement.SalesPersonDirectory;
import info5001.model.SalesManagement.SalesPersonProfile;
import info5001.model.Supplier.Supplier;
import info5001.model.Supplier.SupplierDirectory;
import info5001.model.UserAccountManagement.UserAccount;
import info5001.model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {

  static int upperPriceLimit = 50;
  static int lowerPriceLimit = 10;
  static int range = 5;
  static int productMaxQuantity = 5;
  static Faker faker = new Faker();
  static ArrayList<String> firstNames = new ArrayList();
  static ArrayList<String> lastNames = new ArrayList();

  public static Business createABusinessAndLoadALotOfData(String name, int supplierCount, int productCount,
      int customerCount, int orderCount, int itemCount) {
    Business business = new Business(name);

    // Add Suppliers +
    loadSuppliers(business, supplierCount);

    // Add Products +
    loadProducts(business, productCount);

    // Add Customers
    loadCustomers(business, customerCount);

    // Add Order
    loadOrders(business, orderCount, itemCount);

    return business;
  }

  public static void loadSuppliers(Business b, int supplierCount) {

    SupplierDirectory supplierDirectory = b.getSupplierDirectory();
    for (int index = 1; index <= supplierCount; index++) {
      supplierDirectory.newSupplier(faker.company().name());
    }
  }

  static void loadProducts(Business b, int productCount) {
    SupplierDirectory supplierDirectory = b.getSupplierDirectory();

    for (Supplier supplier : supplierDirectory.getSupplierList()) {

      int randomProductNumber = getRandom(1, productCount);
      ProductCatalog productCatalog = supplier.getProductCatalog();

      for (int index = 1; index <= randomProductNumber; index++) {

        String productName = faker.commerce().productName() + " from " + supplier.getName();
        int randomFloor = getRandom(lowerPriceLimit, lowerPriceLimit + range);
        int randomCeiling = getRandom(upperPriceLimit - range, upperPriceLimit);
        int randomTarget = getRandom(randomFloor, randomCeiling);

        productCatalog.newProduct(productName, randomFloor, randomCeiling, randomTarget);
      }
    }
  }

  public static int getRandom(int lower, int upper) {
    Random r = new Random();

    // nextInt(n) will return a number from zero to 'n'. Therefore e.g. if I want
    // numbers from 10 to 15
    // I will have result = 10 + nextInt(5)
    int randomInt = lower + r.nextInt(upper - lower);
    return randomInt;
  }

  static void loadCustomers(Business b, int customerCount) {
    CustomerDirectory customerDirectory = b.getCustomerDirectory();
    PersonDirectory personDirectory = b.getPersonDirectory();

    for (int index = 1; index <= customerCount; index++) {
      Person newPerson = personDirectory.newPerson(faker.name().fullName());
      customerDirectory.newCustomerProfile(newPerson);
    }
  }

  static void loadOrders(Business b, int orderCount, int itemCount) {

    // reach out to masterOrderList
    MasterOrderList mol = b.getMasterOrderList();

    // pick a random customer (reach to customer directory)
    CustomerDirectory cd = b.getCustomerDirectory();
    SupplierDirectory sd = b.getSupplierDirectory();

    for (int index = 0; index < orderCount; index++) {

      CustomerProfile randomCustomer = cd.pickRandomCustomer();
      if (randomCustomer == null) {
        System.out.println("Cannot generate orders. No customers in the customer directory.");
        return;
      }

      // create an order for that customer
      Order randomOrder = mol.newOrder(randomCustomer);

      // add order items
      // -- pick a supplier first (randomly)
      // -- pick a product (randomly)
      // -- actual price, quantity

      int randomItemCount = getRandom(1, itemCount);
      for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {

        Supplier randomSupplier = sd.pickRandomSupplier();
        if (randomSupplier == null) {
          System.out.println("Cannot generate orders. No supplier in the supplier directory.");
          return;
        }
        ProductCatalog pc = randomSupplier.getProductCatalog();
        Product randomProduct = pc.pickRandomProduct();
        if (randomProduct == null) {
          System.out.println("Cannot generate orders. No products in the product catalog.");
          return;
        }

        int randomPrice = getRandom(randomProduct.getFloorPrice(), randomProduct.getCeilingPrice());
        int randomQuantity = getRandom(1, productMaxQuantity);

        OrderItem oi = randomOrder.newOrderItem(randomProduct, randomPrice, randomQuantity);
      }
    }
    // Make sure order items are connected to the order

  }

  static void populateNames() {
    firstNames.add("James");
    firstNames.add("Robert");
    firstNames.add("John");
    firstNames.add("Michael");
    firstNames.add("David");
    firstNames.add("Mary");
    firstNames.add("Patricia");
    firstNames.add("Jennifer");
    firstNames.add("Linda");
    firstNames.add("Elizabeth");
    // last names
    lastNames.add("Smith");
    lastNames.add("JOHNSON");
    lastNames.add("WILLIAMS");
    lastNames.add("BROWN");
    lastNames.add("JONES");
    lastNames.add("GARCIA");
  }

  public static String getFirstName(){
    int randomIndex = getRandom(0, firstNames.size());
    return firstNames.get(randomIndex);
  }

  public static String getLastName(){
    int randomIndex = getRandom(0, lastNames.size());
    return lastNames.get(randomIndex);
  }



  public static String getRandomName() {
    populateNames();
    return getFirstName() + " " + getLastName();
  }



}
