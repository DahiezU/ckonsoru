package com.fges.ckonsoru.bdd;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fges.ckonsoru.metier.Client;

public class GestionAction {

    public  int selectChoix(){

        String choix = "Actions disponibles : \n"
        + "1: Afficher les créneaux disponibles pour une date donnée\r\n"
        + "2: Lister les rendez-vous passés, présent et à venir d un client\r\n"
        + "3: Prendre un rendez-vous\r\n"
        + "4: Supprimer un rendez-vous\r\n"
        + "9. Quitter";
        
        System.out.println(choix);
        System.out.println("Entrer un numéro d action:");

        int  numero = 0;
        //ConnexionBDD test = null;
        Scanner scanIn = null;

        try{
            scanIn = new Scanner(System.in);
            numero = scanIn.nextInt();
            
            //test = new ConnexionBDD();
        }catch(InputMismatchException e){
            System.out.println("Veullez rentrer un chiffre !");
            System.exit(0);

        }
        return numero;
    }



    public boolean compareDate(LocalDateTime dateRentre){
        LocalDateTime rightNow = LocalDateTime.now();
        boolean isBefore = dateRentre.isBefore(rightNow);
        return isBefore;

    }



    public LocalDateTime convertStringToDate(String dateString){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse(dateString, timeFormatter);
        return debut;

    }

    public void  doAction() throws SQLException{
            int numero = this.selectChoix();
            DateConv convertisseur = new DateConv();
          
            while(numero != 9){
                
    
                switch(numero) {
                    case 1: 

                        String explicationStrDispo = "Entrer une date au format JJ/MM/AAAA (ex: 18/03/2021) :";
                        System.out.println(explicationStrDispo);
                        
                        Scanner scannCrenaux = new Scanner(System.in);
                        String DateIn =  scannCrenaux.nextLine();
                        


                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime debut = LocalDateTime.parse(DateIn+" 01:00", timeFormatter);

            

                        GestionRdvDAO.ListerDisponibilite(debut);
                       

                        this.doAction();

                    break;


                    case 2:
                        String explicationStrRdvCli = "Affichage des rendez-vous d un client \n" + "Indiquer le nom du client";
                        System.out.println(explicationStrRdvCli);
                        Scanner scanNom = new Scanner(System.in);
                        String nomCli =  scanNom.nextLine();

                        Client cli = new Client(nomCli );
                    
                    
                        GestionRdvDAO.ListerRDV(cli);
                   
                    

                        this.doAction();
                    break;



                    case 3:
                        String explicationStrPriseRdv = "Prise de rendez-vous \n" + "Indiquer une date et heure de début au format JJ/MM/AAAA HH:MM (ex: 18/03/2021 15:00)";
                        System.out.println(explicationStrPriseRdv);
                        
                        Scanner scanDateRdv = new Scanner(System.in);
                        String dateRdv =  scanDateRdv.nextLine();



                      
                        
                        boolean boolTest =  this.compareDate(this.convertStringToDate(dateRdv));
                        while(boolTest){
                            System.out.println("Veuillez choisir une date ou une horaire future et non passé ");
                            System.out.println(explicationStrPriseRdv);
                            dateRdv =  scanDateRdv.nextLine();

                            boolTest = this.compareDate(this.convertStringToDate(dateRdv));

                        }
                       

                        System.out.println("Indiquer le nom du vétérinaire" );                   
                        Scanner scanNomRdv = new Scanner(System.in);
                        String nomVetRdv =  scanNomRdv.nextLine();

                        System.out.println("Indiquer le nom du client");
                        Scanner scanNomCliRdv = new Scanner(System.in);
                        String nomCliRdv =  scanNomCliRdv.nextLine();
                        
                        
                        cli = new Client(nomCliRdv  );
                        GestionRdvDAO.AjoutRDV(convertisseur.stringToDate(dateRdv) , cli ,nomVetRdv  );
                       
                        

                      
                        this.doAction();
                    break;


                    case 4:
                        String explicationSupprimerRdv = "Indiquer une date et heure de début au format JJ/MM/AAAA HH:MM (ex: 18/03/2021 15:00)";
                        System.out.println(explicationSupprimerRdv);
                        
                        Scanner scanDateCliSupprRDV = new Scanner(System.in);
                        String DateRdvsupp = scanDateCliSupprRDV.nextLine();



                        boolTest =  this.compareDate(this.convertStringToDate(DateRdvsupp));

                        while(boolTest){
                            System.out.println("Veuillez choisir une date ou une horaire future et non passé ");
                            System.out.println(explicationSupprimerRdv);
                            DateRdvsupp = scanDateCliSupprRDV.nextLine();

                         

                            boolTest =  this.compareDate(this.convertStringToDate(DateRdvsupp));

                        }


                        System.out.println("Indiquer le nom du client");
                        Scanner scanNomCliSupprRDV = new Scanner(System.in);
                        String nomCliRdvsupp = scanNomCliSupprRDV.nextLine();


                        
                          cli = new Client(nomCliRdvsupp );
                
                        
                        GestionRdvDAO.SupprimerRDV(convertisseur.stringToDate(DateRdvsupp) , cli );
                       
                      
                        
                        this.doAction();
                    break;

                    default:
                        System.out.println("Excusez moi je n'ai pas compris \\n");
                        this.doAction();
                }
            
            }
            if(numero == 9){
                System.exit(0);
        }
    }
    
}
