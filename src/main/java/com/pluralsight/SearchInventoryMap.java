package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Search Inventory Map
 * Loads the store's products from inventory.csv into a HashMap
 * (key = product id, value = Product) and lets the user look up
 * a product by its id.
 */
public class SearchInventoryMap {

    // Key is the product id, value is the Product object
    public static HashMap<Integer, Product> inventoryMap = new HashMap<>();


    public static void main(String[] args) {
        // Fill the map with products from the file
        loadInventory();

        // Ask the user which product id to look up
        Scanner scanner = new Scanner(System.in);

        // declared BEFORE the loop so the while() at the bottom can see it
        String again;

        do {
            System.out.println("What product do you want to search for? ");
            int id = scanner.nextInt();

            // Clear leftover
            scanner.nextLine();

            // Look up the product by its id (returns null if it isn't in the map)
            Product found = inventoryMap.get(id);
            if (found != null) {
                // Product exists: show its name and price
                System.out.printf("We have %s and it costs $%.2f%n", found.getName(), found.getPrice());

            } else {
                // No product with that id
                System.out.println("The product with the id " + id + " is not found.");

            }

            System.out.println("Do you want to search again? (yes/no) ");
            again = scanner.nextLine();

        }  while (again.equalsIgnoreCase("yes"));

        System.out.println("Thanks for using our search!");
        // Close the Scanner when the program is done
        scanner.close();
    }

    /**
     * Reads inventory.csv and puts each product into the map,
     * using the product id as the key.
     */
    private static void loadInventory() {
        // Try: reading a file can fail (e.g. file not found)
        try {
            // Open inventory.csv for reading
            BufferedReader prodReader = new BufferedReader(new FileReader("inventory.csv"));
            String prodLine;

            // Read one line at a time until readLine() returns null (end of file)
            while ((prodLine = prodReader.readLine()) != null) {

                // Split the line on | into id, name and price
                String[] prodArr = prodLine.split("\\|");

                // Convert each part from text into the right data type
                int id = Integer.parseInt(prodArr[0]);
                String name = prodArr[1];
                double price = Double.parseDouble(prodArr[2]);

                // Create a Product and add it to the map (id is the key)
                inventoryMap.put(id, new Product(id, name, price));
            }

            // Close the file
            prodReader.close();

        } catch (IOException e) {
            // Runs if the file can not be found or read
            System.out.println("Error: " + e.getMessage());

        }
    }
}