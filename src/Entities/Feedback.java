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
public class Feedback {
    
    private String text ;
    private User editeur ;

    private Projet projet ;
    private String Date ;
    private String owner ;

    public Feedback() {

    }



    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    
  
    


    public String getText() {
        return text;
    }

    public User getEditeur() {
        return editeur;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setEditeur(User editeur) {
        this.editeur = editeur;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    
    
    
}
