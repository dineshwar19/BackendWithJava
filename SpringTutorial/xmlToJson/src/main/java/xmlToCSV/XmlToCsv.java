package xmlToCSV;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlToCsv {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        try {
            File xmlFile = new File("F:\\Intership\\SpringTutorial\\xmlToJson\\src\\main\\java\\com\\xmlToJson\\xmlToJson\\data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            FileWriter csvWriter = new FileWriter("output.csv");
            Element root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    StringBuilder row = new StringBuilder();
                    NodeList childNodes = element.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            row.append(childNode.getTextContent()).append(",");
                        }
                    }
                    csvWriter.append(row.toString().replaceAll(",$", "")).append("\n");
                }
            }
            csvWriter.close();
            System.out.println("CSV file generated successfully.");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
