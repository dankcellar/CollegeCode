/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author:  Eric Downey
 */

package histogram;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HistogramPanel extends JPanel {
   
   private List<String> sents;
   private int snum;
   
   public String readFile( File file ) {
      sents = new ArrayList();
      snum = -1;
      clearDisplay( this.getGraphics() );
      StringBuilder sb = new StringBuilder();
      try {
         Scanner scanner = new Scanner( new FileInputStream(file));
         while( scanner.hasNextLine() ) {
            sents.add( scanner.nextLine() );
         }
         
         for( int i = 0; i < sents.size(); i++ ) {
            sb.append(i + " : " + sents.get( i ) + "\n\n");
         }
      } catch (FileNotFoundException ex) {
         Logger.getLogger(HistogramPanel.class.getName()).log(Level.SEVERE, null, ex);
      }
      return sb.toString().trim();
   }
   
   @Override
   public void paintComponent( Graphics gc ) {
      super.paintComponent( gc );
      if( sents != null && snum >= 0 && snum < sents.size() ) { 
         showHisto( snum, true );
      }
   }
   
   public void showHisto() {
      this.setBackground( Color.white );
      showHisto( snum, false );
   }
   
   public void showHisto( int n, boolean b ) {            
      if( sents != null && n >= 0 && n < sents.size() ) {
         snum = n;
         Graphics gc = this.getGraphics();
         clearDisplay( gc );
         drawLines( gc );
         drawHisto( gc );
      }
      else if( b && sents != null ) {
         JOptionPane.showMessageDialog(this, "Sentence index out of range");
      }
   }
   
   private void clearDisplay( Graphics gc ) {      
      gc.setColor( Color.WHITE );
      gc.fillRect(0,0,this.getWidth(),this.getHeight());
   }
   
   private void drawLines( Graphics gc ) { 
       
       int xLoc = this.getWidth();
       int yLoc = this.getHeight();       
       gc.setColor(Color.RED);
       gc.drawLine((int)(xLoc * 0.1), (int)(yLoc * 0.1), (int)(xLoc * 0.1), (int)(yLoc * 0.9));
       gc.drawLine((int)(xLoc * 0.1), (int)(yLoc * 0.9), (int)(xLoc * 0.9), (int)(yLoc * 0.9));                                                     
   }
   
   private void drawHisto( Graphics gc ) {
               
       int xLoc = this.getWidth();
       int yLoc = this.getHeight();
       int[] histo = new int[26];              
                                  
       String s = sents.get(snum);       
       int[] letters = histo(s);
       scale(letters, histo);
       int widthRect = (int)((xLoc * 0.8) / 26);
                      
       for (int i = 0; i < 26; i++) {
           double spacer = ((double) i / 26.0);                                      
           int x = (int)((xLoc * 0.1) + (xLoc * 0.8) * spacer);
           int y = (int)((yLoc * 0.1) + (findMax(histo) - histo[i]) * ((yLoc * 0.8) / findMax(histo)) - 1);
           gc.setColor(Color.BLUE);
           gc.drawRect(x, y, widthRect, histo[i]);           
       }      
   }
           
   public static int[] histo (String str) {
       
       String s = str.toLowerCase();
       int[] letters = new int[26]; 
       for (int i = 0; i < s.length(); i++) {
           char ch = s.charAt(i);    
           if (Character.isLetter(ch)) {
               int num = ch - 'a';
               letters[num] += 1;
           }
       }
       return letters;
   }
   
   public void scale (int[] inp, int[] histo) {
       
       int max = findMax(inp);
       for (int i = 0; i < 26; i++) {
           double scaled = (this.getHeight() * 0.8) * ((double)(inp[i]) / max);
           histo[i] = (int)scaled;
       }       
   }
                    
   public int findMax (int[] inp) {
       
       int max = inp[0];
       for (int i = 0; i < inp.length; i++) {
           if (inp[i] > max) {
               max = inp[i]; }                      
       }
       return max;
   }

    void setColor(Color WHITE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}

