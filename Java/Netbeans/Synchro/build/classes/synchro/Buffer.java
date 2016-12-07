/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */

package synchro;

// Interface used to implment put and get commands
public interface Buffer {
    
    public void put (String word) throws InterruptedException;
    
    public String get() throws InterruptedException;
    
}
