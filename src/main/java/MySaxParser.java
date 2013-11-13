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
    public static final String COUNTRY = "country";
    public static final String COUNTRY_CODE = "countryCode";
    public static final String COUNTRY_NAME = "countryName";
    public static final String CONTINENT_NAME = "continentName";
    public static final String CAPITAL = "capital";
    List<DataCountries> dataCountriesList = new ArrayList<DataCountries>();
    String xmlFileName;
    String thisElement = "";
    DataCountries dataCountries = null;

    public MySaxParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        parseDocument();
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
            dataCountriesList.add(dataCountries);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals(COUNTRY)) {
            dataCountries = new DataCountries();
        }
        if (thisElement.equals(COUNTRY_CODE)) {
            dataCountries.setCountryCode(new String(ch, start, length));
        }
        if (thisElement.equals(COUNTRY_NAME)) {
            dataCountries.setCountryName(new String(ch, start, length));
        }
        if (thisElement.equals(CONTINENT_NAME)) {
            dataCountries.setContinentName(new String(ch, start, length));
        }
        if (thisElement.equals(CAPITAL)) {
            dataCountries.setCapital(new String(ch, start, length));
        }
    }

    public static void main (String args[]){
        new MySaxParser("C:\\countries.xml");
    }


}
