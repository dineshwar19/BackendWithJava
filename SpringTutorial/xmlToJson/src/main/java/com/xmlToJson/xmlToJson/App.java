package com.xmlToJson.xmlToJson;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;  
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App 
{
	 public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        File file = new File("F:\\Intership\\SpringTutorial\\xmlToJson\\src\\main\\java\\com\\xmlToJson\\xmlToJson\\data.xml");
	        Document doc =  docBuilder.parse(file);
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
	        StringWriter writer = new StringWriter();
	        transformer.transform(new DOMSource(doc), new StreamResult(writer));
	        String xml = writer.getBuffer().toString().trim();
	        try {
	            JSONObject json = XML.toJSONObject(xml);
	            System.out.println(json);
	        } catch (JSONException e)  {
	            System.out.println(e.toString());
	      }
	 }
}
