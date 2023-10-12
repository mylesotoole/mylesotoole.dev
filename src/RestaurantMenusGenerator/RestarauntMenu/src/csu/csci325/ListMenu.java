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
 * @author Logan Ferguson
 */
package csu.csci325;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListMenu {
    // Declare private fields
    private String menu;
    private String[] array;

    // Default constructor
    public ListMenu() {
        menu = ""; // Makes menu an empty string
    }

    public ListMenu(String userSelection) {
        String menuSelection = userSelection;
        menu = "";

        // Declares a file input variable and Scanner to read file
        FileInputStream fileByteStream = null;
        Scanner fileIn;

        // try to open file
        try {
            fileByteStream = new FileInputStream(menuSelection);
        } catch (FileNotFoundException | NullPointerException expn) {
            System.out.println("");
        }

        fileIn = new Scanner(fileByteStream);

        // Reads the file and adds the text to menu variable
        while (fileIn.hasNextLine()) {
            menu += fileIn.nextLine() + "\n";
        }

        // Close the file scanner
        fileIn.close();
    }

    // Creates an array of the menu items to be used later
    public void createItems() {
        // Splits the array by two newlines to fit the format of the file
        array = menu.split("\n\n");
    }

    // Returns the menu array
    public String[] getArray() {
        return array;
    }

    // Prints the menu items from the text file
    public void printMenu() {
        System.out.println(menu);
    }
}
