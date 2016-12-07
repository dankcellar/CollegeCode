///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek1 
{
	
	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Downey, Eric, e3118280");
	}

	// Directions: Return the number of even ints in the given 
	//   array (The number '0' counts as an even number). 
	//   Note: the % "mod" operator computes the remainder, 
	//   e.g. 5 % 2 is 1. 

	// CountEvenNumbersInArray({2, 1, 2, 3, 4}) → 3
	// CountEvenNumbersInArray({2, 2, 0}) → 3
	// CountEvenNumbersInArray({1, 3, 5}) → 0
	
	/**
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		int with the number of even numbers in NumberList
	 */
	static int CountEvenNumbersInArray(int[] NumberList) 
	{
		int evenCount = 0;
		int num;
		for (int i = 0; i < NumberList.length; i++) {
			num = NumberList[i];
			if (num % 2 == 0) {			// modulus to find even numbers
				evenCount++;			// increase counter
			}			
		}			
		return evenCount; 
	}

	// Given an array of ints, return true if the array contains no 
	//   1's and no 3's.

	// LookForLucky13({0, 2, 4}) → true
	// LookForLucky13({1, 2, 3}) → false
	// LookForLucky13({1, 2, 4}) → false
	
	/**
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns false if there is a 1 or 3 in the list.
	 * 		returns true if there are no 1s or 3s in the list.
	 */
	static boolean LookForLucky13(int[] NumberList) 
	{
		boolean testCase = true;				// default case
		int num;
		for (int i = 0; i < NumberList.length; i++) {
			num = NumberList[i];
			if (num == 1 || num == 3) {			// if statement to find a 1 or 3
				testCase = false;				// set test case boolean
				continue;						// break loop
			}
		}				
		return testCase;
	}	

	// Given arrays NumberList1 and NumberList2 of the same length, 
	//   for every element in NumberList1, consider the 
	//   corresponding element in NumberList2 (at the same index). 
	//   Return the count of the number of times that the two 
	//   elements differ by 2 or less, but are not equal. 

	// MatchUpLists({1, 2, 3}, {2, 3, 10}) → 2
	// MatchUpLists({1, 2, 3}, {2, 3, 5}) → 3
	// MatchUpLists({1, 2, 3}, {2, 3, 3}) → 2
	
	static int MatchUpLists(int[] NumberList1, int[] NumberList2) 
	{	
		int num1, num2;
		int countMatch = 0;		
		for (int i = 0; i < NumberList1.length; i++) {
			num1 = NumberList1[i];				// obtain proper index and number...
			num2 = NumberList2[i];				// from both arrays
			int testCase1 = num1 - 2;		
			int testCase2 = num1 - 1;
			int testCase3 = num1 + 1;			// test case for each outcome
			int testCase4 = num1 + 2;
			if (num2 == testCase1 || num2 == testCase2 || num2 == testCase3 || num2 == testCase4 ) {
				countMatch++;		// counter increased when test case found
			}			
		}
		return countMatch;
	}	

	// Given an array of ints, return true if the array 
	//   contains either 3 even or 3 odd values all next 
	//   to each other. 

	// ModThreeNumbers({2, 1, 3, 5}) → true
	// ModThreeNumbers({2, 1, 2, 5}) → false
	// ModThreeNumbers({2, 4, 2, 5}) → true
	
	/**
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		return true if there are three consecutive evens
	 * 			or three consecutive odds
	 * 
	 * 		otherwise returns false
	 */
	public static boolean ModThreeNumbers(int[] NumberList) 
	{
		boolean check = false;
		for (int i = 2; i < NumberList.length; i++) {
			int num1 = NumberList[i];
			int num2 = NumberList[i-1];								// get a series of numbers
			int num3 = NumberList[i-2];			
			int test1 = num1 % 2;
			int test2 = num2 % 2;									// determine if a number is odd or even
			int test3 = num3 % 2;				
			if  (test1 == 0 && test2 == 0 && test3 == 0) {			// check for even series
				check = true;
				continue;
			}
			if (test1 == 1 && test2 == 1 && test3 == 1) { 	// check for odd series
				check = true;
				continue;
			}
		}
		return check;		
	}

	// Return the "centered" average of an array of ints, 
	//   which we'll say is the mean average of the values, 
	//   except ignoring the largest and smallest values in 
	//   the array. If there are multiple copies of the 
	//   smallest value, ignore just one copy, and likewise 
	//   for the largest value. Use int division to produce 
	//   the final average. You may assume that the array is 
	//   length 3 or more. 

	// FindCenteredAverage({1, 2, 3, 4, 100}) → 3
	// FindCenteredAverage({1, 1, 5, 5, 10, 8, 7}) → 5
	// FindCenteredAverage({-10, -4, -2, -4, -2, 0}) → -3	
	
	/**
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		Average of the list of numbers without the
	 * 		first of the lowest numbers and the last of the
	 * 		highest numbers.
	 */
	static int FindCenteredAverage(int[] NumberList) 
	{
		int sum = 0;
		int num1 = NumberList[0];							// starting point for array
		int num2 = NumberList[1];
		int setUpper, setLower;
		if (num1 <= num2) {									// first test case for a base number
			setLower = num1;
		} else {
			setLower = num2;								// first lower check
		}
		if (num1 >= num2) {
			setUpper = num1;								// first upper check
		} else {
			setUpper = num2;
		}
		for (int i = 2; i < NumberList.length; i++) {		// filter out lowest number					
			int num3 = NumberList[i];
			if (num3 <= setLower) {
				setLower = num3;
			}
		}		
		for (int i = 2; i < NumberList.length; i++) {		// filter out highest number
			int num4 = NumberList[i];
			if (num4 >= setUpper) {
				setUpper = num4;
			}			
		}
		for (int i = 0; i < NumberList.length; i++) {		// find the sum
			int num5 = NumberList[i];
			sum = sum + num5;
		}
		sum = sum - setLower - setUpper;
		int div = NumberList.length - 2;		
		int avg = sum / div;								// calculate average
		return avg;
	}
	
	// Given an array of ints, return true if every 2 that 
	//   appears in the array is next to another 2. 

	// LookForTwoTwo({4, 2, 2, 3}) → true
	// LookForTwoTwo({2, 2, 4}) → true
	// LookForTwoTwo({2, 2, 4, 2}) → false
	
	/**
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		true if every 2 is adjacent to another 2
	 * 		otherwise false
	 */
	static boolean LookForTwoTwo(int[] NumberList) 
	{
		boolean twotwo = false;
		for (int i = 1; i < NumberList.length; i++) {
			int num1 = NumberList[i-1];				
			int num2 = NumberList[i];					// count index back one
			if (num2 == 2) {
				if (num1 == 2 && num2 == 2) {			// if two 2's are together -> true
					twotwo = true;			
				} else if (num1 == 2 && num2 != 2) {	// double check outcome
					twotwo = false;					
				} else if (num1 != 2 && num2 == 2) {	// double check outcome
					twotwo = false;
				}
			}		
		}
		return twotwo;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		
	}
}
