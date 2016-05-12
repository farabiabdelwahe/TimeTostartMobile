/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.admin;

import Entities.report;
import Midlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.control.ToneControl;

/**
 *
 * @author Youssef
 */
public class modifreport extends Form implements CommandListener {
  TextField content=new TextField("Changer Contenu", "", 60,TextField.ANY) ; 
  TextField id=new TextField("Id", "", 60,TextField.UNEDITABLE) ;
         DataInputStream dt ;

     Command go = new Command("Modif", Command.SCREEN,0); 
      Command exit = new Command("Exit", Command.EXIT,0); 
    public modifreport(String title , report r ) {
        super(title);
            append(content);
              append(id);
              id.setString(String.valueOf(r.getIdreport()));
            content.setString(r.getContent());
      
        addCommand(go);
          addCommand(exit);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
          try {
               Manager.playTone(ToneControl.C4, 5000 /* ms */, 100 /* max vol */);
               
           } catch (MediaException ex) {
               ex.printStackTrace();
           }
        if(c==go)
        {
        
         try {
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/parsing2016/modifierreport.php?idrep="+id.getString()+"&content="+content.getString());
              dt=hc.openDataInputStream();
               DataInputStream dis = new DataInputStream(hc.openDataInputStream());
             
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(c==exit)
        {
           Midlet.INSTANCE.disp.setCurrent(new Showreport("Reports", 0));
        }
      }
    
}
