/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */ 

package asteroidgame;

import blobzx.Blob;
import blobzx.BlobProximity;

public class Missile extends Blob implements BlobProximity {

    private final int size = 5;
    private final double speed = 5.0;  

    public Missile(int x, int y, double angle) {
        
        super(0, 0, 0);        
        int dx = (int) Math.round(speed * Math.cos(angle));
        int dy = (int) Math.round(speed * Math.sin(angle));        
        setDelta(dx, dy);
        setLoc(x, y);
        setSize(size);

    }
}


