/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.admin;




import Entities.contactus;
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

/**
 *
 * @author Youssef
 */
public class Modifcontactus extends Form implements CommandListener {
         String s[];
       
   

      
        DataInputStream dt ;

  TextField numero=new TextField("numero", "", 60,TextField.ANY) ; 
  TextField fax=new TextField("fax", "", 60,TextField.ANY) ;
   TextField adresse=new TextField("adresse", "", 60,TextField.ANY) ;
    TextField a=new TextField("Latitude", "", 60,TextField.ANY) ;
     TextField b=new TextField("Longitude", "", 60,TextField.ANY) ;
     Command go = new Command("Modif", Command.SCREEN,0); 
      Command retur = new Command("Return", Command.SCREEN,0); 
   
    public Modifcontactus(String title , contactus c ) {
        super(title);
       append(numero);
       numero.setString(c.getNumero());
       append(fax);
       fax.setString(c.getFax());
        append(adresse);
        adresse.setString(c.getAdresse());
         append(a);
       a.setString(c.getA());
        append(b);
       b.setString(c.getB());
        addCommand(go);
        addCommand(retur);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(c==go)
        {
            try {
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/parsing2016/modifcontactus.php?numero="+numero.getString()+"&fax="+fax.getString()+"&adresse="+adresse.getString()+"&a="+a.getString()+"&b="+b.getString());
              dt=hc.openDataInputStream();
               DataInputStream dis = new DataInputStream(hc.openDataInputStream());
             
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(c==retur)
        {
        Midlet.INSTANCE.disp.setCurrent(new Showcontactus());
        }
        }
    
}
