package com.yxiao23.servlet;

import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.yxiao23.bean.Accessory;
import com.yxiao23.bean.FitnessWatches;
import com.yxiao23.bean.HeadPhones;
import com.yxiao23.bean.Laptops;
import com.yxiao23.bean.PetTracker;

import com.yxiao23.bean.SmartSpeaker;
import com.yxiao23.bean.SmartWatches;
import com.yxiao23.bean.VirtualReality;
import com.yxiao23.servlet.Phones;

public class SaxParserDataStore extends DefaultHandler {
    Phones phone;
    Laptops laptop;
    SmartSpeaker smartspeaker;
    
    FitnessWatches fitnesswatch;
    SmartWatches smartwatch;
    HeadPhones headphone;
    VirtualReality virtualreality;
    PetTracker pettracker;
    Accessory accessory;
    
    static HashMap<String,Phones> phonesMap;
    static HashMap<String,Laptops> laptopsMap;
    static HashMap<String,SmartSpeaker> smartspeakersMap;
    static HashMap<String,FitnessWatches> fitnesswatchMap;
    static HashMap<String,SmartWatches> smartwatchMap;
    static HashMap<String,HeadPhones> headphoneMap;
    static HashMap<String,VirtualReality> vrMap;
    static HashMap<String,PetTracker> pTMap;  
    static HashMap<String,Accessory> accessories;
    
    String XmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
    
