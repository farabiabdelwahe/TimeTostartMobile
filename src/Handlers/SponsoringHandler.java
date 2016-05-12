/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Entities.Feedback;
import Entities.Projet;
import Entities.Sponsoring;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author GSC
 */
public class SponsoringHandler extends DefaultHandler {
        private Vector peopleVector;
 
    public SponsoringHandler() {
        peopleVector = new Vector();
    }
 
    public  Sponsoring[] getPeople() {
        Sponsoring[] personTab = new  Sponsoring[peopleVector.size()];
        peopleVector.copyInto(personTab);
        return personTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private  Sponsoring currentPerson; 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("feedback")) {
		    // create new Person object
            
            // "attributes" holds name and value pairs from inside the tag

    
         //   String familyName = attributes.getValue("familyname");
                String Name = attributes.getValue("Owner");
            String DATE=attributes.getValue("DateCreation");
            currentPerson = new  Sponsoring();
                  
                    
           currentPerson.setType(attributes.getValue("Type"));
           currentPerson.setDate(attributes.getValue("Date"));
           currentPerson.setOwner(attributes.getValue("Owner"));
          
              
           
    
              
              
            
            
    
  
    }
    }
 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("feedback")) {
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
