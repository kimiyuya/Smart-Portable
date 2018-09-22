package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxiao23.db.OrderDB;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
		static int orderId = 10000;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet
		
		String recipientName = request.getParameter("recipientName");
		String userAddress = request.getParameter("userAddress");
		String creditCardNo = request.getParameter("creditCardNo");
		

		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() && !recipientName.isEmpty() )
		{

			orderId ++;
			//iterate through each order

			for (OrderItem oi : utility.getCustomerOrders())
			{

				//set the parameter for each column and execute the prepared statement

				utility.storePayment(orderId,oi.getProductName(),oi.getProductPrice(),userAddress,creditCardNo,recipientName);
			}

			//remove the order details from cart after processing

			OrderDB.orders.remove(utility.username());
			
			Date today = new Date();
			Date endDate = new Date();
			Date conceldate = new Date();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String Today = format.format(today);
			Calendar ca = Calendar.getInstance();
			Calendar ca1 = Calendar.getInstance();
			ca.add(Calendar.DATE, 14);
			ca1.add(Calendar.DATE, 9);
			endDate = ca.getTime();
			conceldate = ca1.getTime();
			String enddate = format.format(endDate);
			String concelDate = format.format(conceldate);
			
			
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			
			pw.print("<h2> Your Order");
			pw.print("&nbsp&nbsp");
			pw.print("has been stored! ");
			pw.print("<br>Your Order No is :"+(orderId));
			pw.print("<br>Today is :"+(Today));
			pw.print("<br>Your Order deliver date is :"+(enddate));
			pw.print("<br>You can concel your order before:" + (concelDate));
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");

			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);


	}
}
