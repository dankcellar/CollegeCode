////////////////////////////
// Eric Downey 
// COP 3503C - Section 12
// Lottery.java
// 01/17/2015

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Lottery {
	
	private static Scanner readFile;
	private static Scanner inputFile;
	private static Scanner inputNumbers;

	public static void main(String[] args) throws Exception {
	    
		/**		
		 * Enter file name to be used
		 * Open scanner to prepare for reading input
		 */
		System.out.println("Enter the name of the file with the ticket data.");
		inputFile = new Scanner (System.in);
		String fileName = inputFile.next();
		File file = new File (fileName);       
		readFile = new Scanner (new FileInputStream (file));
		
		/**		 
		 * Prompt user to enter winning Lottery numbers
		 * These numbers are saved to array winningNumbers[]
		 */
		System.out.println("Enter the winning Lottery numbers:");
		inputNumbers = new Scanner (System.in);
		int[] winningNumbers = new int[6];		
		for (int i = 0; i < 6; i++) {
			winningNumbers[i] = inputNumbers.nextInt();
		}
				
		/**		 
		 * Read from the input file to obtain the players names and numbers picked
		 * Stores these names and number in various arrays labeled 
		 * lastName[], firstName[], and ticketInfo[][]		 
		 */
		int last = 0;
		int first = 0;		
		String temp = readFile.next();
		int playerCount = Integer.parseInt(temp);				// gets the amount of tickets sold
		String[] lastName = new String[playerCount];			// lastName[] stores the players lame name in order listed
		String[] firstName = new String [playerCount];			// firstName[] stores the players first name in order listed
		String[][] ticketInfo = new String[playerCount][6];		// ticketInfo[][] stores a player and there chosen numbers
		
		while (last < playerCount) {						
			lastName[last] = readFile.next();
			firstName[first] = readFile.next();
			for (int j = 0; j < 6; j++) {
				ticketInfo[first][j] = readFile.next();			// Stores each players number in ticketInfo[player][#]
			}			
			last += 1;
			first += 1;
		}
				
		/**
		 * Convert String[][] to int[][] for matching winning numbers
		 * Cycles through ticketInfo[][] and converts it into ticketNumbers[][]
		 */
		int[][] ticketNumbers = new int[playerCount][6];
		for (int i = 0; i < playerCount; i++) { 
			for (int j = 0; j < 6; j++) {
				int holder = Integer.parseInt(ticketInfo[i][j]);	// Parse string from file to an int
				ticketNumbers[i][j] = holder;
			}
        }
		
		/**
		 * Conditional if statement used to match winning tickets 
		 * Compares ticketNumbers[player][#] to wininingNumbers[#]
		 * Increase counter for each true match and stores it in respect to each player
		 */		
		int[] matchNumber = new int[playerCount];
		for (int i = 0; i < playerCount; i++) { 
			int counter = 0;
			for (int j = 0; j < 6; j++) {
				
				if (ticketNumbers[i][j] == winningNumbers[0] ||
					ticketNumbers[i][j] == winningNumbers[1] ||
					ticketNumbers[i][j] == winningNumbers[2] ||
					ticketNumbers[i][j] == winningNumbers[3] ||
					ticketNumbers[i][j] == winningNumbers[4] ||
					ticketNumbers[i][j] == winningNumbers[5]   )
				{
					counter += 1;					
				}														
			}
			matchNumber[i] = counter;										
		}
		
        /**        
         * Print statements to declare winners and amount of money won
         * If there are no matches, nothing will be printed
         */
		MatchingNumbers threeMatch = MatchingNumbers.THREE;
		MatchingNumbers fourMatch = MatchingNumbers.FOUR;
		MatchingNumbers fiveMatch = MatchingNumbers.FIVE;
		MatchingNumbers sixMatch = MatchingNumbers.SIX;
		
		for (int i = 0; i < playerCount; i++) { 
			switch (matchNumber[i]) {
			case 3:
				System.out.println(firstName[i] + " " + lastName[i] + threeMatch.getMoney());
				break;
			case 4:
				System.out.println(firstName[i] + " " + lastName[i] + fourMatch.getMoney());
				break;
			case 5:
				System.out.println(firstName[i] + " " + lastName[i] + fiveMatch.getMoney());
				break;
			case 6:
				System.out.println(firstName[i] + " " + lastName[i] + sixMatch.getMoney());
				break;
			default:
				break;	
			}							
		}				
	}
	
	/**	 
	 * MathcingNumbers enum using to hold place of winning values.
	 * Each match relates to the amount of money won and the print
	 * statement is found with the getMoney() method.
	 */
	public enum MatchingNumbers {
		
		THREE	(" mathced 3 Numbers and won $10"),
		FOUR	(" mathced 4 Numbers and won $100"),
		FIVE	(" mathced 5 Numbers and won $10000"),
		SIX		(" mathced 6 Numbers and won $1000000");
		
		private String money;
		
		MatchingNumbers(String money) {
			this.money = money;
		}
		
		public String getMoney() { return money; }
	}
}