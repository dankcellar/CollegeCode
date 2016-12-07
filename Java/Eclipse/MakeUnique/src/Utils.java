
public class Utils
{

	static public boolean StringIsOK( String test )
	{
		int[] Count = new int[26];
		char c;
		
		for( int i=0; i<test.length(); i++ )
		{
			c = test.charAt(i);
			if( c != '.' ) 
			{
				Count[c-'A']++;
				if( Count[c-'A'] > 1 ) return( false );
			}
		}
		
		c = 0;
		for( int i=0; i<test.length(); i++ )
		{
			if( test.charAt(i) != '.' )
			{
				if( test.charAt(i) < c ) return( false );
				c = test.charAt(i);
			}
		}

		return( true );
	}
	
	static String setChar( String original, int index, char c )
	{
		String strNew = original.substring( 0, index );
		strNew += Character.toString( c );
		strNew += original.substring( index + 1 );
		return( strNew );
	}

	static int count( String test )
	{
		int count = 0;
		
		for( int i=0; i<test.length(); i++ )
		{
			if( test.charAt( i ) != '.' ) count++;
		}
		
		return( count );
	}
	
	static String most( String one, String two )
	{
		int _one = count( one );
		int _two = count( two );
		if( _one > _two ) return( one );
		else if( _one < _two ) return( two );
		
		int lex = one.compareTo( two );
		if( lex < 0 ) return( one );
		return( two );
	}
	
}
