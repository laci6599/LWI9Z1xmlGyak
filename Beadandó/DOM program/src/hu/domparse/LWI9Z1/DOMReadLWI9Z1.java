package hu.domparse.LWI9Z1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DOMReadLWI9Z1 {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

	       try {
	   		File xmlFile = new File("src/hu/domparse/LWI9Z1/XMLlwi9z1.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();		
			System.out.println("Nagy LĂˇszlĂł LWI9Z1 XML fĂ©lĂ©ves feladat");
			Action(doc);	   
	       }catch (ParserConfigurationException pce) {
	           pce.printStackTrace();
	       } catch (TransformerException tfe) {
	        tfe.printStackTrace();
	       } catch (IOException ioe) {
	        ioe.printStackTrace();
	       } catch (SAXException sae) {
	        sae.printStackTrace();
	       }
	}

	public static void Action(Document doc) throws TransformerException {
		System.out.println("\nOlvasni vagy modosĂ­tani szeretne?");
		System.out.println("1 - olvasĂˇs");
		System.out.println("2 - mĂłdosĂ­tĂˇs");
		int action = ReadCategory();

		switch (action) {
		case 1:
			Read(doc);
			break;
		case 2:
			Update(doc);
			break;
		default:
			Action(doc);
			break;
		}

	}

	//Kategória beolvasása
	public static int ReadCategory() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\nAdja meg a sorszĂˇmot:");
		int readCategory = scan.nextInt();
		return readCategory;
	}
	
	//Módosítani kívánt adat sorszámának bekérése
	public static void Update(Document doc) throws TransformerException {
		System.out.println("\nXML MĂłdosĂ­tĂˇs\n");
		System.out.println("KĂ©rem adja meg mit szeretne mĂłdosĂ­tani");
		System.out.println("1 - szĂ­nhĂˇz\n2 - igazgatĂł\n3 - elĹ‘adĂˇs\n4 - elĹ‘adĂł\n5 - jegy");
		int category = 0;
		category = ReadCategory();
		ShowElementUpdates(category, doc);
	}
	
	//Olvasni kívánt adatok sorszámának bekérése
	public static void Read(Document doc) {
		System.out.println("\nXML OlvasĂˇs\n");
		System.out.println("KĂ©rem adja meg mit szeretne olvasni");
		System.out.println("1 - szĂ­nhĂˇz\n2 - igazgatĂł\n3 - elĹ‘adĂˇs\n4 - elĹ‘adĂł\n5 - jegy");
		int category = 0;
		category = ReadCategory();
		ShowCategoryElements(category, doc);
	}

	public static void ShowCategoryElements(int category, Document doc) {
		switch (category) {
		case 1:
			ReadTheatre(doc);
			break;
		case 2:
			ReadPrincipal(doc);
			break;
		case 3:
			ReadShow(doc);
			break;
		case 4:
			ReadActor(doc);
			break;
		case 5:
			ReadTicket(doc);
			break;
		default:
			int newCategory = ReadCategory();
			ShowCategoryElements(newCategory, doc);
			break;
		}
	}

	public static void ShowElementUpdates(int category, Document doc) throws TransformerException {
		switch (category) {
		case 1:
			DOMModifyLWI9Z1.UpdateTheatre(doc);
			break;
		case 2:
			DOMModifyLWI9Z1.UpdatePrincipal(doc);
			break;
		case 3:
			DOMModifyLWI9Z1.UpdateShow(doc);
			break;
		case 4:
			DOMModifyLWI9Z1.UpdateActor(doc);
			break;
		case 5:
			DOMModifyLWI9Z1.UpdateTicket(doc);
			break;
		default:
			int newCategory = ReadCategory();
			ShowElementUpdates(newCategory, doc);
			break;
		}
	}
	
	public static void ReadTheatre(Document doc) {
		
		NodeList nList = doc.getElementsByTagName("theatre");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String theatreid = element.getAttribute("id");
				String principalid = element.getAttribute("principalid");
				String showid = element.getAttribute("showid");

				Node node1 = element.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				String zipcode = "";
				String country = "";
				String city = "";

				for (int j = 0; j < nList.getLength(); j++) {

					Node nnode1 = element.getElementsByTagName("zipcode").item(0);
					Node cnode1 = null;
					country = cnode1.getTextContent();

					Node nnode2 = element.getElementsByTagName("country").item(0);
					Node cnode2 = null;
					country = cnode2.getTextContent();
					
					Node cnode3 = element.getElementsByTagName("city").item(0);
					city = cnode3.getTextContent();

				}

				System.out.println("SzĂ­nhĂˇz id:" + theatreid + "\tNev: " + name + "\tIrsz: " + zipcode + "\tOrszĂˇg: " + country + "\tVĂˇros: " + city);
			}
		}

	}

	public static void ReadPrincipal(Document doc) {

		NodeList nList = doc.getElementsByTagName("principal");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String principalid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				System.out.println("IgazgatĂł id:" + principalid + "\tNev: " + name);
			}
		}

	}

	public static void ReadShow(Document doc) {
		
		NodeList nList = doc.getElementsByTagName("show");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String showid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("title").item(0);
				String title = node1.getTextContent();

				Node node2 = element.getElementsByTagName("genre").item(0);
				String genre = node2.getTextContent();

				Node node3 = element.getElementsByTagName("length").item(0);
				String length = node3.getTextContent();

				Node node4 = element.getElementsByTagName("director").item(0);
				String director = node4.getTextContent();

				System.out.println("ElĹ‘adĂˇs id:\t" + showid + "\tCĂ­m:\t" + title + "\tMĹ±faj:\t" + genre + "\tIdĹ‘tartam:\t" + length + "\tRendezĹ‘:\t" + director);
			}
		}
	}

	public static void ReadActor(Document doc) {

		NodeList nList = doc.getElementsByTagName("actor");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String actorid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				Node node2 = element.getElementsByTagName("dramagroup").item(0);
				String dramagroup = node2.getTextContent();

				String dateplace = "";
				String datetime = "";

				for (int j = 0; j < cList.getLength(); j++) {

					Node cnode1 = element.getElementsByTagName("dateplace").item(0);
					dateplace = cnode1.getTextContent();

					Node cnode2 = element.getElementsByTagName("datetime").item(0);
					datetime = cnode2.getTextContent();

				}

				System.out.println("ElĹ‘adĂł id:" + actorid + "\tNĂ©v: " + name + "\tTĂˇrsulat: " + dramagroup
						+ "\tSzĂĽletĂ©si hely: " + dateplace + "\tSzĂĽletĂ©si idĹ‘: " + datetime);
			}
		}
	}

	public static void ReadTicket(Document doc) {

		NodeList nList = doc.getElementsByTagName("ticket");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String ticketid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("category").item(0);
				String category = node1.getTextContent();

				Node node2 = element.getElementsByTagName("price").item(0);
				String price = node2.getTextContent();

				System.out.println("Jegy id:" + ticketid + "\tKategĂłria: " + category + "\tĂ�r: " + price);
			}
		}

	}
	
}