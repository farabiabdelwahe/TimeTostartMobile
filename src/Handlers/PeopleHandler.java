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


public class PeopleHandler extends DefaultHandler {
    
//    public static   String fname;
//public static String mal;
//public static   String pases;
//public static   int idd;
//public static   String gname;
    // this will hold all the data we read
    private Vector peopleVector;
 
    public PeopleHandler() {
        peopleVector = new Vector();
    }
 
    public User[] getPeople() {
        User[] personTab = new User[peopleVector.size()];
        peopleVector.copyInto(personTab);
        return personTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private User currentPerson;
    private static ID currentPhoneNumber;
 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("people")) {
		    // create new User object
            
            // "attributes" holds name and value pairs from inside the tag
            String givenName = attributes.getValue("givenname");
            String familyName = attributes.getValue("familyname");
            String passWord = attributes.getValue("password");
            String maIl = attributes.getValue("email");
            String qualifiCation = attributes.getValue("qual");
            String lastName = attributes.getValue("last");
             String countRy = attributes.getValue("pay");
             String role = attributes.getValue("role");
             
            int id = Integer.parseInt(attributes.getValue("id"));
         
//              fname=familyName;
//              mal=maIl;
//              pases=passWord;
//              gname=givenName;
              //idd=id;
              
        //
        currentPerson = new User();
            currentPerson.setId(id);
            System.out.println("PPPPPPPPPPPPPPPPPPPPPPP"+id);
            currentPerson.setGivenName(givenName);
            currentPerson.setLastName(lastName);
            currentPerson.setQualifiCation(qualifiCation);
            currentPerson.setMaIl(maIl);
            currentPerson.setPassword(passWord);
            currentPerson.setCountRy(countRy);
            currentPerson.setRole(role);
                System.out.println("DDDDDDDDDDDDDDDDDDDDD"+qualifiCation);
            currentPerson.setFamilyName(familyName);
            
//             User u = new User();
//                  
//                 
//            u.setId(currentPerson.getId());
//            u.setGivenName(currentPerson.getGivenName());
//            u.setMaIl(currentPerson.getMaIl());
//            u.setPassword(currentPerson.getPassword());
            System.out.println(currentPerson.getId()+"HATHA HOWA EL IDDDDDDDDDDDD");
      
    
        }
    }
 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("people")) {
            // add completed User object to collection
            peopleVector.addElement(currentPerson);
            
            // we are no longer processing a <person.../> tag
            currentPerson = null;
//        } 
        }
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
//        if (currentPhoneNumber != null) {
//            // don't forget to trim excess spaces from the ends of the string
//            String number = new String(ch, start, length).trim();
//            currentPhoneNumber.setNumber(number);
//        }
    }
}