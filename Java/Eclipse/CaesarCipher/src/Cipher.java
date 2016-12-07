public class Cipher 
{
	static double[] table = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7,
			7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};
	
    static int let2nat (char c)
    {
    	if (c == 'a') return 0; 
    	if (c == 'b') return 1;
    	if (c == 'c') return 2;
    	if (c == 'd') return 3;
    	if (c == 'e') return 4;
    	if (c == 'f') return 5;
    	if (c == 'g') return 6;
    	if (c == 'h') return 7;
    	if (c == 'i') return 8;
    	if (c == 'j') return 9;
    	if (c == 'k') return 10;
    	if (c == 'l') return 11;
    	if (c == 'm') return 12;
    	if (c == 'n') return 13;
    	if (c == 'o') return 14;
    	if (c == 'p') return 15;
    	if (c == 'q') return 16;
    	if (c == 'r') return 17;
    	if (c == 's') return 18;
    	if (c == 't') return 19;
    	if (c == 'u') return 20;
    	if (c == 'v') return 21;
    	if (c == 'w') return 22;
    	if (c == 'x') return 23;
    	if (c == 'y') return 24;
    	if (c == 'z') return 25;    
    	return -1;
    }
    
    static char nat2let (int code)
    {
    	if (code == 0) return 'a'; 
    	if (code == 1) return 'b';
    	if (code == 2) return 'c';
    	if (code == 3) return 'd';
    	if (code == 4) return 'e';
    	if (code == 5) return 'f';
    	if (code == 6) return 'g';
    	if (code == 7) return 'h';
    	if (code == 8) return 'i';
    	if (code == 9) return 'j';
    	if (code == 10) return 'k';
    	if (code == 11) return 'l';
    	if (code == 12) return 'm';
    	if (code == 13) return 'n';
    	if (code == 14) return 'o';
    	if (code == 15) return 'p';
    	if (code == 16) return 'q';
    	if (code == 17) return 'r';
    	if (code == 18) return 's';
    	if (code == 19) return 't';
    	if (code == 20) return 'u';
    	if (code == 21) return 'v';
    	if (code == 22) return 'w';
    	if (code == 23) return 'x';
    	if (code == 24) return 'y';
    	if (code == 25) return 'z';  
    	return ' ';
    }
    
    static char shift (int shftAmt, char c)
    {    	
    	int num = let2nat(c);
    	if (num == -1)
    	{
    		return c;
    	}    	
    	
    	int result = num + shftAmt;
    	while (result < 0)
    	{
    		result = result + 26;
    	}
    	while (result > 25)
    	{
    		result = result  - 26;
    	}
    	return nat2let(result);
    }
    
    static String encode(int shftAmt, String str)
    {
    	char string[] = str.toCharArray();
    	int i = 0;
    	while (i < str.length())
    	{    		
    		string[i] = shift(shftAmt, string[i]);
    		i++;
    	}
    	
    	String newstr = new String(string);
    	return newstr;
    }
    
    static String decode (int shftAmt, String str)
    {
    	char string[] = str.toCharArray();
    	int i = 0;
    	while (i < str.length())
    	{
    		string[i] = shift(-shftAmt, string[i]);
    		i++;    		
    	}    	
    	String newstr = new String(string);
    	return newstr;
    }
    
    static int lowers (String str)
    {
    	char string[] = str.toCharArray();
    	int count = 0;
    	int i = 0;    	
    	while (i < str.length())
    	{
    		int num = let2nat(string[i]);
    		if (num >= 0)
    		{
    			count++;
    		}
    		i++;
    	}
    	return count;
    }
    
    static int count (char c, String str)
    {
    	char string[] = str.toCharArray();
    	int num = let2nat(c);
    	int count = 0;
    	int i = 0;
    	while (i < str.length())
    	{
    		if (num == let2nat(string[i]))
    		{
    			count++;
    		}
    		i++;
    	}
    	return count;
    }
    
    static double percent (int num1, int num2)
    {	
    	double perc = (double)num1 / (double)num2;
    	return perc * 100;
    }
    
    static double[] freqs (String str)
    {
    	char string[] = str.toCharArray();
    	double fnums[] = new double[str.length()];
    	int lows = lowers(str);
    	int i = 0;
    	while (i < str.length())
    	{    		
    		int count = count(string[i], str);
    		double perc = percent(count, lows);
    		fnums[i] = perc;
    		i++;
    	}
    	return fnums;
    }
    
    static double[] rotate (int n, double[] list)
    {
    	int i = table.length - 1;
    	double temp[] = new double[table.length];
    	while (i >= 0)
    	{
    		int spot = i - n;
    		while (spot < 0)
    		{    			
    			spot = spot + 26;    			
    		}
    		temp[spot] = list[i];
    		i--;
    	}
    	return temp;
    }

    static double chisqr (double[] os)
    {
    	int i = 0;
    	double sum = 0;
    	while (i < table.length)
    	{
    		double test = ((os[i] - table[i]) * (os[i] - table[i])) / table[i];
    		sum = sum + test;
    		i++;
    	}
    	return sum;
    }

    static int position (double a, double[] list)
    {
    	int i = 0;
    	int pos = 0;
    	while (i < table.length)
    	{
    		if(a == list[i])
    		{
    			pos = i;
    			i = table.length;
    		}
    		i++;
    	}
    	return pos;
    }
    
    static String crack(String str)
    {
    	double freq[] = freqs(str);
    	double chi[] = new double[table.length];
    	double max = 9999999;
    	int  i = 0;
    	while (i < table.length)
    	{
    		double rot[] = rotate(i, freq);
        	chi[i] = chisqr(rot);  
        	double test = chi[i];
           	if (test < max)
           	{     
           		max = test;
           	}
        	i++;           
    	}
    	int pos = position(max, chi);
    	String pharse = decode(pos - 14, str);    	
    	return pharse;    	
    }
    
    public static void main(String[] args) 
    {
    	System.out.println("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!");
    	System.out.println(crack("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!"));
    }
}