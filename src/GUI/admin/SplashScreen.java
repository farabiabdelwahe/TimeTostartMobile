/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.admin;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m3g.Image2D;

/**
 *
 * @author GSC
 */
public class SplashScreen  extends Canvas{
    Image img;
    Image img1;
    
    int h = getHeight();
    int w = getWidth();
    public SplashScreen() {
        try {
          img=Image.createImage("/GUI/200.gif");
        
        } catch (IOException ex) {
           System.out.print(ex.getMessage()+"fffrefzqdqz");
        }
         
         
    }
    

    protected void paint(Graphics g) {
        try {
            g.drawImage(img, 0, 0, 0);
          // g.wait(3000);
          // g.drawImage(img1, 0, w+30, 0);
          // g.wait(3000);
        }
        catch ( Exception E) {
            System.out.println(E.getMessage()); 
        }
    }
    
}
