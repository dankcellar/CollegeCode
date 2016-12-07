import java.util.Hashtable;

public class EditDistance 
{
	
	static Hashtable<String,Integer> ht = new Hashtable<String,Integer>();
	
	static public int DoBruteForceRecursiveEditDistance(String word1, String word2) 
	{
		if (word1.isEmpty()) return word2.length();
		if (word2.isEmpty()) return word1.length();
		if( word1.equals( word2 ) ) return 0;

		try
		{
			int nTestValue = ht.get( word1 + ":" + word2 );
			return( nTestValue );
		}
		catch(Exception ex){}
		
		int replace = DoBruteForceRecursiveEditDistance(word1.substring(1), word2.substring(1)) + Util.ReplaceCost(word1, word2, 0, 0);
		int delete = DoBruteForceRecursiveEditDistance(word1.substring(1), word2) + 1;
		int insert = DoBruteForceRecursiveEditDistance(word1, word2.substring(1)) + 1;

		ht.put( word1 + ":" + word2, Util.min(replace, delete, insert) );
		
		return Util.min(replace, delete, insert);
	}
	
	public static int DoEditDistance(String s, String t)
	{
		int m = s.length();
		int n = t.length();
		if( m == 0 )
		{
			return( n );
		}
		else if( n == 0 )
		{
			return( m );
		}
	    
		// Allocate a 2D array for dynamic programming
		int[][] nMemo = new int[m+1][n+1];
	    
		// Set the rows to i.
		for( int i=0; i<=m; i++ )
		{
			nMemo[i][0] = i;
		}
	    
	    // Set the columns to j.
		for( int j=0; j<=n; j++ )
		{
			nMemo[0][j] = j;
		}
	    
	    for( int j=1; j<=n; j++ )
	    {
	      for( int i=1; i<=m; i++ )
	      {
	        if( s.charAt(i-1) == t.charAt(j-1) )
	        {
	        	nMemo[i][j] = nMemo[i-1][j-1];
	        }
	        else
	        {
	        	nMemo[i][j] = min( ( nMemo[i-1][j]+1), (nMemo[i][j-1]+1), (nMemo[i-1][j-1]+1) );
	        }
	      }
	    }
	    
	    return( nMemo[m][n] );
	  }
	
	  public static int min(int a,int b,int c)
	  {
	    return( Math.min( Math.min(a,b), c) );
	  }	
	  
		public static void main(String[] args) 
		{
			
			long start = System.currentTimeMillis();
			System.out.println( "Edit distance between Leinecker and Leinecler is " + DoBruteForceRecursiveEditDistance("Leinecker", "Leinecler"));
			long end = System.currentTimeMillis();
			// Print out the time it took.
			System.out.println("Took "+(end-start) + " milliseconds" );
			
			start = System.currentTimeMillis();
			System.out.println( "Edit distance between Leinecker and Leinecler is " + DoEditDistance("Leinecker", "Leinecler"));
			end = System.currentTimeMillis();
			// Print out the time it took.
			System.out.println("Took "+(end-start) + " milliseconds" );
		}	  
	
}
