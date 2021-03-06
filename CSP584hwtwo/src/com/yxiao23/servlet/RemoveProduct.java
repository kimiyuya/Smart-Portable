package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxiao23.bean.FitnessWatches;
import com.yxiao23.bean.HeadPhones;
import com.yxiao23.bean.Laptops;
import com.yxiao23.bean.PetTracker;
import com.yxiao23.bean.SmartSpeaker;
import com.yxiao23.bean.SmartWatches;
import com.yxiao23.bean.VirtualReality;

/**
 * Servlet implementation class RemoveProduct
 */
@WebServlet("/RemoveProduct")
public class RemoveProduct extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
	
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("productId");
		
		 Utilities utility = new Utilities(request, pw);
		 	if (request.getParameter("Product").equals("Remove")) {
		 		
		 		
		 		utility.removeProduct(id);
		 		
		 		response.sendRedirect("StoreManagement");
		 		//return;
		 	}
		 	if (request.getParameter("Product").equals("Update")) {
		 		utility.printHtml("Header.html");
		 		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>UPDATE PRODUCT</a>");
				pw.print("</h2><div class='entry'>");
				
				String productType = request.getParameter("productType");
				

				
				pw.print("<form name ='updateProduct' action='UpdateProduct' method='post'>");
				pw.print("<table align=\"center\" border=\"1\" width=\"500px\"><tr>");
				
				pw.print("<input type ='hidden' name = 'productId' value='"+id+"'>");
				pw.print("<tr><td>Product Name:</td><td><input type ='text' name = 'productName'/></td></tr>");
				//pw.print("<tr><td>Product Type:</td><td><select name='productType'><option>Fitness Watches</option><option>HeadPhones</option><option>Laptops</option><option>PetTracker</option><option>Smart Speaker</option><option>Smart Watches</option><option>Virtual Reality</option><option>Phones</option></td>");
				pw.print("<tr><td>Product Price:</td><td><input type ='text' name = 'productPrice'/></td></tr>");
				pw.print("<tr><td>Product Retailer:</td><td><input type ='text' name = 'productRetailer'/></td></tr>");
				pw.print("<tr><td>Product Condition:</td><td><label for='r5'><input type='radio' value='new' name='productCondition'>NEW</label><label for='r6'><input type='radio' value='used' name='productCondition'>USED</label></td></tr>");
				
				pw.print("<tr><td>Product Discount:</td><td><input type ='text' name = 'productDiscount'/></td></tr>");
				
				switch(productType) {
				case "PetTracker": pw.print("<input type='hidden' name='productType' value='PetTracker'>"); break;
				case "Fitness Watches": pw.print("<input type='hidden' name='productType' value='Fitness Watches'>"); break;
				case "HeadPhones": pw.print("<input type='hidden' name='productType' value='HeadPhones'>"); break;
				case "Laptops": pw.print("<input type='hidden' name='productType' value='Laptops'>"); break;
				case "Smart Speaker": pw.print("<input type='hidden' name='productType' value='Smart Speaker'>"); break;
				case "Smart Watches": pw.print("<input type='hidden' name='productType' value='Smart Watches'>"); break;
				case "Virtual Reality": pw.print("<input type='hidden' name='productType' value='Virtual Reality'>"); break;
				case "Phones": pw.print("<input type='hidden' name='productType' value='Phones'>"); break;
				
				
				
				}

				pw.print("<td><input type='reset' name='Reset' value='Reset' class='btnbuy'></td>");
				pw.print("<td><input type='submit' name='Submit' value='Submit' class='btnbuy'></td>");
				pw.print("</table></form>");
		 		
		 		
		 	}
		 	

	}

}
