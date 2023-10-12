=Student Name:  Myles O’Toole
Program Name:  Magic Number Guessing Game
Creation Date:  Spring 2021
Last Modified Date:  Fall 2022
CSCI Course:  CSCI 301
Grade Received:  A
Design Comments: Plays a numerical guessing game.Print a welcoming message for the user.Ask the user to type in a numerical guess for the magic number. Tell the user if the guess is too high, too low, or just right. Quit with a closing message if the user guesses the magic number or types the quitting signal.
=cut

#!/usr/bin/perl
use strict;
use warnings;

print "Welcome to Myles's Magic Number Guessing Game!\n";

my $magic = int rand 20;
my $guess;
my $playagain = 1;

while ($playagain == 1)
{
	print "\nGuess a number between 1 and 20: ";

	chomp($guess = <STDIN>);


	if($guess == $magic)
	{
	print "You won!\n";
	last;
	}
	if ($guess < $magic)
	{
	print "Too low!\n";
	}
	if ($guess > $magic)
	{
	print "Too high!\n";
	}
	
	
	print "\nTo guess again type '1', or anything else to stop!\n";
	chomp($playagain = <STDIN>);
}

print "\nThank you for playing!\n";