	public SaxParserDataStore(String XmlFileName) {
    this.XmlFileName = XmlFileName;
    phonesMap = new HashMap<String, Phones>();
    laptopsMap = new  HashMap<String, Laptops>();
    smartspeakersMap = new HashMap<String, SmartSpeaker>();
    fitnesswatchMap = new HashMap<String, FitnessWatches>();
    smartwatchMap = new HashMap<String, SmartWatches>();
    headphoneMap = new HashMap<String, HeadPhones>();
    vrMap = new HashMap<String, VirtualReality>();
    pTMap = new HashMap<String, PetTracker>();
    accessories = new HashMap<String, Accessory>();
	accessoryHashMap = new HashMap<String,String>();
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(XmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e)	 {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}


	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
    	
    	
        if (elementName.equalsIgnoreCase("phone")) {
        	currentElement = "phone";
        	phone = new Phones();
        	phone.setId(attributes.getValue("id"));
        	
        }
		
        if (elementName.equalsIgnoreCase("laptop")){
        	currentElement="laptop";
        	laptop = new Laptops();
        	laptop.setId(attributes.getValue("id"));
        	
        }
		
        if (elementName.equalsIgnoreCase("smartspeaker")){
        	currentElement="smartspeaker";
        	smartspeaker = new SmartSpeaker();
        	smartspeaker.setId(attributes.getValue("id"));
        	
        }
        
        if (elementName.equalsIgnoreCase("fitnesswatch")){
        	currentElement="fitnesswatch";
        	fitnesswatch = new FitnessWatches();
        	fitnesswatch.setId(attributes.getValue("id"));
        	
        }
        
        if (elementName.equalsIgnoreCase("smartwatch")){
        	currentElement="smartwatch";
        	smartwatch = new SmartWatches();
        	smartwatch.setId(attributes.getValue("id"));
        	
        }
        
        if (elementName.equalsIgnoreCase("headphone")){
        	currentElement="headphone";
        	headphone = new HeadPhones();
        	headphone.setId(attributes.getValue("id"));
        	
        }
        
        if (elementName.equalsIgnoreCase("virtualreality")){
        	currentElement="virtualreality";
        	virtualreality = new VirtualReality();
        	virtualreality.setId(attributes.getValue("id"));
        	
        }
        
        if (elementName.equalsIgnoreCase("pettracker")){
        	currentElement="pettracker";
        	pettracker = new PetTracker();
        	pettracker.setId(attributes.getValue("id"));
        	
        }

        //
        if (elementName.equals("accessory") 
        		&& !currentElement.equals("fitnesswatch")
        		&& !currentElement.equals("smartwatch")
        		&& !currentElement.equals("headphone")
        		&& !currentElement.equals("virtualreality")
        		&& !currentElement.equals("pettracker")
        		&& !currentElement.equals("phone")
        		&& !currentElement.equals("laptop")
        		&& !currentElement.equals("smartspeaker")
        		){
        	currentElement = "Accessory";
        	accessory = new Accessory();
        	accessory.setId(attributes.getValue("id"));
        	System.out.println("lalalalalalal");
        	
        }


    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 

    	
        if (element.equals("phone")) {
        	phonesMap.put(phone.getId(),phone);
			return;
        }
 
        if (element.equals("laptop")) {	
        	laptopsMap.put(laptop.getId(),laptop);
			return;
        }
        if (element.equals("smartspeaker")) {	  
        	smartspeakersMap.put(smartspeaker.getId(),smartspeaker);
			return;
        }
        if (element.equals("fitnesswatch")) {	  
        	
        	fitnesswatchMap.put(fitnesswatch.getId(),fitnesswatch);
			return;
        }
        if (element.equals("smartwatch")) {	  
        	smartwatchMap.put(smartwatch.getId(),smartwatch);
			return;
        }
        if (element.equals("headphone")) {	  
        	headphoneMap.put(headphone.getId(),headphone);
			return;
        }
        if (element.equals("virtualreality")) {	  
        	vrMap.put(virtualreality.getId(),virtualreality);
			return;
        }
        if (element.equals("pettracker")) {	  
        	pTMap.put(pettracker.getId(),pettracker);
			return;
        }
        
        
        //to Encapsulate accessory Object into accessories(HashMap)
        if (element.equals("accessory") && currentElement.equals("Accessory")) {
        	accessories.put(accessory.getId(),accessory); 
			return; 
        }
        //to get accessory product name of FitnessWatches
		if (element.equals("accessory") && currentElement.equals("fitnesswatch")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
		//to encapsulate the product accessory to the related product Object.
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("fitnesswatch")) {
      		fitnesswatch.setAccessories(accessoryHashMap);
			accessoryHashMap = new HashMap<String,String>();
			return;
		}

      
  		if (element.equals("accessory") && currentElement.equals("smartwatch")) 
  		{
  			accessoryHashMap.put(elementValueRead,elementValueRead);
  		}
  		
    	if (element.equalsIgnoreCase("accessories") && currentElement.equals("smartwatch")) {
    		smartwatch.setAccessories(accessoryHashMap);
  			accessoryHashMap = new HashMap<String,String>();
  			return;
  		}
        	
    	if (element.equals("accessory") && currentElement.equals("headphone")) 
  		{
  			accessoryHashMap.put(elementValueRead,elementValueRead);
  		}
  		
    	if (element.equalsIgnoreCase("accessories") && currentElement.equals("headphone")) {
    		headphone.setAccessories(accessoryHashMap);
			accessoryHashMap = new HashMap<String,String>();
			return;
  		}   
        	
    	if (element.equals("accessory") && currentElement.equals("virtualreality")) 
  		{
  			accessoryHashMap.put(elementValueRead,elementValueRead);
  		}
  		
    	if (element.equalsIgnoreCase("accessories") && currentElement.equals("virtualreality")) {
    		virtualreality.setAccessories(accessoryHashMap);
			accessoryHashMap = new HashMap<String,String>();
			return;
  		} 
        	
    	if (element.equals("accessory") && currentElement.equals("pettracker")) 
  		{
  			accessoryHashMap.put(elementValueRead,elementValueRead);
  		}
  		
    	if (element.equalsIgnoreCase("accessories") && currentElement.equals("pettracker")) {
    		pettracker.setAccessories(accessoryHashMap);
			accessoryHashMap = new HashMap<String,String>();
			return;
  		} 
        	
    	if (element.equals("accessory") && currentElement.equals("phone")) 
  		{
  			accessoryHashMap.put(elementValueRead,elementValueRead);
  		}
  		
    	if (element.equalsIgnoreCase("accessories") && currentElement.equals("phone")) {
    		phone.setAccessories(accessoryHashMap);
			accessoryHashMap = new HashMap<String,String>();
			return;
  		} 
        	
    	if (element.equals("accessory") && currentElement.equals("laptop")) 
  		{
  			accessoryHashMap.put(elementValueRead,elementValueRead);
  		}
  		
    	if (element.equalsIgnoreCase("accessories") && currentElement.equals("laptop")) {
    		laptop.setAccessories(accessoryHashMap);
			accessoryHashMap = new HashMap<String,String>();
			return;
  		} 
    	
    	if (element.equals("accessory") && currentElement.equals("smartspeaker")) 
  		{
  			accessoryHashMap.put(elementValueRead,elementValueRead);
  		}
  		
    	if (element.equalsIgnoreCase("accessories") && currentElement.equals("smartspeaker")) {
    		smartspeaker.setAccessories(accessoryHashMap);
			accessoryHashMap = new HashMap<String,String>();
			return;
  		} 
      	
      	
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("fitnesswatch")) {
		    	fitnesswatch.setImage(elementValueRead);
		    	}
        	if(currentElement.equals("smartwatch"))
        		smartwatch.setImage(elementValueRead);
            if(currentElement.equals("headphone"))
            	headphone.setImage(elementValueRead);
            if(currentElement.equals("virtualreality"))
            	virtualreality.setImage(elementValueRead);
            if(currentElement.equals("pettracker"))
            	pettracker.setImage(elementValueRead);
            if(currentElement.equals("phone"))
            	phone.setImage(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setImage(elementValueRead);
            if(currentElement.equals("smartspeaker"))
            	smartspeaker.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);          
			return;
        }
        
        
		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("fitnesswatch"))
            	fitnesswatch.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("smartwatch"))
        		smartwatch.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("headphone"))
            	headphone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("virtualreality"))
            	virtualreality.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("pettracker"))
            	pettracker.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("phone"))
            	phone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptop"))
            	laptop.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("smartspeaker"))
            	smartspeaker.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }

		
		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("fitnesswatch"))
            	fitnesswatch.setCondition(elementValueRead);
        	if(currentElement.equals("smartwatch"))
        		smartwatch.setCondition(elementValueRead);
            if(currentElement.equals("headphone"))
            	headphone.setCondition(elementValueRead);
            if(currentElement.equals("virtualreality"))
            	virtualreality.setCondition(elementValueRead);
            if(currentElement.equals("pettracker"))
            	pettracker.setCondition(elementValueRead);
            if(currentElement.equals("phone"))
            	phone.setCondition(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setCondition(elementValueRead);
            if(currentElement.equals("smartspeaker"))
            	smartspeaker.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		
		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("fitnesswatch"))
            	fitnesswatch.setRetailer(elementValueRead);
        	if(currentElement.equals("smartwatch"))
        		smartwatch.setRetailer(elementValueRead);
            if(currentElement.equals("headphone"))
            	headphone.setRetailer(elementValueRead);
            if(currentElement.equals("virtualreality"))
            	virtualreality.setRetailer(elementValueRead);
            if(currentElement.equals("pettracker"))
            	pettracker.setRetailer(elementValueRead);
            if(currentElement.equals("phone"))
            	phone.setRetailer(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setRetailer(elementValueRead);
            if(currentElement.equals("smartspeaker"))
            	smartspeaker.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

		
        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("fitnesswatch"))
            	fitnesswatch.setName(elementValueRead);
        	if(currentElement.equals("smartwatch"))
        		smartwatch.setName(elementValueRead);
            if(currentElement.equals("headphone"))
            	headphone.setName(elementValueRead);
            if(currentElement.equals("virtualreality"))
            	virtualreality.setName(elementValueRead);
            if(currentElement.equals("pettracker"))
            	pettracker.setName(elementValueRead);
            if(currentElement.equals("phone"))
            	phone.setName(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setName(elementValueRead);
            if(currentElement.equals("smartspeaker"))
            	smartspeaker.setName(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("smartwatch"))
        		smartwatch.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("headphone"))
            	headphone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("virtualreality"))
            	virtualreality.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("pettracker"))
            	pettracker.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("phone"))
            	phone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptop"))
            	laptop.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("smartspeaker"))
            	smartspeaker.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
			return;
        }

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore("/Users/kimiyuya/Downloads/tomcat7/apache-tomcat-7.0.90/wtpwebapps/CSP584hw1/ProductCatalog.xml");
		
		//new SaxParserDataStore(TOMCAT_HOME+"/wtpwebapps/CSP584hw1/ProductCatalog.xml");
    } 
}
