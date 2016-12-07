
public class DP4
{

	static int getMaxWeight( String str )
	{
	    return getMaxWeight( str, 0, str.length() );
	}	
	
	static int getMaxWeight( String str, int i, int n )
	{
	    if (i >= n) return 0;
	 
	    // Don't make pair, so weight gained is 1
	    int ans = 1 + getMaxWeight( str, i+1, n );
	 
	    // If we can make pair
	    if (i+1 < n)
	    {
	      // If elements are dissimilar, weight gained is 4
	      if ( str.charAt(i) != str.charAt(i+1))
	      {
	    	  ans = Math.max(4 + getMaxWeight(str, i+2, n ), ans );
	      }
	      else ans = Math.max(3 + getMaxWeight(str, i+2, n ), ans );
	    }
	 
	    // save and return maximum of above cases
	    return ans;
	}
	 
	public static void main(String[] args)
	{
		String str = "AAAAABB";
		System.out.println( "getMaxWeight="+getMaxWeight(str));
	}

}
