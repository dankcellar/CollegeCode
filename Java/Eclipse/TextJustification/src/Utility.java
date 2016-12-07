import java.util.ArrayList;
import java.util.Arrays;

public class Utility
{

	static int GetBadness( String strText )
	{
		int count = 0;
		for( int i=0; i<strText.length(); i++ )
		{
			if( strText.charAt(i)==' ' ) count++;
		}
		return( count * count * count );
	}

	static String WordListToLineString( ArrayList<String> Line, int charWidth )
	{
		String strLine = "";
		
		for( int i=0; i<Line.size(); i++ )
		{
			if( strLine.length() > 0 )
			{
				strLine += " ";
			}
			strLine += Line.get( i );
		}
		
		return( strLine );
	}
	
	static String WordListToJustifiedLineString( ArrayList<String> Line, int charWidth )
	{
		int characters = 0;
		for( int i=0; i<Line.size(); i++ )
		{
			characters += Line.get(i).length();
		}
		
		double spacesNeeded = charWidth - characters;
		double averageNumSpaces = (double)spacesNeeded / (double)(Line.size()-1);
		double spacesToThisPoint = 0;
		
		String strLine = "";
		for( int i=0; i<Line.size(); i++ )
		{
			strLine += Line.get(i);
			if( i < Line.size() - 1 )
			{
				double expectedSpaces = Math.ceil( averageNumSpaces * (double)( i + 1 ) );
				int spaces = (int)( expectedSpaces - spacesToThisPoint );

				if( i == Line.size() - 2 )
				{
					spaces = (int)Math.ceil( spacesNeeded );
				}
				strLine += createRepeatingString( ' ', spaces );
				
				spacesToThisPoint += averageNumSpaces;
				spacesNeeded -= averageNumSpaces;
			}
		}
		
		return( strLine );
	}

	static String createRepeatingString( char c, int count )
	{
		char[] chars = new char[count];
		Arrays.fill(chars, c);
		String s = new String(chars);	
		return( s );
	}

	static String[] SplitTextIntoWordList( String strText )
	{
		String[] Ret;
		
		Ret = strText.trim().split(" ");
		
		return( Ret );
	}

	public static int min(int... numbers) 
	{
		int min = Integer.MAX_VALUE;
		for( int number : numbers )
		{
			if( number < min ) min = number;
		}
		return( min );
	}
}
