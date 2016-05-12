/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Midlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author GSC
 */
public class Report  extends Form implements CommandListener {
    int fp ;
        Command back = new Command("Back",Command.EXIT, 0);
         TextField cin = new TextField("FeedbackText",null, 250,TextField.ANY);
           TextField response = new TextField("FeedbackText",null, 250,TextField.ANY);
                Command feebak = new Command("Done", Command.SCREEN, 0);
                 Alert alert_login = new Alert("Hey There", "Champ de nom vide!", null, AlertType.CONFIRMATION);
                    HttpConnection ht ;

            DataInputStream dt ;
            StringBuffer bf = new StringBuffer() ;
            int ch;
    
      Image  img;  
    public Report(String title,int i) {
         super(title);
         fp=i;
               try {
           
            img=Image.createImage("/GUI/Feedm.png");
           
            
             
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
        append(img);
        append(cin);
    
        
        addCommand(back);
        addCommand(feebak);
        
        setCommandListener(this);
        
       
    }

    public void commandAction(Command c, Displayable d) {
        if (c==back){
    Midlet.INSTANCE.disp.setCurrent(new ListProjects("Projects",List.IMPLICIT));
        }
        
             if (c==feebak){
                                 try {
ht=(HttpConnection) Connector.open("http://127.0.0.1/parsing2016/report.php?text="+urlEncode(cin.getString())+"&iduser="+Midlet.INSTANCE.u.getId()+"&idproject="+this.fp);
                    dt=ht.openDataInputStream();
                  
                      while ((ch=dt.read())!=-1)
                      {
                         
                          
                   bf.append((char) ch);
                      }
                 
                    
                     // StringItem.setText(bf.toString());
                      alert_login.setString(bf.toString());
                      Midlet.INSTANCE.disp.setCurrent(alert_login);
             
                    
                       
                         
                      
                      
        } catch (IOException ex) {
           cin.setString(ex.getMessage());
                                     System.out.println(""+ex.getMessage());
      

        }
             
    }
    }
    
    static public String urlEncode(String sUrl) 
{
     StringBuffer urlOK = new StringBuffer();
     for(int i=0; i<sUrl.length(); i++) 
     {
         char ch=sUrl.charAt(i);
         switch(ch)
         {
             case '<': urlOK.append("%3C"); break;
             case '>': urlOK.append("%3E"); break;
             case '/': urlOK.append("%2F"); break;
             case ' ': urlOK.append("%20"); break;
             case ':': urlOK.append("%3A"); break;
             case '-': urlOK.append("%2D"); break;
             default: urlOK.append(ch); break;
         } 
     }
     return urlOK.toString();
 }
    
    
    
    
}
