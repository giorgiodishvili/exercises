package com.george.convertor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CurrencyConvertor {
    private final String currencyURL = "http://www.nbg.ge/rss.php";


    public Double exchangeRate(String from, String to){
        Map<String, Double> currencyMap = populateCurrencyMap();
        Double fromCurrency = currencyMap.get(from);
        Double toCurrency = currencyMap.get(to);
        if(from.equalsIgnoreCase("GEL")){
            return toCurrency;
        }else if(to.equalsIgnoreCase("GEL")){
            return fromCurrency;
        }

            return fromCurrency / toCurrency;
    }

    /**
     *
     *
     * @return map which is populated by currency 1 foreign currency is appropriate GEL amount
     */
    private Map<String, Double> populateCurrencyMap(){
        Map <String,Double> mp = new HashMap<>();

        try {
            Document doc = readXML(currencyURL);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//description/text()");

            //get appropriate subset
            Object result = expr.evaluate(doc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;

            //hardcoded as zero index came as title
            String textContent = nodes.item(1).getTextContent();

            //used Jsoup to parse html content
            org.jsoup.nodes.Document document =  Jsoup.parse(textContent);
            Element table = document.selectFirst("table");


            for (Element element : table.select("tr")) {
                Iterator<Element> ite = element.select("td").iterator();
                String currencyCode = ite.next().text();
                double quantityToGel = Double.parseDouble(ite.next().text().split(" ")[0]);
                double currencyToGel = Double.parseDouble(ite.next().text());
                mp.put(currencyCode, currencyToGel / quantityToGel);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return mp;
    }

    /**
     *
     * @param url url of xml data
     * @return document in xml state
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    private Document readXML(String url) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(new URL(url).openStream());
    }
}
