/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Projet;
import Handlers.ProjectHandler;
import Midlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author nader
 */
public class ListProjects extends List implements CommandListener, Runnable {

    Command cmdRefresh = new Command("back", Command.SCREEN, 0);
    
        Command feebak = new Command("Show Feedbacks", Command.SCREEN, 0);
        
        Command Sponsor = new Command("Show Sponsoring", Command.SCREEN, 0);
           Command sh = new Command("Show Details", Command.SCREEN, 0);
              Command edit = new Command("Edit Project", Command.SCREEN, 0);
    
    Projet[] projects;
    StringBuffer sb;

    Display disp;
    public ListProjects(String title, int b) {
        super(title,b);
      
        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdRefresh) {
            //disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }
        
          if (c == sh) {
    
                   ProjectsDetails form = new ProjectsDetails("ProjectsDteails");
          

            Midlet.INSTANCE.disp.setCurrent(new DetailsCanvs("hey there", showNumbers(this.getSelectedIndex())));
        }
              if (c == feebak) {
    
                  
         
           Midlet.INSTANCE.disp.setCurrent(new Feedbacks ("Feedbacks",projects[this.getSelectedIndex()].getId()));
        }
                   if (c == Sponsor) {
    
                  
         
           Midlet.INSTANCE.disp.setCurrent(new Sponsors ("Feedbacks",projects[this.getSelectedIndex()].getId()));
        }
                             if (c == cmdRefresh) {
    
                  
         
           Midlet.INSTANCE.disp.setCurrent(new MainProject("main Projects"));
        }
                                                      if (c == edit) {
                                                         System.out.println("hoiuduhz");
                                                             Midlet.INSTANCE.disp.setCurrent(new EditProject("Edit",projects[this.getSelectedIndex()].getId(),projects[this.getSelectedIndex()]));
    
                  
         
         
        }
    }

    public void run() {
        try {
            addCommand(Sponsor);
            setCommandListener(this);
            addCommand(cmdRefresh);
            addCommand(feebak);
            addCommand(sh);
            addCommand(edit);
            ProjectHandler peopleHandler = new ProjectHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            System.out.println(Midlet.u.getId());
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/parsing2016/select.php?nom="+Midlet.u.getId());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);
            // display the result
            projects = peopleHandler.getPeople();

          System.out.println(projects.length);
                for (int i = 0; i < projects.length; i++) {
                    append(i+1+"-"+projects[i].getName()+"\n------------------------------------------", null);
                       
             
         
            }
        } catch (ParserConfigurationException ex) {
             System.out.println(ex.getMessage());
        } catch (SAXException ex) {
             System.out.println(ex.getMessage());
        } catch (IOException ex) {
          System.out.println(ex.getMessage());
        }

    }
    
        private String[] showNumbers(int i) {
            String t[]= new String[4];

        t[0]="Owner :"+projects[i].getOwner()+"\n";
t[1]=  "Project Discrition :"+projects[i].getDiscription()+"\n";
 t[2]=    "CreationDate :"+projects[i].getDate()+"\n";     
              
                  t[3]="Help ype :"+projects[i].getHelpType()+"\n";
               
               
 
return t;

}
        
        
}
