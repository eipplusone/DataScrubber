package com.pearson.DataScrubber;

/**
*
* @author jma1450
*/

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class xmlParsing {


  public static void main(String argv[]) {

    try {


  File fXmlFile = new File("dataMaskerRules.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);


	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("rule");

	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			System.out.println("rule id : " + eElement.getAttribute("id"));
			System.out.println("dependency : " + eElement.getElementsByTagName("dependency").item(0).getTextContent());
			System.out.println("column : " + eElement.getElementsByTagName("column").item(0).getTextContent());
			System.out.println("type : " + eElement.getElementsByTagName("type").item(0).getTextContent());


		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
  }

}


