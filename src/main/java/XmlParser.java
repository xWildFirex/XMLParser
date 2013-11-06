import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static void main(String args[]) {
        parseDOM();
    }

    private static List<DataCountries> parseDOM(){

        List<DataCountries> dataCountriesList = new ArrayList<DataCountries>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("C:\\countries.xml"));

            NodeList nodeList = document.getElementsByTagName("country");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element itemElement = (Element) nodeList.item(i);
                String countryCode = getValue(itemElement, "countryCode");
                String countryName = getValue(itemElement, "countryName");
                String continentName = getValue(itemElement, "continentName");
                String capital = getValue(itemElement, "capital");
                DataCountries dataCountries = new DataCountries(countryCode, countryName, continentName, capital);
                dataCountriesList.add(dataCountries);
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Parser Configuration Exception occurred");
            e.printStackTrace();
        } catch (SAXException e){
            System.out.println("SAX Exception occurred");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception occurred");
            e.printStackTrace();
        }
        return dataCountriesList;
    }

    public static String getValue(Element parent, String element) {
        NodeList n = parent.getElementsByTagName(element);
        return getElementValue(n.item(0));
    }

    public static String getElementValue( Node elem ) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

}
