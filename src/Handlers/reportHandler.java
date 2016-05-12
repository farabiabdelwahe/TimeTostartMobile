/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Entities.report;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Youssef
 */
public class reportHandler extends DefaultHandler {
     private Vector peopleVector;
 
    public reportHandler() {
        peopleVector = new Vector();
    }
 
    public report[] getPeople() {
        report[] reportTab = new report[peopleVector.size()];
        peopleVector.copyInto(reportTab);
        return reportTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private report currentreport; 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("report")) {
		    // create new Person object
            
            // "attributes" holds name and value pairs from inside the tag
          
         //   String familyName = attributes.getValue("familyname");
            currentreport = new report();
            currentreport.setIdreport(Integer.parseInt(attributes.getValue("idreport")));
            currentreport.setDatesignal(attributes.getValue("datesignal"));
            currentreport.setContent(attributes.getValue("content"));
            currentreport.setuser(attributes.getValue("nameuser"));
              currentreport.setIdproject(Integer.parseInt(attributes.getValue("idproject")));
      
              
              
            
            
    
  
    }
    }
 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("report")) {
            // add completed Person object to collection
            peopleVector.addElement(currentreport);
            
            // we are no longer processing a <person.../> tag
            currentreport = null;
 
    }
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
   
    }
    
    
    
}
