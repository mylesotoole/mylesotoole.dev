
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
 * @author Jared Andraszek, Jaye Engelhardt, Logan Ferguson, Joseph Kaufman, 
 * and Myles O’Toole
 */
package csu.csci325;

import java.util.Scanner;

public class RestarauntMenu {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        // WELCOME TO PROGRAM
        System.out.println("\n==========================");
        System.out.println("WELCOME TO RESTAURANT MENU");
        System.out.println("==========================\n");
        System.out.println("Select a restaurant by its number or its " + "abbreviation.\n");

        // Define variables
        String userSelection;
        Scanner scnr = new Scanner(System.in);
        SelectRestaurant dayTime = new SelectRestaurant();

        // gets day and time to see if menus can be accessed
        dayTime.getDayOfWeek();
        dayTime.getTimeOfDay();

        // Output Menu Options
        System.out.println("Choose a place to eat: ");
        System.out.println("1. (CFA) Chick-fil-A" + "\n2. (MCD) McDonald's" + "\n3. (PHZ) Pizza Hut");
        System.out.print("Your Choice: ");

        // Choose Menu Selection
        userSelection = scnr.nextLine();

        // Ensures menu will not proceed until a proper restaraunt selection is
        // selected
        while (!(userSelection.equalsIgnoreCase("CFA") || userSelection.equalsIgnoreCase("MCD")
                || userSelection.equalsIgnoreCase("PHZ"))
                && !(userSelection.equals("1") || userSelection.equals("2") || userSelection.equals("3"))) {

            System.out.print("\nEnter either the restaurant number or" + " it's abbreviation: ");
            userSelection = scnr.nextLine();
        }

        System.out.println("");

        // Chick-fil-A menu will not print if it is Sunday, and Pizza Hut menu
        // will not print if it is morning. The user will be prompted for
        // another Restaurant choice.

        while ((userSelection.equals("1") || userSelection.equalsIgnoreCase("CFA")) && dayTime.getDayOfWeek() == 7) {

            System.out.println("Sorry, Chick-Fil-A is closed on Sunday. " + "Please choose another resturant." + "\n");
            System.out.print("Your Choice: ");
            userSelection = scnr.nextLine();
            System.out.println("");
        }

        while ((userSelection.equals("3") || userSelection.equalsIgnoreCase("PHZ")) && dayTime.getTimeOfDay() < 10) {

            System.out.println("Pizza Hut is currently closed. We will "
                    + "open at 10:00 AM. Please choose another resturant." + "\n");
            System.out.print("Your Choice: ");
            userSelection = scnr.nextLine();
            System.out.println("");

        }

        // Creates string to open the menu file based on userSelection
        switch (userSelection) {
        case "1":
            userSelection = "CFA.txt";
            break;
        case "2":
            userSelection = "MCD.txt";
            break;
        case "3":
            userSelection = "PHZ.txt";
            break;
        default:
            userSelection += ".txt";
            break;
        }

        // Jared //
        // Propmpts the user if they would like to see the reviews for the class
        System.out.print("Enter 'y' to see reviews or any other key to" + " continue: ");

        // Gets the user's response and then capitalize the response //
        String yesOrNo = scnr.nextLine().toUpperCase();

        // If yes, print the Reviews //
        if (yesOrNo.equals("YES") || yesOrNo.equals("Y")) {
            // Makes a ListReviews object and passes the userSelection String
            ListReviews reviews = new ListReviews(userSelection);
            reviews.printReviews();

            // Waits for the user to press a key to continue so they can read
            // the reviews
            System.out.print("Enter any key to proceed to order: ");
            String continueString = scnr.nextLine();
            System.out.println();
        } // End Jared //

        try {
            // Declares ListMenu object and sends it the userSelection
            ListMenu menu = new ListMenu(userSelection);
            System.out.println("");

            // Prints the menu selected by the user and creates the array of the
            // items
            clearConsole();

            // WELCOME TO PROGRAM
            System.out.println("\n================");
            System.out.println("PLACE YOUR ORDER");
            System.out.println("================\n");
            menu.printMenu();
            menu.createItems();

            // Stores the menu array to the array variable
            String[] array = menu.getArray();

            // create variable to call item choice prompt
            ChooseItems items = new ChooseItems(array);

            // prompts user for desired menu, stores item choice, grabs and
            // calculates subtotal
            items.choose();

            clearConsole();

            Checkout checkout = new Checkout();
            checkout.updateCart(items.getItems(), items.getSubtotal());
            checkout.outputReceipt(scnr);
            checkout.tipPrompt(scnr);

            // create a variable to call function
            MakePayment payment = new MakePayment();

            // prompts for card, confirms card with user
            clearConsole();
            payment.paymentPrompt();

            // Jared //
            System.out.print("Would you like to add a review (y/n)?: ");
            String yesOrNo2 = scnr.nextLine().toUpperCase();

            if (yesOrNo2.equals("YES") || yesOrNo2.equals("Y")) {
                clearConsole();

                System.out.println("\n==============");
                System.out.println("WRITE A REVIEW");
                System.out.println("==============\n");

                ListReviews reviews = new ListReviews(userSelection);
                String userName, reviewRating, reviewText;

                System.out.print("What is your name?: ");
                userName = scnr.nextLine();
                System.out.println();

                System.out.print("On a scale of 1 to 5," + " how would you rate your food?: ");
                reviewRating = scnr.nextLine();
                System.out.println();

                System.out.print("Comment: ");
                reviewText = scnr.nextLine();
                System.out.println();

                reviews.addReview(userName, reviewRating, reviewText);
                reviews.updateTextFile();
            } // End Jared //

            // End credits
            endMessage();

        } catch (NullPointerException e) {
            fileErrorMessage();
        }

    }

    public static void clearConsole() {
        // Clears console
        for (int iter = 0; iter < 50; iter++) {
            System.out.println("\n");
        }
    }

    public static void creatorNames() {
        // Lists project authors
        System.out.println("\"Restaurant Menu\" was created by: ");
        System.out.println("Jared Andraszek");
        System.out.println("Jaye Engelhardt");
        System.out.println("Logan Ferguson");
        System.out.println("Joseph Kaufman");
        System.out.println("& Myles O'Toole\n");
    }

    public static void endMessage() {
        // When the program ends successfully
        clearConsole();
        System.out.println("\n==========");
        System.out.println("THANK YOU!");
        System.out.println("==========\n");
        creatorNames();
    }

    public static void cancelMessage() {
        // When the user cancels order
        clearConsole();
        System.out.println("\n*******************");
        System.out.println("* ORDER CANCELLED *");
        System.out.println("*******************\n");
        creatorNames();
    }

    public static void fileErrorMessage() {
        // When the menu file is not found
        clearConsole();
        System.out.println("\n******************");
        System.out.println("* FILE NOT FOUND *");
        System.out.println("******************\n");
        creatorNames();
    }

}
