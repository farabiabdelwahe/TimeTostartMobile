/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Youssef
 */
public class report {
     private int idreport ;
   private String datesignal ;
    private String content ;
    private String user;
    private int idproject;

    public void setIdproject(int idproject) {
        this.idproject = idproject;
    }
  

    public int getIdproject() {
        return idproject;
    }

    public int getIdreport() {
        return idreport;
    }

    public String getDatesignal() {
        return datesignal;
    }

    public String getContent() {
        return content;
    }

    public String getuser() {
        return user;
    }


    public void setIdreport(int idreport) {
        this.idreport = idreport;
    }

    public void setDatesignal(String datesignal) {
        this.datesignal = datesignal;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setuser(String user) {
        this.user = user;
    }

    public report() {
    }
     
    public report(int idreport, String datesignal, String content,String user,int idproject) {
        this.idreport = idreport;
        this.datesignal = datesignal;
        this.content = content;
        this.user = user;
        this.idproject=idproject;
    }
    
}
