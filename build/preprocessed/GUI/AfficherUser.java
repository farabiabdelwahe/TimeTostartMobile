/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Mohamed Raid Raddaou
 */
import Midlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.obex.PasswordAuthentication;


public class AfficherUser extends Form implements
        CommandListener, Runnable {
    
    TextField username = new TextField("username", "     ", 10, TextField.ANY);
Command cmdNext = new Command("Afficher", Command.OK, 0);
     Command cmdBack = new Command("Retoure", Command.OK, 0);
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;

    String url = "http://localhost/parsing2016/selectuser.php?";
   
    public AfficherUser() {
        super("Afficher");
           addCommand(cmdNext);
        addCommand(cmdBack);
        append(username);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        
 if (c == cmdNext) {
            Thread th = new Thread(this);
            th.start();
            ListUsers lstp = new ListUsers("User", List.IMPLICIT, Midlet.INSTANCE.disp);  

 Midlet.INSTANCE.disp.setCurrent(lstp);
        }
    }

    public void run() {
        try{
 hc = (HttpConnection) Connector.
                    open(url);
            dis = hc.openDataInputStream();

            int ch;
            sb = new StringBuffer();

            while ((ch = dis.read()) != -1) {

                sb.append((char) ch);

            }

            if (sb.toString().equals("successfully added")) {
                Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                a.setTimeout(3000);
                Midlet.INSTANCE.disp.setCurrent(a);
            } else {
                Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                a.setTimeout(3000);
                Midlet.INSTANCE.disp.setCurrent(a);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
}
