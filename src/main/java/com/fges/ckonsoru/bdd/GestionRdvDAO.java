package com.fges.ckonsoru.bdd;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.fges.ckonsoru.metier.Disponibilite;

import com.fges.ckonsoru.metier.RDV;

import com.fges.ckonsoru.metier.Client;

public class GestionRdvDAO {
    private static ConnexionBddDAO maCo = new ConnexionBddDAO();
   
    public static void ListerDisponibilite(LocalDateTime dateDonnee) throws SQLException {
        for (Disponibilite maDispo : maCo.ListerDisponibilite(dateDonnee)) {
            System.out.println(maDispo);
        }  
    }



   public static List<RDV> ListerRDV(Client monClient) throws SQLException{
        return maCo.ListerRDV(monClient);
    }




    public static void SupprimerRDV(LocalDateTime dateDonnee , Client nomClient) throws SQLException{ 
            maCo.SupprimerRDV(dateDonnee, nomClient);
       
    }



    public static void AjoutRDV(LocalDateTime dateDonnee , Client nomClient , String nomVet) throws SQLException{
            maCo.AjoutRDV(dateDonnee, nomClient, nomVet);
        
    }

    
}
