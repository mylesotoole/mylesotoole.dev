# Menus Generator

<link rel="stylesheet" type="text/css" href="assets/css/style.scss" />
<link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
<link rel="manifest" href="/site.webmanifest">

[Back to Portfolio](/)

<h2 style="font-size: 30px">Restaurant Menus Generator</h2>

- **Groupmates:** Jared Andraszek, Jaye Engelhardt, Logan Ferguson, and Joseph Kaufman
- **Class:** CSCI 325
- **Grade:** A
- **Language:** Java
- **Source Code Repository:**
  [ot8le/RestaurantMenusGenerator](https://github.com/ot8le/ot8le.github.io/tree/master/src/RestaurantMenusGenerator)  
  <!-- (Please [email me](mailto:mpotoole@csustudent.net?subject=GitHub%20Access) to request access.) -->

## Project Description

This program represents a restaurant ordering service and allows a user to select a restaurant and
create a mock order. Previous reviews can be printed from a text file of the selected restaurant or
the user can go straight to the menu. The menu will then be printed from a text file based on the
selection. The user can then choose items to be added to their order. Once the user is finished with
their order, the user’s itemized receipt will be printed based on everything they have selected.
Then the user can add a tip (in percentage) and pay with a card. The order total plus tax and tip
will be printed for the user. The user will then have the option to review the selected restaurant
and add a rating. If the user decides to add a review, then the review will be stored in a text file
so it can be viewed by other users.

## How to Compile and Run the Program

How to compile and run the project via terminal:

```bash
$ java -jar "RestarauntMenu.jar"
```

## UI Design

1. Displays all of the menus in the terminal giving the user the option to select an option by
   entering the Selection Code.
2. User receives a prompt asking if they would like to view the reviews for the restaurant. If so, a
   new screen will be displaying reviews otherwise it will move on to displaying the menu Items.
3. The menu of the selected restaurant is displayed and items can be added by typing in the items
   number.
4. Displays receipt and asks if user wants to continue to payment or cancel.
5. Payment information and additional confirmation.
6. Option to add review, and thank you credits.

<em>Fig 1. The welcome screen.</em> ![screenshot](/images/rmg-figure1.jpeg) <br />

<em>Fig 2. Example output after input is processed when you choose to see reviews.</em>
![screenshot](/images/rmg-figure2.jpeg)<br />

<em>Fig 3. Example output after input is processed for adding items to your order.</em>
![screenshot](/images/rmg-figure3.jpeg) ![screenshot](/images/rmg-figure4.jpeg)<br />

<em>Fig 4. Example output after input is processed (seeing receipt).</em>
![screenshot](/images/rmg-figure5.jpeg)<br />

<em>Fig 5. Example output after input is processed (payment confirmation prompt).</em>
![screenshot](/images/rmg-figure6.jpeg)<br />

<em>Fig 6. Example output after input is processed (write a review).</em>
![screenshot](/images/rmg-figure7.jpeg)<br />

<em>Fig 7. Example output after input is processed (yes selected).</em>
![screenshot](/images/rmg-figure8.jpeg)<br />

<em>Fig 8. Example output after input is processed (submitted/credits).</em>
![screenshot](/images/rmg-figure9.jpeg)<br>

<em>Fig 9. Feedback when an error occurs a restaurant closed.</em>
![screenshot](/images/rmg-figure10.jpeg)<br>

<em>Fig 10. Feedback when an error occurs (number out off menu/not a number).</em>
![screenshot](/images/rmg-figure11.jpeg)<br>

<em>Fig 11. Feedback when an error occurs (card errors).</em>
![screenshot](/images/rmg-figure12.jpeg)<br>

## Additional Considerations

- The user can keep adding food until they don't want to.
- Designed for the addition of restaurants and menus of any size.
- Only accepts correct character responses.
- Cards must have valid date and correct number formats.

[Back to Portfolio](/)
