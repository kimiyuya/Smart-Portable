package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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

@WebServlet("/StoreManagement")
public class StoreManagement extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		utility.printHtml("Header.html");
		
		HashMap<String, FitnessWatches> fwmap = utility.getFitnessWatches();
		HashMap<String, HeadPhones> hpmap = utility.getHeadPhones();
		HashMap<String, Laptops> ltmap = utility.getLaptops();
		HashMap<String, PetTracker> ptmap = utility.getPetTracker();
		HashMap<String, SmartSpeaker> ssmap = utility.getSmartSpeaker();
		HashMap<String, SmartWatches> swmap = utility.getSmartWatches();
		HashMap<String, VirtualReality> vrmap = utility.getVirtualReality();
		HashMap<String, Phones> phonemap = utility.getPhones();
		
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Product Management</a>");
		pw.print("</h2><div class='entry'>");
		
		pw.print("<h2><form name ='AddProduct' action='AddProduct' method='post'></h2>");
		pw.print("<td><input type='submit' name='AddProduct' value='Add Product' class='btnbuy'></td>");
		pw.print("</form>");
		
		pw.print("<table style='width:100%' class='gridtable'>");
		pw.print("<tr>");
		pw.print("<td>id:</td>");
		pw.print("<td>ProductName:</td>");
		pw.print("<td>productPrice:</td></tr>");
//		pw.print("<td>productPrice:</td></tr>");
		
		int i = 1;
		for (Entry<String, FitnessWatches> fw : fwmap.entrySet()) 
		{
			pw.print("<form name ='ProductList' action='RemoveProduct' method='post'>");
			pw.print("<input type='hidden' name='productType' value='Fitness Watches'>");
			pw.print("<tr>");		
			
			pw.print("<input type='hidden' name='productId' value='"+fw.getKey()+"'>");		
			pw.print("<td>"+i+".</td><td>"+fw.getValue().getName()+"</td>");	
			pw.print("<td>"+fw.getValue().getPrice()+"</td>");	
			pw.print("<td><input type='submit' name='Product' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Product' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			i++;
			pw.print("</form>");
		
		}
		
		for (Entry<String, HeadPhones> hp : hpmap.entrySet()) 
		{
			pw.print("<form name ='ProductList' action='RemoveProduct' method='post'>");
			pw.print("<input type='hidden' name='productType' value='HeadPhones'>");
			pw.print("<tr>");		
			
			pw.print("<input type='hidden' name='productId' value='"+hp.getKey()+"'>");		
			pw.print("<td>"+i+".</td><td>"+hp.getValue().getName()+"</td>");	
			pw.print("<td>"+hp.getValue().getPrice()+"</td>");	
			pw.print("<td><input type='submit' name='Product' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Product' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			i++;
			pw.print("</form>");
			
			
		}
		
		for (Entry<String, Laptops> lt : ltmap.entrySet()) 
		{
			pw.print("<form name ='ProductList' action='RemoveProduct' method='post'>");
			pw.print("<input type='hidden' name='productType' value='Laptops'>");
			pw.print("<tr>");		
			
			pw.print("<input type='hidden' name='productId' value='"+lt.getKey()+"'>");		
			pw.print("<td>"+i+".</td><td>"+lt.getValue().getName()+"</td>");	
			pw.print("<td>"+lt.getValue().getPrice()+"</td>");	
			pw.print("<td><input type='submit' name='Product' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Product' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			i++;
			pw.print("</form>");
		}
		
		for (Entry<String, PetTracker> pt : ptmap.entrySet()) 
		{
			pw.print("<form name ='ProductList' action='RemoveProduct' method='post'>");
			pw.print("<input type='hidden' name='productType' value='PetTracker'>");		
			pw.print("<tr>");		
			pw.print("<input type='hidden' name='productId' value='"+pt.getKey()+"'>");		
			pw.print("<td>"+i+".</td><td>"+pt.getValue().getName()+"</td>");	
			pw.print("<td>"+pt.getValue().getPrice()+"</td>");	
			pw.print("<td><input type='submit' name='Product' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Product' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			i++;
			pw.print("</form>");
		}
		
		for (Entry<String, SmartSpeaker> ss : ssmap.entrySet()) 
		{
			pw.print("<form name ='ProductList' action='RemoveProduct' method='post'>");
			pw.print("<input type='hidden' name='productType' value='Smart Speaker'>");
			pw.print("<tr>");		
			
			pw.print("<input type='hidden' name='productId' value='"+ss.getKey()+"'>");		
			pw.print("<td>"+i+".</td><td>"+ss.getValue().getName()+"</td>");	
			pw.print("<td>"+ss.getValue().getPrice()+"</td>");	
			pw.print("<td><input type='submit' name='Product' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Product' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			i++;
			pw.print("</form>");
		}
		
		for (Entry<String, SmartWatches> sw : swmap.entrySet()) 
		{
			pw.print("<form name ='ProductList' action='RemoveProduct' method='post'>");
			pw.print("<input type='hidden' name='productType' value='Smart Watches'>");
			pw.print("<tr>");		
			
			pw.print("<input type='hidden' name='productId' value='"+sw.getKey()+"'>");		
			pw.print("<td>"+i+".</td><td>"+sw.getValue().getName()+"</td>");	
			pw.print("<td>"+sw.getValue().getPrice()+"</td>");	
			pw.print("<td><input type='submit' name='Product' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Product' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			i++;
			pw.print("</form>");
		}
		
		for (Entry<String, VirtualReality> vr : vrmap.entrySet()) 
		{
			pw.print("<form name ='ProductList' action='RemoveProduct' method='post'>");
			pw.print("<input type='hidden' name='productType' value='Virtual Reality'>");
			pw.print("<tr>");		
			
			pw.print("<input type='hidden' name='productId' value='"+vr.getKey()+"'>");		
			pw.print("<td>"+i+".</td><td>"+vr.getValue().getName()+"</td>");	
			pw.print("<td>"+vr.getValue().getPrice()+"</td>");	
			pw.print("<td><input type='submit' name='Product' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Product' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			i++;
			pw.print("</form>");
		}
		
		for (Entry<String, Phones> p : phonemap.entrySet()) 
		{
			pw.print("<form name ='ProductList' action='RemoveProduct' method='post'>");
			pw.print("<input type='hidden' name='productType' value='Phones'>");
			pw.print("<tr>");		
			
			pw.print("<input type='hidden' name='productId' value='"+p.getKey()+"'>");		
			pw.print("<td>"+i+".</td><td>"+p.getValue().getName()+"</td>");	
			pw.print("<td>"+p.getValue().getPrice()+"</td>");	
			pw.print("<td><input type='submit' name='Product' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Product' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			i++;
			pw.print("</form>");
		}
		pw.print("</table></div></div></div>");
		
		utility.printHtml("Footer.html");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
