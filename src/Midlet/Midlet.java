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
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
/**
 * @author nader
 */
public class Midlet extends MIDlet {
      private RecordStore rs = null;
  static final String REC_STORE = "ReadWriteRMS";
  public   Display disp = Display.getDisplay(this);
   
        public static  Midlet INSTANCE;
        public static User u ;
           
    
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
                   u.setId(Integer.parseInt(readRecords()));
                
                
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

