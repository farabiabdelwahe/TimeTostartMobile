/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.wireless.messaging.*;


public class AjouterUser extends Form implements
        CommandListener, Runnable {

    
    TextField username = new TextField("username", "", 10, TextField.ANY);
    TextField password = new TextField("password", "", 10, TextField.ANY);
    TextField firstname = new TextField("prenom    ", "", 10, TextField.ANY);
    TextField lastname = new TextField("nom          ", "", 10, TextField.ANY);
    DateField datenais = new DateField("DateNaissance",DateField.DATE);
    TextField pays = new TextField("pays      ", "", 10, TextField.ANY);
    TextField qualification = new TextField("qualification", "", 10, TextField.ANY);
    TextField email = new TextField("email     ", "", 10, TextField.ANY);
       TextField phone = new TextField("This phone is Used for confirmation ", "", 250, TextField.ANY);

    Command cmdNext = new Command("Ajouter", Command.OK, 0);
     Command cmdBack = new Command("Retoure", Command.OK, 0);
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
    MessageConnection clientConn;
       private Alert alert;

    String url = "http://localhost/parsing2016/insertuser.php?";

    public AjouterUser() {
        super("Ajout");
        append(username);
        append(password);
        append(firstname);
        append(lastname);
        append(datenais);
        append(pays);
        append(qualification);
        append(email);
        append(phone);
        addCommand(cmdNext);
        addCommand(cmdBack);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdNext) {
            Thread th = new Thread(this);
            th.start();
            

        }
        else if (c == cmdBack)
        {
          // Accueil lstp = new Accueil("User", List.IMPLICIT, Midlet.INSTANCE.disp);

 //Midlet.INSTANCE.disp.setCurrent(lstp);}
    }
    }
    public void run() {
        try {
           
             String usernam, pass, first, last, pay, qual, mail;
             usernam = username.getString();
             pass = password.getString();
             first = firstname.getString();
             last = lastname.getString();
        
        Calendar cal= Calendar.getInstance();
cal.setTime(datenais.getDate());
String daten = cal.get(Calendar.YEAR) + "-" + ( cal.get(Calendar.MONTH) + 1 ) + "-" + cal.get(Calendar.DAY_OF_MONTH);

             pay = pays.getString();
             qual = qualification.getString();
             mail = email.getString();

            hc = (HttpConnection) Connector.
                    open(url + "username=" + usernam + "&password=" + pass + "&firstname=" + first + "&lastname=" + last + "&datenais=" + daten + "&pays=" + pay + "&qualification=" + qual + "&email=" + mail);
            dis = hc.openDataInputStream();

            int ch;
            sb = new StringBuffer();

            while ((ch = dis.read()) != -1) {

                sb.append((char) ch);

            }
//
//            if (sb.toString().equals("successfully added")) {
//                Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
//                a.setTimeout(3000);
//                Midlet.mMidlet.disp.setCurrent(a);
//            } else {
//                Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
//                a.setTimeout(3000);
//                Midlet.mMidlet.disp.setCurrent(a);
//            }

try {
                             clientConn=(MessageConnection)Connector.open("sms://"+phone.getString());
                        }
                        catch(Exception e) {
                              
                        }
                        try {
                              TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
                              textmessage.setAddress("sms://"+phone.getString());
                              textmessage.setPayloadText("your account was successfully added , you can now use the application");
                              clientConn.send(textmessage);
                        }
                        catch(Exception e)
                        {
                              
                        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //Accueil lstp = new Accueil("User", List.IMPLICIT, Midlet.INSTANCE.disp);

// Midlet.INSTANCE.disp.setCurrent(lstp);
    }

}
