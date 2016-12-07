
public class MakeUnique 
{


	static public String eliminated( String original )
	{
		return( eliminated( original, 0 ) );
	}
	
	static String eliminated( String original, int index )
	{
		
		if( Utils.StringIsOK( original ) ) return( original );
		if( index >= original.length() ) return( "" );

		String one = eliminated( Utils.setChar( original, index, '.' ), index + 1 );
		String two = eliminated( original, index + 1 );
		
		if( one.length() == 0 ) return( two );
		if( two.length() == 0 ) return( one );
		
		return( Utils.most( one, two ) );
	}
	
	static String test = "AAAABBCDEF";
	static public void main( String[] args )
	{
		System.out.println( "Input:" + test + ", Output:" + eliminated( test ) );
	}
	
}
