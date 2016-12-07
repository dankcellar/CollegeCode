
public class MakeChange
{
	static int[] Denominations = { 25, 10, 5, 1 };
//	static int[] Denominations = { 10, 6, 1 };
	
	static int NaiveChange( int total )
	{
		int count = 0;
		
		for( int i=0; i<Denominations.length; i++ )
		{
			while( total >= Denominations[i] )
			{
				total -= Denominations[i];
				count++;
			}
		}

		return( count );
	}

	static int RecursiveChange( int total, int index )
	{
		if( total <= 0 ) return( 0 );
		if( index >= Denominations.length ) return( 0 );
		
		int with = RecursiveChange( total - Denominations[index], index ) + 1;
		int without = RecursiveChange( total, index + 1 );
		
		if( with == 0 ) return( without );
		if( without == 0 ) return( with );
		
		return( Math.min( with,  without ) );
	}
	
	static int IterativeChange( int total )
	{
		if( total <= 0 ) return( 0 );
		
		int[] CoinCounts = new int[total+1];
		
		for( int s=1; s<=total; s++ )
		{
			CoinCounts[s] = Integer.MAX_VALUE;
			
			for( int j=0; j<Denominations.length; j++ )
			{
				if( Denominations[j] <= s &&
					CoinCounts[s-Denominations[j]] + 1 < CoinCounts[s] )
				{
					CoinCounts[s] = CoinCounts[s-Denominations[j]] + 1;
				}
			}
		}
		return( CoinCounts[total] );
	}
	
	public static void main(String[] args)
	{
		System.out.println( "For 35, the number of coins required is " + NaiveChange( 35 ));
//		System.out.println( "For 12, the number of coins required is " + NaiveChange( 12 ));
	}
}
