package com.fges.ckonsoru.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fges.ckonsoru.metier.Disponibilite;
import com.fges.ckonsoru.metier.RDV;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

import org.w3c.dom.Node;  


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Disponibilites {

	
	
	public ArrayList<Disponibilite> getAllDidponibiliteDispo(LocalDateTime Date) throws SAXException, IOException {
		
		ArrayList<Disponibilite> Liste = new ArrayList<Disponibilite>();
		Disponibilite Complet = new Disponibilite(Date, "");
		
		
		ArrayList<String> Vetodb = new ArrayList<String>();
		ArrayList<Disponibilite> ListDispo = new ArrayList<Disponibilite>();
		Document doc = ConnexionXmlSingleton.getInstance().getDoc(); 
		NodeList NodeList = doc.getElementsByTagName("veterinaire");
		
		for (int i = 0; i <NodeList.getLength() ; i++) {
			String Veterinaire = doc.getElementsByTagName("veterinaire").item(i).getTextContent();
			Vetodb.add(Veterinaire);
		}
		
		Set<String> mySet = new HashSet<String>(Vetodb);
		List<String> Veto = new ArrayList<String>(mySet);
		

		
		for (int i = 0; i <Veto.size() ; i++) {
			ListDispo = searchDisponibilite( Date, Veto.get(i));
			
			for (int j = 0; j <ListDispo.size() ; j++) {
				
				
				
				Complet = new Disponibilite(ListDispo.get(j).getDate() , ListDispo.get(j).getVeto());
				
				Liste.add(Complet);
				
				
				
			}
		}  
		return Liste;
	}
	
	
	public ArrayList<Disponibilite> searchDisponibilite (LocalDateTime Date, String Veterinaire) throws SAXException, IOException {
		
		ArrayList<Disponibilite> Disponibilites = new ArrayList<Disponibilite>();
		Disponibilite Complet = new Disponibilite(Date, "");
		try {
		ArrayList<Disponibilite> RendezVous = new ArrayList<Disponibilite>();
		ArrayList<Integer> Index = new ArrayList<Integer>();
		
		
		Disponibilites = getDisponibilites(Veterinaire,Date);
		
		
		RendezVous = getRDVVeto(Veterinaire, Date);
		
		for(int i = 0; i<Disponibilites.size(); i++) {
			
			for(int j = 0; j<RendezVous.size(); j++) {
				if(Disponibilites.get(i).getDate().equals(RendezVous.get(j).getDate())) {
					Index.add(i);
				}
			}
		}
		
		for(int i = Index.size()-1; i>=0; i--) {
			Disponibilites.remove(Index.get(i).intValue());
			
		}
		
		return Disponibilites;
	} catch (Exception e) {
		
		return Disponibilites;
		
		}
		
	}
	
	public ArrayList<Disponibilite> getDisponibilites(String veterinaire, LocalDateTime Date) throws SAXException, IOException {
		int Yout = 0;
		
		ArrayList<Disponibilite> Disponibilites = new ArrayList<Disponibilite>();
		
		 	String LeJour = Date.toString().substring(8, 10);
	        String LeMois = Date.toString().substring(5, 7);
	        String LAnnee = Date.toString().substring(0,4);
	       
		
		
		Document doc = ConnexionXmlSingleton.getInstance().getDoc();
		
		
		NodeList Journee = doc.getElementsByTagName("jour");
		
		
		for (int i = 0; i <Journee.getLength() ; i++) {
			
			
			
				if(Journee.item(i).getTextContent().equals(this.getDay(Date))) {
					
					
					String Dispo = doc.getElementsByTagName("debut").item(i).getTextContent();
					String Veterinaire = doc.getElementsByTagName("veterinaire").item(i).getTextContent();
					String Heure = "";
					String Minutes = "";
					Yout = 0;
				do {

					
					Heure = Dispo.substring(0,2);
					Minutes = Dispo.substring(3,5);
					
					if(Yout != 0) {
					Minutes = String.valueOf(Integer.parseInt (Minutes) + 20);
					
					}else {Yout = 1;}

					if(Minutes.equals("60")) {
						Minutes = "00";
						
						Heure = String.valueOf(Integer.parseInt (Heure) + 1);
						
						if(Integer.parseInt(Heure) < 10) {
							Heure = "0" + Heure;
						}
					}
					
					Dispo = Heure+":"+ Minutes;
					String Ligne =LAnnee +"-"+LeMois+"-"+ LeJour+ "T" +Dispo+":00";
					
					if(Veterinaire.equals(veterinaire)){
						
					
						DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						LocalDateTime debut1 = LocalDateTime.parse(Ligne, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
						
				
						Disponibilites.add(new Disponibilite(debut1  , Veterinaire));
					
				}
			    } while (Dispo.equals(doc.getElementsByTagName("fin").item(i).getTextContent()) == false);
					
				}
		}  
		Disponibilites.remove(Disponibilites.size()-1);
		
		
		return Disponibilites;
		
	}
	
	
	public ArrayList<Disponibilite> getRDVVeto(String veterinaire, LocalDateTime Date) {
		
		ArrayList<Disponibilite> RendezVous = new ArrayList<Disponibilite>();
		Disponibilite Complet = new Disponibilite(Date, "");
		
		try {
			Document doc = ConnexionXmlSingleton.getInstance().getDoc();
			
			NodeList RDV = doc.getElementsByTagName("rdv");
			Node DebutRendezVous = (Node) RDV.item(0).getChildNodes();
			//2021-03-18T14:00:00
			String LeJour = Date.toString().substring(8, 10);
	        String LeMois = Date.toString().substring(5, 7);
	        String LAnnee = Date.toString().substring(0,4);
	        
			String veto;
			
			for (int i = 0; i <RDV.getLength() ; i++) {
				
				
				
				DebutRendezVous = (Node) RDV.item(i).getChildNodes();
				
				veto = DebutRendezVous.getTextContent().toString();
				

				
				String[] parts = veto.split("\\n");
				String datedure = parts[1];
				
				String vetodure = parts[3];
				
				
				
				String Heure  = datedure.substring(11, 13);
		        String Minutes = datedure.substring(14,16);
		        
		        
		        
				if(vetodure.equals(veterinaire)) {
			
		        if(datedure.substring(0,10).equals(Date.toString().substring(0,10))) {
		        	
		        		
		        		
		        		String Ligne =LAnnee +"-"+LeMois+"-"+ LeJour+ "T" +Heure+":"+ Minutes+":00";
		        		
		        		
					
						DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						LocalDateTime debut1 = LocalDateTime.parse(Ligne, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
						
		        	
		        	Complet = new Disponibilite(debut1 , vetodure);
					RendezVous.add(Complet);
		        }
				}
			}

		} catch (Exception e) {
			System.out.println("problème : getRDVVeto()");
		}
		
		
		return RendezVous;
	}
	
	public String getDay(LocalDateTime dateDispo) {
		int dayofweek = dateDispo.getDayOfWeek().getValue();
		String jour = "";
		if(dayofweek == 1) {
			jour = "lundi";
		}
		if(dayofweek == 2) {
			jour = "mardi";
		}
		if(dayofweek == 3) {
			jour = "mercredi";
		}
		if(dayofweek == 4) {
			jour = "jeudi";
		}
		if(dayofweek == 5) {
			jour = "vendredi";
		}
		if(dayofweek == 6) {
			jour = "samedi";
		}
		if(dayofweek == 7) {
			jour = "dimanche";
		}
		return jour;
	}

	
}