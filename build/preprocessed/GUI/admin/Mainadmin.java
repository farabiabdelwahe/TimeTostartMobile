/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.admin;

import GUI.Login;
import Midlet.Midlet;
import java.io.IOException;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

/**
 *
 * @author GSC
 */
public class Mainadmin extends Form implements CommandListener {
        Command next = new Command("suivant",Command.SCREEN, 0);
         Command logout = new Command("Logout",Command.SCREEN, 0);
      Command back = new Command("exit",Command.EXIT, 0);
       Image img ;
    
         String[] projects = {"Reports ", "Contactus", "Articles"};
 ChoiceGroup c1=new ChoiceGroup("projects : ", Choice.EXCLUSIVE, projects, null);

    public Mainadmin(String title) {
        super(title);
               try {
           
            img=Image.createImage("/GUI/crowd.jpg");
           
            
             
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     append(img);
        append(c1);
       
        addCommand(next);
            addCommand(back);
            addCommand(logout);
            setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {


         if ((c==next) && (c1.getSelectedIndex()==0) ){
          Midlet.INSTANCE.disp.setCurrent(new Showreport("Reports",List.IMPLICIT));
           
        }
          if ((c==next) && (c1.getSelectedIndex()==1) ){
                        Midlet.INSTANCE.disp.setCurrent(new Showcontactus());
    
           
        }
              if ((c==next) && (c1.getSelectedIndex()==2) ){
         Midlet.INSTANCE.disp.setCurrent(new Showarticle(null, List.IMPLICIT));
           
        }
        if (c==back) {
              
        }
        
          if (c==logout) {
         
              


                  
              System.out.println(Midlet.INSTANCE.readRecords()+"hellothere");
                  Midlet.INSTANCE.closeRecStore();
                   Midlet.INSTANCE.deleteRecStore();
                  
       
               Midlet.INSTANCE.disp.setCurrent(new Login("Time To Start",List.IMPLICIT));
         
        
         
        }
       
 
    }
    
}
