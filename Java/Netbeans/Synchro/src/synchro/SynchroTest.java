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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SynchroTest {   

    // Global variables
    static File file = null;
    static String line = null;
    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
        
        // Header
        System.out.println("COP3330 (Fall 2015) Program 8: Synchronized Buffer");
        System.out.println("Author: Eric Downey\n");
        
        // File chooser prompting user for a text file
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setCurrentDirectory(new File("."));
        
        // Approves selection
        int returnVal = fileDialog.showOpenDialog(null);                
        if (returnVal == JFileChooser.APPROVE_OPTION) {          
            file = fileDialog.getSelectedFile(); }
         
        // Prepares a buffered reader to read input file
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        line = br.readLine();                                                   // Reads input line                       
        br.close();           
        JOptionPane.showMessageDialog(null, "Input sentence: \n" + line);       // Echos selection
        
        // Sets up system out prompt
        System.out.printf("%-40s%s\t\t%s%n%-40s%s%n", "Operation", "Buffer",
                "Occupied", "---------", "------\t\t--------");
        
        // Begins buffer for synchronization
        Buffer sharedLocation = new SynchronizedBuffer();
            
        ExecutorService es = Executors.newCachedThreadPool();
        
        es.execute(new Producer(sharedLocation));                   // Executes producer thread  
        es.execute(new Consumer(sharedLocation));                   // Executes consumer thread
       
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);                   // Close executors
        
    }
    
    // Function used to send file between classes
    static File getFile () {
        File setFile = file;
        return setFile;
    }
    
    // Function used to send string input between classes
    static String getLine() {
        String setLine = line;
        return setLine;               
    }
}
    
            
            
            
            
            
            
            
            

    
    
    
    
    
    
    

