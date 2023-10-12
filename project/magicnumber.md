# Magic Number

<link rel="stylesheet" type="text/css" href="assets/css/style.scss" />
<link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
<link rel="manifest" href="/site.webmanifest">

[Back to Portfolio](/)

<h2 style="font-size: 30px">Magic Number Guessing Game</h2>

- **Class:** CSCI 301
- **Grade:** A
- **Language:** PERL
- **Source Code Repository:**
  [ot8le/MagicNumberGuessingGame](https://github.com/ot8le/ot8le.github.io/tree/master/src/MagicNumberGuessingGame)  
  <!-- (Please [email me](mailto:mpotoole@csustudent.net?subject=GitHub%20Access) to request access.) -->

## Project Description

Plays a numerical guessing game.

- Print a welcoming message for the user.
- Ask the user to type in a numerical guess for the magic number.
- Tell the user if the guess is too high, too low, or just right.
- Quit with a closing message if the user guesses the magic number or types the quitting signal.

## How to Run the Program

How to run the project via Terminal:

```bash
$ perl guessGame.pl
```

## UI Design

1. Have the computer randomly assign a number to a variable
2. Receive user input and have the computer say whether it’s higher or lower than the variable.
3. If the number is the same as the variable, congratulate user and end game
4. Prompt user each time to see if they want to keep playing

<em>Fig 1. The launch screen</em> <br />![screenshot](/images/mngg-figure1.jpeg)<br />

<em>Fig 2. Example output after input is processed.</em>
![screenshot](/images/mngg-figure2.jpeg)<br />

<em>Fig 3. Feedback when an error occurs.</em> ![screenshot](/images/mngg-figure3.jpeg)<br />

## Additional Considerations

- Gives the user the option to say they are tired of playing this game or to try again if he/she
  wishes.

[Back to Portfolio](/)
