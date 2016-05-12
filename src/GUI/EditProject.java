/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Projet;
import Midlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
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
public class EditProject extends Form implements CommandListener {
    int fp ;
    Projet p;
        Command back ;  
         TextField cin ;
           TextField response; 
                    String[] projects = {"Art", "Science", "Technology","Social","Services"};
 ChoiceGroup c1=new ChoiceGroup("Type : ", Choice.EXCLUSIVE, projects, null);
                Command feebak = new Command("Done", Command.SCREEN, 0);
                 Alert alert_login = new Alert("Hey There", "Champ de nom vide!", null, AlertType.CONFIRMATION);
                    HttpConnection ht ;

            DataInputStream dt ;
            StringBuffer bf = new StringBuffer() ;
            int ch;
    
      Image  img;  
    public EditProject(String title,int i,Projet p) {
          super(title);
            back = new Command("Back",Command.EXIT, 0);
     cin = new TextField("Project Name",""+p.getName(), 250,TextField.ANY);
         response = new TextField("Project Description",""+p.gettDiscription(), 500,TextField.ANY);
       
         fp=i;
         this.p=p;
               try {
           
            img=Image.createImage("/GUI/Feedm.png");
           
            
             
        } catch (IOException ex) {
                   System.out.println(""+ex.getMessage());
            
        }
    
        append(img);
        append(cin);
        append(response);
        append(c1);
    
        
        addCommand(back);
        addCommand(feebak);
        
        setCommandListener(this);
        
       
    }

    public void commandAction(Command c, Displayable d) {
        if (c==back){
    Midlet.INSTANCE.disp.setCurrent(new MainProject("Projects"));
        }
        
             if (c==feebak){
                                 try {
ht=(HttpConnection) Connector.open("http://127.0.0.1/parsing2016/editp.php?Name="+urlEncode(cin.getString())+"&Description="+urlEncode(response.getString())+"&idproject="+this.fp+"&category="+this.c1.getString(c1.getSelectedIndex()));
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
