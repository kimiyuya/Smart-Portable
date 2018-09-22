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
@WebServlet("/RemoveOrder")
public class RemoveOrder extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
	
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("OrderId");
		
		 Utilities utility = new Utilities(request, pw);
		 //Remove orders
		 	if (request.getParameter("Order").equals("Remove")) {
		 		
		 		
		 		utility.removePayment(Integer.parseInt(id));;
		 		
		 		response.sendRedirect("SalesmanHome");
		 	}
		 	//update orders
		 	if (request.getParameter("Order").equals("Update")) {
		 		utility.printSalesmanHomeHtml("SalesmanHeader.html");
		 		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>UPDATE ORDER</a>");
				pw.print("</h2><div class='entry'>");
				
				pw.print("<form name ='AddProduct' action='UpdateOrder' method='post'>");
				pw.print("<table align=\"center\" border=\"1\" width=\"500px\"><tr>");
				
				pw.print("<input type ='hidden' name = 'orderId' value='"+id+"'>");
				pw.print("<tr><td>Order Name:</td><td><input type ='text' name = 'orderName'/></td></tr>");
				pw.print("<tr><td>User Name:</td><td><input type ='text' name = 'userName'/></td></tr>");
				pw.print("<tr><td>User Address:</td><td><input type ='text' name = 'userAdd'/></td></tr>");
				pw.print("<tr><td>Order Price:</td><td><input type ='text' name = 'orderPrice'/></td></tr>");
				pw.print("<tr><td>User CreditCard:</td><td><input type ='text' name = 'userCreditcard'/></td></tr>");
				pw.print("<td><input type='reset' name='Reset' value='Reset' class='btnbuy'></td>");
				pw.print("<td><input type='submit' name='Submit' value='Submit' class='btnbuy'></td>");
				pw.print("</table></form>");
				
		 		
		 	}
		 	

	}

}
