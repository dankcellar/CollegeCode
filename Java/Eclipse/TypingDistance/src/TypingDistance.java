
public class TypingDistance
{
	
	static public int DoTypingDistance(String word1, String word2) 
	{
		if (word1.isEmpty()) return word2.length();
		if (word2.isEmpty()) return word1.length();
		if( word1.equals( word2 ) ) return 0;

		int replace = DoTypingDistance(word1.substring(1), word2.substring(1)) + Util.ReplaceCost(word1, word2, 0, 0);
		int delete = DoTypingDistance(word1.substring(1), word2) + 5;
		int insert = DoTypingDistance(word1, word2.substring(1)) + 5;

		return Util.min(replace, delete, insert);
	}
	
	static public void main(String[] args)
	{
		System.out.println( "cat --> vat is " + DoTypingDistance( "cat", "vat" ) );
		System.out.println( "dkunr --> drunk is " + DoTypingDistance( "dkunr", "drunk" ) );
	}

}
