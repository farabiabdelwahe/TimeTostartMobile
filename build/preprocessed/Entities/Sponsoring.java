/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author GSC
 */
public class Sponsoring {
     private int id ;
        private String date;
        private String Type ;
         private String Text ;
         private Float sum;
         private User sponsor;
         private int isGoing;
         private String file ;
         private String filelint ;
         private Projet projet ;
         private String owner ;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
         
    public String toString() {
        return "Sponsoring{" + "id=" + id + ", date=" + date + ", Type=" + Type + ", Text=" + Text + ", sum=" + sum + ", sponsor=" + sponsor + ", isGoing=" + isGoing + ", file=" + file + ", filelint=" + filelint + ", projet=" + projet + '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
         
         
   


    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public User getSponsor() {
        return sponsor;
    }

    public void setSponsor(User sponsor) {
        this.sponsor = sponsor;
    }

    public int getIsGoing() {
        return isGoing;
    }

    public void setIsGoing(int isGoing) {
        this.isGoing = isGoing;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFilelint() {
        return filelint;
    }

    public void setFilelint(String filelint) {
        this.filelint = filelint;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
         
         

 

  
    
}
