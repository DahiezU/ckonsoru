package com.fges.ckonsoru.metier;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Disponibilite {
    private LocalDateTime date;
    private String veto ;

    public Disponibilite( LocalDateTime date, String nonVet){
        this.date = date;
        this.veto = nonVet;
    }

    public  LocalDateTime getDate(){
        return this.date;
    }

    public String getVeto(){
        return this.veto;
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime debut1 = LocalDateTime.parse(this.date.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        return ( this.veto + " : " + debut1.format(timeFormatter) ) ;
    }
  

}
