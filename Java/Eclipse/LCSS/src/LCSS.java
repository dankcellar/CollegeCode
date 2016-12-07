import java.util.Scanner;

public class LCSS {
	
	static Scanner in;
	static int nums1, nums2;
	static String[] words1;
	static String[] words2;
	static int grid[][];
	
	public static void main (String [] args) {
		
		in = new Scanner (System.in);
		int numSeq = in.nextInt();
		
		int loops = 0;
		while (loops < numSeq) {
		
			in = new Scanner (System.in);
			String s1 = in.nextLine();
			in = new Scanner (System.in);
			String s2 = in.nextLine();
				
			prepWords(s1, s2);				
			String[] sm = solveIt(grid, words1, words2);
			
			//printGrid(grid);
			//System.out.println();
		
			int counter = 0;
			for (int i=0; sm[i] != null; i++) {counter++;}
		
			System.out.println("LCSS Length = " + counter);
			System.out.print("LCSS = ");
			for (int j=counter; j>0; j--)
				System.out.print(sm[j-1] + " ");
			System.out.println();
			
			loops++;
			
		}
	}


    private static int wordCount(String str) {
    	
        int words;
        String [] sentence = str.split(" ");
        words = sentence.length;         
        return words;
    } 
    
    /*
    private static void printGrid (int[][] grid) {
    	
    	int count = 0;
    	for (int i=0; i<nums1; i++) {
			for ( int j=0; j<nums2; j++) {
				System.out.print(grid[i][j]);
				count++;
				if (count == 7) {
					count = 0;
					System.out.println();
				}
			}
    	}
    }
    */
    
    private static String[] solveIt (int[][] grid, String[] words1, String[] words2) {
    	
    	String sm[] = new String[nums1+nums2];
    	int i = nums1 - 1;
    	int j = nums2 - 1;
    	int k = 0;
    	
    	if (words1.length > 2 && words2.length > 2) {
    		if (grid[i-1][j-1] < grid[i-2][j-2]) { 
    			sm[k] = words2[j-1];
    			k++;
    		}
    	}

    	while (j>0 && i>0) {
    		if (grid[i][j] > grid[i-1][j-1]) { 
    			sm[k] = words2[j];
    			k++;
    			i--;
    			j--;
    		} else { 
    			j--;
    		}    		    		
    	}
    	
    	if (sm[0] == null)
    	{
    		i = 0;
    		while (i < words2.length) {
    			if (grid[0][i] == 1)
    				sm[0] = words2[i];
    			if (grid[i][0] == 1)
    				sm[0] = words2[0];
    			i++;
    		}    			
    	}
    	
    	return sm;	
    }    	
    
    public static void prepWords (String s1, String s2) {
    	
    	nums1 = wordCount(s1);
		nums2 = wordCount(s2);
		grid = new int[nums1][nums2];	
		
		words1 = s1.replace(".", "").replace(",", "").replace("?", "").replace("!","").split(" ");
		words2 = s2.replace(".", "").replace(",", "").replace("?", "").replace("!","").split(" ");
		for (int i=0; i < words1.length; i++) 
		    words1[i] = words1[i].replaceAll("[^\\w]", "");
		for (int i = 0; i < words2.length; i++) 
		    words2[i] = words2[i].replaceAll("[^\\w]", "");
				
		int match = 0;
		for ( int j=0; j<nums2; j++) {				
			if (words1[0].equals(words2[j])) {	
				match++;
				grid[0][j] = match;
			}
			grid[0][j] = match;
		}
		
		match = 0;
		for (int i=1; i<nums1; i++) {
			for (int j=0; j<nums2; j++) {
				if (grid[i-1][j] > match) 					
					match = grid[i-1][j];			
				if (words1[i].equals(words2[j])){ 

					match++;				}
				grid[i][j] = match;
			}
			match = 0;
		}
    }
    
}	
		