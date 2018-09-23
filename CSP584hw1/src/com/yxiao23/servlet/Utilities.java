package com.yxiao23.servlet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxiao23.bean.Accessory;
import com.yxiao23.bean.FitnessWatches;
import com.yxiao23.bean.HeadPhones;
import com.yxiao23.bean.Laptops;
import com.yxiao23.bean.PetTracker;
import com.yxiao23.bean.SmartSpeaker;
import com.yxiao23.bean.SmartWatches;
import com.yxiao23.bean.Users;
import com.yxiao23.bean.VirtualReality;
import com.yxiao23.db.OrderDB;
import com.yxiao23.servlet.OrderItem;
import com.yxiao23.servlet.OrderPayment;

import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	
	/*  Print SalesmanHome html Function gets the html file name as function Argument, 
	If the html file name is Header.html then It gets Username from session variables.
	Account ,Cart Information ang Logout Options are Displayed*/

	public void printSalesmanHomeHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "SalesmanHeader.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";			
			}
			else
				result = result + "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				
				pw.print(result);
		} else
				pw.print(result);
	}
		

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public Users getUser(){
		String usertype = usertype();
		HashMap<String, Users> hm=new HashMap<String, Users>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//UserInfo.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
		Users user = hm.get(username());
		return user;
	}
	
	/*  getUserList Function putting all the user in local file to a arraylist.*/
	public List<Users> getUserList(){
		String usertype = usertype();
		HashMap<String, Users> hm=new HashMap<String, Users>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//UserInfo.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
			List<Users> userList = new ArrayList<Users>(hm.values());

		return userList;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrderDB.orders.containsKey(username()))
			order= OrderDB.orders.get(username());
		return order;
	}
	
	/*  getCustomerOrders Function deletes  the Orders for the user*/
	public void deleteCustomerOrders(String itemName){
		ArrayList<OrderItem> orderItems = OrderDB.orders.get(username());
		  int index = 0;
		  //traverse orderItem to find the index of item to be removed
		  for(OrderItem oi : orderItems) {
		   if(oi.getProductName().equals(itemName)) {
		    break;
		   } else index++;
		  }
		  orderItems.remove(index);

	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=size + 1;
					 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	public List<OrderItem> showAllOrders() {
		List<Users> userList = getUserList(); 
		List<OrderItem> orders = new ArrayList<OrderItem>();
		
		for(int i = 0; i<userList.size();i++) {
			//orders= OrderDB.orders.get(userList.get(i));
			orders.addAll(OrderDB.orders.get(userList.get(i)));
		}
		//orders.addAll(OrderDB.orders.get(username()));

		return orders;
		//getUserList();
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrderDB.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrderDB.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrderDB.orders.get(username());
		if(type.equals("fitnesswatches")){
			FitnessWatches fw = null;
			fw = SaxParserDataStore.fitnesswatchMap.get(name);
			OrderItem orderitem = new OrderItem(fw.getName(), fw.getPrice(), fw.getImage(), fw.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("headphones")){
			HeadPhones hp = null;
			hp = SaxParserDataStore.headphoneMap.get(name);
			OrderItem orderitem = new OrderItem(hp.getName(), hp.getPrice(), hp.getImage(), hp.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("laptops")){
			Laptops laptop = null;
			laptop = SaxParserDataStore.laptopsMap.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("pettrackers")){
			PetTracker pt = null;
			pt = SaxParserDataStore.pTMap.get(name);
			OrderItem orderitem = new OrderItem(pt.getName(), pt.getPrice(), pt.getImage(), pt.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("smartspeakers")){
			SmartSpeaker ss = null;
			ss = SaxParserDataStore.smartspeakersMap.get(name);
			OrderItem orderitem = new OrderItem(ss.getName(), ss.getPrice(), ss.getImage(), ss.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("smartwatches")){
			SmartWatches sw = null;
			sw = SaxParserDataStore.smartwatchMap.get(name);
			OrderItem orderitem = new OrderItem(sw.getName(), sw.getPrice(), sw.getImage(), sw.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("virtualrealities")){
			VirtualReality vr = null;
			vr = SaxParserDataStore.vrMap.get(name);
			OrderItem orderitem = new OrderItem(vr.getName(), vr.getPrice(), vr.getImage(), vr.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("phones")){
			Phones phone = null;
			phone = SaxParserDataStore.phonesMap.get(name);
			OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("accessories")){	
			Accessory accessory = SaxParserDataStore.accessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer());
			orderItems.add(orderitem);
		}
		
	}
	//for storemanager to add Product
	public void addProduct(String productId,Object o ,String productType) {
		if(productType.equals("Fitness Watches")) {
			SaxParserDataStore.fitnesswatchMap.put(productId, (FitnessWatches) o);
		}
		if(productType.equals("HeadPhones")) {
			SaxParserDataStore.headphoneMap.put(productId, (HeadPhones) o);
		}
		if(productType.equals("Laptops")) {
			SaxParserDataStore.laptopsMap.put(productId, (Laptops) o);
		}
		if(productType.equals("PetTracker")) {
			SaxParserDataStore.pTMap.put(productId, (PetTracker) o);
		}
		if(productType.equals("Smart Speaker")) {
			SaxParserDataStore.smartspeakersMap.put(productId, (SmartSpeaker) o);
		}
		if(productType.equals("Smart Watches")) {
			SaxParserDataStore.smartwatchMap.put(productId,  (SmartWatches) o);
		}
		if(productType.equals("Virtual Reality")) {
			SaxParserDataStore.vrMap.put(productId,  (VirtualReality) o);
		}
		if(productType.equals("Phones")) {
			SaxParserDataStore.phonesMap.put(productId, (Phones) o);
		}
	}
	
	//for storemanager to remove Product
	public void removeProduct(String productId) {
		if(SaxParserDataStore.fitnesswatchMap.containsKey(productId)) {
			SaxParserDataStore.fitnesswatchMap.remove(productId);
		}
		if(SaxParserDataStore.headphoneMap.containsKey(productId)) {
			SaxParserDataStore.headphoneMap.remove(productId);
		}
		if(SaxParserDataStore.laptopsMap.containsKey(productId)) {
			SaxParserDataStore.laptopsMap.remove(productId);
		}
		if(SaxParserDataStore.phonesMap.containsKey(productId)) {
			SaxParserDataStore.phonesMap.remove(productId);
		}
		if(SaxParserDataStore.pTMap.containsKey(productId)) {
			SaxParserDataStore.pTMap.remove(productId);
		}
		if(SaxParserDataStore.smartspeakersMap.containsKey(productId)) {
			SaxParserDataStore.smartspeakersMap.remove(productId);
		}
		if(SaxParserDataStore.smartwatchMap.containsKey(productId)) {
			SaxParserDataStore.smartwatchMap.remove(productId);
		}
		if(SaxParserDataStore.vrMap.containsKey(productId)) {
			SaxParserDataStore.vrMap.remove(productId);
		}
	}
	//for storemanager to update product
	public void updateProduct(String productId,Object o ) {
		if(SaxParserDataStore.fitnesswatchMap.containsKey(productId)) {
			SaxParserDataStore.fitnesswatchMap.replace(productId, (FitnessWatches) o);
		}
		if(SaxParserDataStore.headphoneMap.containsKey(productId)) {
			SaxParserDataStore.headphoneMap.replace(productId,  (HeadPhones) o);
		}
		if(SaxParserDataStore.laptopsMap.containsKey(productId)) {
			SaxParserDataStore.laptopsMap.replace(productId,  (Laptops) o);
		}
		if(SaxParserDataStore.phonesMap.containsKey(productId)) {
			SaxParserDataStore.phonesMap.replace(productId,  (Phones) o);
		}
		if(SaxParserDataStore.pTMap.containsKey(productId)) {
			SaxParserDataStore.pTMap.replace(productId,  (PetTracker) o);
		}
		if(SaxParserDataStore.smartspeakersMap.containsKey(productId)) {
			SaxParserDataStore.smartspeakersMap.replace(productId,  (SmartSpeaker) o);
		}
		if(SaxParserDataStore.smartwatchMap.containsKey(productId)) {
			SaxParserDataStore.smartwatchMap.replace(productId, (SmartWatches) o);
		}
		if(SaxParserDataStore.vrMap.containsKey(productId)) {
			SaxParserDataStore.vrMap.replace(productId, (VirtualReality) o);
		}
	}
	
	// store the payment details for orders
	public void storePayment(
			int orderId,
			String orderName,
			double orderPrice,
			String userAddress,
			String creditCardNo,
			String recipientName){
		
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,recipientName,orderName,orderPrice,userAddress,creditCardNo);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try
			{	
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}
	
	// store the payment details for orders, same as above, just different in parameter
		public void AddPayment(OrderPayment op){
			
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
				// get the payment details file 
				try
				{
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
				}
				catch(Exception e)
				{
				
				}
				if(orderPayments==null)
				{
					orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
				}
				// if there exist order id already add it into same list for order id or create a new record with order id
				
				if(!orderPayments.containsKey(op.getOrderId())){	
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(op.getOrderId(), arr);
				}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(op.getOrderId());		
			//OrderPayment orderpayment = new OrderPayment(orderId,recipientName,orderName,orderPrice,userAddress,creditCardNo);
			listOrderPayment.add(op);	
				
				// add order details into file

				try
				{	
					FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	            	objectOutputStream.writeObject(orderPayments);
					objectOutputStream.flush();
					objectOutputStream.close();       
					fileOutputStream.close();
				}
				catch(Exception e)
				{
					System.out.println("inside exception file not written properly");
				}	
		}
	
	// return all the payment details for file
		public HashMap<Integer, ArrayList<OrderPayment>> getOrderPayment(){
			
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
				// get the payment details file 
				try
				{
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
				}
				catch(Exception e)
				{
				
				}
				if(orderPayments==null)
				{
					orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
				}
		
				
				return orderPayments;
		
		}

	// remove the payment details for orders
		public void removePayment(int orderId){
			
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
				// get the payment details file 
				try
				{
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
				}
				catch(Exception e)
				{
				
				}
				if(orderPayments==null)
				{
					orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
				}
				// if there exist order id already add it into same list for order id or create a new record with order id
				
				if(!orderPayments.containsKey(orderId)){	
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(orderId, arr);
				}
				
				orderPayments.remove(orderId);
//			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
//			OrderPayment orderpayment = new OrderPayment(orderId,recipientName,orderName,orderPrice,userAddress,creditCardNo);
//			listOrderPayment.add(orderpayment);	
				
				// add order details into file

				try
				{	
					FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	            	objectOutputStream.writeObject(orderPayments);
					objectOutputStream.flush();
					objectOutputStream.close();       
					fileOutputStream.close();
				}
				catch(Exception e)
				{
					System.out.println("inside exception file not written properly");
				}	
		}
		
		// update the payment details for orders
		public void updatePayment(int id,OrderPayment op){
			
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
				// get the payment details file 
				try
				{
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
				}
				catch(Exception e)
				{
				
				}
				if(orderPayments==null)
				{
					orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
				}
				// if there exist order id already add it into same list for order id or create a new record with order id
				
				if(!orderPayments.containsKey(id)){	
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(id, arr);
				}
				
				
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(id);		
			//OrderPayment orderpayment = new OrderPayment(orderId,recipientName,orderName,orderPrice,userAddress,creditCardNo);
			listOrderPayment.add(op);
			orderPayments.remove(id);
			try
			{	
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
			
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			
			
			orderPayments.put(id, listOrderPayment);
			
			//orderPayments.replace(id, listOrderPayment);
				
				// add order details into file

				try
				{	
					FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	            	objectOutputStream.writeObject(orderPayments);
					objectOutputStream.flush();
					objectOutputStream.close();       
					fileOutputStream.close();
				}
				catch(Exception e)
				{
					System.out.println("inside exception file not written properly");
				}	
		}
	
	/* getConsoles Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, FitnessWatches> getFitnessWatches(){
			HashMap<String, FitnessWatches> hm = new HashMap<String, FitnessWatches>();
			hm.putAll(SaxParserDataStore.fitnesswatchMap);
			return hm;
	}
	
	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, HeadPhones> getHeadPhones(){
			HashMap<String, HeadPhones> hm = new HashMap<String, HeadPhones>();
			hm.putAll(SaxParserDataStore.headphoneMap);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, Laptops> getLaptops(){
			HashMap<String, Laptops> hm = new HashMap<String, Laptops>();
			hm.putAll(SaxParserDataStore.laptopsMap);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, PetTracker> getPetTracker(){
			HashMap<String, PetTracker> hm = new HashMap<String, PetTracker>();
			hm.putAll(SaxParserDataStore.pTMap);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, SmartSpeaker> getSmartSpeaker(){
			HashMap<String, SmartSpeaker> hm = new HashMap<String, SmartSpeaker>();
			hm.putAll(SaxParserDataStore.smartspeakersMap);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, SmartWatches> getSmartWatches(){
			HashMap<String, SmartWatches> hm = new HashMap<String, SmartWatches>();
			hm.putAll(SaxParserDataStore.smartwatchMap);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, VirtualReality> getVirtualReality(){
			HashMap<String, VirtualReality> hm = new HashMap<String, VirtualReality>();
			hm.putAll(SaxParserDataStore.vrMap);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, Phones> getPhones(){
			HashMap<String, Phones> hm = new HashMap<String, Phones>();
			hm.putAll(SaxParserDataStore.phonesMap);
			return hm;
	}
	
	 public boolean isContainsStr(String string) {
	        String regex = ".*[a-zA-Z]+.*";
	        Matcher m = Pattern.compile(regex).matcher(string);
	        return m.matches();
	    }
	 
	 public boolean isItemExist(String itemCatalog, String itemName) {

	        HashMap<String, Object> hm = new HashMap<String, Object>();

	        switch (itemCatalog) {
	            case "FitnessWatch":
	                hm.putAll(SaxParserDataStore.fitnesswatchMap);
	                break;
	            case "SmartWatch":
	                hm.putAll(SaxParserDataStore.smartwatchMap);
	                break;
	            case "VirtualReality":
	                hm.putAll(SaxParserDataStore.vrMap);
	                break;
	            case "PetTracker":
	                hm.putAll(SaxParserDataStore.pTMap);
	                break;
	            case "Headphone":
	                hm.putAll(SaxParserDataStore.headphoneMap);
	                break;
	            case "Phone":
	                hm.putAll(SaxParserDataStore.phonesMap);
	                break;
	            case "Laptop":
	                hm.putAll(SaxParserDataStore.laptopsMap);
	                break;
	            case "SmartSpeaker":
	                hm.putAll(SaxParserDataStore.smartspeakersMap);
	                break;
	            case "Accessory":
	                hm.putAll(SaxParserDataStore.accessories);
	                break;
	        }
	        return true;
	    }
	 
	 public void storeNewOrder(int orderId, String orderName, String customerName, double orderPrice, String userAddress, String creditCardNo) {
	        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
	        String TOMCAT_HOME = System.getProperty("catalina.home");
	        // get the payment details file
	        try {
	            FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME + "//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
	            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	            orderPayments = (HashMap) objectInputStream.readObject();
	        } catch (Exception ignored) {

	        }
	        if (orderPayments == null) {
	            orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
	        }
	        // if there exist order id already add it into same list for order id or create a new record with order id

	        if (!orderPayments.containsKey(orderId)) {
	            ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
	            orderPayments.put(orderId, arr);
	        }
	        ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);

	        OrderPayment orderpayment = new OrderPayment(orderId, customerName, orderName, orderPrice, userAddress, creditCardNo);
	        listOrderPayment.add(orderpayment);

	        // add order details into file
	        updateOrderFile(orderPayments);

	    }
	 
	 //将更新后的数据保存到文件中
	    public boolean updateOrderFile(HashMap<Integer, ArrayList<OrderPayment>> orderPayments) {
	        String TOMCAT_HOME = System.getProperty("catalina.home");

	        try {
	            FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME + "//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	            objectOutputStream.writeObject(orderPayments);
	            objectOutputStream.flush();
	            objectOutputStream.close();
	            fileOutputStream.close();
	        } catch (Exception e) {

	        }
	        return true;
	    }
	 
	    public void removeOldOrder(int orderId, String orderName, String customerName) {
	        String TOMCAT_HOME = System.getProperty("catalina.home");
	        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
	        ArrayList<OrderPayment> ListOrderPayment = new ArrayList<OrderPayment>();
	        //get the order from file
	        try {
	            FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME + "//wtpwebapps//CSP584hw1//PaymentDetails.txt"));
	            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	            orderPayments = (HashMap) objectInputStream.readObject();
	        } catch (Exception e) {

	        }
	        //get the exact order with same ordername and add it into cancel list to remove it later
	        for (OrderPayment oi : orderPayments.get(orderId)) {
	            if (oi.getOrderName().equals(orderName) && oi.getUserName().equals(customerName)) {
	                ListOrderPayment.add(oi);
	                //pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
//	                        response.sendRedirect("SalesmanHome");
//	                        return;
	            }
	        }
	        //remove all the orders from hashmap that exist in cancel list
	        orderPayments.get(orderId).removeAll(ListOrderPayment);
	        if (orderPayments.get(orderId).size() == 0) {
	            orderPayments.remove(orderId);
	        }

	        //save the updated hashmap with removed order to the file
	        updateOrderFile(orderPayments);
	    }
	 
	 
	 
	/* getProducts Functions returns the Arraylist of consoles in the store.*/

//	public ArrayList<String> getProductsFitnessWatch(){
//		ArrayList<String> ar = new ArrayList<String>();
//		for(Map.Entry<String, FitnessWatches> entry : getFitnessWatches().entrySet()){			
//			ar.add(entry.getValue().getName());
//		}
//		return ar;
//	}
//	
//	/* getProducts Functions returns the Arraylist of games in the store.*/
//
//	public ArrayList<String> getProductsGame(){		
//		ArrayList<String> ar = new ArrayList<String>();
//		for(Map.Entry<String, Game> entry : getGames().entrySet()){
//			ar.add(entry.getValue().getName());
//		}
//		return ar;
//	}
//	
//	/* getProducts Functions returns the Arraylist of Tablets in the store.*/
//
//	public ArrayList<String> getProductsTablets(){		
//		ArrayList<String> ar = new ArrayList<String>();
//		for(Map.Entry<String, Tablet> entry : getTablets().entrySet()){
//			ar.add(entry.getValue().getName());
//		}
//		return ar;
//	}
	
	

}
