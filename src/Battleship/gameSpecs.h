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
 * This file holds global constants, data types, and their associated helper
 * functions for the game of battleship.
 */
#ifndef ___GAME_SPECS___
#define ___GAME_SPECS___

#include <string>
using namespace std;

// stores ship placement and hit/miss information
enum Tile { WATER, AIRCRAFT_CARRIER, BATTLESHIP, SUBMARINE, DESTROYER, 
	PATROL_BOAT, MISS, AIRCRAFT_CARRIER_HIT, BATTLESHIP_HIT, SUBMARINE_HIT, 
	DESTROYER_HIT, PATROL_BOAT_HIT };

// converts the tile to the corresponding char value for board
char tileToSymbol(Tile givenTile);

// converts the tile to the corresponding string name
string shipToString(Tile ship);

const int BOARD_LENGTH = 10;

const int SHIP_SIZE[] {0, 5, 4, 3, 3, 2};

char tileToSymbol(Tile givenTile)
{
	switch(givenTile) // takes in tile
	{
		case WATER: // if tile matches... return char
			return '.';
		case AIRCRAFT_CARRIER:
			return 'A';
		case AIRCRAFT_CARRIER_HIT:
			return 'a';
		case BATTLESHIP:
			return 'B';
		case BATTLESHIP_HIT:
			return 'b';
		case SUBMARINE:
			return 'S';
		case SUBMARINE_HIT:
			return 's';
		case DESTROYER:
			return 'D';
		case DESTROYER_HIT:
			return 'd';
		case PATROL_BOAT:
			return 'P';
		case PATROL_BOAT_HIT:
			return 'p';
		case MISS:
			return '~';
	}
	cout << "Error" << endl; // if it doesnt... display error

	return ' '; // return space
}

string shipToString(Tile ship)
{
	switch(ship)
	{
		case WATER: // if ship value is... return "..."
			return "Water";
		case AIRCRAFT_CARRIER: case AIRCRAFT_CARRIER_HIT:
			return "Aircraft Carrier";
		case BATTLESHIP: case BATTLESHIP_HIT:
			return "Battleship";
		case SUBMARINE: case SUBMARINE_HIT:
			return "Submarine";
		case DESTROYER: case DESTROYER_HIT:
			return "Destroyer";
		case PATROL_BOAT: case PATROL_BOAT_HIT:
			return "Patrol Boat";
		case MISS:
			return "Miss";
	}
	return "Error"; // else return error
}

#endif
