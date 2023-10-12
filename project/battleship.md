# Battleship

<link rel="stylesheet" type="text/css" href="assets/css/style.scss" />
<link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
<link rel="manifest" href="/site.webmanifest">

[Back to Portfolio](/)

<h2 style="font-size: 30px">Battleship</h2>

- **Class:** CSCI 235
- **Grade:** A
- **Language:** C++
- **Source Code Repository:**
  [ot8le/Battleship](https://github.com/ot8le/ot8le.github.io/tree/master/src/Battleship)
  <!-- (Please [email me](mailto:mpotoole@csustudent.net?subject=GitHub%20Access) to request access.) -->

## Project Description

A working game of battleship, in which the user plays an enemy AI, using cumulative material over
the Procedural Programming semester.

- Displays random battleship coordinates in quick succession to make the illusion that the
  computer/enemy is thinking about which coordinate to shoot at.
- After the function returns the last random coordinate is still on the screen, but the cursor is
  back up so that it can be overwritten with something new.

## How to Compile and Run the Program

How to compile and run the project via Terminal with g++:

```bash
$ g++ battleship.cpp && ./battleship
```

## UI Design

1. Randomizes battleship board of choice for the user, the user can choose to regenerate.
2. The user picks coordinates to guess the enemy ship.
3. Board outputs hit, miss, sink, or error and updates board.
4. The enemy AI returns guesses and repeats _Step 3_.
5. Repeats _Steps 2-4_ until games finishes, where you can play again.

<em>Fig 1. The launch screen (before board choice)</em>
![screenshot](/images/battleship-figure1.jpeg) <br />

<em>Fig 2. The launch screen (after board choice).</em>
![screenshot](/images/battleship-figure2.jpeg) <br />

<em>Fig 3. Example output after input is processed (your shot).</em>
![screenshot](/images/battleship-figure3.jpeg) <br />

<em>Fig 4. Example output after input is processed (enemies shot).</em>
![screenshot](/images/battleship-figure4.jpeg) <br />

<em>Fig 5. Feedback when an error occurs (invalid input).</em>
![screenshot](/images/battleship-figure4.jpeg) <br />

<em>Fig 6. Feedback when an error occurs (already used coordinates).</em>
![screenshot](/images/battleship-figure5.jpeg) <br />

## Additional Considerations

- The user gets to decide if they want to keep their board at the beginning of the game, and if not
  a new board is generated until they say yes!

[Back to Portfolio](/)
