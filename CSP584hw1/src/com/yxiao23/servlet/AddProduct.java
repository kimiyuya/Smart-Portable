package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxiao23.bean.FitnessWatches;
import com.yxiao23.bean.HeadPhones;
import com.yxiao23.bean.Laptops;
import com.yxiao23.bean.PetTracker;
import com.yxiao23.bean.SmartSpeaker;
import com.yxiao23.bean.SmartWatches;
import com.yxiao23.bean.VirtualReality;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		String username = utility.username();
		utility.printHtml("Header.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Product Management</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<form name ='AddProduct' action='AddProduct' method='post'>");
		pw.print("<table align=\"center\" border=\"1\" width=\"500px\"><tr>");
		
		
		pw.print("<tr><td>Product Name:</td><td><input type ='text' name = 'productName'/></td></tr>");
		pw.print("<tr><td>Product Type:</td><td><select name='productType'><option>Fitness Watches</option><option>HeadPhones</option><option>Laptops</option><option>PetTracker</option><option>Smart Speaker</option><option>Smart Watches</option><option>Virtual Reality</option><option>Phones</option></td>");
		pw.print("<tr><td>Product Price:</td><td><input type ='text' name = 'productPrice'/></td></tr>");
		pw.print("<tr><td>Product Retailer:</td><td><input type ='text' name = 'productRetailer'/></td></tr>");
		pw.print("<tr><td>Product Condition:</td><td><label for='r5'><input type='radio' value='new' name='productCondition'>NEW</label><label for='r6'><input type='radio' value='used' name='productCondition'>USED</label></td></tr>");
		pw.print("<tr><td>Product Discount:</td><td><input type ='text' name = 'productDiscount'/></td></tr>");
		pw.print("<td><input type='reset' name='Reset' value='Reset' class='btnbuy'></td>");
		pw.print("<td><input type='submit' name='Submit' value='Submit' class='btnbuy'></td>");
		pw.print("</form></table></div></div></div>");
		utility.printHtml("Footer.html");
		
		//default id = productName
		String id = request.getParameter("productName");
		String productName = request.getParameter("productName");
		String productType = request.getParameter("productType");
		String productPrice = request.getParameter("productPrice");
		String productRetailer = request.getParameter("productRetailer");
		String productCondition = request.getParameter("productCondition");
		String productDiscount = request.getParameter("productDiscount");
		
		if(productType.equals("Fitness Watches")) {
			FitnessWatches fw = new FitnessWatches();
			fw.setId(productName);
			fw.setName(productName);
			fw.setPrice(Double.parseDouble(productPrice));
			fw.setRetailer(productRetailer);
			fw.setCondition(productCondition);
			fw.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.addProduct(id, fw,productType);	
		}
		if(productType.equals("HeadPhones")) {
			HeadPhones hp = new HeadPhones();
			hp.setId(productName);
			hp.setName(productName);
			hp.setPrice(Double.parseDouble(productPrice));
			hp.setRetailer(productRetailer);
			hp.setCondition(productCondition);
			hp.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.addProduct(id, hp,productType);	
		}
		if(productType.equals("Laptops")) {
			Laptops lt = new Laptops();
			lt.setId(productName);
			lt.setName(productName);
			lt.setPrice(Double.parseDouble(productPrice));
			lt.setRetailer(productRetailer);
			lt.setCondition(productCondition);
			lt.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.addProduct(id,lt,productType);	
		}
		if(productType.equals("PetTracker")) {
			PetTracker pt = new PetTracker();
			pt.setId(productName);
			pt.setName(productName);
			pt.setPrice(Double.parseDouble(productPrice));
			pt.setRetailer(productRetailer);
			pt.setCondition(productCondition);
			pt.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.addProduct(id, pt,productType);	
		}
		if(productType.equals("Smart Speaker")) {
			SmartSpeaker ss = new SmartSpeaker();
			ss.setId(productName);
			ss.setName(productName);
			ss.setPrice(Double.parseDouble(productPrice));
			ss.setRetailer(productRetailer);
			ss.setCondition(productCondition);
			ss.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.addProduct(id, ss,productType);	
		}
		if(productType.equals("Smart Watches")) {
			SmartWatches sw = new SmartWatches();
			sw.setId(productName);
			sw.setName(productName);
			sw.setPrice(Double.parseDouble(productPrice));
			sw.setRetailer(productRetailer);
			sw.setCondition(productCondition);
			sw.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.addProduct(id, sw,productType);	
		}
		if(productType.equals("Virtual Reality")) {
			VirtualReality vr = new VirtualReality();
			vr.setId(productName);
			vr.setName(productName);
			vr.setPrice(Double.parseDouble(productPrice));
			vr.setRetailer(productRetailer);
			vr.setCondition(productCondition);
			vr.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.addProduct(id, vr,productType);	
		}
		if(productType.equals("Phones")) {
			Phones phone = new Phones();
			phone.setId(productName);
			phone.setName(productName);
			phone.setPrice(Double.parseDouble(productPrice));
			phone.setRetailer(productRetailer);
			phone.setCondition(productCondition);
			phone.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.addProduct(id, phone,productType);	
		}
		response.sendRedirect("StoreManagement");
	}

}
