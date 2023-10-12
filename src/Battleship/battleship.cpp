/*
 Student Name:  Myles O’Toole
 Program Name:  Battleship
 Creation Date:  Fall 2020
 Last Modified Date:  Fall 2022
 CSCI Course:  CSCI 235
 Grade Received:  A
 Design Comments: Randomizes battleship board of choice for the user, the user can choose to regenerate. The user picks coordinates to guess the enemy ship. Board outputs hit, miss, sink, or error and updates board. The enemy AI returns guesses. Repeats until games finishes, where you can play again.
 */
/*
A working game of battleship, in which the user plays an enemy AI, using
cumulative material over the Procedural Programming semester.
By Myles O'Toole
*/
#include <iostream>
#include <iomanip>
#include <thread>
#include <chrono>
#include <cstdlib>
#include <ctime>
#include <climits>
using namespace std;

// Include custom headers
#include "gameSpecs.h"
#include "enemyAI.h"

/**
 * Displays random battleship coordinates in quick succession to make the
 * illusion that the computer/enemy is thinking about which coordinate to shoot
 * at.
 *
 * After the function returns the last random coordinate is still on the screen,
 * but the cursor is back up so that it can be overwritten with something new.
 */
void randomCoordinatesAnimation();

// sets values of the arrays to WATER
void wipeBoard(Tile fleet[][BOARD_LENGTH], Tile value = WATER);

// displays player and enemy boards, formatted correctly
void displayBoards(const Tile player[][BOARD_LENGTH], 
	const Tile enemy[][BOARD_LENGTH], bool showAll = false);

// placing ships on horizontally within the board, without hitting other ships
bool placeShipHorizontally( Tile whatShip, int shipSize, 
	int letterIndex, int numberIndex, Tile gameBoard[][BOARD_LENGTH]);

// keeps track of hoe many hits are left on each ship before it sinks
string playersTurn(Tile enemyBoard[][BOARD_LENGTH], int enemyShipCount[]);

// converts row letters to numbers
int charToNumber(char letter);

// placing ships on vertically within the board, without hitting other ships
bool placeShipVertically(Tile whatShip, int shipSize, 
	int letterIndex, int numberIndex, Tile gameBoard[][BOARD_LENGTH]);

// clears the board, and iterates placing ships until all are done
void placeShipsRandomly(Tile gameBoard[][BOARD_LENGTH]);

/* 
calls placeShipsRandomly(), displayBoards(), and asks if you want to use
the board.
*/
void placePlayersShips(Tile board[][BOARD_LENGTH]);

// checks if all ships are sunk in a given array
bool isAllZeroes(const int shipCount[]);

int main ()
{
	// both matrices
	Tile playerBoard [BOARD_LENGTH][BOARD_LENGTH];
	Tile enemyBoard [BOARD_LENGTH][BOARD_LENGTH];

	// used to keep track how many hits left on each ship before it sinks
	int playerShipCount[] {0, 5, 4, 3, 3, 2};
	int enemyShipCount[] {0, 5, 4, 3, 3, 2};

	// gives "random" seed based on time (from Dr. Hayes' video)
	unsigned int seed = static_cast<unsigned int>(time(NULL));
	srand(seed);

	placeShipsRandomly(playerBoard);
	placeShipsRandomly(enemyBoard);
	placePlayersShips(playerBoard);

	do
	{
		cout << playersTurn(enemyBoard, enemyShipCount);
		
		// if enemy ships are all zero
		if(isAllZeroes(enemyShipCount))
		{
			cout << "You win!" << endl; // display win
			break; // exit loop
		}

		displayBoards(playerBoard, enemyBoard);

		cout << "Enemy's shot: " 
			<< EnemyAI::enemyTurn(playerBoard, playerShipCount);

		if (isAllZeroes(playerShipCount))
		{
			cout << "You lose!" << endl; // display lose
			break; // exit loop
		}
		
		displayBoards(playerBoard, enemyBoard);

	} while (!(isAllZeroes(enemyShipCount)));

	displayBoards(playerBoard, enemyBoard, true); // display both sides of board

	return 0;
}

void randomCoordinatesAnimation()
{
	// Variables to hold random coordinates to print
	int colNumber;
	char rowLetter;

	cout << left; // left align columns

	// Repeatedly output a random coordinate, then wait, then backup to overwrite
	// The pauses increase in time exponentially until it is >= 160 msecs
	for (int msecs = 10; msecs < 160; msecs = static_cast<int>(msecs * 1.1))
	{
		// Get random numbers
		rowLetter = rand() % BOARD_LENGTH + 'A';
		colNumber = rand() % (BOARD_LENGTH) + 1;

		// Output numbers
		cout << rowLetter << setw(2) << colNumber << flush;

		// Wait the alloted time
		this_thread::sleep_for(chrono::milliseconds(msecs));

		// Backup for next output.
		cout << "\b\b\b";
	}

	cout << right; // reset alignment back to the default
}

