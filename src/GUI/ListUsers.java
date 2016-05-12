/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.IDform;
import Entities.User;
import Entities.ID;
import Handlers.PeopleHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class ListUsers extends List implements CommandListener, Runnable {

    Command cmdRefresh = new Command("Rafraichir", Command.SCREEN, 0);
    Command add = new Command("Ajouter User", Command.SCREEN, 0);
    Command search = new Command("Chercher User", Command.SCREEN, 0);
    User[] people;
    StringBuffer sb;
    ID[] numbers;
    Display disp;
    public ListUsers(String title, int listType, Display d) {
        super(title, listType);
        disp=d;
        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdRefresh) {
            //disp.setCurrent(loadingDialog);
//            Thread th = new Thread(this);
//            th.start();
        }
         if (c == add) {
             
             AjouterUser u = new AjouterUser();
             disp.setCurrent(u);
         }
         if (c == search) {
             
              search searching = new search("User", List.IMPLICIT, disp);
       disp.setCurrent(searching);
         }
         
          if (c == List.SELECT_COMMAND) {
            IDform form = new IDform("Email", disp);
            form.append("Email User: \n");
            form.append(showNumbers(this.getSelectedIndex()));
            disp.setCurrent(form);
            
        }
    }

    public void run() {
        try {
            
            setCommandListener(this);
            addCommand(cmdRefresh);
            addCommand(add);
            addCommand(search);
            PeopleHandler peopleHandler = new PeopleHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing2016/selectuser.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);
            // display the result
            people = peopleHandler.getPeople();

            if (people.length > 0) {
                for (int i = 0; i < people.length; i++) {
                    append(people[i].getFullName(), null);
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
        private String showNumbers(int i) {
        sb = new StringBuffer();
        String res = "";
        numbers = people[i].getPhoneNumbers();
        if (numbers.length > 0) {
            for (int j = 0; j < numbers.length; j++) {
                sb.append("* ");
                sb.append(numbers[j].getNumber());
                sb.append("\n");
            }
        }
        res = sb.toString();
        return res;
    }

}
