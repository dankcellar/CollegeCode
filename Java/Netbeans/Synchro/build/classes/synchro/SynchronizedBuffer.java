/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */

package synchro;

public class SynchronizedBuffer implements Buffer {
    
    // Variables used in theis class
    long totalTime1 = 0;
    long totalTime2 = 0;
    private String buffer = null;
    private boolean occupied = false;    
    int count1 = 1;
    int count2 = 1;
    
    // Put function from buffer
    public synchronized void put(String word) throws InterruptedException {
        
        while(occupied) {            
            
            // Write to system out
            System.out.print("Producer wait # " + count1);
            System.out.println("\t\t\t" + buffer + "\t\t" + occupied);
            count1++;
            
            // Timers used to calculate producer wait
            long startTimer1 = System.currentTimeMillis();
            wait();
            long endTimer1 = System.currentTimeMillis();            
            long findTime1 = endTimer1 - startTimer1;
            totalTime1 += findTime1;
        }
        
        buffer = word;
        occupied = true;        
        
        // If statement handles null and prints summary with time
        if (buffer == null) {
            buffer = "@@@";
            System.out.print("\nProducer writes: " + buffer + "\n");
            String line = SynchroTest.getLine();
            System.out.println("\nProducer Summary: " + line + " " + buffer);            
            System.out.println("Total wait time: " + totalTime1 + " milliseconds");
        } else {
            System.out.print("\nProducer writes: " + buffer + "\n");
        }
        notifyAll();     
    }
    
    public synchronized String get () throws InterruptedException {        
        
        while(!occupied) {
            
            // If statment handle starting null
            if (buffer == null) {
                buffer = "NIL"; }
            
            // Writes to system out
            System.out.print("Consumer wait # " + count2);
            System.out.println("\t\t\t" + buffer + "\t\t" + occupied);
            count2++;
            
            // Timers used to calculate consumer wait
            long startTimer2 = System.currentTimeMillis();
            wait();
            long endTimer2 = System.currentTimeMillis();            
            long findTime2 = endTimer2 - startTimer2;
            totalTime2 += findTime2;
        }
        
        occupied = false;        
        System.out.print("\nConsumer reads: " + buffer + "\n");
        
        // If statement handles null and prints summary with time
        if (buffer == "@@@") {
            String line = SynchroTest.getLine();
            System.out.println("\nConsumer Summary: " + line + " " + buffer);                        
            System.out.println("Total wait time: " + totalTime2 + " milliseconds\n");
        }
        notifyAll();
        return buffer;
    }
}

