package hu.domparse.LWI9Z1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Scanner;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMModifyLWI9Z1 {

	public static String ReadId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nid:");
		String id = sc.nextLine();
		return id;
	}
	
	public static void CreateXML(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/hu/domparse/LWI9Z1/XMLlwi9z1.updated.xml"));
        transformer.transform(source, result);
	}
	
	//Bekérjük a módosítani kívánt színáz id-ját
	public static void UpdateTheatre(Document doc) throws TransformerException {

		System.out.println("\nMelyik színházat szeretnĂ© mĂłdosĂ­tani?\n");

		DOMReadLWI9Z1.ReadTheatre(doc);

		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("SzĂ­nhĂˇz neve: ");
		String name = sc.nextLine();
		System.out.print("SzĂ­nhĂˇz irĂˇnyĂ­tĂłszĂˇma: ");
		String zipcode = sc.nextLine();
		System.out.print("OrszĂˇga: ");
		String country = sc.nextLine();
		System.out.print("VĂˇrosa: ");
		String city = sc.nextLine();
		
		UpdateTheatreById(doc, id, name, zipcode, country, city);
	}

	//Módosítjuk a kívánt színházat
	public static void UpdateTheatreById(Document doc, String id, String name, String zipcode, String country, String city) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("theatre");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String theatreid = element.getAttribute("id");

				if (theatreid.equals(id)) {

					String principalid = element.getAttribute("principalid");
					String showid = element.getAttribute("showid");

					Node node1 = element.getElementsByTagName("name").item(0);
					node1.setTextContent(name);

					Node node2 = element.getElementsByTagName("zipcode").item(0);
					node2.setTextContent(zipcode);
					
					Node node3 = element.getElementsByTagName("country").item(0);
					node3.setTextContent(country);
					
					Node node4 = element.getElementsByTagName("city").item(0);
					node4.setTextContent(city);
					
					System.out.println("SzĂ­nhĂˇz ID:" + theatreid + "\tIgazgatĂł ID:" + principalid + "\tElĹ‘adĂˇs ID:"
							+ showid + "\tNĂ©v: " + node1.getTextContent() + "\tIrĂˇnyĂ­tĂłszĂˇm: " + node2.getTextContent()
							+ "\tOrszĂˇg: " + node3.getTextContent() + "\tVĂˇros:" + node4.getTextContent());

					System.out.println("\nSikeres mĂłdosĂ­tĂˇs tĂ¶rtĂ©nt!\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdatePrincipal(Document doc) throws TransformerException {
	
		System.out.println("\nMelyik igazgatĂłt szeretnĂ© mĂłdosĂ­tani?\n");

		DOMReadLWI9Z1.ReadPrincipal(doc);

		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("NĂ©v: ");
		String name = sc.nextLine();

		UpdatePrincipalById(doc, id, name);
		
	}

	public static void UpdatePrincipalById(Document doc, String id, String name) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("principal");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String principalid = element.getAttribute("id");

				if (principalid.equals(id)) {

					Node node1 = element.getElementsByTagName("name").item(0);
					node1.setTextContent(name);
					
					System.out.println("IgazgatĂł ID:" + principalid + "\tNĂ©v: " + node1.getTextContent());
					
					System.out.println("\nSikeres mĂłdosĂ­tĂˇs tĂ¶rtĂ©nt!\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdateShow(Document doc) throws TransformerException {

		System.out.println("\nMelyik elĹ‘adĂˇst szeretnĂ© mĂłdosĂ­tani?\n");

		DOMReadLWI9Z1.ReadShow(doc);
		
		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("CĂ­m: ");
		String title = sc.nextLine();
		System.out.print("MĹ±faj: ");
		String genre = sc.nextLine();
		System.out.print("IdĹ‘tartam: ");
		String length = sc.nextLine();
		System.out.print("RendezĹ‘: ");
		String director = sc.nextLine();
	
		UpdateShowById(doc, id, title, genre, length, director);
		
	}

	public static void UpdateShowById(Document doc, String id, String title, String genre, String length, String director) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("show");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String showid = element.getAttribute("id");

				if (showid.equals(id)) {

					Node node1 = element.getElementsByTagName("title").item(0);
					node1.setTextContent(title);

					Node node2 = element.getElementsByTagName("genre").item(0);
					node2.setTextContent(genre);
					
					Node node3 = element.getElementsByTagName("length").item(0);
					node3.setTextContent(length);
					
					Node node4 = element.getElementsByTagName("director").item(0);
					node4.setTextContent(director);

					System.out.println("ElĹ‘adĂˇs ID:" + id + "\tCĂ­m: " + node1.getTextContent() + "\tMĹ±faj: " + node2.getTextContent()
							+ "\tIdĹ‘tartam: " + node3.getTextContent() + "\tRendezĹ‘: " + node4.getTextContent());
										
					System.out.println("\nSikeres mĂłdosĂ­tĂˇs tĂ¶rtĂ©nt!\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdateActor(Document doc) throws TransformerException {

		System.out.println("\nMelyik elĹ‘adĂłt szeretnĂ© mĂłdosĂ­tani?\n");

		DOMReadLWI9Z1.ReadActor(doc);

		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("NĂ©v: ");
		String name = sc.nextLine();
		System.out.print("SzĂĽletĂ©si hely: ");
		String dateplace = sc.nextLine();
		System.out.print("SzĂĽletĂ©si idĹ‘: ");
		String datetime = sc.nextLine();
		System.out.print("TĂˇrsulat: ");
		String dramagroup = sc.nextLine();
	
		UpdateActorById(doc, id, name, dateplace, datetime, dramagroup);
		
	}

	public static void UpdateActorById(Document doc, String id, String name, String dateplace, String datetime, String dramagroup) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("actor");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String actorid = element.getAttribute("id");

				if (actorid.equals(id)) {

					Node node1 = element.getElementsByTagName("name").item(0);
					node1.setTextContent(name);

					Node node2 = element.getElementsByTagName("dramagroup").item(0);
					node2.setTextContent(dramagroup);
										
					for (int j = 0; j < cList.getLength(); j++) {

						Node cnode1 = element.getElementsByTagName("dateplace").item(0);
						cnode1.setTextContent(dateplace);

						Node cnode2 = element.getElementsByTagName("datetime").item(0);
						cnode2.setTextContent(datetime);

					}

					System.out.println("ElĹ‘adĂł ID:" + id + "\tNĂ©v: " + node1.getTextContent() + "\tTĂˇrsulat: " + node2.getTextContent()
							+ "\tSzĂĽletĂ©si adatok: " + dateplace + "." + datetime);
										
					System.out.println("\nSikeres mĂłdosĂ­tĂˇs tĂ¶rtĂ©nt!\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdateTicket(Document doc) throws TransformerException {

		System.out.println("\nMelyik jegyet szeretnĂ© mĂłdosĂ­tani?\n");

		DOMReadLWI9Z1.ReadTicket(doc);
		
		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("KategĂłria: ");
		String category = sc.nextLine();
		System.out.print("Ă�r: ");
		String price = sc.nextLine();
		
		UpdateTicketById(doc, id, category, price);
		
	}

	public static void UpdateTicketById(Document doc, String id, String category, String price) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("ticket");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String ticketid = element.getAttribute("id");

				if (ticketid.equals(id)) {

					Node node1 = element.getElementsByTagName("category").item(0);
					node1.setTextContent(category);

					Node node2 = element.getElementsByTagName("price").item(0);
					node2.setTextContent(price);
					
					System.out.println("Ticket ID:" + ticketid + "\tKategĂłria: " + node1.getTextContent() + "\tĂ�r: " + node2.getTextContent());
					
					System.out.println("\nSikeres mĂłdosĂ­tĂˇs tĂ¶rtĂ©nt!\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}

}