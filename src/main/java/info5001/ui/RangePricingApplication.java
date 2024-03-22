/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5001.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import com.github.javafaker.Faker;

import info5001.model.Business.Business;
import info5001.model.Business.ConfigureABusiness;
import info5001.model.ProductManagement.Product;
import info5001.model.ProductManagement.ProductCatalog;
import info5001.model.ProductManagement.ProductSummary;
import info5001.model.ProductManagement.ProductsReport;
import info5001.model.Supplier.Supplier;
import info5001.model.Supplier.SupplierComparator;
import info5001.model.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */
public class RangePricingApplication {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here

    // Sorting

    // ArrayList<Integer> numbers = new ArrayList<Integer>();

    // numbers.add(10);
    // numbers.add(5);
    // numbers.add(20);
    // numbers.add(15);
    // numbers.add(25);

    // System.out.println("Original list: ");

    // Collections.sort(numbers);

    // for (Integer number : numbers) {
    // System.out.println(number);
    // }

    // Random numbers

    // Random randomInstance = new Random();

    // System.out.println(" Random Numbers: ");

    // ArrayList<Integer> randomIntegers = new ArrayList<Integer>();
    // int totalNumberOfRandomNumbers = 100;
    // int counter = 0;

    // while (counter < totalNumberOfRandomNumbers) {
    // int randomInt = randomInstance.nextInt(100);
    // if (!randomIntegers.contains(randomInt)) {
    // randomIntegers.add(randomInt);
    // counter++; // increment counter only if the number is not already in the list
    // }
    // }

    // for (Integer integer : randomIntegers) {
    // System.out.println(integer);
    // }

    // System.out.println("We picked "+ randomIntegers.size() + " unique random
    // numbers from 0 to 100.");

    // // Data that is not number (e.g. names, addresses, etc.)

    // Faker fakerInstance = new Faker();

    // System.out.println(" Customer list: ");
    // for (int i = 0; i < 200; i++) {

    // String fullName = fakerInstance.name().fullName();
    // String address = fakerInstance.address().fullAddress();

    // System.out.println(fullName);
    // System.out.println(address);
    // System.out.println("-----------------");
    // }

    // System.out.println(" Supplier list: ");
    // for (int i = 0; i < 200; i++) {

    // String companyName = fakerInstance.company().name();

    // System.out.println(companyName);
    // System.out.println("-----------------");
    // }

    Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("Amazon", 20, 10, 30,
        100, 10);
    // business.printShortInfo();

    ProductsReport productsreport = business.generateProductSalesReport();
    productsreport.printProductReport();

  }

}
