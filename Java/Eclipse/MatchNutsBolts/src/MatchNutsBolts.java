////////////////////////////
// Eric Downey 
// COP 3503C - Section 12
// MatchNutsBolts.java
// 01/20/2015

import java.util.*;

public class MatchNutsBolts extends NutsBolts {

	final static int AMN = 100;
	
	public static void main(String[] args) {
			
		Random rn = new Random();
		Bolt[] bolts = makeRandomBolts(rn, AMN);
		Nut[] nuts = makeMatchingNuts(rn, bolts);
		Bolt[] newBolts = new Bolt[AMN];
		Nut[] newNuts = new Nut[AMN];
			 
		int j = 0;
		for(int i = j; i < AMN; i++){
			for(j = 0; j < AMN; j++){
				int temp1 = nuts[i].compareTo(bolts[j]);
				int temp2 = bolts[j].compareTo(nuts[i]);
				if(temp1 == 0 && temp2 == 0) {
					newBolts[i] = bolts[j];
					newNuts[i] = nuts[i];					
				}
			}					
		}					
		boolean matched = correctFit(newNuts, newBolts);
		System.out.println(matched);		
	}
	
}