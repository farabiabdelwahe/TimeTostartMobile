/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.admin;

import Entities.contactus;
import Handlers.contactusHandler;
import Midlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Youssef
 */
public class Showcontactus extends Canvas implements CommandListener{
     String s[];
        Command back = new Command("exit",Command.EXIT, 0);
        Command back1 = new Command("return",Command.EXIT, 0);
        Command go = new Command("Modif", LEFT, KEY_POUND);
         Command gomap = new Command("map", LEFT, KEY_POUND);
     Image img;
      int pid ;
       contactus c = new contactus();
      contactusHandler rh=new contactusHandler() ;
        DataInputStream dt ;
      contactus r[] ;
       
    public Showcontactus()
    {
        try {
           
            img=Image.createImage("/GUI/image.jpg");
           
            
             
        } catch (IOException ex) {
            ex.printStackTrace();
        }
               try{
         SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/parsing2016/affichercontact.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            
          parser.parse(dis, rh);
          // display the result
           r= rh.getPeople();
         
                   System.out.println( r.length);
            
                        
            
                 
                       
             
     
        } catch (ParserConfigurationException ex) {
             System.out.println(ex.getMessage());
        } catch (SAXException ex) {
             System.out.println(ex.getMessage());
        } catch (IOException ex) {
          System.out.println(ex.getMessage());
        }


        
        

          this.s=s;
        addCommand(back);
         addCommand(back1);
        addCommand(go);
        addCommand(gomap);
        setCommandListener(this);
    }

    protected void paint(Graphics g) {
          g.setColor(255, 255, 255);//blanc
        
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(255, 0, 0);
        g.drawImage(img, 0, 0, Graphics.TOP | Graphics.LEFT);
        int hi= 20;
      g.drawString("notre num :"+r[0].getNumero(),50, hi+110, Graphics.LEFT| Graphics.BASELINE);
        g.drawString("notre fax :"+r[0].getFax(), 50, hi+130, Graphics.LEFT| Graphics.BASELINE);
        g.drawString("notre adresse :"+r[0].getAdresse(), 50, hi+150, Graphics.LEFT| Graphics.BASELINE);
       //   g.drawString("A:"+r[0].getA(), 50, hi+170, Graphics.LEFT| Graphics.BASELINE);
           // g.drawString("B :"+r[0].getB(), 50, hi+180, Graphics.LEFT| Graphics.BASELINE);
   }

    public void commandAction(Command c, Displayable d) 
    {
        if(c==go)
        {
        Midlet.INSTANCE.disp.setCurrent(new Modifcontactus("Modifier",r[0]));
             
        }
      if(c==gomap)
      
  Midlet.INSTANCE.disp.setCurrent(new GoogleMapsMoveCanvas(Midlet.INSTANCE,d));
          if(c==back1)
      
  Midlet.INSTANCE.disp.setCurrent(new Mainadmin("TimeToStart"));
             
        }
     }
    

