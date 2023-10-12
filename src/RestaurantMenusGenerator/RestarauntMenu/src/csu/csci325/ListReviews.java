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
    This class is responsible for the following:
        Opening the file with the reviews
        Creating an arrayList with the reviews
        Outputting the reviews
        Adding a review
        Saving those reviews to a text file
*/
package csu.csci325;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author jared
 */
public class ListReviews {
    // Declare variables //
    private ArrayList<Review> reviews;
    private String restaurantName;
    private String fileName; // Used for simplicity purposes //
    FileOutputStream fileOutStream = null; // Need this later //

    // Creates the Scanner //
    Scanner fileIn;

    // Default Constructor //
    public ListReviews(String restaurantName) {
        this.restaurantName = restaurantName;
        // For simplicity purposes //
        fileName = "Reviews" + restaurantName;

        // This will create the array and open the file each time the class
        // is called
        fileIn = openFile();
        reviews = createList();
    }

    // Tests to see if the file exists and returns a Scanner that reads the
    // file
    private Scanner openFile() {
        Scanner retVal = null;
        // Tests to see if the file exists //
        try {
            FileInputStream fileInStream;

            // fileName = "Reviews" + restuaurantName + ".txt" //
            fileInStream = new FileInputStream(fileName);
            retVal = new Scanner(fileInStream);

            // If the program returns a file not found error, print out
            // File not found
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        // Returns either a Scanner to read the file or Null //
        return retVal;
    }

    public ArrayList<Review> createList() {
        // Declare variables //
        ArrayList<Review> retVal = new ArrayList<>();
        long lines = 0;
        int index = 0;
        String temp;

        // This is used for a the variable lines //
        Path path = Paths.get("Reviews" + restaurantName);

        // If the fileIn was returned Null from openFile, print out the
        // error message
        if (fileIn == null) {
            System.out.println("");

            // Else, make the arrayList //
        } else {
            try {
                // Here is lines. Lines = number of lines in the text file //
                lines = Files.lines(path).count();
            } catch (IOException e) {
                System.out.println("");
            }

            // Each review is 3 lines long. So take lines and divide by 3 //
            index = (int) lines / 3;

            // Creates an array of Reviews. The length of a review is 3 lines
            // so total lines / 3
            Review reviews[] = new Review[index];

            // Fills out the array //
            for (int i = 0; i < index; i++) {
                // Creates a Review and adds it to the reviews array //
                reviews[i] = new Review(fileIn.nextLine(), fileIn.nextLine(), fileIn.nextLine(), restaurantName);
                retVal.add(reviews[i]);
            }
        }

        // Either returns an array of Reviews or null //
        return retVal;
    }

    // Add a review to the array list //
    public void addReview(String userName, String userRating, String userText) {
        // Makes a new Review with the items sent in //
        Review newReview = new Review(userName, userRating, userText, restaurantName);

        // Adds it to the arrayList //
        reviews.add(newReview);
    }

    // Print out the List of Reviews to the text file //
    public void updateTextFile() {
        // If the file was not found, output error //
        if (fileIn == null) {
            System.out.println("");
        } else {
            // Tests because I have to (Even though I already did test it in
            // openFile()) //
            try {
                // Creates a outputStream object that can write to a file.
                // Overwrites the beginning of the file
                fileOutStream = new FileOutputStream(fileName, false);

                /*
                 * This for loop looks complicated, but is actually simple. The fileOutputStream
                 * object needs to convert a String to a Byte array. So There are 3 variables
                 * from each Review that needs to be written to the file.
                 * 
                 * So we convert the User's Name by getting the ReviewerName of the review, and
                 * converting that String to bytes. Then assign that to a byte array called
                 * convertToByteName.
                 * 
                 * This happens 2 more times. the ReviewRating needs to be converted to a String
                 * and then converted to a byte Array.
                 * 
                 * BlankLine adds the \n character to write a new line.
                 */
                for (int i = 0; i < reviews.size(); i++) {
                    byte[] convertToByteName = reviews.get(i).getReviewerName().getBytes();
                    byte[] convertToByteScore = Double.toString(reviews.get(i).getReviewRating()).getBytes();
                    byte[] convertToByteText = reviews.get(i).getReviewText().getBytes();
                    byte[] newLine = "\n".getBytes();

                    // The loop then writes the Name, Score, and Text separated
                    // by a newLine
                    fileOutStream.write(convertToByteName);
                    fileOutStream.write(newLine); // '\n' //
                    fileOutStream.write(convertToByteScore);
                    fileOutStream.write(newLine);
                    fileOutStream.write(convertToByteText);
                    fileOutStream.write(newLine);
                }

                // Closes the file //
                fileOutStream.close();
            } catch (IOException e) {
                // A necessity //
                System.out.println("");
            }
        }
    }

    // Updates the rating out of 5 (gets the average) //
    public double updateRestaurantReview() {
        double average = 0;

        // Adds the reviewRating to average //
        for (int i = 0; i < reviews.size(); i++) {
            try {
                average += (double) reviews.get(i).getReviewRating();
            } catch (NumberFormatException num) {
                System.out.println("");
            }
        }

        // Divides average by the number of reviews //
        average /= reviews.size();

        return average;
    }

    public void printReviews() {
        double ratings = updateRestaurantReview();

        // Formatter for review ratings
        final DecimalFormat df = new DecimalFormat("0.0");
        // Prints out the Restaurant Name and average rating
        System.out.print("\n-------------------------------------------------");
        System.out.println("\nAverage Rating: " + df.format(ratings) + "/5");
        System.out.println("-------------------------------------------------\n");

        /*
         * Prints out the following: ReviewerName Rating
         * Text----------------------------------
         * 
         */
        for (int i = 0; i < reviews.size(); i++) {
            System.out.println(reviews.get(i).getReviewerName());
            System.out.println(reviews.get(i).getReviewRating());
            System.out.println(reviews.get(i).getReviewText());
            System.out.println();
        }

        System.out.println("-------------------------------------------------\n");

    }
}