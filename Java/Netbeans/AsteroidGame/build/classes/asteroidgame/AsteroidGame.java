/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */ 

package asteroidgame;

import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;
import java.util.Random;

public class AsteroidGame implements BlobGUI {
    
    public static Random rand = new Random();          // random number generator

    public static void main(String[] args) {

        new AsteroidGame();       
    }

    SandBox sb = new SandBox();

    public AsteroidGame() {
        
        sb.setSandBoxMode(SandBoxMode.FLOW);            // sets SandBox mode
        sb.setFrameRate(15);                            // sets SandBox frame rate
        sb.init(this);             
      
    }
    
    public void generate() {
        
        sb.addBlob(new Rocket(300, 225, sb));           // adds a rocket on screen
        
        int count = 0;
        while (count < 10) {        
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
