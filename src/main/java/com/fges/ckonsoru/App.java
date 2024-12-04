
package com.fges.ckonsoru;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.fges.ckonsoru.bdd.GestionAction;
import com.fges.ckonsoru.xml.Ecran;
import org.xml.sax.SAXException;


public class App {

    public static void main(String args[]) throws ParseException, SAXException, IOException, TransformerException, SQLException, ParserConfigurationException{
      
       System.out.println("Bienvenue sur Clinique Konsoru !");
        
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();



        System.out.println("Mode de persistence : "
                +properties.getProperty("persistence"));


        if(properties.getProperty("persistence").equals("xml") ){
          

            String choix = "Actions disponibles : \n"
            + "1: Afficher les créneaux disponibles pour une date donnée\r\n"
            + "2: Lister les rendez-vous passés, présent et à venir d un client\r\n"
            + "3: Prendre un rendez-vous\r\n"
            + "4: Supprimer un rendez-vous\r\n"
            + "9. Quitter";
            
            System.out.println(choix);
            System.out.println("Entrer un numéro d action:");
                Ecran Ecran = new Ecran();
        
                Ecran.lecran();
        }else if(properties.getProperty("persistence").equals("bdd")){
           
            GestionAction maGestion = new GestionAction();
            maGestion.doAction();
        }
       
    
       
      


    }

}
