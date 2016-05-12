/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Midlet;

import Entities.User;

import GUI.ListProjects;
import GUI.Login;
import GUI.LoginForm;
import GUI.MainApp;
import GUI.MainProject;
import GUI.SplashScreen;
import Handlers.PeopleHandler;
import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/**
 * @author nader
 */
public class Midlet extends MIDlet {
      private RecordStore rs = null;
  static final String REC_STORE = "ReadWriteRMS";
  public   Display disp = Display.getDisplay(this);
   
        public static  Midlet INSTANCE;
        public static User u ; 
        User[] people ;
           
    
    public void startApp() {
  
        
        
            
            INSTANCE=this ;
            u= new User();
         
     
            try {
                disp.setCurrent(new SplashScreen());
                
                Thread.sleep(3000);
                
                
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
        
             openRecStore();
//           
           
            System.out.println(readRecords()+"test mta3 ald");
            if(readRecords().equals("")){
                
                disp.setCurrent(new Login("TimeToStart",List.IMPLICIT));
                
                
                
                
                
            }
            else{
                
                disp.setCurrent(new MainApp("TimeToStart"));
         
                 try{
        
            
            PeopleHandler peopleHandler = new PeopleHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing2016/finduser.php?username="+Integer.parseInt(readRecords()));
                     System.out.println("http://localhost/parsing2016/Login.php?username="+Integer.parseInt(readRecords()));
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);

            // display the result
       //     log = (idd,givenName,familyName,pases,maIl);
          
             // Midlet.mMidlet.u = new User();
           
               // System.out.println(Midlet.mMidlet.u.getId() +"*****************");
          
             
             //  System.out.println(Midlet.u.getId()+"IIIIIIIIIIIIIIIIIIIDDDDDDDDDDDDDDDDDDDDDDDDDD");
            people = peopleHandler.getPeople();

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
                  disp.setCurrent(new MainApp("TimeToStart"));
                 
                      
         
                  
                }
            
        
            }
                 }
                     catch (Exception e){
                    
                    }
          
                 }
            

                   
                   
                   
                   
                
                
            
            closeRecStore();
            
        
    }   
    
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
      public void openRecStore(){
    try{
      rs = RecordStore.openRecordStore(REC_STORE, true );
    }catch (Exception e){}
  }    

  public void closeRecStore(){
    try{
      rs.closeRecordStore();
    }catch (Exception e){}
  }
    
  public void deleteRecStore(){
      
    if (RecordStore.listRecordStores() != null){
      try{
       RecordStore.deleteRecordStore(REC_STORE);
    
      }catch (Exception e){}
    }      
  }

  public void writeRecord(String str){
    byte[] rec = str.getBytes();
    try{
      rs.addRecord(rec, 0, rec.length);
    }catch (Exception e){}
  }

  public String  readRecords(){
      String  s="";
    try{
      byte[] recData = new byte[5]; 
      int len;
      
      for(int i = 1; i <= rs.getNumRecords(); i++){
        if(rs.getRecordSize(i) > recData.length){
          recData = new byte[rs.getRecordSize(i)];
        }
        len = rs.getRecord(i, recData, 0);
       

     
       s=s+ new String(recData, 0, len);
                            
      }
    }catch (Exception e){}
    return s ;
  }

}

