import java.util.Scanner; // Import Scanner
import java.util.NoSuchElementException; // Importing NoSuchElementException

public class Main
{

	public static void main(String[] args)
	{

		Scanner input = new Scanner(System.in); // Creating Scanner
		
		// Creating 2 Arrays that will become the game board and the reference/instruction board
		char[][] instBoard = new char[3][3] ;
		char[][] playBoard = new char[3][3] ;

		// Creating variables used in this program
		String str;
		double nSelected;
		int iSelected, cRow, cCol;
		char user='X'; // Setting first user to X
		char winner = ' '; 
		boolean isValid = true; // Enabling the play again loop
		do
		{
			resetArray(instBoard, 0) ; // reset the instruction board with box numbers 0-9
			resetArray(playBoard, 1) ; // reset the playing board with empty spaces

			// Printing Welcome Message
			System.out.print("\n\n\n\n\nWelcome to Multiplayer Tic Tac Toe!\nThe First Player will be X and the Second will be O. The first player to get 3 X's or Y's in a row(Up, Down, Across, or Diagonally) will win. If all squares are filled it is a tie. Each player should enter a number to which box they want to select. Enter -1 to forfeit the game. The available box numbers are as follows:\n\n");

			for (int moves = 0; moves < 9;) // Looping this program 9 times as there are 9 places on the board
			{
				displayBoards (instBoard, playBoard); // Calling function to display the boards
				System.out.print("Player " + user + ", Please select an available box:\t"); // Prompts user to select a box

				str = input.nextLine(); // Reading the input from Scanner
				Scanner spot = new Scanner(str); // Creating another Scanner to the inputted string

				// Try and Catch for a proper number. Also to prevent a user to enter 2 numbers with a space
				try 
				{
	                        	nSelected = spot.nextDouble(); // Only Reads the first double from string
				}
				catch (NoSuchElementException e) // Catching the NoSuchElement exception so that a user cannot crash the system when only pressing 'Enter'
				{
					errorInfo (); // Display error message
					spot.close(); // Closing and releasing the resources used
					continue; // Keeps repeating the for loop until there is no errors
				}

				if (spot.hasNext()) // If input has characters after the space
				{
					errorInfo (); 
					spot.close(); 
					continue;   // Shows error message and breaks the loop to the beginning
				}

				iSelected = (int)nSelected; // Comparing to see if the number inputted is an integer.

                                if (iSelected == nSelected) // If it is an integer proceed
                                {
                                	if (iSelected == -1) // -1 is uesd to quit
                                	{               
                                        	System.out.print("*********************\n");
                                        	System.out.print("*Game was terminated*\n");
                                        	System.out.print("*********************\n\n");
						user = 'X'; // reset user back to X
						winner = ' ' ; // reset the winner
                                        	break; // Breaks loop to the do while(play again) loop
                                	}
                                	else if(iSelected > 0 && iSelected < 10) // matches the 1-9 boxes
                                	{	
                                        	// Transform the selected number into coordinates (row and col)
                                       		cRow = (iSelected - 1) / 3 ;
                                        	cCol = (iSelected - 1) % 3 ;

                                        	// Check if the spot is already taken
                                        	if (instBoard[cRow][cCol] == ' ') 
						{
                                                	System.out.print("\n\n") ;
                                                	System.out.print("*************************\n");
                                                	System.out.print("* Spot already taken!!! *\n");
                                                	System.out.print("*************************\n");
                                        	}
                                        	else // Checking to see who wins
                                        	{
                                                	instBoard[cRow][cCol] = ' ' ;
                                                	playBoard[cRow][cCol] = user; // Initializing the user inputs to the coordinates

							switch (iSelected) // All the ways a player can win on each box
							{ 
								case 1:
									if (winCheck(playBoard, 1, 2, 3) || winCheck(playBoard, 1, 5, 9) || winCheck(playBoard, 1, 4, 7))
										winner = user;
									break;
								case 2:
									if (winCheck(playBoard, 1, 2, 3) || winCheck(playBoard, 2, 5, 8))
										winner = user;
									break;
								case 3:
									if (winCheck(playBoard, 1, 2, 3) || winCheck(playBoard, 7, 5, 3) || winCheck(playBoard, 3, 6, 9))
										winner = user;
									break;
								case 4:
									if (winCheck(playBoard, 1, 4, 7) || winCheck(playBoard, 4, 5, 6))
										winner = user;
									break;
								case 5:
									if (winCheck(playBoard, 1, 5, 9) || winCheck(playBoard, 7, 5, 3) ||
								   	    winCheck(playBoard, 4, 5, 6) || winCheck(playBoard, 2, 5, 8)) 
										winner = user;
									break;
								case 6:
									if (winCheck(playBoard, 4, 5, 6) || winCheck(playBoard, 3, 6, 9))
										winner = user;
									break;
								case 7:
									if (winCheck(playBoard, 1, 4, 7) || winCheck(playBoard, 7, 5, 3) || winCheck(playBoard, 7, 8, 9))
										winner = user;
									break;
								case 8:
									if (winCheck(playBoard, 2, 5, 8) || winCheck(playBoard, 7, 8, 9))
										winner = user;
									break;
								case 9:
									if (winCheck(playBoard, 7, 8, 9) || winCheck(playBoard, 1, 5, 9) || winCheck(playBoard, 3, 6, 9))
										winner = user;
									break;
								default:  
									break; 
							}

							if (winner != ' ') // If a winner has been decided break the loop
							{
								break ;
							} else // Change the user's turn depending on if it was originally X or Y
							{
                                                		user = (user == 'X') ? 'O' : 'X';
	                                                	moves++;
							}
                                        	}
                                	}
					else
					{
						errorInfo (); // Display error message if number is not within the range or -1
					}
				}
                                else 
				{
					errorInfo (); // If not an integer
                                }
			}
			displayBoards (instBoard, playBoard); // Displaying the boards
			if (winner != ' ')
			{
				System.out.print("\n\n\n\n");
				System.out.print("*********************************\n");
				System.out.print("* Congrats to winner " + winner + " !!!\n");
				System.out.print("*********************************\n");
			}
			else
			{
				System.out.print("\n\n\n\n");
				System.out.print("****************\n");
				System.out.print("* It's a TIE !!!\n");
				System.out.print("****************\n");
			}
			winner = ' ' ; // Reseting winner
			user = 'X'; // Reset 1st player to x
			System.out.println("\n\n\nEnter 1 to play again. Enter anything else to quit: ");
			String fDecision = input.nextLine();
			if (!fDecision.equals("1"))
			{
				isValid = false; // If not 1 break this loop
			}
		}

		while (isValid);
	}
	

