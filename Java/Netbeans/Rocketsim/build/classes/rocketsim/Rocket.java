/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */

package rocketsim;

import blobzx.BlobAction;
import blobzx.BlobUtils;
import blobzx.PolyBlob;
import blobzx.SandBox;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class Rocket extends PolyBlob implements BlobAction {
    
    // global define of rocket shape
    private final int[] xShape = {10, -10, -5, -10};  
    private final int[] yShape = {0, -7, 0, 7};
    
    // set defalut speed and rotation
    private double angle = 0.0;
    private final double delta = 0.15;
    private final double speed = 5.0;                         
                
    public Rocket(int x, int y, SandBox sb) {
        
        super(0, 0, 0);      
        setPolygon(xShape, yShape);                 // builds rocket
        setLoc(x, y);                               // set starting location    
    }       

    public void keyAction(KeyEvent e) {
        
        if (e.getKeyCode() == 37 ) {               // left turn function
            angle -= delta;
            if (angle < 0)
                angle += Math.PI * 2;
            turn(angle);          
            
        } else if (e.getKeyCode() == 39) {        // right turn function
            angle += delta;            
            if (angle > Math.PI * 2) 
                angle -= Math.PI * 2;            
            turn(angle);

        } else if (e.getKeyCode() == 38) {        // forward function
            Point p = getLoc();
            int xLoc = p.x + (int) Math.round(speed * Math.cos(angle));
            int yLoc = p.y + (int) Math.round(speed * Math.sin(angle));            
            setLoc(xLoc, yLoc);
        }                                            
    }
    
    public void turn(double tempAngle) {                
        
        // using two arrays the new shpae of the rocket
        // is determined by a reference array and the
        // actual turnt array shape of the rocket
        int i = 0;                                  
        int[] xRef = new int[4];
        int[] yRef = new int[4];
        for (i = 0; i < 4; i++) {
            xRef[i] = xShape[i];
            yRef[i] = yShape[i];
        }
        
        // the actual turnt rocket
        int[] xUpdate = new int[4];
        int[] yUpdate = new int[4];       
        for (i = 0; i < 4; i++) {
            Point p = BlobUtils.rotatePoint(xRef[i], yRef[i], tempAngle);
            xUpdate[i] = p.x;
            yUpdate[i] = p.y;
        }
        
        setPolygon(xUpdate, yUpdate);               // updates rocket
    }                            
}   
