/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.admin;

import Entities.article;
import Entities.report;
import Handlers.articleHandler;
import Handlers.reportHandler;
import javax.microedition.lcdui.List;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.control.ToneControl;
import Midlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
/**
 *
 * @author FC
 */
public class Showarticle extends List implements CommandListener,Runnable {
       Command back = new Command("Refresh",Command.SCREEN, 0);
         Command back1 = new Command("Return",Command.SCREEN, 0);
     Command feebak = new Command("Supprimer", Command.SCREEN, 0);
     //Command stat = new Command("statistique", Command.SCREEN, 0);
     Command modif = new Command("Modifier", Command.SCREEN, 0);

int pid ;
       public  article r[];
       articleHandler rh ;
        DataInputStream dt ;
        
        public Showarticle (String Showarticles, int listType) {
      super(Showarticles ,List.IMPLICIT);
          Thread th = new Thread(this);
        th.start();
        pid=listType;
        
         rh=new articleHandler();
        addCommand(back);
          addCommand(back1);
         addCommand(modif);
           //addCommand(stat);
        setCommandListener(this);
        addCommand(feebak);
    
}

public void commandAction(Command c, Displayable d)
    {   
           try {
               Manager.playTone(ToneControl.C4, 5000 /* ms */, 100 /* max vol */);
               
           } catch (MediaException ex) {
               ex.printStackTrace();
           }
           if (c==back) {
      
                      Midlet.INSTANCE.disp.setCurrent(new Showarticle("Articles", 0));
       
        }
           if (c==back1) {
      
                      Midlet.INSTANCE.disp.setCurrent(new Mainadmin("TimeToStart"));
       
        }
           if (c==modif) {
       Midlet.INSTANCE.disp.setCurrent(new modifarticle("Modifier",r[this.getSelectedIndex()]));
        }
        if (c==feebak) 
          {
              System.out.println("Gaaaaa");
              System.out.println(r[this.getSelectedIndex()].getId());

   try {
             HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/parsing2016/suparticle.php?id="+r[this.getSelectedIndex()].getId());
              dt=hc.openDataInputStream();
               //DataInputStream dis = new DataInputStream(hc.openDataInputStream());
              r[this.getSelectedIndex()].getId();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


   }
       
     }
 public void run() 
    {
        
             try {
           
            setCommandListener(this);

            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing2016/afficherarticle.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            
            parser.parse(dis,rh);
            // display the result
           r= rh.getPeople();
         System.out.println(r.length+"flengthh");
          System.out.println(pid+" pid");
    
          
                for (int i = 0; i < r.length; i++) {
                          String s = "";
                           s= s+"Id :" +r[i].getId()+"\n";
                      s= s+"type :" +r[i].getType()+"\n";
                        s= s+"Author :" +r[i].getAuthor()+"\n";
                         s= s+"Filename :" +r[i].getFilename()+"\n";
                        
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
