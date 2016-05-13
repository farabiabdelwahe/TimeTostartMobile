/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.admin;

import Entities.report;
import Handlers.reportHandler;
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
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.control.ToneControl;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Youssef
 */
public class Stat extends Canvas implements CommandListener {
     report[] reports;
  Command go = new Command("Modif", Command.SCREEN,0); 
        Image imgBackground;
    int colors[]={2037680,8519755,4557568,65535,2631720};

    public Stat()
    {         addCommand(go);
            setCommandListener(this);
        try {
            reportHandler hand = new reportHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
             HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing2016/affichereport.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, hand);
            this.reports = hand.getPeople();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    
    }
    
    protected void paint(Graphics g) 
    {
          
        /* g.setColor(255,255,255);
        g.fillRect(0, 0, getWidth(), getHeight());*/
         try {
             imgBackground = Image.createImage("/GUI/admin/image.jpg");
         } catch (IOException ex) {
             ex.printStackTrace();
         }
          g.drawImage(imgBackground, 0, 0, Graphics.TOP | Graphics.LEFT);
  g.setColor(255,0,0);
 g.drawString("Nombre de rapport selon utilisateur", getWidth()/2-110, getHeight()-20,0);
        
        int ca=0,cy=230;

        for (int i = 0; i < reports.length; i++)
        {
            if(pourcentage(reports[i].getuser(),i)!=0)
            {   g.setColor(colors[i]);
            
            g.fillArc(getWidth()/2-100, 10, 200, 200, ca,pourcentage(reports[i].getuser(),i));
            
            g.fillRect(2, cy, 10, 10);
            g.drawString(reports[i].getuser()+" "+pourcentage(reports[i].getuser(),i)*0.277+"%",15 , cy-3,0);
            ca+=pourcentage(reports[i].getuser(),i);
            cy+=15;
            System.out.println("aaaaaaa");
            System.out.println(pourcentage(reports[i].getuser(),i));}
         
            
        }
       
    }
        public int pourcentage(String store, int a ){
        int counter=0;
        int test=0;
        for(int j =0;j<a;j++)
        {
      if (reports[j].getuser().equals(store))
                test=-1;
        }
        if( test == -1)
            return 0 ;
        for (int i = a; i < reports.length; i++) {
            if (reports[i].getuser().equals(store))
                counter++; 
            
        }
        return counter*360/reports.length;
   
        }
    

    public void commandAction(Command c, Displayable d)
    {
          try {
               Manager.playTone(ToneControl.C4, 5000 /* ms */, 100 /* max vol */);
               
           } catch (MediaException ex) {
               ex.printStackTrace();
           }
        if(c==go)
        {
          Midlet.INSTANCE.disp.setCurrent(new Showreport("Reports", 0));
        }
    }
    
}
