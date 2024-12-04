package com.fges.ckonsoru.metier;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fges.ckonsoru.bdd.DateConv;
import com.fges.ckonsoru.metier.Client;

public class RDV {
    private LocalDateTime dateRdv ;
    private String veto;
    private Client client;

    public  RDV(LocalDateTime dateRdv,String monVet,Client monClient){
        this.dateRdv = dateRdv;
        this.veto = monVet;
        this.client = monClient;
    }


    public LocalDateTime getdateRdv(){
        return this.dateRdv;
    }


    public String getVeto(){
        return this.veto;
    }

    public Client getClient(){
        return this.client;
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime debut1 = LocalDateTime.parse(this.dateRdv.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        return ( debut1.format(timeFormatter)+ " avec " + this.veto) ;
    }



}
