package com.fges.ckonsoru.xml;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.fges.ckonsoru.metier.Client;
import com.fges.ckonsoru.metier.RDV;

public class AfficherRDV {
	

	
	public ArrayList<RDV> getAllRDVAfficher(Client Client) throws SAXException, IOException, ParserConfigurationException {
		LocalDateTime rienDate = null;
		ArrayList<RDV> RendezVous = new ArrayList<RDV>();
		
		RDV Complet = new RDV(rienDate, "veto", Client);
		
		Document doc = ConnexionXmlSingleton.getInstance().getDoc(); 
			
			NodeList RDVC = doc.getElementsByTagName("rdv");
			Node DebutRendezVous = (Node) RDVC.item(0).getChildNodes();
			
			
			
			String client;
			//String ligne = null;
			for (int i = 0; i <RDVC.getLength() ; i++) {
				
				
				
				DebutRendezVous = (Node) RDVC.item(i).getChildNodes();
				
				client = DebutRendezVous.getTextContent().toString();
				
				String[] parts = client.split("\\n");
				String datedure = parts[1];
				String clientdure = parts[2];
				String vetodure = parts[3];
				
				
				if(clientdure.equals(Client.getNom())) {
					
				 DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				 LocalDateTime debut1 = LocalDateTime.parse(datedure, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
					
					Complet = new RDV(debut1 ,vetodure, Client);
					RendezVous.add(Complet);
		        }
			}
		return RendezVous;
	}
	
	
	
	
}
