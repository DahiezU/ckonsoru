package com.fges.ckonsoru.bdd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  class DateConv {


    public LocalDateTime stringToDate(String maDate){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse( maDate, timeFormatter);
        return debut;
    } 
   

    public String afficherGoodFormat(String maDate){

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse(maDate, timeFormatter);
       

        
        //System.out.println("Disponibilités pour le " + debut.format(timeFormatter));
        return debut.format(timeFormatter);
    }

    public LocalDateTime formatMerdeux(String date){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime debut = LocalDateTime.parse(date, timeFormatter);
        return debut;
    }
 
   
    public LocalDateTime ajout20Minute(LocalDateTime date){
        return date.plus(20,java.time.temporal.ChronoUnit.MINUTES);
    }
     
}
