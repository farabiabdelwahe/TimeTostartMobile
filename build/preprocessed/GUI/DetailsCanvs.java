/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Midlet.Midlet;
import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author GSC
 */
public class DetailsCanvs extends Canvas implements CommandListener{
    String s[];
        Command back = new Command("exit",Command.EXIT, 0);
        Image img;
    public DetailsCanvs ( String t,String[] s ){
               try {
           
            img=Image.createImage("/GUI/bac2.jpg");
           
            
             
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.s=s;
        addCommand(back);
        setCommandListener(this);
        
        
    }

    protected void paint(Graphics g) {
              g.setColor(255, 255, 255);//blanc
        g.fillRect(0, 0, getWidth(), getHeight());

      
  g.drawImage(img, 0, 0, Graphics.TOP | Graphics.LEFT);
           
            g.setColor(255, 0, 0);//noir.
            g.setFont(Font.getFont(Font.FACE_MONOSPACE, 
Font.STYLE_BOLD, 
Font.SIZE_SMALL));
            int hi= 20;
            for ( int i =0 ; i<s.length ;i++){
            g.drawString(s[i], 10 ,hi+40 , Graphics.LEFT| Graphics.BASELINE);
            hi=hi+40;
            System.out.println(s[i]);
            }
    }

    public void commandAction(Command c, Displayable d) {
                               if (c == back) {
    
                  
         
           Midlet.INSTANCE.disp.setCurrent(new MainProject("main Projects"));
        }
    }
    
    
}
