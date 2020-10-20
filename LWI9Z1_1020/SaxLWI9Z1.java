package com.journaldev.xml.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.journaldev.xml.Person;

public static void main(String[] args) {

      try {
         File inputFile = new File("input.txt");
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         UserHandler userhandler = new UserHandler();
         saxParser.parse(inputFile, userhandler);     
      } catch (Exception e) {
         e.printStackTrace();
      }
   }   
}

class MyHandler extends DefaultHandler {

    public class Person {
        private int id;
        private String name;
        private int age;
        private String city;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "Person: ID="+this.id+" Name=" + this.name + " Age=" + this.age + " City=" + this.city;
        }

    }

	// List to hold Persons object
	private List<Person> personList = null;
	private Person person = null;
	private StringBuilder data = null;

	// getter method for person list
	public List<Person> getPersonList() {
		return personList;
	}

    boolean bName = false;
	boolean bAge = false;
	boolean bCity = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("Person")) {
			// create a new Person and put it in Map
			String id = attributes.getValue("id");
			// initialize Person object and set id attribute
			person = new Person();
			person.setId(Integer.parseInt(id));
			// initialize list
			if (personList == null)
				personList = new ArrayList<>();
		} else if (qName.equalsIgnoreCase("name")) {
			// set boolean values for fields, will be used in setting Person variables
			bName = true;
		} else if (qName.equalsIgnoreCase("age")) {
			bAge = true;
		} else if (qName.equalsIgnoreCase("city")) {
			bCity = true;
		}
		// create the data container
		data = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (bAge) {
			// age element, set Person age
			emp.setAge(Integer.parseInt(data.toString()));
			bAge = false;
		} else if (bName) {
			emp.setName(data.toString());
			bName = false;
		} else if (bCity) {
			emp.setCity(data.toString());
			bCity = false;
		}
		
		if (qName.equalsIgnoreCase("Person")) {
			// add Person object to list
			personList.add(person);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}
}