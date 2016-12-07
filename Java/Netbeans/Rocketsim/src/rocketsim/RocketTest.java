/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Eric Downey
 */

package rocketsim;

import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;

public class RocketTest implements BlobGUI {
    
    public static void main(String[] args) {            // main 
        
        new RocketTest();
    }         
    
    SandBox sb = new SandBox();
    
    public RocketTest() {
        
        sb.setSandBoxMode(SandBoxMode.FLOW);            // sets SandBox mode
        sb.setFrameRate(15);                            // sets SandBox frame rate
        sb.init(this);           
    }

    public void generate() {
       
        sb.addBlob(new Rocket(300, 225, sb));           // adds a rocket on screen
    }
}
