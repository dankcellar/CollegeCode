/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */

package jaccardindex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JaccardIndex {

    public static void main(String[] args) throws FileNotFoundException {        
        File file = new File (args[0]);
        DecimalFormat df = new DecimalFormat("0.0000");        
        try {            
            BufferedReader br = new BufferedReader (new FileReader (file));            
            br.close();                                    
        } catch (Exception e) {
            System.out.println("File not found!");
        } 
        
        Scanner scanner1 = new Scanner (new FileInputStream (file));
        int count = 0;
        System.out.println("Input Sentences: \n");  
        while (scanner1.hasNextLine()) {           
            String wholeStr = scanner1.nextLine();                
            System.out.print(count + " : ");
            System.out.println(wholeStr);              
            count++;                
        }
        
        Set<String> set = new HashSet();  
        Scanner scanner2 = new Scanner (new FileInputStream (file));
        Scanner scanner3 = new Scanner (new FileInputStream (file));
        System.out.println("\nSorted Shingle Arrays: \n");                
        int caseCount = 0;  
        char ch1, ch2;
        String wholeStr = null;
        String strSet, str1, str2;
        while (scanner2.hasNext()) {                      
            switch (caseCount) {               
                
                case 0: 
                    if(scanner3.hasNextLine())
                        wholeStr = scanner3.nextLine();  
                    int strLength = wholeStr.length();
                    String inputWord = scanner2.next();           
                    int amount  = inputWord.length();
                    int temp1 = 0;  
                    int temp2 = 0;
                    
            
                    while (temp1 <= amount - 1) {                       
                        temp2 = temp1 + 1;
                        ch1 = inputWord.charAt(temp1);                               
                        if (temp2 == amount) 
                            ch2 = ' ';                        
                        else
                            ch2 = inputWord.charAt(temp2);
                        str1 = Character.toString(ch1);
                        str2 = Character.toString(ch2);
                        strSet = str1 + str2;
                        set.add (strSet);
                        System.out.println(strSet);
                        if (temp1 + 1 == strLength) {
                            //caseCount = 1;
                            break;
                        } else
                            temp1++;
                    }

                  
                
            
            
            }                                        
        } 
        
        String[] setArray = set.toArray(new String[0]);
        strSet = Arrays.toString(setArray);
        System.out.println(strSet);

    }        
}    
        
        
        
        
     