void wipeBoard(Tile fleet[][BOARD_LENGTH], Tile value)
{
	// initialize each row
	for (int rowIndex = 0; rowIndex < BOARD_LENGTH; ++ rowIndex)
	{
		// initalize each column (item) in one row
		for (int columnIndex = 0; columnIndex < BOARD_LENGTH; ++columnIndex)
		{
			fleet[rowIndex][columnIndex] = value; // set to designated value
		}
	}
}

void displayBoards(const Tile player[][BOARD_LENGTH], 
	const Tile enemy[][BOARD_LENGTH], bool showAll)
{
	cout << "  Enemy's Fleet           Your Fleet" << endl;

	cout << "   1 2 3 4 5 6 7 8 910     1 2 3 4 5 6 7 8 910" << endl;

	// display each row
	for (int rowIndex = 0; rowIndex < BOARD_LENGTH; ++ rowIndex)
	{
		// place letters in front of rows
		cout << ' ' << static_cast<char>('A' + rowIndex) << " ";

		// display each column (item) in a row
		for (int columnIndex = 0; columnIndex < BOARD_LENGTH; ++columnIndex)
		{			
			if (showAll)
			{
				// reveals enemy board at end
				cout << tileToSymbol(enemy[rowIndex][columnIndex]);
			}
			else if (enemy[rowIndex][columnIndex] < MISS)
			{
				// if not a hit or a miss
				cout << ".";
			}
			else if (enemy[rowIndex][columnIndex] == MISS)
			{
				// if a miss
				cout << "~";
			}
			else
			{
				// if a hit
				cout << "X";
			}

			// if its not the end of the board
			if (columnIndex < (BOARD_LENGTH - 1))
				cout << " ";
		}

		// player loop
		cout << setw(4) << static_cast<char>('A' + rowIndex) << " ";

		for (int columnIndex = 0; columnIndex < BOARD_LENGTH; ++columnIndex)
		{
			cout << tileToSymbol(player[rowIndex][columnIndex]);

			if (columnIndex < (BOARD_LENGTH - 1))
				cout << " ";
		}

		// new line after each row
		cout << endl;
	}
}

string playersTurn(Tile enemyBoard[][BOARD_LENGTH], int enemyShipCount[])
{
	char rowLetter; // letter user entered
	int columnNumber; // number entered, will be converted to index
	int rowNumber; // letter convertered to index
	string tileType; // return value
	string missReturn; // return value for miss (with coordinates)
	bool isValid = false; // for the loop
	string colReturn; // column number converted to string
	string hitReturn;
	string sinkReturn;
	Tile tileIndex;

	do
	{
		// displays out, user enters a letter and a number
		cout << "Enter the coordinates for your shot (e.g. B2): ";
		cin >> rowLetter >> columnNumber;
		rowLetter = toupper(rowLetter);

		// calculate indexes
		rowNumber = charToNumber(rowLetter); // converts letter to index
		columnNumber = columnNumber - 1; // converts presented num to index

		// if not in range
		if (rowNumber >= BOARD_LENGTH || columnNumber >= BOARD_LENGTH)
		{
			// if the indexes are not within the board length, error message
			cout << "Invalid coordinates." << endl;
			cout << "Pick a row value between A and J and a column value" 
				<< " between 1 and 10." << endl;
		}
		else if(enemyBoard[rowNumber][columnNumber] >= MISS)
		{
			cout << "You have already shot at " << rowLetter 
				<< columnNumber + 1 << endl;
		}
		else
		{	
			isValid = true; // if its not a miss or out of bounds, exit loop
		}

	} while (!isValid); // keep repeating whenever the numbers are not in bounds

	colReturn = to_string(columnNumber + 1); // converts int to string 

	cout << "Your shot: ";

	tileIndex = enemyBoard[rowNumber][columnNumber];

	if (enemyBoard[rowNumber][columnNumber] < MISS)
	{
		enemyBoard[rowNumber][columnNumber] = 
			static_cast<Tile>(enemyBoard[rowNumber][columnNumber]
			+ MISS);
	}
	
	if (enemyBoard[rowNumber][columnNumber] == MISS)
	{
		missReturn = rowLetter + colReturn + " Miss.\n"; // makes return string

		return missReturn;
	}
	else
	{
		hitReturn = rowLetter + colReturn + " Hit!\n";

		// substract ships remaining
		enemyShipCount[tileIndex] = 
			enemyShipCount[tileIndex] - 1;

		if (enemyShipCount[tileIndex] <= 0)
		{
			// set string to the Tile name for return
			tileType = shipToString(tileIndex);

			sinkReturn = hitReturn + "  You sunk the enemy's " 
				+ tileType + "!\n";

			return sinkReturn;
		}
	
		return hitReturn;
	}
}

