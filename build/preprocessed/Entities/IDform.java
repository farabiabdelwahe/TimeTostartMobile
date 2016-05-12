/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import Midlet.Midlet;
import GUI.Connected;
import GUI.ListUsers;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;


public class IDform extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 0);
    Command next = new Command("next", Command.BACK, 0);
    Display disp;

    public IDform(String title, Display d) {
        
        super(title);
        addCommand(cmdBack);
        setCommandListener(this);
        disp = d;
    }
    
    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
              Connected lstp = new Connected("User");
disp.setCurrent(lstp);
        }
        if (c == next)
        {   ListUsers lstpe = new ListUsers("User", List.IMPLICIT, Midlet.INSTANCE.disp);  }
    }
    
}
