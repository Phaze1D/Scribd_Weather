package com.steadypathapp.david.scribdweather;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by david on 11/8/16.
 */

public class XMLExtractor {


    private NodeList nodes;
    private Document doc;

    public XMLExtractor(String xml){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse( new InputSource( new StringReader( xml ) ) );
            nodes = doc.getElementsByTagName("yweather:forecast");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasErrors(){
        return nodes.getLength() == 0;
    }

    public String getTitle(){
        NodeList titles = doc.getElementsByTagName("title");

        if(titles.getLength() > 0) {
            return titles.item(0).getTextContent().replace("Yahoo! Weather - ", "");
        }
        return "Error";
    }

    public CityWeather[] getForecasts(){
        CityWeather[] items = new CityWeather[nodes.getLength()];
        for(int i = 0; i < nodes.getLength(); i++){
            Element node = (Element)nodes.item(i);
            items[i] = new CityWeather(
                                node.getAttribute("date"),
                                node.getAttribute("day"),
                                node.getAttribute("high"),
                                node.getAttribute("low"),
                                node.getAttribute("text")
                            );
        }
        return items;
    }

}


