/*
Student Name:  Myles O’Toole
Program Name:  Restaurant Menus Generator
Creation Date:  Fall 2021
Last Modified Date:  Fall 2022
CSCI Course:  CSCI 325
Grade Received:  A
Design Comments: This program represents a restaurant ordering service and allows a user to select a restaurant and create a mock order. Previous reviews can be printed from a text file of the selected restaurant or the user can go straight to the menu. The menu will then be printed from a text file based on the selection. The user can then choose items to be added to their order. Once the user is finished with their order, the user’s itemized receipt will be printed based on everything they have selected. Then the user can add a tip (in percentage) and pay with a card. The order total plus tax and tip will be printed for the user. The user will then have the option to review the selected restaurant and add a rating. If the user decides to add a review, then the review will be stored in a text file so it can be viewed by other users.
*/

/*
 * This class will be used to show the user's receipt. It will do this by taking
 * the chosen items and ouputting them. Using this information it will calculate
 * the subtotal, prompt for a tip, and total.
 */
package csu.csci325;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joseph Kaufman
 */
public class Checkout extends RestarauntMenu{
    /*
        Declare Private Variables
    */
    private final double TAX_RATE = .07; // constant tax rate
    // https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
    // DecimalFormat rounds each number to two decimals for each price
    private static final DecimalFormat df = new DecimalFormat("0.00"); 
        
    private double subtotal;
    private double taxes;
    private double total;
    private double tip;
    private ArrayList<String> cart = new ArrayList<>();

    /*
        This method prints out the receipt the items selected as well as the 
        subtotal, taxes, tip, and total. This will also prompt the user to 
        see if they would like to leave a tip.
    */
    public void outputReceipt(Scanner scnr) {
        // output receipt header
        System.out.println("\n***********************************************");
        System.out.println("*                   Receipt                   *");
        System.out.println("***********************************************");
        
        // Output the ordered items
        System.out.println("Items Selected: ");
        for (int i = 0; i < cart.size(); i++) {
            int cutoff = cart.get(i).indexOf(".") + 2; 
                // Line 45 formats the code to be outputted without the default
                // numbers and just the description.
                // Ex. "10. McDouble" to "McDouble"
            System.out.println("~ " + cart.get(i).substring(cutoff));
                // Outputs the formatted string
        }

        // Output the Subtotal, Taxes, Tip, and Total
        System.out.println("---------------------------------");
        System.out.println("Subtotal: $" + df.format(subtotal));
        System.out.println("Taxes:    $" + df.format(taxes));

        // Only output tip if a tip has been entered
            // Ex: no tip entered Ex
              //  "Subtotal: $6.39"
              //  "Taxes:    $0.45"
              //  "Total:    $6.84"
             //  tip entered Ex:
            // Ex: tip entered Ex
              //  "Subtotal: $6.39"
              //  "Taxes:    $0.45"
              //  "Tip:      $5.00"
              //  "Total:    $11.84"
        if (tip > 0) {
            System.out.println("Tip:      $" + df.format(tip));
        }
        
        System.out.println("Total:    $" + df.format(total));
        System.out.println("---------------------------------");
        System.out.println("***********************************************");

        // User option to Cancel Order and terminate program
           System.out.print("\nEnter 'c' to cancel or any other key to" +
            " proceed to payment: ");
            String input = scnr.nextLine();
            input = input.toUpperCase();
            if (input.equals("C")) {

            // Confirm payment processed
            cancelMessage();
                        
            System.exit(0); // terminate program

            }
        }
    
    /*
      This method asks the user if they would like to enter a tip. If they do
      it outputs the amount. If they number is above $10 it asks the user to
      confirm that is the amount they would like to output. This also prevents
      the user from inputting invalid inputs below a cent or non-monetary 
      inputs.      
    */
    public void tipPrompt(Scanner scnr) {
        // prompt user for tip
        System.out.print("\nEnter 'y' to leave a tip or any other key to" +
            " continue: ");
        String input = scnr.nextLine();
        System.out.println("");
        input = input.toUpperCase();
        
        // leave a tip
        if (input.equals("Y") || input.equals("YES")) {
            // output the gratuity and take inputted tip amount
            System.out.println("What dollar amount would you like to tip?");
            System.out.print(" - 15% gratuity is $" + df.format(.15 * total) + 
                "\n");
            System.out.print(" - 18% gratuity is $" + df.format(.18 * total) + 
                "\n");
            System.out.print(" - 20% gratuity is $" + df.format(.20 * total) + 
                "\n");
            System.out.print("   $");
            int i = 0; // set condition to stay in loop
            String tipAmount = ""; // holds tip value
            
            do {
                try {
                    tipAmount = scnr.nextLine();

                    // To-Do: does this work without?
                    Double.parseDouble(tipAmount); 
                    tip = Double.parseDouble(tipAmount);
                    i = 1; // if the valid is incorrect then an error will be
                           // thrown and the loop will repeat
                    
                    // if a tip is 10 or greater then confirm with user
                    if (tip >= 10 && tip < 1000 ) {
                        input = ""; //set input to blank to avoid using previous
                                    // input
                                    
                        // Confirm with user or get new input   
                        while (!(input.equals("YES") || input.equals("Y"))) {
                            System.out.println("");
                            System.out.print("Enter 'y' to confirm tip of $" + 
                            df.format(tip) + " or any other key to make" +
                                " changes: ");
                            input = scnr.nextLine();
                            System.out.println("");
                            input = input.toUpperCase();
                            
                            // enter in new tip amount
                            if (!(input.equals("YES") || input.equals("Y"))) {
                                System.out.print("Enter in a new value: $");
                                tipAmount = scnr.nextLine();
                                Double.parseDouble(tipAmount);
                                tip = Double.parseDouble(tipAmount);
                            }
                        }
                    }
                    
                // Throw exception if the input is valid until valid input is 
                // taken.    
                } catch (NumberFormatException e) {
                System.out.print("Please enter a value without characters: $ ");
                    i = 0; // go through the loop again
                }
                
                // Does not allow for inputs of less than 1 cent or greater than
                // $1000
                if (tip < 0.01 || tip >= 1000) {
                    System.out.println("");
                    if (tip < 0.01) {
                        System.out.println("Unable to tip less than 1 cent");
                    }
                    else {
                        System.out.println("Unable to more than $999");
                    }
                    
                    // prompt for new values
                    System.out.println("Please enter a valid amount:");
                    System.out.print("$");
                    i = 0; // go through loop again
                }
        
            } while (i == 0);
        }
        calculateTotal();

        // if a tip was entered then reoutput the receipt
        if (input.equals("Y") || input.equals("YES")) {

            clearConsole();
            outputReceipt(scnr);
        }
    }

    /**
     * Calculates the amount taxed in addition to the total.
     */
    public void calculateTotal() {
        taxes = subtotal * TAX_RATE;
        total = subtotal + taxes + tip;
    }

    /*
     * Takes the an array of chosen items into a cart. It also sets the subtotal
     * taken from chosen items.
     */
    public void updateCart(ArrayList parameterArray, double subtotal) {
        
        // sets the cart equal to the chosen items
        cart = parameterArray;

        this.subtotal = subtotal; // sets the subtotal

        calculateTotal(); // Calculates the chosen items total, taxes, and
                          // subtotal prior to tip.
    }

}
