package Entities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Handlers.PeopleHandler;
import java.util.Date;

import java.util.Vector;


public class User {
    private String givenName;
    private String familyName;
      private String passWord;
        private String countRy;
            private String firstName;
      private Date dat;
        private String maIl;
        private int id;
    private Vector phoneNumbers;
     private String qualifiCation;
      private String lastName;
            private String pays;
            private String role;

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
   
 
    public User(String givenName, String familyName,String passWord,String maIl) {
        this.givenName = givenName;
        this.familyName = familyName;
           this.passWord = passWord;
           this.maIl = maIl;
        phoneNumbers = new Vector();
    }
      public User(String givenName, String familyName,String passWord,String maIl,int id) {
        
          this.givenName = givenName;
        this.familyName = familyName;
           this.passWord = passWord;
           this.maIl = maIl;
           this.id = id;
        phoneNumbers = new Vector();
    }
public String getMaIl() {
        return maIl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCountRy() {
        return countRy;
    }

    public void setCountRy(String countRy) {
        this.countRy = countRy;
    }

    public String getQualifiCation() {
        return qualifiCation;
    }

    public void setQualifiCation(String qualifiCation) {
        this.qualifiCation = qualifiCation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMaIl(String email) {
        this.maIl = email;
    }

    public User(String givenName, String familyName, String maIl, String qualifiCation, String lastName) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.maIl = maIl;
        this.qualifiCation = qualifiCation;
        this.lastName = lastName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getPassword() {
        return passWord;
    }
    public void setPassword(String passWord) {
        this.passWord = passWord;
    }
    public User() {
    }
 
    public String getFullName() {
        return familyName + ", " + givenName;
    }
     public String getData() {
        return "first name :  "+familyName + " " + "\nusername :"+givenName + "\nemail: "+maIl;
    }
 
    public void addPhoneNumber(ID pn) {
        phoneNumbers.addElement(pn);
    }
 
    public ID[] getPhoneNumbers() {
        ID[] numbers = new ID[phoneNumbers.size()];
        phoneNumbers.copyInto(numbers);
        return numbers;
    }

    public String toString() {
        return "User{" + "givenName=" + givenName + ", familyName=" + familyName + ", passWord=" + passWord + ", countRy=" + countRy + ", dat=" + dat + ", maIl=" + maIl + ", id=" + id + ", phoneNumbers=" + phoneNumbers + ", qualifiCation=" + qualifiCation + ", lastName=" + lastName + '}';
    }

 
   

    public Date getDat() {
return dat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
