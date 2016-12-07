package knapsack1;

public class knapsack1 
{
	
	static int[] weights = { 2, 3, 4, 5 };
	static int[] values = { 3, 4, 5, 6 };
	static int maxweight = 5;
	
	static int DoKnapsack( int index, int weight )
	{
		if( index < 0 ) return( 0 );
		
		if( weights[index] > weight ) return( DoKnapsack( index - 1, weight ) );
		
		return Math.max( DoKnapsack( index-1, weight ), DoKnapsack( index-1, weight-weights[index])+values[index] );
	}

	public static void main(String[] args) 
	{
		System.out.println( DoKnapsack( values.length-1, maxweight ) );
	}

}
