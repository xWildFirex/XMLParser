import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySaxParser extends DefaultHandler {
    List<DataCountries> dataCountriesesList = new ArrayList<DataCountries>();
    String xmlFileName;
    String tmpValue;
    String thisElement = "";
    DataCountries dataCountries = null;

    public MySaxParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        parseDocument();
    }

    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException: xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }

    }

    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;

    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
        if (qName.equals("country")){
            System.out.println("----------------------");
            dataCountriesesList.add(dataCountries);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("country")) {
            dataCountries = new DataCountries();
        }
        if (thisElement.equals("countryCode")) {
            dataCountries.setCountryCode(new String(ch, start, length));
            System.out.println(dataCountries.getCountryCode());
        }
        if (thisElement.equals("countryName")) {
            dataCountries.setCountryName(new String(ch, start, length));
            System.out.println(dataCountries.getCountryName());
        }
        if (thisElement.equals("continentName")) {
            dataCountries.setContinentName(new String(ch, start, length));
            System.out.println(dataCountries.getContinentName());
        }
        if (thisElement.equals("capital")) {
            dataCountries.setCapital(new String(ch, start, length));
            System.out.println(dataCountries.getCapital());
        }
    }

    public static void main (String args[]){
        new MySaxParser("C:\\countries.xml");
    }


}
