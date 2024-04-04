package com.xmlToJson.xmlToJson;



import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;


public class Demo {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File("F:\\Intership\\SpringTutorial\\xmlToJson\\src\\main\\java\\com\\xmlToJson\\xmlToJson\\demo.xml");
        Document doc = builder.parse(file);
        System.out.println(doc.getNodeName()); //op: #document
        System.out.println(doc.getDocumentElement()); //root : [student: null]
        System.out.println(doc.getDocumentElement().getFirstChild() instanceof Element); // true
        NodeList childNodes = doc.getDocumentElement().getChildNodes();
        Node node = childNodes.item(0);
        System.out.println(node.getNodeName()); //firstName
        System.out.println(node.getFirstChild() instanceof Element); //false
        System.out.println(doc.getElementsByTagName("student").item(0)); //[student: null]

    }
}
