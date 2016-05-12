/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Entities.Feedback;
import Entities.Sponsoring;
import Handlers.FeedbcakHandler;
import Handlers.ProjectHandler;
import Handlers.SponsoringHandler;
import Midlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author GSC
 */
public class Sponsors extends List implements CommandListener,Runnable{
  Command back = new Command("Back",Command.EXIT, 0);
     Command feebak = new Command("add A feedback", Command.SCREEN, 0);
 int pid ;
       public  Sponsoring f[];
      SponsoringHandler fh ;
   Sponsors(String feedbacks, int id) {
       super(feedbacks ,List.IMPLICIT);
          Thread th = new Thread(this);
        th.start();
        pid=id;

        fh=new SponsoringHandler();
        addCommand(back);
        setCommandListener(this);
    
    }

    public void commandAction(Command c, Displayable d) {
   


      
        if (c==back) {
                   Midlet.INSTANCE.disp.setCurrent(new MainProject("Projects"));
        }
 
       
 
    }

    public void run() {
             try {
           
            setCommandListener(this);

            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/parsing2016/Sponsor.php?nom="+pid);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            
            parser.parse(dis, fh);
            // display the result
           f= fh.getPeople();
         System.out.println(f.length+"flengthh");
          System.out.println(pid+" pid");
    
          
                for (int i = 0; i < f.length; i++) {
                          String s = "";
                    s= s+"Sponsor :" +f[i].getOwner()+"\n";
                      s= s+"Type :" +f[i].getType()+"\n";
                        s= s+"Date :" +f[i].getDate()+"\n";
                    append(s, null);
                 
                       
             
         
            }
        } catch (ParserConfigurationException ex) {
             System.out.println(ex.getMessage());
        } catch (SAXException ex) {
             System.out.println(ex.getMessage());
        } catch (IOException ex) {
          System.out.println(ex.getMessage());
        }


    }
    
}

