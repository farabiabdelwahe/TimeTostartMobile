package Handlers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import Entities.Projet;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ProjectHandler extends DefaultHandler {
    // this will hold all the data we read
    private Vector peopleVector;
 
    public ProjectHandler() {
        peopleVector = new Vector();
    }
 
    public Projet[] getPeople() {
        Projet[] personTab = new Projet[peopleVector.size()];
        peopleVector.copyInto(personTab);
        return personTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private Projet currentPerson; 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("projects")) {
		    // create new Person object
            
            // "attributes" holds name and value pairs from inside the tag
            String Name = attributes.getValue("Name");
            String DATE=attributes.getValue("DateCreation");
         //   String familyName = attributes.getValue("familyname");
            currentPerson = new Projet();
            
            currentPerson.setName(Name);
              currentPerson.setId(Integer.parseInt(attributes.getValue("Id")));
            currentPerson.setDate(DATE);
              currentPerson.setHelpType(attributes.getValue("HelpType"));
              currentPerson.setDiscription(attributes.getValue("Description"));
              currentPerson.setFile(attributes.getValue("File"));
              currentPerson.setType(attributes.getValue("Type"));
              currentPerson.setOwner(attributes.getValue("Owner"));
              
              
            
            
    
  
    }
    }
 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("projects")) {
            // add completed Person object to collection
            peopleVector.addElement(currentPerson);
            
            // we are no longer processing a <person.../> tag
            currentPerson = null;
 
    }
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
   
    }
    
}
