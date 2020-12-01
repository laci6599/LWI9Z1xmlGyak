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
			System.out.println("Nagy László LWI9Z1 XML féléves feladat");
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
		System.out.println("\nOlvasni vagy modosítani szeretne?");
		System.out.println("1 - olvasás");
		System.out.println("2 - módosítás");
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

	public static int ReadCategory() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\nAdja meg a sorszámot:");
		int readCategory = scan.nextInt();
		return readCategory;
	}
	
	public static void Update(Document doc) throws TransformerException {
		System.out.println("\nXML Módosítás\n");
		System.out.println("Kérem adja meg mit szeretne módosítani");
		System.out.println("1 - színház\n2 - igazgató\n3 - előadás\n4 - előadó\n5 - jegy");
		int category = 0;
		category = ReadCategory();
		ShowElementUpdates(category, doc);
	}
	
	public static void Read(Document doc) {
		System.out.println("\nXML Olvasás\n");
		System.out.println("Kérem adja meg mit szeretne olvasni");
		System.out.println("1 - színház\n2 - igazgató\n3 - előadás\n4 - előadó\n5 - jegy");
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
					country = cnode1.getTextContent();

					Node nnode2 = element.getElementsByTagName("country").item(0);
					country = cnode2.getTextContent();
					
					Node cnode3 = element.getElementsByTagName("city").item(0);
					city = cnode3.getTextContent();

				}

				System.out.println("Színház id:" + theatreid + "\tNev: " + name + "\tIrsz: " + zipcode + "\tOrszág: " + country + "\tVáros: " + city);
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

				System.out.println("Igazgató id:" + principalid + "\tNev: " + name);
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

				Node node1 = element.getElementsByTagName("genre").item(0);
				String genre = node1.getTextContent();

				Node node1 = element.getElementsByTagName("length").item(0);
				String length = node1.getTextContent();

				Node node1 = element.getElementsByTagName("director").item(0);
				String director = node1.getTextContent();

				System.out.println("Előadás id:\t" + showid + "\tCím:\t" + title + "\tMűfaj:\t" + genre + "\tIdőtartam:\t" + length + "\tRendező:\t" + director);
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

				System.out.println("Előadó id:" + actorid + "\tNév: " + name + "\tTársulat: " + dramagroup
						+ "\tSzületési hely: " + dateplace + "\tSzületési idő: " + datetime);
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

				Node node1 = element.getElementsByTagName("price").item(0);
				String price = node1.getTextContent();

				System.out.println("Jegy id:" + ticketid + "\tKategória: " + category + "\tÁr: " + price);
			}
		}

	}
	
}
