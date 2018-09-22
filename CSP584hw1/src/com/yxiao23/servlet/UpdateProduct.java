package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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


@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	
	
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

		
		String id = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String productType = request.getParameter("productType");
		String productPrice = request.getParameter("productPrice");
		String productRetailer = request.getParameter("productRetailer");
		String productCondition = request.getParameter("productCondition");
		String productDiscount = request.getParameter("productDiscount");

		if(productType.equals("Fitness Watches")) {
			FitnessWatches fw = new FitnessWatches();
			fw.setName(productName);
			fw.setPrice(Double.parseDouble(productPrice));
			fw.setRetailer(productRetailer);
			fw.setCondition(productCondition);
			fw.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.updateProduct(id, fw);	
		}
		if(productType.equals("HeadPhones")) {
			HeadPhones hp = new HeadPhones();
			hp.setName(productName);
			hp.setPrice(Double.parseDouble(productPrice));
			hp.setRetailer(productRetailer);
			hp.setCondition(productCondition);
			hp.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.updateProduct(id, hp);	
		}
		if(productType.equals("Laptops")) {
			Laptops lt = new Laptops();
			lt.setName(productName);
			lt.setPrice(Double.parseDouble(productPrice));
			lt.setRetailer(productRetailer);
			lt.setCondition(productCondition);
			lt.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.updateProduct(id,lt);	
		}
		if(productType.equals("PetTracker")) {
			PetTracker pt = new PetTracker();
			pt.setName(productName);
			pt.setPrice(Double.parseDouble(productPrice));
			pt.setRetailer(productRetailer);
			pt.setCondition(productCondition);
			pt.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.updateProduct(id, pt);	
		}
		if(productType.equals("Smart Speaker")) {
			SmartSpeaker ss = new SmartSpeaker();
			ss.setName(productName);
			ss.setPrice(Double.parseDouble(productPrice));
			ss.setRetailer(productRetailer);
			ss.setCondition(productCondition);
			ss.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.updateProduct(id, ss);	
		}
		if(productType.equals("Smart Watches")) {
			SmartWatches sw = new SmartWatches();
			sw.setName(productName);
			sw.setPrice(Double.parseDouble(productPrice));
			sw.setRetailer(productRetailer);
			sw.setCondition(productCondition);
			sw.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.updateProduct(id, sw);	
		}
		if(productType.equals("Virtual Reality")) {
			VirtualReality vr = new VirtualReality();
			vr.setName(productName);
			vr.setPrice(Double.parseDouble(productPrice));
			vr.setRetailer(productRetailer);
			vr.setCondition(productCondition);
			vr.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.updateProduct(id, vr);	
		}
		if(productType.equals("Phones")) {
			Phones phone = new Phones();
			phone.setName(productName);
			phone.setPrice(Double.parseDouble(productPrice));
			phone.setRetailer(productRetailer);
			phone.setCondition(productCondition);
			phone.setDiscount(Double.parseDouble(productDiscount));
			//fw.setImage(image);
			utility.updateProduct(id, phone);	
		}
		
		
		response.sendRedirect("StoreManagement");

	}

}
