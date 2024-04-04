package com.xmlToJson.xmlToJson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class IterateXML {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File("F:\\Intership\\SpringTutorial\\xmlToJson\\src\\main\\java\\com\\xmlToJson\\xmlToJson\\data.xml");
        Document document = builder.parse(file);
        JSONObject jsonObject = convert(document.getDocumentElement());
        JSONObject ans = new JSONObject();
        ans.put(document.getDocumentElement().getNodeName(), jsonObject);
        System.out.println(ans.toString());
    }

    private static JSONObject convert(Element element) {
        JSONObject json = new JSONObject();

        // Add attributes
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attribute = (Attr) attributes.item(i);
                json.put("@" + attribute.getName(), attribute.getValue());
            }
        }

        // Add child elements
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) node;
                String nodeName = childElement.getNodeName();
                Object value = null;

                NodeList grandChildNodes = childElement.getChildNodes();
                if (grandChildNodes.getLength() == 1 && grandChildNodes.item(0) instanceof Text) {
                    // Handle text nodes
                    value = grandChildNodes.item(0).getNodeValue().trim();
                } else {
                    // Recursively process child elements
                    value = convert(childElement);
                }

                // Check if the JSON already contains the node name
                if (json.has(nodeName)) {
                    Object existingValue = json.get(nodeName);
                    if (existingValue instanceof JSONArray) {
                        // If it's an array, add the value to the array
                        ((JSONArray) existingValue).put(value);
                    } else {
                        // If it's a single value, convert it to an array and add the new value
                        JSONArray tempArray = new JSONArray();
                        tempArray.put(existingValue);
                        tempArray.put(value);
                        json.put(nodeName, tempArray);
                    }
                } else {
                    // If the JSON doesn't contain the node name, simply put the value
                    json.put(nodeName, value);
                }
            }
        }

        return json;
    }
}
