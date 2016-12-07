
public class EggDrop
{

	static int DoEggDrop( int eggs, int floors )
	{
		if( floors == 0 || floors == 1 ) return( floors );
		if( eggs <= 1 ) return( floors );
		
		int min = Integer.MAX_VALUE;
		
		for( int x=1; x<=floors; x++ )
		{
			int res = Math.max( DoEggDrop( eggs - 1, x - 1 ),
								DoEggDrop( eggs, floors - x ) );
			
			if( res < min ) min = res;
		}
		
		return( min + 1 );
	}
	
	public static void main(String[] args)
	{
		System.out.println( "For 2 eggs with 10 floors worst case you need at least " + DoEggDrop( 2, 10 ) + " drops");
	}
	
}
