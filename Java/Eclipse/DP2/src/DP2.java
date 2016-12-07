
public class DP2
{

	static int minSizeRec(int arr[], int n, int k)
	{
		return minSizeRec(arr, 0, n-1, k);
	}
	
	static int minSizeRec(int[] arr, int low, int high, int k )
	{
		if ( (high-low + 1) < 3)  return high-low +1;
	 
		int res = 1 + minSizeRec(arr, low+1, high, k);
	 
		for (int i = low+1; i<=high-1; i++)
		{
			for (int j = i+1; j <= high; j++ )
			{
				if (	arr[i] == (arr[low] + k) &&
						arr[j] == (arr[low] + 2*k) &&
						minSizeRec(arr, low+1, i-1, k) == 0 &&
						minSizeRec(arr, i+1, j-1, k) == 0)
				{
					res = Math.min(res, minSizeRec(arr, j+1, high, k));
				}
			}
		}
	 
		return (res);
	}
	 
	public static void main(String[] args)
	{
		int[] arr = {2, 3, 4, 5, 6, 4};
		System.out.println( "minSizeRec="+minSizeRec(arr, arr.length, 1));
	}
	
}
