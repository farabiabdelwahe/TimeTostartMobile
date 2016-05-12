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
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
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
public class Showreport  extends List implements CommandListener,Runnable {
       Command back = new Command("Refresh",Command.SCREEN, 0);
         Command back1 = new Command("Return",Command.SCREEN, 0);
     Command feebak = new Command("Supprimer", Command.SCREEN, 0);
     Command stat = new Command("statistique", Command.SCREEN, 0);
     Command modif = new Command("Modifier", Command.SCREEN, 0);
     
 int pid ;
       public  report r[];
       reportHandler rh ;
        DataInputStream dt ;
       
    public Showreport(String Showreports, int listType) {
      super(Showreports ,List.IMPLICIT);
          Thread th = new Thread(this);
        th.start();
        pid=listType;

        rh=new reportHandler();
        addCommand(back);
        addCommand(back1);
        addCommand(modif);
           addCommand(stat);
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
        if (c==stat) {
       Midlet.INSTANCE.disp.setCurrent(new Stat());
        }
        if (c==back1) {
       Midlet.INSTANCE.disp.setCurrent(new Mainadmin("TimeToStart"));
        }
         if (c==back) {
      
                      Midlet.INSTANCE.disp.setCurrent(new Showreport("Reports", 0));
       // rh=new reportHandler();
        }
        if (c==modif) {
       Midlet.INSTANCE.disp.setCurrent(new modifreport("Modifier",r[this.getSelectedIndex()]));
        }
          if (c==feebak) 
          {
              System.out.println("Gaaaaa");
              System.out.println(r[this.getSelectedIndex()].getIdreport());
     try {
             HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/parsing2016/supreport.php?idr="+r[this.getSelectedIndex()].getIdreport()+"&idp="+r[this.getSelectedIndex()].getIdreport());
              dt=hc.openDataInputStream();
               DataInputStream dis = new DataInputStream(hc.openDataInputStream());
              r[this.getSelectedIndex()].getIdreport();
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
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/parsing2016/affichereport.php?nom="+pid);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            
            parser.parse(dis, rh);
            // display the result
           r= rh.getPeople();
         System.out.println(r.length+"flengthh");
          System.out.println(pid+" pid");
    
          
                for (int i = 0; i < r.length; i++) {
                          String s = "";
                           s= s+"Id :" +r[i].getIdreport()+"\n";
                    s= s+"Content :" +r[i].getContent()+"\n";
                      s= s+"Date :" +r[i].getDatesignal()+"\n";
                        s= s+"User name :" +r[i].getuser()+"\n";
                         s= s+"project id :" +r[i].getIdproject()+"\n";
                        
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
