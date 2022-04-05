/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.utils;

import es.sacyl.hnss.testaliento.NewMain;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LeerXML {
    final static Logger log= LogManager.getLogger(NewMain.class);

public String devuelveValor(String constante) {
	String value=null;
	FileInputStream fileInputStream=null;
	try {
		File ExisteFichero= new File("./xml/properties.xml");
                if (!ExisteFichero.exists())
                {
                    log.fatal("Problemas graves. No existe el fichero properties.xml en la carpeta xml");
                    Utilidades utilidades=new Utilidades();
                    utilidades.salirPrograma("Salida en control ficheeros");
                }
		  fileInputStream = new FileInputStream("./xml/properties.xml");
		 DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		 org.w3c.dom.Document xmlDocument = documentBuilder.parse(fileInputStream);
		 
		  
		 Element rootElement = xmlDocument.getDocumentElement();
		 //NodeList elements = rootElement.getElementsByTagName("DIRECTORIO_PENDIENTE");
		 NodeList elements = rootElement.getElementsByTagName(constante);
 		 for (int i = 0; i < elements.getLength(); i++) {
			 Element element = (Element) elements.item(i);
			// String attribute = element.getAttribute("VALOR");
			  value = element.getChildNodes().item(0).getNodeValue();
			}
		 fileInputStream.close();
                 return value;
                 
		}
	
	
  catch (Exception e) {
    	e.printStackTrace();
    	return null;
  }
	finally {
		try {
			fileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 }
}
