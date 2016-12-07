import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class CandyStore
{
    public Scanner sc;
    public PrintStream ps;
        
    public void doit() throws Exception
    {
    	sc = new Scanner(System.in);
    	ps = System.out;
        
        int best[] = new int[10001];
        int fattest = 0;
        
        int calories[] = new int[5000];
        int cost[] = new int[5000];
                        
        while(true)
        {
        	int n = sc.nextInt();
            int m = (int) Math.round(sc.nextDouble()*100);
            if (n==0) break;              
            for( int i=0; i<n; i++ )
            {
                calories[i] = sc.nextInt();
                cost[i] = (int) Math.round(sc.nextDouble()*100);
            }
            
            Arrays.fill(best, 0);
            fattest = 0;
            int lastbest = -1;            
            for (int i=0; i<m; i++) if (best[i]>lastbest)
            {
                lastbest = best[i];
                for (int j=0; j<n; j++) 
                {
                    int target = i+cost[j];
                    int fatness = best[i]+calories[j];
                    if (target<=m && fatness>best[target])
                    {
                        best[target] = fatness;
                        if (fatness>fattest) fattest = fatness;
                    }
                }
            }            
            ps.println(fattest);
        }
    }
    
    public static void main (String[] args) throws Exception
    {
        new CandyStore().doit();
    }
}