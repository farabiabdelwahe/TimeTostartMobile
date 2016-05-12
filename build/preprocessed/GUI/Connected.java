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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Connected extends Form implements CommandListener{
    String Userid;
    String Usernam;
   public  Alert alert;
    String[] options = {"Recherche User ", "Modifier Profile", };
ChoiceGroup c1=new ChoiceGroup("Instructions : ", Choice.EXCLUSIVE, options, null);
    
      Command next = new Command("Submit", Command.SCREEN, 0);
TextField username = new TextField("username", "", 20, TextField.ANY);
    TextField password = new TextField("password       ", "", 20, TextField.ANY);
     // Command cmdNext = new Command("Add", Command.OK, 0);
    Command back = new Command("Exit", Command.OK, 0);
     Command cmdBack = new Command("Back", Command.OK, 0);
    Command cmdRefresh = new Command("Refresh", Command.SCREEN, 0);
    User[] people;
    StringBuffer sb;
    ID[] numbers;
    
    User logged;
     ImageItem img;
    Image im;
    private Display disp;
    public Connected(String title) {
        super("User");
        try {
            im = Image.createImage("/crowd.jpg");
        } catch (IOException ex) {
            System.out.println("erreur");
        }
        img = new ImageItem("", im, ImageItem.LAYOUT_CENTER, null);
        
         append(img);
//        Thread th = new Thread(this);
//        th.start();
        addCommand(back);
        
        append(c1);
       
            setCommandListener(this);
            addCommand(cmdRefresh);
        addCommand(next);
        //disp=d;
        
    }

    public void commandAction(Command c, Displayable d) {
       if (c==next){
           System.out.println(Midlet.INSTANCE.u.getGivenName()+"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        if (c1.getSelectedIndex()==0)
       {search searching = new search("search", List.IMPLICIT, Midlet.INSTANCE.disp);
       Midlet.INSTANCE.disp.setCurrent(searching);}
        
         if (c1.getSelectedIndex()==1)
         {       
//{//
//             
        ModifierUser a = new ModifierUser();
             Midlet.INSTANCE.disp.setCurrent(a);
       
       }}
         
        if (c == cmdRefresh) {
            //disp.setCurrent(loadingDialog);
//            Thread th = new Thread(this);
//            th.start();
        }
        if (c == back) {
            //disp.setCurrent(loadingDialog);
             Midlet.INSTANCE.notifyDestroyed();
        }
//       if (c == see) { 
////       ListUsers lstpa = new ListUsers("User", List.IMPLICIT, disp);
////       disp.setCurrent(lstpa);
//  Connected lstp = new Connected("User", List.IMPLICIT, disp);
//disp.setCurrent(lstp);
//       }
       
      
       
//          if (c == List.SELECT_COMMAND) {
////            IDform form = new IDform("User", disp);
////            form.append("User Id: \n");
////            form.append(showNumbers(this.getSelectedIndex()));
////            disp.setCurrent(form);
//            
//        }
    }}

//    public void run() {
//       
////        try {
////         String strG="medraid";
////        String strF="medraid";
//       
//       // addCommand(cmdNext);
//        
//     addCommand(back);
//        
//        append(c1);
//       
//            setCommandListener(this);
//            addCommand(cmdRefresh);
////            PeopleHandler peopleHandler = new PeopleHandler();
////            // get a parser object
////            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
////            // get an InputStream from somewhere (could be HttpConnection, for example)
////            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing2016/Login.php?username="+strG+"&password="+strF);
////            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
////            parser.parse(dis, peopleHandler);
//            // display the result
//       //     log = (idd,givenName,familyName,pases,maIl);
//          
//             // Midlet.mMidlet.u = new User();
//           
//               // System.out.println(Midlet.mMidlet.u.getId() +"*****************");
//          
//             
//               System.out.println(Midlet.mMidlet.u.getId()+"IIIIIIIIIIIIIIIIIIIDDDDDDDDDDDDDDDDDDDDDDDDDD");
////            people = peopleHandler.getPeople();
////Usernam=username.getString();
////            if (people.length > 0) {
////               
////                for (int i = 0; i < people.length; i++) {
////                  //  append(people[i].getFullName(), null);
////                //  (String givenName, String familyName,String passWord,String maIl,int id)
////               User u = new User();
////                  
////                  u.setGivenName(people[i].getGivenName());
////                 u.setFamilyName(people[i].getFamilyName());
////                  u.setPassword(people[i].getPassword());
////                  u.setMaIl(people[i].getMaIl());
////                     System.out.println(people[i].getFullName()+"qsdqsdqsdqsdqsd");
////                     
////                      System.out.println(people.length);
////                    
////                      Midlet.mMidlet.u=u;
////                }
////                                     }
////            else  if (people==null) {
////                
////                System.out.println(people.length+"haawiiiiniiiii------------------------------");
////            alert = new Alert("Alert");
////                        alert.setString(" Login or Password missing !!!");
////                        alert.setTimeout(2000);
////                        Midlet.mMidlet.disp.setCurrent(alert);
////           // append("nomlqsdmlqsqsdqsdo", null);
////            
////            
////                  }
////            else {System.out.println(people.length+"haawiiiiniiiii------------------------------");
////            alert = new Alert("Alert");
////                        alert.setString(" Login or Password missing !!!");
////                        alert.setTimeout(2000);
////                        Midlet.mMidlet.disp.setCurrent(alert);
////           // append("nomlqsdmlqsqsdqsdo", null);
////            }
////        } catch (ParserConfigurationException ex) {
////            ex.printStackTrace();
////        } catch (SAXException ex) {
////            ex.printStackTrace();
////        } catch (IOException ex) {
////            ex.printStackTrace();
////        }
//
//    }}
    
//        private String showNumbers(int i) {
//        sb = new StringBuffer();
//        String res = "";
//        numbers = people[i].getPhoneNumbers();
//        if (numbers.length > 0) {
//            for (int j = 0; j < numbers.length; j++) {
//                sb.append("* ");
//                sb.append(numbers[j].getNumber());
//                sb.append("\n");
//                Userid=Userid+numbers[j].getNumber();
//            }
//        }
//        res = sb.toString();
//        return res;
//    }


