package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RemoveProduct
 */
@WebServlet("/RemoveUpdateOrder")
public class RemoveUpdateOrder extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String a = request.getParameter("123");
		System.out.println(a+"=================================");
		//int orderId = Integer.parseInt(request.getParameter("OrderId"));
		String oid = request.getParameter("b");
		System.out.println(oid+"++++++++++++++++++++++");
		int orderId = Integer.parseInt(oid);
		String username = request.getParameter("username");
        String productName = request.getParameter("productName");
        String price = request.getParameter("price");
        String address = request.getParameter("address");
        String creditCard = request.getParameter("creditCard");
        String userType = request.getParameter("userType");
        
        
		 //Remove orders
	 	if (request.getParameter("Order") != null && request.getParameter("Order").equals("Cancel")) {
	 		
	 		utility.removeOldOrder(orderId, productName, username);
	 		if (userType.equals("customer")) {
	                response.sendRedirect("Account");
	        } else {
	
	                response.sendRedirect("SalesmanHome");
	        }
	 	} else if (request.getParameter("Order") != null && request.getParameter("Order").equals("Update")) {
	 		utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");

            pw.print("<div id='content'>");
            pw.print("<div class='post'>");
            pw.print("<h3 class='title'>");
            pw.print("Update Order");
            pw.print("</h3>");
            pw.print("<div class='entry'>");

            //显示更新order的表格
            pw.print("<form action='UpdateOrder' method='post'");
            pw.print("<table style='width:100%'>");

            pw.print("<tr>");
            pw.print("<td><h4>Order ID: " + orderId + "</h4></td>");
            pw.print("</tr>");

            pw.print("<tr>");
            pw.print("<td><h4>Customer Name: " + username + "</h4></td>");
            pw.print("</tr><tr><td>");

            pw.print("<tr>");
            pw.print("<td><h4>Product Name: " + productName + "</h4></td>");
            pw.print("</tr><tr><td>");

            pw.print("<tr>");
            pw.print("<td><h4>Price: " + price + "</h4></td>");
            pw.print("</tr><tr><td>");

            pw.print("<h4>Address</h4><td><input type='text' name='address' value='" + address + "' class='input' required></input>");
            pw.print("</td></tr></td><tr><td>");

            pw.print("<h4>Credit Card</h4></td><td><input type='text' name='creditCard' value='" + creditCard + "' class='input' required></input>");
            pw.print("</td></tr><tr><td>");
            
            pw.print("<input type='hidden' name='orderId' value='" + orderId + "'>");
            pw.print("<input type='hidden' name='customerName' value='" + username + "'>");
            pw.print("<input type='hidden' name='productName' value='" + productName + "'>");
            pw.print("<input type='hidden' name='price' value='" + price + "'>");
            pw.print("<input type='hidden' name='address' value='" + address + "'>");
            pw.print("<input type='hidden' name='creditCard' value='" + creditCard + "'>");

            pw.print("<input type='submit' class='btnbuy' value='Update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
            pw.print("</td></tr><tr><td></td><td>");
            pw.print("</td></tr></table>");
            pw.print("</form></div></div></div>");
	 	}
		 	//update orders
//		 	if (request.getParameter("Order").equals("Update")) {
//		 		utility.printSalesmanHomeHtml("SalesmanHeader.html");
//		 		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
//				pw.print("<a style='font-size: 24px;'>UPDATE ORDER</a>");
//				pw.print("</h2><div class='entry'>");
//				
//				pw.print("<form name ='AddProduct' action='UpdateOrder' method='post'>");
//				pw.print("<table align=\"center\" border=\"1\" width=\"500px\"><tr>");
//				
//				pw.print("<input type ='hidden' name = 'orderId' value='"+id+"'>");
//				pw.print("<tr><td>Order Name:</td><td><input type ='text' name = 'orderName'/></td></tr>");
//				pw.print("<tr><td>User Name:</td><td><input type ='text' name = 'userName'/></td></tr>");
//				pw.print("<tr><td>User Address:</td><td><input type ='text' name = 'userAdd'/></td></tr>");
//				pw.print("<tr><td>Order Price:</td><td><input type ='text' name = 'orderPrice'/></td></tr>");
//				pw.print("<tr><td>User CreditCard:</td><td><input type ='text' name = 'userCreditcard'/></td></tr>");
//				pw.print("<td><input type='reset' name='Reset' value='Reset' class='btnbuy'></td>");
//				pw.print("<td><input type='submit' name='Submit' value='Submit' class='btnbuy'></td>");
//				pw.print("</table></form>");
//				
//		 		
//		 	}
		 	

	}

}
