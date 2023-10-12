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
 * @author Myles O'Toole with Logan Ferguson
 */
package csu.csci325;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;

public class MakePayment extends RestarauntMenu {

    private String cardName;
    private String cardDate;
    private String cardNum;
    private String cardCVV;

    public void paymentPrompt() {
        // Declare variables
        Scanner scnr = new Scanner(System.in);
        String selection = "";

        // LOGAN: Date formatter object to check for correct date format entered
        // by user
        int tempMonth;
        int tempYear;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/YY");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("M/YY");

        // LOGAN: Calendar object to return the current day and year
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int currMonth = calendar.get(Calendar.MONTH) + 1;
        // Gets the last two digits of the year
        int currYear = calendar.get(Calendar.YEAR) % 100;

        // Display tile
        System.out.println("\n===================");
        System.out.println("PAYMENT INFORMATION");
        System.out.println("===================\n");

        // Prompts user for card information, confirms with user
        do {

            // Get card name
            do {
                // Prompt user
                System.out.print("Please enter the name on your card: ");
                cardName = scnr.nextLine();

                if (cardName.isEmpty()) {
                    // Prompt user to enter a name
                    System.out.println(">Please enter a name.\n");
                }
            } while (cardName.isEmpty()); // While theres no input, repeat

            // Get card number

            // This keeps the for loop going, until changed
            boolean cardNumRepeat = true;
            do {
                // Prompts user for card number.
                System.out.print("Please enter your card number: ");
                cardNum = scnr.nextLine();
                cardNum = cardNum.replaceAll(" ", "").replaceAll("-", "");

                // Checks if the input is a number, otherwise throws catch and
                // repeats loop
                try {
                    // Check to see if string can be converted to to long
                    long cardNumNumeric = Long.parseLong(cardNum);

                    // Check if the string is an integer, and is 16 digits
                    if (cardNum.length() == 16) {
                        cardNumRepeat = false; // if correct, break loop
                    } else {
                        // if incorrect, prompt and restart
                        System.out.println(">Please enter a valid card number" + ".\n");
                        // scnr.nextLine();
                    }
                    // If the input is not a number, prompt and restart
                } catch (NumberFormatException e) {
                    System.out.println(">Please enter a valid card number.\n");
                    // scnr.nextLine();
                }
                // The loop will keep going until valid input, and boolean is
                // changed
            } while (cardNumRepeat);

            // LOGAN: Checks if the date entered is in the correct format and if
            // it is expired
            boolean correct;
            do {
                correct = true;
                System.out.print("Please enter the date on your card" + " (mm/yy): ");
                cardDate = scnr.next();

                try {
                    // Makes the date a temp array so it can be split into
                    // two integers
                    String[] tempDate = cardDate.split("/");
                    tempMonth = Integer.parseInt(tempDate[0]);
                    tempYear = Integer.parseInt(tempDate[1]);

                    if (tempMonth > 12) {
                        System.out.println(">Please enter a valid date.\n");
                        correct = false;
                    } else if (tempYear < currYear) {
                        System.out.println(">Card is expired, please use a" + " valid card.\n");
                        correct = false;
                    } else if (tempMonth < currMonth && tempYear == currYear) {
                        System.out.println(">Card is expired, please use a" + " valid card.\n");
                        correct = false;
                    }

                    if (cardDate.trim().length() != dateFormat.toPattern().length()
                            && cardDate.trim().length() != dateFormat2.toPattern().length()) {
                        System.out.println(">Please enter a valid date.\n");
                        correct = false;
                    }
                    // Catches index out of bounds exception
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(">Please enter a valid date.\n");
                    correct = false;
                } catch (NumberFormatException e) {
                    System.out.println(">Please enter a valid date.\n");
                    correct = false;
                }
            } while (!correct); // END OF LOGAN

            // Get card CVV

            // This keeps the for loop going, until changed
            boolean cardCVVRepeat = true;
            do {
                System.out.print("Please enter your card CVV: "); // Prompt user
                cardCVV = scnr.next();
                // Checks if the input is a number, otherwise throws catch and
                // repeats loop
                try {
                    // Convert the string to int
                    int cardCVVNumeric = Integer.parseInt(cardCVV);

                    // If the string is an integer, and is 3 digits
                    if (cardCVV.length() == 3) {
                        cardCVVRepeat = false;
                    } else {
                        // If not 3 numbers, reprompt and restart
                        System.out.println(">Please enter a valid CVV.\n");
                    }
                    // If the input is not a number, this outputs to user and
                    // repeats loop
                } catch (NumberFormatException e) {
                    // If not a number, reprompt and restart
                    System.out.println(">Please enter a valid CVV.\n");
                }
                // The loop will keep going until valid input, and boolean
                // is changed
            } while (cardCVVRepeat);

            // Repeat card info back to user for confirmation
            clarifyInfo();

            // if the user enters 'y' it will continue to process, otherwise
            // restart prompt
            selection = scnr.next();
            System.out.println("");
            switch (selection) {
            case "y":
                break;
            case "Y":
                break;
            default:
                scnr.nextLine();
                break;
            }

        } while (!selection.equals("y") && !selection.equals("Y"));

        // Confirm payment processed
        clearConsole();
        System.out.println("\n****************************************");
        System.out.println("* PAYMENT PROCESSED - ORDER SUCCESSFUL *");
        System.out.println("****************************************\n");

    }

    public void clarifyInfo() {
        // Add dashes to card number
        cardNum = (cardNum.replaceFirst("(\\d{4})(\\d{4})(\\d{4})", "$1-$2-$3-"));
        // Prompts user and repeats information for confirmation
        System.out.print("\n-------------------------------------------------");
        System.out.println("\nIs this the correct card information?\n");
        System.out.println("        Name:   " + cardName);
        System.out.println("        Number: " + cardNum);
        System.out.println("        Date:   " + cardDate);
        System.out.println("        CVV:    " + cardCVV);
        System.out.print("-------------------------------------------------\n");
        System.out.print("\nEnter 'y' to confirm or any other key to try" + " again: ");

    }

}