	// Function for resetting the 3x3 array
        public static void resetArray(char[][] a, int p) {
                for (int i = 0; i < a.length ; i++) {
                        for (int j = 0; j < a[0].length ; j++) {
                                a[i][j] = (p == 1) ? ' ' : (char) (i * 3 + j + 49) ; //If p ==1, it is the playboard and we reset it back to all blanks. if not we use ASCII(Characters list) to change reference board back to 1-9
                        }
                }
        }

	// Function for checking the winner
        public static boolean winCheck(char[][] board, int pos1, int pos2, int pos3) {

		int c1r = (pos1 - 1) / 3 ;  // calculate the row coordinate on the board array from the 1st position 
		int c1c = (pos1 - 1) % 3 ;  // calculate the col coordinate on the board array from the 1st position
		int c2r = (pos2 - 1) / 3 ;  // calculate the row coordinate on the board array from the 2nd position 
		int c2c = (pos2 - 1) % 3 ;  // calculate the col coordinate on the board array from the 2nd position
		int c3r = (pos3 - 1) / 3 ;  // calculate the row coordinate on the board array from the 3rd position 
		int c3c = (pos3 - 1) % 3 ;  // calculate the col coordinate on the board array from the 3rd position

		return (board[c1r][c1c]==board[c2r][c2c]) && (board[c2r][c2c]==board[c3r][c3c]) ;

        }

	// Function to display the boards from the array. All of this code in the function is to format the tictactoe board from a 3x3 to a 5x12
	public static void displayBoards (char[][] iBoard, char[][] pBoard) {

		char symbol ;

		System.out.print("\n") ;
		for (int i = 0; i < 5 ; i++)
	       	{
			for (int k=0 ; k < 2 ; k++) { 
				for (int j = 0; j < 12 ; j++)
				{
					if ((i==0) || (i==2) || (i==4))
				       	{
						if ((j==1) || (j==5) || (j==9))
					       	{
							symbol = (k == 0) ? iBoard[i/2][(j-1)%3] : pBoard[i/2][(j-1)%3] ;
						}
						else if ((j==3) || (j==7))
						{
							symbol = '|' ;
						}
						else if (j==11)
						{
							symbol = (k == 0) ? '\t' : '\n' ;
						}
						else
						{
							symbol = ' ' ;
						}
					}
					else
					{
						if ((j==3) || (j==7))
						{
							symbol = '+' ;
						}
						else if (j==11)
						{
							symbol = (k == 0) ? '\t' : '\n' ;
						}
						else
						{
							symbol = '-' ;
						}
					}

					System.out.print(symbol) ;
				}
			}
		}
		System.out.print("\n") ;
	}

	// Function to printout Error Message when called
        public static void errorInfo () {
		System.out.print("\n\n");
                System.out.print("***********************************\n");
                System.out.print("* Invalid Number. Please Try Again*\n");
                System.out.print("***********************************\n");
        }
}
