package com.fges.ckonsoru.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class ConnexionXmlSingleton {
	
	protected Document  doc = null;
	 private static ConnexionXmlSingleton  maConnexion = null;
	   
	    private ConnexionXmlSingleton() throws SAXException, IOException{
	        
	        
	        
	        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
	        	//an instance of builder to parse the specified xml file  
	        	DocumentBuilder db = null;
	        	try {
					db = dbf.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	this.doc = db.parse(".\\src\\main\\resources\\ckonsoru.xml"); 
	        	this.doc.getDocumentElement().normalize();  
	        
	        
	    }

	    public static ConnexionXmlSingleton getInstance() throws SAXException, IOException  {
	    	
	    	if (maConnexion == null) {
	            maConnexion = new  ConnexionXmlSingleton();
	        }
			return maConnexion;
	      
	    }
	    public Document getDoc() {
	    	return this.doc;
	    }

	    
	
	
	
}







