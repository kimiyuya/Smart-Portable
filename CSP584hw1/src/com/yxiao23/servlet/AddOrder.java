package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	
   
	
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
		pw.print("<a style='font-size: 24px;'>ADD ORDER</a>");
		pw.print("</h2><div class='entry'>");
		
		pw.print("<form name ='AddProduct' action='AddOrder' method='post'>");
		pw.print("<table align=\"center\" border=\"1\" width=\"500px\"><tr>");
		
		pw.print("<tr><td>Order ID:</td><td><input type ='text' name = 'orderId'/></td></tr>");
		pw.print("<tr><td>Order Name:</td><td><input type ='text' name = 'orderName'/></td></tr>");
		pw.print("<tr><td>User Name:</td><td><input type ='text' name = 'userName'/></td></tr>");
		pw.print("<tr><td>User Address:</td><td><input type ='text' name = 'userAdd'/></td></tr>");
		pw.print("<tr><td>Order Price:</td><td><input type ='text' name = 'orderPrice'/></td></tr>");
		pw.print("<tr><td>User CreditCard:</td><td><input type ='text' name = 'userCreditcard'/></td></tr>");
		pw.print("<td><input type='reset' name='Reset' value='Reset' class='btnbuy'></td>");
		pw.print("<td><input type='submit' name='Submit' value='Submit' class='btnbuy'></td>");
		pw.print("</table></form>");
		
		String orderId = request.getParameter("orderId");
		//should check if this orderid is been used
		
		String orderName = request.getParameter("orderName");
		String userName = request.getParameter("userName");
		String userAdd = request.getParameter("userAdd");
		String orderPrice = request.getParameter("orderPrice");
		String userCreditcard = request.getParameter("userCreditcard");
		
		OrderPayment op = new OrderPayment();
		op.setCreditCardNo(userCreditcard);
		op.setOrderId(Integer.parseInt(orderId));
		op.setOrderName(orderName);
		op.setOrderPrice(Double.parseDouble(orderPrice));
		op.setUserAddress(userAdd);
		op.setUserName(userName);
		
		utility.AddPayment(op);
		response.sendRedirect("SalesmanHome");
	}

}
