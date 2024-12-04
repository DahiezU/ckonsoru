package com.fges.ckonsoru.metier;

import java.util.List;

public class Client{
    private String nom;
    private List<RDV> rdvs;

    public  Client(String nom){
        this.nom = nom;
        
    }

    public String getNom(){
        return this.nom;
    }


    public List<RDV> getRdvs(){
        return 
        this.rdvs;
    }



}
