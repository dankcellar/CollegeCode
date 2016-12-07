
public class DP1
{

	static int k = 12;
	static int[][] mat = 	{ 	{1, 2, 3},
								{4, 6, 5},
								{3, 2, 1} };
	
	static int pathCount( int m, int n, int k )
	{
		if( m < 0 || n < 0 ) return 0;
		if( m == 0 && n == 0 )
		{
			if( mat[0][0] == k ) return 1;
			return 0;
		}
		
		return( pathCount( m - 1, n, k - mat[m][n] ) + 
				pathCount(m, n - 1, k - mat[m][n]) );
	}
	
	public static void main(String[] args)
	{
		System.out.println( "pathCount="+pathCount( 2, 2, k ) );
	}

}
