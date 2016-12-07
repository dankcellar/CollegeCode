/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */

package tempconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class TempConverter {

    public static void main(String[] args) throws FileNotFoundException 
    {                         
        File file = new File (args[0]);
        
/*        try {            
            BufferedReader br = new BufferedReader (new FileReader (file));            
            br.close();                                    
        } catch (Exception e) {
            System.out.println("File not found!");
        }
*/            
        Scanner input = new Scanner (new FileInputStream (file));
        DecimalFormat df = new DecimalFormat("###0.00");
            
        int count = 0;
        float temper = 0;            
        float convertF, convertC;
        
        System.out.println("Temperature Covnverter by Eric Downey\n");
        System.out.println("Input\tType\tConversion");
        System.out.println("--------------------------");
        
        while (input.hasNext()) {                                
            switch (count) {                                               
                    
                case 0 : 
                    String s = input.next();
                    temper = Float.parseFloat (s);
                    String fmt = df.format(temper);
                    System.out.printf("%6s", fmt);
                    count = 1;
                    break;
                
                case 1 :
                    s = input.next();
                    System.out.print("\t " + s);
                    if (s.startsWith("C")) {
                        convertF = ((9 * temper) / 5) + 32;
                        fmt = df.format(convertF);
                        System.out.printf("\t %6s F\n", fmt);
                    } else {
                        convertC = ((temper - 32) * 5) / 9;
                        fmt = df.format(convertC);
                        System.out.printf("\t %6s C\n", fmt );
                    } 
                    count = 0;
                    break;
                        
                default :
                    break;
            }
        }                                 
    }
}
