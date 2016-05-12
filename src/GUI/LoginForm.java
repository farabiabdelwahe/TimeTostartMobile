
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
public class LoginForm  extends Form implements
        CommandListener, Runnable {
    public  Alert alert;
 public static String strG;
public static   String strF;
  
TextField username = new TextField("username", "", 20, TextField.ANY);
    TextField password = new TextField("password       ", "", 20, TextField.ANY);
    Command cmdNext = new Command("Login", Command.OK, 0);
     Command cmdBack = new Command("Back", Command.OK, 0);
   
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
  Display disp2;
//    String url = "http://localhost/parsing2016/Login.php?";
   
  public LoginForm (String title){
        super("Login");
  try {
            im = Image.createImage("/GUI/Feedm.png");
        } catch (IOException ex) {
            System.out.println("erreur");
        }
        img = new ImageItem("", im, ImageItem.LAYOUT_CENTER, null);
        
         append(img);
     
        Thread th = new Thread(this);
        th.start();
    }
    

    public void commandAction(Command c, Displayable d) {
 if (c == cmdNext) {
           Midlet.INSTANCE.openRecStore();
             Midlet.INSTANCE.writeRecord(""+1);
      Midlet.INSTANCE.disp.setCurrent(new MainApp("TimeToStart"));
    
             
     
         
     }
   if (c==cmdBack) {
           Midlet.INSTANCE.notifyDestroyed();
        }
    }

    public void run() {
//        String strG =Midlet.mMidlet.strG;
//        String strF =Midlet.mMidlet.strF;
        append(username);
         append(password);
       
        
       
        addCommand(cmdNext);
        addCommand(cmdBack);
        setCommandListener(this);
      
        String a=username.getString();
      
      
       
    }
}