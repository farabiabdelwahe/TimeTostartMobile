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
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author Mohamed Raid Raddaou
 */
public class search  extends Form implements
        CommandListener, Runnable {
    public  Alert alert;
 public static String stra;



TextField username = new TextField("username", "", 20, TextField.ANY);
    
    Command cmdNext = new Command("Chercher", Command.OK, 0);
     Command cmdBack = new Command("Retoure", Command.OK, 0);
   
     ImageItem img;
    Image im;
//    HttpConnection hc;
//    DataInputStream dis;
//    StringBuffer sb;
//Command cmdRefresh = new Command("refresh", Command.SCREEN, 0);
//    Person[] people;
//    StringBuffer sb2;
//    PhoneNumber[] numbers;
 Display disp;
//  Display disp2;
//    String url = "http://localhost/parsing2016/Login.php?";
   
  public search(String title, int listType, Display d){
        super("Search");
  try {
            im = Image.createImage("/sear.jpg");
        } catch (IOException ex) {
            System.out.println("erreur");
        }
        img = new ImageItem("", im, ImageItem.LAYOUT_CENTER, null);
        
         append(img);
          
        disp=d;
        Thread th = new Thread(this);
        th.start();
    }
    

    public void commandAction(Command c, Displayable d) {
 if (c == cmdNext) {
    
      
     
            Thread th = new Thread(this);
            th.start();
            stra=username.getString();
           System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww  "+stra);
              if(stra.equals("")){
                  System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
             alert = new Alert("Alert");
                        alert.setString(" Login or Password missing !!!");
                        alert.setTimeout(2000);
                        System.out.println(alert.getString());
                        Midlet.INSTANCE.disp.setCurrent(alert);}
              else {
//               System.out.println(username.getString()+"******************************************");
//         System.out.println(password.getString()+"******************************************");
                searchUser lstp25 = new searchUser(stra, List.IMPLICIT, disp);
disp.setCurrent(lstp25);
           } } 
 if (c==cmdBack){
    Connected lstp = new Connected("User");
             disp.setCurrent(lstp);
    }}

    public void run() {
//        String strG =Midlet.mMidlet.strG;
//        String strF =Midlet.mMidlet.strF;
        append(username);
       
       
        
       
        addCommand(cmdNext);
        addCommand(cmdBack);
        setCommandListener(this);
      
        String a=username.getString();
        System.out.println("++++++++"+a+"+++++++");
  
      
       
    }
}
