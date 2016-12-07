/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */ 

package asteroidgame;

import blobzx.BlobUtils;
import blobzx.PolyBlob;
import java.awt.Point;
import java.util.Random;

public class Asteroid extends PolyBlob {                    
    
    private static Random rand = new Random();                      // random number generateor

    // Asteroid constructor
    public Asteroid(int idx, int jdx, double rot) {
        
        // sets the location where each asteroid 
        // will appear
        super(-100, -100, rot);
        setDelta(idx, jdx);
        
        int sides = rand.nextInt(5) + 5;                            // determines the number of sides for an asteroid       
        double region = ((Math.PI * 2)/sides);                      // amount of regions for each asteroid
        
        // initalzie arrays for each variable 
        // of size equal to the number of sides
        int[] x = new int[sides];           
        int[] y = new int[sides];
        double[] angle = new double [sides];
        int[] dist = new int[sides];
                                        
        int i = 0;
        while (i < sides) {
            dist[i] = rand.nextInt(11) + 5;                         // gets a pixel distance from origin
            angle[i] = ((i * region) + (Math.random() * region));   // finds some angle of each given region
            Point p = BlobUtils.rotatePoint(dist[i], angle[i]);                        
            x[i] = p.x;                                             // plots x coordinates 
            y[i] = p.y;                                             // plots y coordinates
            i++;                                                    // counts the sides                      
        }  
            
        setPolygon(x, y);                                            // sets each (x,y) coordinate                                     
    }        
}