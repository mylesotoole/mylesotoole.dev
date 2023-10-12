/*
Student Name:  Myles O’Toole
Program Name:  Restaurant Menus Generator
Creation Date:  Fall 2021
Last Modified Date:  Fall 2022
CSCI Course:  CSCI 325
Grade Received:  A
Design Comments: This program represents a restaurant ordering service and allows a user to select a restaurant and create a mock order. Previous reviews can be printed from a text file of the selected restaurant or the user can go straight to the menu. The menu will then be printed from a text file based on the selection. The user can then choose items to be added to their order. Once the user is finished with their order, the user’s itemized receipt will be printed based on everything they have selected. Then the user can add a tip (in percentage) and pay with a card. The order total plus tax and tip will be printed for the user. The user will then have the option to review the selected restaurant and add a rating. If the user decides to add a review, then the review will be stored in a text file so it can be viewed by other users.
*/

/**
 * @author Myles O'Toole
 */
package csu.csci325;

import java.util.ArrayList;
import java.util.Scanner;

public class ChooseItems {
    // Variable declaration
    private String[] arrMenuList;
    private ArrayList<String> cart = new ArrayList<>();
    private double subtotal;

    // Sets the array to the correct menu
    public ChooseItems(String[] array) {
        arrMenuList = array;
    }

    // Prints the string list user selected items
    public ArrayList<String> getItems() {
        return cart;
    }

    // Returns subtotal of all the items selected
    public double getSubtotal() {
        return subtotal;
    }

    public void choose() {
        // Variable declaration
        Scanner scnr = new Scanner(System.in);
        String[] arrPricesAsString = new String[arrMenuList.length];
        arrPricesAsString[0] = "0";
        subtotal = 0;
        boolean moreFood = true;

        // Converts full lines menu array, into string array of just the prices
        for (int i = 1; i < arrMenuList.length; i++) {
            String menuLine = arrMenuList[i]; // gets line, converts to string
            String justNumber = menuLine.substring(menuLine.lastIndexOf("$")
                + 1); // cuts string to just price
            arrPricesAsString[i] = justNumber; // sets new price array
        }

        // Declare new array to hold prices as double
        double[] arrPricesAsNumbers = new double[arrPricesAsString.length];

        // Parsing the array of string prices, into new array with double prices
        for (int i = 0; i < arrPricesAsString.length; i++) {
            arrPricesAsNumbers[i] = Double.parseDouble(arrPricesAsString[i]);
        }

        // Prompts user what you want to eat
        do {
            boolean repeat = true; //This keeps the for loop going until changed

            do{
                // Prompts user for food item number, and adds its price to 
                //subtotal.
                System.out.print("\nPlease enter the item's number you would" +
                    " like to order: ");
                String itemChoiceString = scnr.next();

                // Checks if the input is a number, otherwise throws catch and 
                // repeats loop
                try{
                    // Convert the string to integer
                    int itemChoice = Integer.parseInt(itemChoiceString);

                    // If the item is within bounds on the menu it will add it
                    if (itemChoice < arrMenuList.length && itemChoice > 0){

                        // Add and tell user item has been added
                        subtotal = subtotal + arrPricesAsNumbers[itemChoice];
                        System.out.println(">Successfully added # " + 
                            itemChoice);
            
                        // Adds menu line to repeat during receipt
                        cart.add(arrMenuList[itemChoice]);

                        // Exit the loop, because prompt was right
                        repeat = false;
                    }
                    else{
                        // If the item number is not in bounds, this outputs to 
                        // user and repeats loop
                        System.out.println(">Invalid item number, please" +
                            " enter a number on the menu!");
                    }

                // If the input is not a number, this outputs to user and 
                // repeats loop
                }catch (NumberFormatException e) {
                    System.out.println(">Invalid input, please enter a number" +
                        " on the menu!");
                }
            // The loop will keep going until valid input, when boolean is 
            // changed    
            } while(repeat); 

            // Prompt user if they want to add more items to their order
            System.out.print("\nEnter 'y' to add to your order or any other" +
                " character to continue: ");

            // If the user enters 'y' they will be reprompted, otherwise to 
            // checkout
            String selection = scnr.next();
            switch (selection) {
            case "y":
                break;
            case "Y":
                break;
            default:
                // The user is done checking out, exit ChooseItems()
                moreFood = false; 
                break;
            }
        // This loop will keep going until the user no longer wants to add more 
        // items 
        } while (moreFood); 
    }
}