int charToNumber(char letter)
{
	switch(letter) // takes in tile
	{
		case 'A': // if tile matches... return char
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		case 'F':
			return 5;
		case 'G':
			return 6;
		case 'H':
			return 7;
		case 'I':
			return 8;
		case 'J':
			return 9;
	}
	return 99; // out of bounds
}

bool placeShipHorizontally( Tile whatShip, int shipSize, 
	int letterIndex, int numberIndex, Tile gameBoard[][BOARD_LENGTH])
{
	bool canPlace = true; // if there are no ships in the way, stays true
	int boundry = (BOARD_LENGTH - shipSize); // closest to edge, to be placed
	
	// if the indexes can be on the board
	if (letterIndex >= 0 && numberIndex >= 0)
	{
		// if the indexes have enough space to be placed on the board
		if (letterIndex <= (BOARD_LENGTH - 1) && numberIndex <= boundry)
		{
			// for each potential ship coordinate
			for (int index = 0; index < shipSize; ++ index)
			{
				// if the coordinate is not water
				if (gameBoard[letterIndex][numberIndex + index] > WATER)
				{
					// if this loop happens, the placement loop will not
					canPlace = false;
				}
			}
			// if there is no other item placed along the ship length
			if (canPlace)
			{
				for (int index = 0; index < shipSize; ++index)
				{
					// update board coordinate
					gameBoard[letterIndex][numberIndex + index] = whatShip;
				}
				return true;
			}
		}
	}
	return false;
}

bool placeShipVertically(Tile whatShip, int shipSize, 
	int letterIndex, int numberIndex, Tile gameBoard[][BOARD_LENGTH])
{
	bool canPlace = true; // if there are no ships in the way, stays true
	int boundry = (BOARD_LENGTH - shipSize); // the max out that ship still fits
	
	// if the indexes can be on the board
	if (letterIndex >= 0 && numberIndex >= 0)
	{
		// if the indexes have enough space to be placed on the board
		if (letterIndex <= boundry && numberIndex < BOARD_LENGTH)
		{
			for (int index = 0; index < shipSize; ++index)
			{
				if (gameBoard[letterIndex + index][numberIndex] > WATER)
				{
					// returns false if there is a ship in the way
					canPlace = false;
				}
			}
			// if there is no other item placed there
			if (canPlace)
			{
				for (int index = 0; index < shipSize; ++index)
				{
					// update board coordinate
					gameBoard[letterIndex + index][numberIndex] = whatShip;
				}
				return true;
			}
		}
	}
	return false;
}

void placeShipsRandomly(Tile gameBoard[][BOARD_LENGTH])
{
	// clear board
	wipeBoard(gameBoard, WATER);

	for (int iter = AIRCRAFT_CARRIER; iter <= PATROL_BOAT; ++iter)
	{
		int size = SHIP_SIZE[iter]; // convert current tile to int
		Tile ship = (Tile)iter; // convert current iter to Tile
		bool shipPlaced; // if not placed, it will re-loop

		do
		{
			// declare random numbers for use and limits numbers to 0-9
			int letterIndex = rand() % BOARD_LENGTH; 
			int numberIndex = rand() % BOARD_LENGTH;

			// if a random number is even
			if (rand() % 2 == 0)
			{
				shipPlaced = placeShipHorizontally(ship, size, letterIndex, 
					numberIndex, gameBoard);
			}
			else
			{
				shipPlaced = placeShipVertically(ship, size, letterIndex, 
					numberIndex, gameBoard);
			}
		} while (!shipPlaced);	
	}

}

void placePlayersShips(Tile board[][BOARD_LENGTH])
{
	char yesNo;
	bool loop = true;

	do
	{
		placeShipsRandomly(board);
		displayBoards(board, board);

		// displays out, user enters a letter
		cout << "Do you want to play with this board? (y/n): ";
		cin >> yesNo;
		cin.ignore(INT_MAX, '\n'); // ignore everything up to new line
		yesNo = toupper(yesNo); // make answer uppercase

		// if not y or n, keep prompting until it is
		while (!(yesNo == 'Y' || yesNo == 'N'))
		{
			cout << "Please enter 'y' or 'n': ";
			cin >> yesNo;
			cin.ignore(INT_MAX, '\n'); // ignore everything up to new line
			yesNo = toupper(yesNo);
		}
		if (yesNo == 'Y')
		{
			// exit the loop
			loop = false;
		}
		else if(yesNo == 'N')
		{
			// loop to top again
		}
	} while (loop);

}

bool isAllZeroes(const int shipCount[])
{
	// if each index of ship count == 0
	if (shipCount[0] == 0 && shipCount[1] == 0 
		&& shipCount[2] == 0 && shipCount[3] == 0
		&& shipCount[4] == 0 && shipCount[5] == 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}
