/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */

package asteroidfield;

import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;
import java.util.Random;

public final class AsteroidField implements BlobGUI {    
      
    private static Random rand = new Random();          // random number generator

    // main function call
    public static void main(String[] args) {
        new AsteroidField();
    }
    
    // creates object SandBox
    SandBox sb = new SandBox(); 

    // AsteroidField constructor
    public AsteroidField() {
        
        sb.setSandBoxMode(SandBoxMode.FLOW);            // sets SandBox mode
        sb.setFrameRate(15);                            // sets SandBox frame rate
        sb.init(this);                                  // initalizes the SandBox
    }                                  
     
    // generate method
    public void generate() {
        
        int count = 0;
        while (count < 20) {        
            double rotation = 0.1;
            int veloX = rand.nextInt(3) + 1;            // random velocity in X plane 1 to 3
            int veloY = rand.nextInt(3) + 1;            // random velocity in Y plane 1 to 3
            int signX = rand.nextInt(2);                // determines direction left or right
            int signY = rand.nextInt(2);                // determines dircetion up or down
            int signRot = rand.nextInt(2);              // determines clockwise or counter clockwise rotation            
            
            // sets the negative value of 
            // any direction or rotation
            if (signX == 1) {                   
                veloX *= (-1);}       
            if (signY == 1) {
                veloY *= (-1);}
            if (signRot == 1) {
                rotation *= (-1);}
            
            // adds the asteroid on the screen
            sb.addBlob(new Asteroid(veloX, veloY, rotation));
            count++;                                 
        }
    }
}        


