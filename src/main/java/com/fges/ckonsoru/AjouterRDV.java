package com.fges.ckonsoru;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;

public class AjouterRDV {
	File filepath = new File("C:\\Users\\dahie\\Documents\\SDN-S6\\design_pattern\\ckonsoru\\src\\main\\resources\\ckonsoru.xml");
	
	public void addRDV(String Veterinaire, String Date, String Client) throws SAXException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		//List<RendezVous> rdvs = new LinkedList<>();
		String sdate = Date.formatted(DateTimeFormatter.ISO_LOCAL_DATE);
		try {
			// charger le fichier xml
			
			builder = factory.newDocumentBuilder();
			String filepath = "C:\\Users\\dahie\\Documents\\SDN-S6\\design_pattern\\ckonsoru\\src\\main\\resources\\ckonsoru.xml";
			Document xmldoc = builder.parse(filepath);
			// cree un rdv avec client & veterinaire
			
			
			String LeJour = Date.substring(0,2);
	        String LeMois = Date.substring(3,5);
	        String LAnnee = Date.substring(6,10);
	        String Heure = Date.substring(11,13);
			String Minutes = Date.substring(14,Date.length());
			String LaDate = LAnnee+"-"+LeMois+"-"+LeJour+"T"+Heure+":"+Minutes+":00";
			
			
			
			System.out.println(LaDate);
				Element rdv = xmldoc.createElement("rdv");
				
				String newSeparator = "\n";
				System.setProperty("line.separator", newSeparator);
				
				Element date = xmldoc.createElement("debut");
				date.appendChild(xmldoc.createTextNode(LaDate));
				rdv.appendChild(date);
				
				 newSeparator = "\n";
				System.setProperty("line.separator", newSeparator);
				
				Element client = xmldoc.createElement("client");
				client.appendChild(xmldoc.createTextNode(Client));
				rdv.appendChild(client);
				
				 newSeparator = "\n";
				System.setProperty("line.separator", newSeparator);
				
				Element veterinaire = xmldoc.createElement("veterinaire");
				veterinaire.appendChild(xmldoc.createTextNode(Veterinaire));
				rdv.appendChild(veterinaire);
				
				
				
				
			// ajout au noeud rdvs
			
				NodeList nodes = xmldoc.getElementsByTagName("rdvs");
				nodes.item(0).appendChild(rdv);
			// enregistrer le fichier
				
				DOMSource source = new DOMSource(xmldoc);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = null;
				try {
					if(rdv.equals(transformer)) {
					transformer = transformerFactory.newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
					}else {
						transformer = transformerFactory.newTransformer();
					}

				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				StreamResult result = new StreamResult(filepath);
				transformer.transform(source, result);
		} catch (IOException | ParserConfigurationException e) {
		System.err.println("Erreur à l'ouverture de la bdd xml : " + filepath);
		e.printStackTrace(System.err);
		}
	}

}
