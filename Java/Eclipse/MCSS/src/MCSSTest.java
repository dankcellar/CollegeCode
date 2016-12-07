public class MCSSTest 
{

	// Optimized
	static int MCSS1(int[] List)
	{
		int nMax = 0, nSum, i = 0;
		
		// These are not necessary but can be used later 
		//   show the start and end of the MCSS.
		//int nStart = 0, nEnd = 0;
		
		// Start loop
//		for(int i=0; i<List.length; i++ )
		{

			nSum = 0;
			
			// End loop.
			for( int j=i; j<List.length; j++ )
			{
				
				// Now we get the sum between i and j.
//				nSum = 0;
//				for( int k=i; k<=j; k++ )
				{
					nSum += List[j];
				}
				
				if( nSum > nMax )
				{
					nMax = nSum;
					
					// These are not necessary but can be used later 
					//   show the start and end of the MCSS.
					//nStart = i;
					//nEnd = j;
				}
				else if( nSum < 0 )
				{
					i = j + 1;
					nSum = 0;
				}
			}
		}
		
		return( nMax );
	}
	
	// UnOptimized
	static int MCSS2(int[] List)
	{
		int nMax = 0, nSum;
		
		// These are not necessary but can be used later 
		//   show the start and end of the MCSS.
		//int nStart = 0, nEnd = 0;
		
		// Start loop
		for(int i=0; i<List.length; i++ )
		{

			// End loop.
			for( int j=i; j<List.length; j++ )
			{
				
				// Now we get the sum between i and j.
				nSum = 0;
				for( int k=i; k<=j; k++ )
				{
					nSum += List[k];
				}
				
				if( nSum > nMax )
				{
					nMax = nSum;
					
					// These are not necessary but can be used later 
					//   show the start and end of the MCSS.
					//nStart = i;
					//nEnd = j;
				}
			}
		}
		
		return( nMax );
	}
	
	static public void main(String[] args)
	{
		// These lists are used later.
		MCSSLists.GenerateRandomLists();
		
		// Start the clock.	
		long start = System.currentTimeMillis();
		
		int nMax = MCSS1( MCSSLists.ListSeven );
		System.out.println( "The max is " + nMax );
		
		long end = System.currentTimeMillis();
		
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
	}
	
}
