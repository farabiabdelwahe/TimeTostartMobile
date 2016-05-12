/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;




import Handlers.PeopleHandler;
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
import javax.obex.PasswordAuthentication;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.wireless.messaging.*;import Midlet.Midlet;
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

public class ModifierUser extends Form implements
        CommandListener, Runnable {

     TextField phone = new TextField("This phone is Used for confirmation ", "", 250, TextField.DECIMAL);

    TextField username = new TextField("Username", "", 10, TextField.ANY);
    TextField firstname = new TextField("prenom    ", "", 10, TextField.ANY);
    TextField lastname = new TextField("nom          ", "", 10, TextField.ANY);
  DateField datenais = new DateField("DateNaissance",DateField.DATE);
    TextField pays = new TextField("pays      ", "", 10, TextField.ANY);
    TextField qualification = new TextField("qualification", "", 10, TextField.ANY);
    TextField email = new TextField("email     ", "", 10, TextField.ANY);

    Command cmdNext = new Command("Modify Profile", Command.OK, 0);
     Command cmdBack = new Command("Back", Command.OK, 0);
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
     MessageConnection clientConn;


    String url = "http://localhost/parsing2016/modifieruser.php?";

    public ModifierUser() {
        super("Modify");
        
        
      append(username);
     //   append(password);
        append(firstname);
        append(lastname);
      append(datenais);
     //  append(pays);
        append(qualification);
         append(phone);
     
   //     password.setString(Midlet.u.getPassword());
        
    //    System.out.println(password.getString()+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAS");
          username.setString(Midlet.u.getGivenName());
         lastname.setString(Midlet.u.getLastName());
         firstname.setString(Midlet.u.getFamilyName());
       qualification.setString(Midlet.u.getQualifiCation());
        // pays.setString(Midlet.u.getCountRy());
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
        { Connected lstp = new Connected("User");  

 Midlet.INSTANCE.disp.setCurrent(lstp);}
    }

    public void run() {
        try {
           
             String usernam, pass, first, last, pay, qual, mail;
             usernam =Midlet.INSTANCE.u.getGivenName();
             
       //      pass = password.getString();
             first = firstname.getString();
             last = lastname.getString();
        
        Calendar cal= Calendar.getInstance();
cal.setTime(datenais.getDate());
String daten = cal.get(Calendar.YEAR) + "-" + ( cal.get(Calendar.MONTH) + 1 ) + "-" + cal.get(Calendar.DAY_OF_MONTH);

     //      pay = pays.getString();
         qual = qualification.getString();
//             mail = email.getString();

            hc = (HttpConnection) Connector.
                    open(url + "username=" + usernam + "&firstname=" + first + "&lastname=" +  last +"&datenais="+daten + "&qualification=" + qual);
            dis = hc.openDataInputStream();

            int ch;
            sb = new StringBuffer();

            while ((ch = dis.read()) != -1) {

                sb.append((char) ch);

            }

            if (sb.toString().equals("successfully added")) {
                Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
              try {
                             clientConn=(MessageConnection)Connector.open("sms://"+phone.getString());
                        }
                        catch(Exception e) {
                              
                        }
                        try {
                              TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
                              textmessage.setAddress("sms://"+phone.getString());
                              textmessage.setPayloadText("your account was successfully modified , you can now use the application");
                              clientConn.send(textmessage);
                        }
                        catch(Exception e)
                        {
                              
                        }
                 
               Connected lstp = new Connected("User");  

 Midlet.INSTANCE.disp.setCurrent(lstp);
                 a.setTimeout(3000);
               Midlet.INSTANCE.disp.setCurrent(a);
            } else {
           Connected lstp = new Connected("User");  

Midlet.INSTANCE.disp.setCurrent(lstp);         Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
               
                 Connected s2 = new Connected("User");  

 Midlet.INSTANCE.disp.setCurrent(s2);
   a.setTimeout(3000);
                 Midlet.INSTANCE.disp.setCurrent(a);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}





