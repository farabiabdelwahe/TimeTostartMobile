/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Midlet.Midlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

/**
 *
 * @author GSC
 */
public class ProjectsDetails extends Form implements CommandListener {
        Command next = new Command("suivant",Command.SCREEN, 0);
      Command back = new Command("Back",Command.EXIT, 0);
      
    public ProjectsDetails(String title) {
        super(title);
        addCommand(back);
        setCommandListener(this);
        
    }

    public void commandAction(Command c, Displayable d) {


      
        if (c==back) {
           Midlet.INSTANCE.disp.setCurrent(new ListProjects("Projects",List.IMPLICIT));
        }
       
 
    }
}
