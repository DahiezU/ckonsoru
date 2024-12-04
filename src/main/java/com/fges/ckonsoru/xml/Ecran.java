package com.fges.ckonsoru.xml;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Ecran {
	public void lecran() throws SAXException, IOException, TransformerException, ParserConfigurationException {
		FonctionalitesDAO fonct = new FonctionalitesDAO();
        
        String choix = "Actions disponibles : \n"
                + "1: Afficher les créneaux disponibles pour une date donnée\r\n"
                + "2: Lister les rendez-vous passés, présent et à venir d un client\r\n"
                + "3: Prendre un rendez-vous\r\n"
                + "4: Supprimer un rendez-vous\r\n"
                + "9. Quitter";
        Scanner scanIn = new Scanner(System.in);
        int numero = scanIn.nextInt();
		while(numero != 9){

		            switch(numero) {
		                case 1:
		                    
		                    String explicationStrDispo = "Entrer une date au format JJ/MM/AAAA (ex: 18/03/2021) :";
		                    System.out.println(explicationStrDispo);
		                    
		                    Scanner scannCrenaux = new Scanner(System.in);
		                    String DateIn =  scannCrenaux.nextLine();
		                    
		
		                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		                    LocalDateTime debut = LocalDateTime.parse(DateIn+" 11:50", timeFormatter);
		                    fonct.AfficherListDispo(fonct.getRDVVeto(debut));
		                    //scannCrenaux.close();
		
		                    System.out.println(choix);
		                    System.out.println("Entrer un numéro d action:");
		                    numero = scanIn.nextInt();
		                break;
		                case 2:
		                    String explicationStrRdvCli = "Affichage des rendez-vous d un client \n" + "Indiquer le nom du client";
		                    System.out.println(explicationStrRdvCli);
		                    Scanner scanNom = new Scanner(System.in);
		                    String nomCli =  scanNom.nextLine();
		                    
		                    fonct.AfficherListRDV(fonct.getAllRDV(nomCli));
		
		                    System.out.println(choix);
		                    System.out.println("Entrer un numéro d action:");
		                    numero = scanIn.nextInt();
		                break;
		                case 3:
		                    String explicationStrPriseRdv = "Prise de rendez-vous \n" + "Indiquer une date et heure de début au format JJ/MM/AAAA HH:MM (ex: 18/03/2021 15:00)";
		                    System.out.println(explicationStrPriseRdv);
		                    
		                    Scanner scanDateRdv = new Scanner(System.in);
		                    String dateRdv =  scanDateRdv.nextLine();
		
		                    System.out.println("ndiquer le nom du vétérinaire" );                   
		                    Scanner scanNomRdv = new Scanner(System.in);
		                    String nomVetRdv =  scanNomRdv.nextLine();
		
		                    System.out.println("Indiquer le nom du client");
		                    Scanner scanNomCliRdv = new Scanner(System.in);
		                    String nomCliRdv =  scanNomCliRdv.nextLine();
		                    
		                    fonct.addRDV(nomVetRdv, dateRdv,nomCliRdv);
		                    System.out.println(choix);
		                    System.out.println("Entrer un numéro d action:");
		                    numero = scanIn.nextInt();
		                break;
		                case 4:
		                    String explicationSupprimerRdv = "Indiquer une date et heure de début au format JJ/MM/AAAA HH:MM (ex: 18/03/2021 15:00)";
		                    System.out.println(explicationSupprimerRdv);
		                    
		                    Scanner scanDateCliSupprRDV = new Scanner(System.in);
		                    String DateRdvsupp = scanDateCliSupprRDV.nextLine();
		
		                    System.out.println("Indiquer le nom du client");
		                    Scanner scanNomCliSupprRDV = new Scanner(System.in);
		                    String nomCliRdvsupp = scanNomCliSupprRDV.nextLine();
		
		
		                    //String essai = "27/05/2021 08:20";
		                    
		                    fonct.SupprRdv(DateRdvsupp, nomCliRdvsupp);
		                    
		                    System.out.println(choix);
		                    System.out.println("Entrer un numéro d action:");
		                    numero = scanIn.nextInt();
		                break;
		                case 9:
		                    System.exit(0);
		                    break;
		                default:
		                    System.out.println("Excusez moi je n'ai pas compris \n");
		                    System.out.println(choix);
		                    System.out.println("Entrer un numéro d action:");
		                    numero = scanIn.nextInt();
		            }
		
		        }
		        scanIn.close();
		        	
			}
}
