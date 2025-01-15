// Code Taken(Array) From my Final Project Last SummerSchool. Removed the abort game, as well as added CPU.
import java.util.Scanner;//Import Scanner
import java.util.NoSuchElementException;// Importing NoSuchElementException
import java.util.Random;//Importing Random

public class Main 
{
	public static void main(String[] args) 
	{
        	Scanner input = new Scanner(System.in);//Creating Scanner
        	Random random = new Random();//Creates Random
		// Creating 2 Arrays that will become the game board and the reference/instruction board
        	char[][] instBoard = new char[3][3];
        	char[][] playBoard = new char[3][3];

		// Creating variables used in this program
	        String str;
	        double nSelected;
	        int iSelected, cRow, cCol;
	        char user = 'X';// Setting first user to X
        	char winner = ' ';
        	boolean isValid = true;// Enabling the play again loop

        	do 
		{
         		resetArray(instBoard, 0);// reset the instruction board with box numbers 0 - 9
            		resetArray(playBoard, 1);//reset playing board with empty spaces

            		System.out.print("\n\n\n\n\nWelcome to Tic Tac Toe!\nThe First Player will be X and the CPU will be O. The first player to get 3 X's or O's in a row(Up, Down, Across, or Diagonally) will win. If all squares are filled, it's a tie. You are to enter a number to the box you want to select. The box numbers are shown on the left:\n\n");

            		for (int moves = 0; moves < 9; )// Looping this program 9 times as there are 9 spots 
			{
                		displayBoards(instBoard, playBoard);// Calling fc to display boards

                		if (user == 'X') //When it is the user's turn
				{
                    			System.out.print("Player X, Please select an available box:\t");		
					str = input.nextLine();
                    			Scanner spot = new Scanner(str);

                    			try //Tries and catch for a proper number and prevents entering the same number
					{
                        			nSelected = spot.nextDouble();
                    			}
		       			catch (NoSuchElementException e) 
					{
                        			errorInfo();
                        			spot.close();
                        			continue;
                    			}

                    			if (spot.hasNext())//If input has characters after the space
					{
                        			errorInfo();
                        			spot.close();
                        			continue;//Shows error message and breaks loop to beginning
                    			}

                    			iSelected = (int) nSelected;//Comparing to see if the number is an integer

                    			if (iSelected > 0 && iSelected < 10) //Mateches the 1-9 boxes
					{
                        			cRow = (iSelected - 1) / 3;
                        			cCol = (iSelected - 1) % 3;
                        			if (playBoard[cRow][cCol] == ' ')//if empty 
						{
                            				playBoard[cRow][cCol] = user;//updates with X
							instBoard[cRow][cCol] = ' ';//updates inst board
                            				winner = checkWinner(playBoard);
                            				if (winner != ' ') //checks for winner
							{
                                				break;
                            				}
					       		else 
							{
                                				user = 'O';
                                				moves++;
                            				}
                        			}			 
						else 
						{
                            				System.out.print("\n\n");
                            				System.out.print("*************************\n");
                            				System.out.print("* Spot already taken!!! *\n");
                            				System.out.print("*************************\n");
                        			}
                    			} 
					else//if any number else 
					{
                        			errorInfo();
                    			}
				}	
				else 
				{ // CPU's turn
                    			System.out.print("CPU is making a move...\n");
                    			do 
					{
                        			cRow = random.nextInt(3);//Generate rdm row and col
                        			cCol = random.nextInt(3);
                    			}
	       				while (playBoard[cRow][cCol] != ' ');//while the spot isn't empty

                    			playBoard[cRow][cCol] = 'O';//set that generated one as O
					instBoard[cRow][cCol] = ' ';//updates inst board
                    			winner = checkWinner(playBoard);//checks winner
                    			if (winner != ' ') 
					{
                        			break;
                    			} else 
					{
                        			user = 'X';
                        			moves++;//adds more to the 9 moves
                    			}
                		}
			}

			displayBoards(instBoard, playBoard);
            		if (winner != ' ') 
			{
                		if (winner == 'X') 
				{
                    			System.out.print("\n\n\n\n");
                    			System.out.print("*********************************\n");
                    			System.out.print("* Congrats!!! YOU WIN!!!!!\n");
                    			System.out.print("*********************************\n");
                		}
			       	else //if winner is O
				{
                    			System.out.print("\n\n\n\n");
                    			System.out.print("************************************\n");
                    			System.out.print("* YOU LOSE!!!!\n");
                    			System.out.print("************************************\n");
                		}
            		}	 
			else 
			{
                		System.out.print("\n\n\n\n");
                		System.out.print("****************\n");
                		System.out.print("* It's a TIE !!!\n");
                		System.out.print("****************\n");
            		}
            		winner = ' ';
           	 	user = 'X';
           		System.out.println("\n\n\nEnter 1 to play again. Enter anything else to quit: ");
            		String fDecision = input.nextLine();
            		if (!fDecision.equals("1")) //Allows for play again
			{
                		isValid = false;
            		}
		}
		while (isValid);
	}

    	public static void resetArray(char[][] a, int p) //reset array function
	{
        	for (int i = 0; i < a.length; i++) 
		{
            		for (int j = 0; j < a[0].length; j++) 
			{
                		a[i][j] = (p == 1) ? ' ' : (char) (i * 3 + j + 49);//If p ==1, it is the playboard and we reset it back to all blanks. if not we use ASCII(Characters list) to reset.
            		}
        	}	
    	}

    	public static char checkWinner(char[][] board) //checks for any winning combinations
	{
        	for (int i = 0; i < 3; i++) 
		{
            		if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) 
			{
                		return board[i][0];
            		}
            		if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) 
			{
                		return board[0][i];
            		}
        	}

        	if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) 
		{
        		return board[0][0];
        	}

        	if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) 
		{
            		return board[0][2];
        	}

        	return ' ';
    	}

    	public static void displayBoards(char[][] iBoard, char[][] pBoard) //Function to display the boards from the array. formatting the board from 3x3 to 5x12
	{
        	char symbol;
        	System.out.print("\n");
        	for (int i = 0; i < 5; i++) 
		{
            		for (int k = 0; k < 2; k++) 
			{
                		for (int j = 0; j < 12; j++) 
				{
                    			if ((i == 0) || (i == 2) || (i == 4)) 
					{
                        			if ((j == 1) || (j == 5) || (j == 9)) 
						{
                            				symbol = (k == 0) ? iBoard[i / 2][(j - 1) % 3] : pBoard[i / 2][(j - 1) % 3];
                        			}
					       	else if ((j == 3) || (j == 7)) 
						{
                            				symbol = '|';
                        			}
				       		else if (j == 11) 
						{
                            				symbol = (k == 0) ? '\t' : '\n';
                        			}
					       	else 
						{
                            				symbol = ' ';
                        			}
                    			}
				       	else
				       	{
                        			if ((j == 3) || (j == 7)) 
						{
                            				symbol = '+';
                        			} else if (j == 11) 
						{
                            				symbol = (k == 0) ? '\t' : '\n';
                        			}
					       	else
					       	{
                            				symbol = '-';
                        			}
                    			}

                    			System.out.print(symbol);
				}
			}
		}
        	System.out.print("\n");
	}

    	public static void errorInfo() //error message when called
	{
        	System.out.print("\n\n");
        	System.out.print("***********************************\n");
        	System.out.print("* Invalid Number. Please Try Again*\n");
        	System.out.print("***********************************\n");
    	}
}

