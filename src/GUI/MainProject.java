/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
public class MainProject extends Form implements CommandListener {
        Command next = new Command("suivant",Command.SCREEN, 0);
         Command logout = new Command("Logout",Command.SCREEN, 0);
      Command back = new Command("exit",Command.EXIT, 0);
       Image img ;
    
         String[] projects = {"My projects ", "Projects by Name", "Projects by Categories"};
 ChoiceGroup c1=new ChoiceGroup("projects : ", Choice.EXCLUSIVE, projects, null);

    public MainProject(String title) {
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
          
            setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {


         if ((c==next) && (c1.getSelectedIndex()==0) ){
          Midlet.INSTANCE.disp.setCurrent(new ListProjects("my Projects",List.IMPLICIT));
           
        }
          if ((c==next) && (c1.getSelectedIndex()==1) ){
          Midlet.INSTANCE.disp.setCurrent(new getProjectyName("Look For A project"));
           
        }
              if ((c==next) && (c1.getSelectedIndex()==2) ){
          Midlet.INSTANCE.disp.setCurrent(new GetProjectsByType("Look For A project"));
           
        }
        if (c==back) {
                    Midlet.INSTANCE.disp.setCurrent(new MainApp("TimeToStart"));
        }
        
          if (c==logout) {
         
              

         System.out.println(Midlet.INSTANCE.readRecords()+"hellothere");
                  Midlet.INSTANCE.closeRecStore();
                   Midlet.INSTANCE.deleteRecStore();
                  
       
               Midlet.INSTANCE.disp.setCurrent(new LoginForm("Time To Start"));
         
        }
       
 
    }
    
}
