/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ID;
import Entities.User;
import GUI.admin.Mainadmin;
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
import GUI.admin.Mainadmin;

/**
 *
 * @author Mohamed Raid Raddaou
 */
public class Login  extends Form implements
        CommandListener{
    public  Alert alert;
String strG;
String strF;

   String Userid;
   String Usernam;
   int uid;
    Command search = new Command("Search User", Command.SCREEN, 0);
      Command modif = new Command("Modify Profile", Command.SCREEN, 0);
      Command next = new Command("Access Account", Command.SCREEN, 0);
TextField username = new TextField("username", "", 20, TextField.ANY);
    TextField password = new TextField("password       ", "", 20, TextField.PASSWORD);
     // Command cmdNext = new Command("Add", Command.OK, 0);
    Command back = new Command("Exit", Command.OK, 0);
     Command cmdBack = new Command("Back", Command.OK, 0);
    
    User[] people;
    StringBuffer sb;
    ID[] numbers;
    Display disp;
  

   
     ImageItem img;
    Image im;
//    HttpConnection hc;
//    DataInputStream dis;
//    StringBuffer sb;
//Command cmdRefresh = new Command("refresh", Command.SCREEN, 0);
//    Person[] people;
//    StringBuffer sb2;
//    PhoneNumber[] numbers;

  Display disp2;
//    String url = "http://localhost/parsing2016/Login.php?";
   
  public Login(String title, int listType){
        super("Login");
  try {
            im = Image.createImage("/4.jpg");
        } catch (IOException ex) {
            System.out.println("erreur");
        }
        img = new ImageItem("", im, ImageItem.LAYOUT_CENTER, null);
        
         append(img);
          append(username);
         append(password);
                
        
       
        addCommand(next);
        addCommand(cmdBack);
        setCommandListener(this);
      
    
//         String strG="medraid";
//        String strF="medraid";
        
       // addCommand(cmdNext);
        
     addCommand(back);
    
        
    }
    

    public void commandAction(Command c, Displayable d) {
 if (c == next) {
    
      
     
            strG=username.getString();
             strF=password.getString();
             System.out.println(strF+"654654654654654654654");
              if(strG.equals("")){
                 
             alert = new Alert("Alert");
                        alert.setString(" Login or Password missing !!!");
                        alert.setTimeout(2000);
                        System.out.println(alert.getString());
                 }
              
              else {
try{
            setCommandListener(this);
            
            PeopleHandler peopleHandler = new PeopleHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing2016/Login.php?username="+strG+"&password="+strF);
System.out.println(strG+strF+"5raaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaamamasmdmsqdmqsmdqsmdmqsdmqsdmQSDQSDQSD");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);

            // display the result
       //     log = (idd,givenName,familyName,pases,maIl);
          
             // Midlet.mMidlet.u = new User();
           
               // System.out.println(Midlet.mMidlet.u.getId() +"*****************");
          
             
             //  System.out.println(Midlet.u.getId()+"IIIIIIIIIIIIIIIIIIIDDDDDDDDDDDDDDDDDDDDDDDDDD");
            people = peopleHandler.getPeople();
Usernam=username.getString();
            if (people.length > 0) {
               
                for (int i = 0; i < people.length; i++) {
                  //  append(people[i].getFullName(), null);
                //  (String givenName, String familyName,String passWord,String maIl,int id)
               User u = new User();
                  
                  u.setGivenName(people[i].getGivenName());
                 u.setFamilyName(people[i].getFamilyName());
                  u.setPassword(people[i].getPassword());
                  u.setMaIl(people[i].getMaIl());
                  u.setId(people[i].getId());
                  u.setQualifiCation(people[i].getQualifiCation());
                   u.setLastName(people[i].getLastName());
                   u.setCountRy(people[i].getCountRy());
                   u.setRole(people[i].getRole());
                     System.out.println(people[i].getFullName()+"qsdqsdqsdqsdqsd");
                     
                      System.out.println(people.length);
                    
                      Midlet.u=u;
                         Midlet.INSTANCE.openRecStore();
                            Midlet.INSTANCE.writeRecord(""+String.valueOf(u.getId()));
                      
         
                      System.out.println(Midlet.u.toString()+"llllllllllllllll");
                }

                                  
                                       if (Midlet.u.getRole().equals("a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}")){
                                         
                                                  Mainadmin lstp = new  Mainadmin("TimeToStart");  

 Midlet.INSTANCE.disp.setCurrent(lstp);
                                       }
                                       else{
                                       MainApp lstp = new  MainApp("TimeToStart");  

 Midlet.INSTANCE.disp.setCurrent(lstp);}
                    
                
    
            }
            else {Login lsotp = new Login("User", List.IMPLICIT);
disp.setCurrent(lsotp);}
            
//            else  if (people==null) {
//                
//                System.out.println(people.length+"haawiiiiniiiii------------------------------");
//            alert = new Alert("Alert");
//                        alert.setString(" Login or Password missing !!!");
//                        alert.setTimeout(2000);
//                        Midlet.mMidlet.disp.setCurrent(alert);
//           // append("nomlqsdmlqsqsdqsdo", null);
//            
//            
//                  }
//            else {System.out.println(people.length+"haawiiiiniiiii------------------------------");
//            alert = new Alert("Alert");
//                        alert.setString(" Login or Password missing !!!");
//                        alert.setTimeout(2000);
//                        Midlet.mMidlet.disp.setCurrent(alert);
//           // append("nomlqsdmlqsqsdqsdo", null);
//            }
            }catch(Exception ex){
    System.out.println(ex.getMessage());
}
        
           }
              


 }
    if (c == back) {
            //disp.setCurrent(loadingDialog);
             Midlet.INSTANCE.notifyDestroyed();
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
                Userid=Userid+numbers[j].getNumber();
                uid=Integer.parseInt(Userid);
            }
        }
        res = sb.toString();
        return res;
    }

       
    }

