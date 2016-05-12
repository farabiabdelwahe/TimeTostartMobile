package Handlers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.ID;
import Entities.User;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class UserHandler extends DefaultHandler {
    // this will hold all the data we read
    private Vector peopleVector;
 
    public UserHandler() {
        peopleVector = new Vector();
    }
 
    public User[] getPeople() {
        User[] personTab = new User[peopleVector.size()];
        peopleVector.copyInto(personTab);
        return personTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private User currentPerson;
    private ID currentPhoneNumber;
 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
		    // create new User object
            
            // "attributes" holds name and value pairs from inside the tag
            String givenName = attributes.getValue("givenname");
            String familyName = attributes.getValue("familyname");
            String passWord = attributes.getValue("password");
             String mail = attributes.getValue("email");
       
            
            currentPerson = new User(givenName,familyName,passWord,mail);
            if (givenName == null || familyName == null) {
                throw new IllegalArgumentException("Person requires both givenname and familyname");
            }
            
        } else if (qName.equals("phone")) {
            
            currentPhoneNumber = new ID();
            String type = attributes.getValue("type");
            
            currentPhoneNumber.setType(type);
        }
    }
 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            // add completed User object to collection
            peopleVector.addElement(currentPerson);
            
            // we are no longer processing a <person.../> tag
            currentPerson = null;
        } else if (qName.equals("phone")) {
            // add completed ID object to current User
            currentPerson.addPhoneNumber(currentPhoneNumber);
            // we are no longer processing a <phone.../> tag
            currentPhoneNumber = null;
        }
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPhoneNumber != null) {
            // don't forget to trim excess spaces from the ends of the string
            String number = new String(ch, start, length).trim();
            currentPhoneNumber.setNumber(number);
        }
    }
}