package com.fges.ckonsoru.xml;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.fges.ckonsoru.metier.Client;
import com.fges.ckonsoru.metier.Disponibilite;
import com.fges.ckonsoru.metier.RDV;


public class FonctionalitesDAO {
	Disponibilites dispo = new Disponibilites();
	AfficherRDV affi = new AfficherRDV();
	AjouterRDV add = new AjouterRDV();
	SupprimerRDV suppr = new SupprimerRDV();
	AfficherListe affich = new AfficherListe();
	
	public ArrayList<Disponibilite> getRDVVeto( LocalDateTime dateRDV) throws SAXException, IOException{
		
		return dispo.getAllDidponibiliteDispo(dateRDV);
	}
	
	public ArrayList<RDV> getAllRDV(String client) throws SAXException, IOException, ParserConfigurationException{
		
		Client client1 = new Client(client);
		
		return affi.getAllRDVAfficher(client1);
	}
	
	public void addRDV(String Veterinaire, String Date, String Client) throws SAXException, TransformerException {
		
		add.addRDVAjouter(Veterinaire,  Date, Client);
		return;
	}
	
	public void SupprRdv(String Date, String Client) throws TransformerException, SAXException {
		
		suppr.SupprRdvSuppr( Date, Client);
		return;
	}
	
	public void AfficherListRDV(ArrayList<RDV> ListeRdv){
		
		affich.AfficherListeAfficherRDV(ListeRdv);
		return;
	}
	

	public void AfficherListDispo(ArrayList<Disponibilite> rdvVeto) {
		affich.AfficherListeAfficheDispo(rdvVeto);
		return;
		
	}
	
	
	
}
