///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2Week2 
{
	
	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
	/**
	 * Returns the last name, first name, and PID of the student.
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Downey , Eric , e3118280");
	}
	
	//	Problem #1
	// Directions: Return true if the array contains, somewhere,
	// three increasing consecutive numbers like ....4, 5, 6,... or
	// 23, 24, 25.

	//	FindThreeIncreasingNumbers({1, 4, 5, 6, 2}) → true
	//	FindThreeIncreasingNumbers({1, 2, 3}) → true
	//	FindThreeIncreasingNumbers({1, 2, 4}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns true if there are three increasing consecutive numbers
	 * 		returns false if there are not three increasing consecutive numbers
	 */
	static boolean FindThreeIncreasingNumbers(int[] NumberList)
	{
		boolean check = false;
		for (int i = 2; i < NumberList.length; i++){
			int num1 = NumberList[i];
			int num2 = NumberList[i-1];								// Get a series of numbers
			int num3 = NumberList[i-2];
			if(num1 - 1 == num2 && num3 + 1 == num2){				// Check if they are in order
				check = true;
				continue;											// Set check to true		
			}
		}
		return check;												// Return new array
	}

	//	Problem #2
	//	For each multiple of 10 in the given array, change all the values 
	//	following it to be that multiple of 10, until encountering another 
	//	multiple of 10. So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.

	//	multiplesOfTen({2, 10, 3, 4, 20, 5}) → {2, 10, 10, 10, 20, 20}
	//	multiplesOfTen({10, 1, 20, 2}) → {10, 10, 20, 20}
	//	multiplesOfTen({10, 1, 9, 20}) → {10, 10, 10, 20}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return NumberList
	 * 		int[] list of the same numbers changed to multiples of
	 * 		ten as they are encountered.
	 */
	static int[] multiplesOfTen(int[] NumberList) 
	{
		int[] check = new int[NumberList.length];
		for (int i = 0; i < NumberList.length; i++){
			int num = NumberList[i];
			if(num % 10 == 0 && num != 0){							// Check if multiple of 10
				check[i] = num;
				for (int j = i; j < NumberList.length; j++){		
					if(NumberList[j] % 10 != 0){					// Not a multiple of 10 find where to stop
						check[j] = num;				
					}
				}
			}
		}
		
		for (int k = 0; k <NumberList.length; k++){
			if(check[k] == 0 ){
				check[k] = NumberList[k];							// Set starting numbers correctly 
			}
		}
		return check; 												// Return new array
	}

	//	Problem #3
	//	We'll say that an element in an array is "alone" if there are 
	//	values before and after it, and those values are different 
	//	from it. Return a version of the given array where every instance 
	//	of the given value which is alone is replaced by whichever 
	//	value to its left or right is larger.

	//	CheckForAloneNumbers({1, 2, 3}, 2) → {1, 3, 3}
	//	CheckForAloneNumbers({1, 2, 3, 2, 5, 2}, 2) → {1, 3, 3, 5, 5, 2}
	//	CheckForAloneNumbers({3, 4}, 3) → {3, 4}
	
	/**
	 * 
	 * @param NumberList, changingNumber
	 * 		int[] list containing some numbers.
	 * 		int value of the number that should change in the array.
	 * 
	 * @return NumberList
	 * 		int[] list of numbers where every occurrence of changingNumber
	 * 		has been replaced by the larger of its two neighbors.
	 */
	static int[] CheckForAloneNumbers(int[] NumberList, int changingNumber) 
	{
		int[] check = new int[NumberList.length];
		check = NumberList;
		for (int i = 0; i < NumberList.length; i++){
			int num = NumberList[i];
			if(changingNumber == num) {							// Find number to change in array
				for (int j = 0; j < NumberList.length; j++){
					if(i == 0) {
						if (num <= NumberList[i+1]){ 			// Check for greater neighbor to right
							check[i] = NumberList[i+1];			// if number is the first index in the array
							continue;
						}
					} else if (i == NumberList.length){			// Check for greater number to left
						 if(num <= NumberList[i-1]){			// if number is the last index in the array
							 check[i] = NumberList[i-1];
							 continue;
						 }
					} else if(num <= NumberList[i-1]){			// Check great number to left and right
						check[i] = NumberList[i-1];				// if number is not in the first or last 
					} else if(num <= NumberList[i+1]){ 			// index of the original array
						check[i] = NumberList[i+1];
					}
				} 					
			}			
		}		
		return check;											// Return new array
	}	

	//	Problem #4
	//	Return a version of the given array where each zero value in 
	//	the array is replaced by the largest odd value to the right 
	//	of the zero in the array. If there is no odd value to the 
	//	right of the zero, leave the zero as a zero. 

	//	ReplaceZerosWithLargestOdd({0, 5, 0, 3}) → {5, 5, 3, 3}
	//	ReplaceZerosWithLargestOdd({0, 4, 0, 3}) → {3, 4, 3, 3}
	//	ReplaceZerosWithLargestOdd({0, 1, 0}) → {1, 1, 0}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return NumberList
	 * 		int[] list containing the numbers where the zeros have been
	 * 		replaced with the largest odd number to the right of them.
	 */
	public static int[] ReplaceZerosWithLargestOdd(int[] NumberList) 
	{
		int[] check = new int[NumberList.length];
		for(int i = 0; i < NumberList.length; i++){							
			int num = NumberList[i];										
			check[i] = num;													// Store number in new array
			if(num == 0){													// Check for 0's in array
				for(int j = i; j < NumberList.length; j++){
					if(NumberList[j] >= num && NumberList[j] % 2 != 0){		// Find largest odd number					
						num = NumberList[j];								// to the right of the 0
						check[i] = num;
					}			
				}	
			}					
		}			
		return check;														// Return new array
	}
	
	//	Problem #5
	//	Given start and end numbers, return a new array containing 
	//	the sequence of integers from start up to but not including end, 
	//	so start=5 and end=10 yields {5, 6, 7, 8, 9}. The end number 
	//	will be greater or equal to the start number. 
	//	Note that a length-0 array is valid. 

	//	CreateIncreasingArray(5, 10) → {5, 6, 7, 8, 9}
	//	CreateIncreasingArray(11, 18) → {11, 12, 13, 14, 15, 16, 17}
	//	CreateIncreasingArray(1, 3) → {1, 2}	
	
	/**
	 * 
	 * @param start, end
	 * 		Two integers stating the start and end of the sequence.
	 * 
	 * @return NumberList
	 * 		int [] containg numbers ranging from start to end
	 * 		in order from least to greatest.
	 */
	static int[] CreateIncreasingArray(int start, int end) 
	{
		int counter = 0;
		int tempStart = start;
		while(tempStart < end){					// Get length between numbers
			tempStart++;
			counter++;
		}			
		int[] check = new int[counter];			// New array from counter
		for(int i = 0; i < check.length; i++){
			check[i] = start;					// Add in-between numbers to array
			start++;
		}
		return check;							// Return new array
	}
	
	//	Problem #6
	//	Given a non-empty array of ints, return a new array containing 
	//	the elements from the original array that come before the 
	//	first 4 in the original array. The original array will contain 
	//	at least one 4. Note that it is valid in java to create 
	//	an array of length 0.  

	//	CopyNumbersBeforeFour({1, 2, 4, 1}) → {1, 2}
	//	CopyNumbersBeforeFour({3, 1, 4}) → {3, 1}
	//	CopyNumbersBeforeFour({1, 4, 4}) → {1}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return AbridgedList
	 * 		int[] list containing all the numbers that appeared
	 * 		before the first 4 in the array.
	 */
	static int[] CopyNumbersBeforeFour(int[] NumberList) 
	{
		int counter = 0;
		for(int i = 0; NumberList[i] != 4; i++){		// Find first 4 in array
			counter++;									
			}
		int[] check = new int[counter];					// New array from numbers before 4
		for(int j = 0; j < counter; j++){
			check[j] = NumberList[j];					// Add all numbers before 4
		}		
		return check;									// Return new array
	}	
	
	//	Problem #7
	//	Return an array that contains the exact same numbers as 
	//	the given array, but rearranged so that all the zeros 
	//	are grouped at the start of the array. The order of the 
	//	non-zero numbers does not matter. So {1, 0, 0, 1} becomes 
	//	{0 ,0, 1, 1}. You may modify and return the 
	//	given array or make a new array.  

	//	MoveZerosToFront({1, 0, 0, 1}) → {0, 0, 1, 1}
	//	MoveZerosToFront({0, 1, 1, 0, 1}) → {0, 0, 1, 1, 1}
	//	MoveZerosToFront({1, 0}) → {0, 1}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return RearrangedList
	 * 		int[] list containing all the numbers from original
	 * 		list with the zeros moved to the front.
	 */
	static int[] MoveZerosToFront(int[] NumberList) 
	{
		int counter = 0;
		int[] check = new int[NumberList.length];
		for(int i = 0; i < NumberList.length; i++){			// Find amount of 0's in array
			if(NumberList[i] == 0){
				counter++;
			}
		}
		for(int j = 0; j < counter; j++){					// Add 0's to front of array
			check[j] = 0;			
		}
		for(int k = 0; k < NumberList.length; k++){			// Add non 0's to back of array
			if(NumberList[k] != 0){
				check[counter] = NumberList[k];	
				counter++;
			}
		}
		return check;										// Return new array
	}
	
	//	Problem #8
	//	Return an array that contains the exact same numbers as 
	//	the given array, but rearranged so that all the even numbers 
	//	come before all the odd numbers. Other than that, the 
	//	numbers can be in any order. You may modify and 
	//	return the given array, or make a new array.  

	//	EvenFrontOddBack({1, 0, 1, 0, 0, 1, 1}) → {0, 0, 0, 1, 1, 1, 1}
	//	EvenFrontOddBack({3, 3, 2}) → {2, 3, 3}
	//	EvenFrontOddBack({2, 2, 2}) → {2, 2, 2}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return RearrangedList
	 * 		int[] list containing all the numbers from original
	 * 		list with the even numbers in the front and the
	 * 		odd numbers in the back.
	 */
	static int[] EvenFrontOddBack(int[] NumberList) 
	{
		int holder = 0;
		int[] check = new int[NumberList.length];
		for(int i = 0; i < NumberList.length; i++){			// Add even numbers to front of array
			if(NumberList[i] % 2 == 0){
				check[holder] = NumberList[i];
				holder++;
			}
		}
		for(int j = 0; j < NumberList.length; j++){			// Add odd numbers to back of array
			if(NumberList[j] % 2 != 0){
				check[holder] = NumberList[j];
				holder++;		
			}
		}		
		return check;										// Return new array
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
	
