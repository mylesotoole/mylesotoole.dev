
/*
Student Name:  Myles O’Toole
Program Name:  Restaurant Menus Generator
Creation Date:  Fall 2021
Last Modified Date:  Fall 2022
CSCI Course:  CSCI 325
Grade Received:  A
Design Comments: This program represents a restaurant ordering service and allows a user to select a restaurant and create a mock order. Previous reviews can be printed from a text file of the selected restaurant or the user can go straight to the menu. The menu will then be printed from a text file based on the selection. The user can then choose items to be added to their order. Once the user is finished with their order, the user’s itemized receipt will be printed based on everything they have selected. Then the user can add a tip (in percentage) and pay with a card. The order total plus tax and tip will be printed for the user. The user will then have the option to review the selected restaurant and add a rating. If the user decides to add a review, then the review will be stored in a text file so it can be viewed by other users.
*/

package csu.csci325;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author jayee
 */
public class SelectRestaurant {
    private int timeOfDay;
    private int dayOfTheWeek;  



    // Gets time of the day from the system returns as an integer 0-24
    public int getTimeOfDay() {
        timeOfDay = 0;
        timeOfDay = LocalTime.now().getHour();
        return timeOfDay;
    }

    // Gets day of the week from the system returns as integer 1-7
    public int getDayOfWeek() {
        dayOfTheWeek = 7;
       LocalDate.now();
       dayOfTheWeek = DayOfWeek.from(LocalDate.now()).getValue();
        return dayOfTheWeek;
    }

}
