package com.fges.ckonsoru.xml;

import java.io.File;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import org.w3c.dom.Node;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;


public class SupprimerRDV {
	
	public void SupprRdvSuppr(String Date, String Client) throws TransformerException, SAXException {
	
		
		
		try {
			// charger le fichier xml
			
			Document xmldoc = ConnexionXmlSingleton.getInstance().getDoc();
			NodeList RDV = xmldoc.getElementsByTagName("rdv");

			String LeJour = Date.substring(0,2);
	        String LeMois = Date.substring(3,5);
	        String LAnnee = Date.substring(6,10);
	        String Heure = Date.substring(11,13);
			String Minutes = Date.substring(14,Date.length());
			String LaDate = LAnnee+"-"+LeMois+"-"+LeJour+"T"+Heure+":"+Minutes+":00";
			
		
			
			for (int i = 0; i < RDV.getLength(); i++) {
				Element product = (Element) RDV.item(i);
				Element dateTag = (Element) product.getElementsByTagName("debut").item(0);
				Node clientTag = product.getElementsByTagName("client").item(0);
				
				if (dateTag.getTextContent().equalsIgnoreCase(LaDate) && clientTag.getTextContent().equalsIgnoreCase(Client)) {
					
					clientTag.getParentNode().getParentNode().removeChild(RDV.item(i));
					
					
				}
			}
			

			
			// enregistrer le fichier
				
				DOMSource source = new DOMSource(xmldoc);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = null;
				try {
					
					transformer = transformerFactory.newTransformer();
					/*transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");*/
					

				} catch (TransformerConfigurationException e) {
				
					e.printStackTrace();
				}
				StreamResult result = new StreamResult(".\\src\\main\\resources\\ckonsoru.xml");
				transformer.transform(source, result);
		} catch (IOException e) {
		e.printStackTrace(System.err);
		}
		return;
	}


}
