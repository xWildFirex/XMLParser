import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static final String COUNTRY_TAG = "country";
    public static final String COUNTRY_CODE = "countryCode";
    public static final String COUNTRY_NAME = "countryName";
    public static final String CONTINENT_NAME = "continentName";
    public static final String CAPITAL = "capital";
    public static String filePath;
    public static List<DataCountries> dataCountriesList;


    public XmlParser(String filePath) {
        this.filePath = filePath;
        parseDOM();
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        for (DataCountries item : dataCountriesList){
            toString(stringBuffer, item).append('\n');
        }
        return stringBuffer.toString();
    }

    private StringBuffer toString(StringBuffer stringBuffer, DataCountries dataCountries) {
        stringBuffer.append(dataCountries.getCountryCode()).append('\n');
        stringBuffer.append(dataCountries.getCountryName()).append('\n');
        stringBuffer.append(dataCountries.getContinentName()).append('\n');
        stringBuffer.append(dataCountries.getCapital()).append('\n');
        stringBuffer.append("----------------");
        return stringBuffer;
    }

    private static List<DataCountries> parseDOM(){

        dataCountriesList = new ArrayList<DataCountries>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));


            NodeList nodeList = document.getElementsByTagName(COUNTRY_TAG);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element itemElement = (Element) nodeList.item(i);
                String countryCode = getValue(itemElement, COUNTRY_CODE);
                String countryName = getValue(itemElement, COUNTRY_NAME);
                String continentName = getValue(itemElement, CONTINENT_NAME);
                String capital = getValue(itemElement, CAPITAL);
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
