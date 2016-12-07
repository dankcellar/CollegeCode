import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class DuelingPhilosophers 
{
	private static Scanner in;

	public static void main(String[] args) throws Exception 
	{
		in = new Scanner(new File("input.txt"));

		int n = in.nextInt();
		int m = in.nextInt();

		while (n != 0) 
		{
			vertex[] ver = new vertex[n];
			for (int i=0; i<n; i++)
				ver[i] = new vertex();

			for (int i=0; i<m; i++) 
			{
				int prev = in.nextInt();
				int next = in.nextInt();
				ver[prev-1].out.add(next-1);
				ver[next-1].in.add(prev-1);
			}

			topSort prob = new topSort(ver);
			System.out.println(prob.checkSorts());
			n = in.nextInt();
			m = in.nextInt();
		}
	}
}

class vertex 
{
	public HashSet<Integer> in;
	public HashSet<Integer> out;
	
	public vertex() 
	{
		in = new HashSet<Integer>();
		out = new HashSet<Integer>();
	}
}

class topSort 
{
	private int[] ordering;
	private boolean[] used;
	private int n;
	private vertex[] vertex;

	public topSort(vertex[] list) 
	{
		vertex = list;
		n = list.length;
		used = new boolean[n];
		ordering = new int[n];
	}


	public int checkSorts() 
	{
		int index = 0;
		int value = 1;
		
		while (index < n) 
		{
			int count = 0;
			int[] remove = new int[n];
			for (int i=0; i<n; i++) 
			{
				if (!used[i] && vertex[i].in.size() == 0)
					remove[count++] = i;
			}
			
			if (count == 0)
				return 0;
			if (count > 1) 
				value = 2;

			for (int i=0; i<count; i++) 
			{
				ordering[index] = remove[i];
				index++;
				used[remove[i]] = true;

				for (Integer e: vertex[remove[i]].out)
					vertex[e].in.remove(remove[i]);
			}
		}
		
		return value;
	}
}
