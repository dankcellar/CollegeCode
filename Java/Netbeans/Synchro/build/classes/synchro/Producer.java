/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */

package synchro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;

public class Producer implements Runnable {
    
    // Prepares producer for synchronization
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;
    
    public Producer(Buffer sharedLocation) {
        this.sharedLocation = sharedLocation;

    }
    
    public void run() {                      
        
        // Reads file for input
        File f = SynchroTest.getFile();  
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException ex) {}
        
        // Reads line of input from text file
        BufferedReader br = new BufferedReader(fr);
        String line = null;            
        try {
            line = br.readLine();
        } catch (IOException ex) {}
        
        // Two strings used to setup words pushed into system out
        String [] temp = line.split(" ");
        int length = temp.length;
        String [] words = new String[length + 1];       // Referencing the referecne array 
        words[length] = null;
        int count = 0;
        
        while (count < length) {              
          
            words[count] = temp[count];                 
            String putWord = words[count];                        
             
            // Induce sleep timer for waiting
            try {
                Thread.sleep(generator.nextInt(3000));
                sharedLocation.put(putWord);            // Put words into buffer
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();            
            }
            count++;
            
            // If statment handles null
            if (count == length) {
                putWord = words[length];
                try {
                    Thread.sleep(generator.nextInt(3000));
                    sharedLocation.put(putWord);        // Put words into buffer
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();            
                }
            }
        }
    }
}         