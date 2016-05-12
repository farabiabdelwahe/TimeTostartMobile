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
public class GetProjectsByType extends Form implements CommandListener {
        Command next = new Command("suivant",Command.SCREEN, 0);
      Command back = new Command("exit",Command.EXIT, 0);
    
         String[] projects = {"Art", "Science", "Technology", "Services","Social"};
 ChoiceGroup c1=new ChoiceGroup("project Type : ", Choice.EXCLUSIVE, projects, null);
 Image img ;

    public GetProjectsByType (String title) {
        super(title);
                       try {
           
            img=Image.createImage("/GUI/Main.png");
           
            
             
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


         if ((c==next)  ){
          Midlet.INSTANCE.disp.setCurrent(new ProjectsByType(projects[c1.getSelectedIndex()],List.IMPLICIT));
           
        }
   
        if (c==back) {
           Midlet.INSTANCE.disp.setCurrent(new MainProject("Projects Main"));
        }
       
 
    }
    
}
