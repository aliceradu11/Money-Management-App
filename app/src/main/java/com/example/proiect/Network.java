package com.example.proiect;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Network extends AsyncTask<URL, Void, InputStream> {

    InputStream inputStream=null;
    static String result=null;
    public Currencies currencies;//object FXRate used to incapsulate the attrubutes

    @Override
    protected InputStream doInBackground(URL... urls) {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)urls[0].openConnection();
            conn.setRequestMethod("GET");
            inputStream = conn.getInputStream();

            Parsing(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static Node getNodeByName(String nodeName, Node parentNode) throws Exception {

        if (parentNode.getNodeName().equals(nodeName)) {
            return parentNode;
        }

        NodeList list = parentNode.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            Node node = getNodeByName(nodeName, list.item(i));
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    public static String getAttributeValue(Node node, String attrName) {
        try {
            return ((Element)node).getAttribute(attrName);
        }
        catch (Exception e) {
            return "";
        }
    }

    //parsing method which is called in doInBackground
    public void Parsing(InputStream ist)
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(ist);
            domDoc.getDocumentElement().normalize();

            currencies = new Currencies();

            //start parsing process
            Node cube = getNodeByName("Cube", domDoc.getDocumentElement());
            if(cube!=null)
            {
                //extracting the cube attribute (which is date)
                String date = getAttributeValue(cube, "date");
                currencies.setDate(date);

                //we continue to extract the childs of the Cube attribute
                NodeList childs = cube.getChildNodes();
                for(int i=0;i<childs.getLength();i++)
                {
                    Node node = childs.item(i);
                    //extracting the rate attribute (which is currency which is of multiple kinds)
                    String attribute = getAttributeValue(node, "currency");
                    if(attribute.equals("EUR"))
                        currencies.setEuro(node.getTextContent());
                    if(attribute.equals("USD"))
                        currencies.setUsd(node.getTextContent());
                    if(attribute.equals("GBP"))
                        currencies.setGbp(node.getTextContent());
                    if(attribute.equals("RUB"))
                        currencies.setRub(node.getTextContent());
                    if(attribute.equals("JPY"))
                        currencies.setJpy(node.getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
