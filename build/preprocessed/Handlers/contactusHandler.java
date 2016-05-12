/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Entities.contactus;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Youssef
 */
public class contactusHandler extends DefaultHandler {
     private Vector peopleVector;
 
    public contactusHandler() {
        peopleVector = new Vector();
    }
 
    public contactus[] getPeople() {
        contactus[] reportTab = new contactus[peopleVector.size()];
        peopleVector.copyInto(reportTab);
        return reportTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private contactus currentcontactus; 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("contactus")) {
            System.out.print("eee");
		    // create new Person object
            
            // "attributes" holds name and value pairs from inside the tag
          
         //   String familyName = attributes.getValue("familyname");
            currentcontactus = new contactus();
            currentcontactus.setNumero(attributes.getValue("numero"));
             currentcontactus.setFax(attributes.getValue("fax"));
              currentcontactus.setAdresse(attributes.getValue("adresse"));
                    currentcontactus.setIdcontact(Integer.parseInt(attributes.getValue("idcontact")));
               currentcontactus.setA(attributes.getValue("a"));
                currentcontactus.setB(attributes.getValue("b"));

      
              
              
            
            
    
  
    }
    }
 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("contactus")) {
            // add completed Person object to collection
            peopleVector.addElement(currentcontactus);
            
            // we are no longer processing a <person.../> tag
            currentcontactus = null;
 
    }
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
   
    }
    
    
    
}
