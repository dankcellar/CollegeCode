

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek5 
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
		return( "Downey,Eric,e3118280");
	}
	
	//	Problem #1
	//	Given an array of ints, is it possible to choose a group 
	//	of some of the ints, such that the group sums to the given 
	//	target? This is a classic backtracking recursion problem. 
	//	Once you understand the recursive backtracking strategy in 
	//	this problem, you can use the same pattern for many problems to
	//	search a space of choices. Rather than looking at the whole array, 
	//	our convention is to consider the part of the array starting at 
	//	index start and continuing to the end of the array. The caller 
	//	can specify the whole array simply by passing start as 0. No loops 
	//	are needed -- the recursive calls progress down the array. 

	//	groupSumsTarget(0, {2, 4, 8}, 10) → true
	//	groupSumsTarget(0, {2, 4, 8}, 14) → true
	//	groupSumsTarget(0, {2, 4, 8}, 9) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget(int start, int[] nums, int target) 
	{
		if (start >= nums.length) return (target == 0);
		if (groupSumsTarget(start + 1, nums, target - nums[start])) return true;
		if (groupSumsTarget(start + 1, nums, target)) return true;
		return false;
	}

	//	Problem #2
	//	Given an array of ints, is it possible to choose a group of 
	//	some of the ints, beginning at the start index, such that 
	//	the group sums to the given target? However, with the additional 
	//	constraint that all 6's must be chosen. (No loops needed.)

	//	groupSumsTarget6(0, {5, 6, 2}, 8) → true
	//	groupSumsTarget6(0, {5, 6, 2}, 9) → false
	//	groupSumsTarget6(0, {5, 6, 2}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including all 6's in the group
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget6(int start, int[] nums, int target) 
	{
		if(start >= nums.length)
			return (target == 0);
			    
		int diff = target - (6 * count6(start, nums));
		if(groupSumsTarget(start, nums, diff))
			return true;
		return false;	   
	}
		public static int count6(int start, int [] nums)
		{
			int count = 0;
			if(nums[start] == 6)
			{
				count++;
				nums[start] = 0;
			}	
			if(start + 1 < nums.length)
				count += count6(start + 1, nums);
			   	return count;
		}



	//	Problem #3
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with this 
	//	additional constraint: If a value in the array is chosen to be in 
	//	the group, the value immediately following it in the array 
	//	must not be chosen. (No loops needed.)

	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 12) → true
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 14) → false
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetNoAdj(int start, int[] nums, int target) 
	{
	  if (start >= nums.length) return (target == 0);
	  if (groupSumsTargetNoAdj(start + 2, nums, target - nums[start]))
		  return true;
	  if (groupSumsTargetNoAdj(start + 1, nums, target)) 
		  return true;
	  return false;
	}	

	//	Problem #4
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with these 
	//	additional constraints: all multiples of 5 in the array must be 
	//	included in the group. If the value immediately following a multiple 
	//	of 5 is 1, it must not be chosen. (No loops needed.) 

	//	groupSumsTarget5(0, {2, 5, 10, 4}, 19) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 17) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 12) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget5(int start, int[] nums, int target) 
	{
		if(start >= nums.length)
			return (target == 0);
			    
		int diff = target - (countMult5(start, nums));
		if(groupSumsTarget(start, nums, diff))
			return true;
		return false;			   
	}
	
		public static int countMult5(int start, int [] nums)
		{
			int sum = 0;
			if(nums[start] % 5 == 0)
			{
				sum += nums[start];
				nums[start] = 0;
				if(start + 1 < nums.length)
					if(nums[start + 1] == 1)
						nums[start + 1] = 0;
			}	
			if(start + 1 < nums.length)
				sum += countMult5(start + 1, nums);
			return sum;
		}
	
	
	//	Problem #5
	//	Given an array of ints, is it possible to choose a group of some of 
	//	the ints, such that the group sums to the given target, with this 
	//	additional constraint: if there are numbers in the array that are adjacent 
	//	and the identical value, they must either all be chosen, or none of 
	//	them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all 
	//	three 2's in the middle must be chosen or not, all as a group. (one loop 
	//	can be used to find the extent of the identical values). 

	//	groupSumsTargetClump(0, {2, 4, 8}, 10) → true
	//	groupSumsTargetClump(0, {1, 2, 4, 8, 1}, 14) → true
	//	groupSumsTargetClump(0, {2, 4, 4, 8}, 14) → false	
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetClump(int start, int[] nums, int target) 
	{
		if(start>=nums.length){
			return target==0;}
			 
			if(start!=0 )
			{ 
				int count=0;
				int i=start;
				while(i<nums.length && nums[start]==nums[i])
				{
					count++;
					i++;
				}
				if(groupSumsTargetClump(i, nums, target-((count)*nums[start]))) return true;
				if(groupSumsTargetClump(i, nums, target)) return true;
				return false;
			} else {
			 
			if(groupSumsTargetClump(start+1, nums, target-nums[start])) return true;
			if(groupSumsTargetClump(start+1, nums, target)) return true;
			 
			return false;
			}		
	}
	
	//	Problem #6
	//	Given an array of ints, is it possible to divide the ints into two 
	//	groups, so that the sums of the two groups are the same. Every int must 
	//	be in one group or the other. Write a recursive helper method that takes 
	//	whatever arguments you like, and make the initial call to your recursive 
	//	helper from splitArray(). (No loops needed.)    

	//	divideArray({2, 2}) → true
	//	divideArray({2, 3}) → false
	//	divideArray({5, 2, 3}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divideArray(int[] nums) 
	{
		if(nums.length == 1)
			return false;
		if(nums.length == 0)
			return true;
		int total = total(nums , nums.length);
		if(total % 2 == 0)
		{
			if(groupSum(0, nums.length, nums, total/2))
				return true;
		}
		return false;	
	}
			public static int total(int [] nums, int n)
			{
				if (n == 1)
					return nums[0];
			    else
			    	return total(nums, n-1) + nums[n-1];
			}
			public static boolean groupSum(int start, int end, int[] nums, int target) {
				if (start >= end) 
					if (target == 0)
						return true;
					else 
						return false;
				
				if (groupSum(start + 1, end, nums, target - nums[start])) 
					return true;
				if (groupSum(start + 1, end, nums, target)) 
					return true;
				return false;
			  }
	
	
	//	Problem #7
	//	Given an array of ints, is it possible to divide the ints into two groups, 
	//	so that the sum of one group is a multiple of 10, and the sum of the 
	//	other group is odd. Every int must be in one group or the other. Write 
	//	a recursive helper method that takes whatever arguments you like, and 
	//	make the initial call to your recursive helper from 
	//	splitOdd10(). (No loops needed.)  

	//	oddDivide10({5, 5, 5}) → true
	//	oddDivide10({5, 5, 6}) → false
	//	oddDivide10({5, 5, 6, 1}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met 
	 */
	static boolean oddDivide10(int[] nums) 
	{
		if(nums.length == 1)
			if(nums[0] % 2 != 0 || nums[0] % 10 == 0)
				return true;
			else return false;
		if(nums.length == 0)
			return false;
		int total = total(nums, nums.length);
		int tens = total / 10;
		if(determineValidity(total, tens, nums))
			return true;
		return false;	   
	}			
			public static int totalSum(int [] nums, int n)
			{
			   if (n == 1)
			        return nums[0];
			    else
			        return total(nums, n-1) + nums[n-1];
			}
			
			public static boolean determineValidity(int total, int tens, int [] nums)
			{
			   if(tens <= 0)
			      return false;
			   if(groupSum(0, nums.length, nums, tens * 10) && (total - tens*10) % 2 != 0 )
			      return true;
			   else tens--;
			   return determineValidity(total, tens, nums);
			}
	
	//	Problem #8
	//	Given an array of ints, is it possible to divide the ints into 
	//	two groups, so that the sum of the two groups is the same, with 
	//	these constraints: all the values that are multiple of 5 must 
	//	be in one group, and all the values that are a multiple of 3 
	//	(and not a multiple of 5) must be in the other. (No loops needed.)  

	//	divide53({1,1}) → true
	//	divide53({1, 1, 1}) → false
	//	divide53({2, 4, 2}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divide53(int[] nums) 
	{
		if(nums.length == 0)
			return false;
		else
			return groupSum53(0, nums, 0, 0);
	}
			 
		public static boolean groupSum53(int i, int[] nums, int sum5, int sum3)
		{
			if(i == nums.length)
				return (sum5 == sum3);
			 
			if(nums[i]%5==0) {
				return groupSum53(i+1, nums, sum5 + nums[i], sum3);
			} else if(nums[i]%3==0) {
				return groupSum53(i+1, nums, sum5, sum3 + nums[i]);
			} else
				return groupSum53( i+1, nums, sum5 + nums[i], sum3) || groupSum53(i+1, nums, sum5, sum3 + nums[i]);
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