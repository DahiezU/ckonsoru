package com.fges.ckonsoru.xml;


import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fges.ckonsoru.metier.Disponibilite;
import com.fges.ckonsoru.metier.RDV;



public class AfficherListe {

	
		
	//L. Belacqua : 18/03/2021 08:00

	public void AfficherListeAfficheDispo(ArrayList<Disponibilite> ListeRdv) {
		for (int i = 0; i<ListeRdv.size(); i++) {
			
			LocalDateTime datedure = ListeRdv.get(i).getDate();
			String vetodure = ListeRdv.get(i).getVeto();
			
			String LeJour = datedure.toString().substring(8,10);
	        String LeMois = datedure.toString().substring(5,7);
	        String LAnnee = datedure.toString().substring(0,4);
	        String Heure = datedure.toString().substring(11,13);
			String Minutes = datedure.toString().substring(14,16);
			
			String Complet = vetodure+ " : "+LeJour+"/"+LeMois+"/"+LAnnee+" "+Heure+":"+Minutes;
			System.out.println(Complet);
		}
		return;
	}
	
	public void AfficherListeAfficherRDV(ArrayList<RDV> ListeRdv) {
		for (int i = 0; i<ListeRdv.size(); i++) {
			
			LocalDateTime datedure = ListeRdv.get(i).getdateRdv();
			String vetodure = ListeRdv.get(i).getVeto();		
			
			String LeJour = datedure.toString().substring(8,10);
	        String LeMois = datedure.toString().substring(5,7);
	        String LAnnee = datedure.toString().substring(0,4);
	        String Heure = datedure.toString().substring(11,13);
			String Minutes = datedure.toString().substring(14,16);
			
			String Complet = LeJour+"/"+LeMois+"/"+LAnnee+" "+Heure+":"+Minutes+" avec "+ vetodure;
			System.out.println(Complet);
			
			
		}
		return;
	}
	
	
}
