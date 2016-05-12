/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Entities.article;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author FC
 */
public class articleHandler extends DefaultHandler {
    
private Vector peopleVector;
 
    public articleHandler() {
        peopleVector = new Vector();
    }
 
    public article[] getPeople() {
        article[] articleTab = new article[peopleVector.size()];
        peopleVector.copyInto(articleTab);
        return articleTab;
    }
    
    private article currentarticle; 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("article")) {
		    // create new Person object
            
            // "attributes" holds name and value pairs from inside the tag
          int id = Integer.parseInt(attributes.getValue("id"));
         //   String familyName = attributes.getValue("familyname");
            currentarticle = new article();
            currentarticle.setId(id);
            currentarticle.setType(attributes.getValue("type"));
            currentarticle.setAuthor(attributes.getValue("author"));
              currentarticle.setFilename(attributes.getValue("filename"));
    }
    }
public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("article")) {
            // add completed Person object to collection
            peopleVector.addElement(currentarticle);
            
            // we are no longer processing a <person.../> tag
            currentarticle = null;
 
    }
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
   
    }
}
