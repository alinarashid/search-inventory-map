package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Scanner;

public class SearchInventoryMap {

    public static HashMap<Integer, Product> inventoryMap = new HashMap<>();


    public static void main(String[] args) {
        loadInventory();

        Scanner scanner = new Scanner(System.in);
        System.out.println("What product do you want to search for? ");
        int id = scanner.nextInt();

        Product found = inventoryMap.get(id);
        if (found != null) {
            System.out.printf("We have %s and it costs $%.2f%n", found.getName(), found.getPrice());

        } else {
            System.out.println("The product with the id " + id + " is not found.");

        }
        scanner.close();
    }

    private static void loadInventory() {
        try {
            BufferedReader prodReader = new BufferedReader(new FileReader("inventory.csv"));
            String prodLine;

            while ((prodLine = prodReader.readLine()) != null) {

                // Split the line on | into id, name and price
                String[] prodArr = prodLine.split("\\|");

                // Convert each part from text into the right data type
                int id = Integer.parseInt(prodArr[0]);
                String name = prodArr[1];
                double price = Double.parseDouble(prodArr[2]);

                // Create a Product and add it to the list
                inventoryMap.put(id, new Product(id, name, price));
            }

            prodReader.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
}
