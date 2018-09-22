package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxiao23.bean.FitnessWatches;


@WebServlet("/SalesmanHome")
public class SalesmanHome extends HttpServlet {
	
  
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
		String username=utility.username();
		utility.printSalesmanHomeHtml("SalesmanHeader.html");
		HashMap<Integer, ArrayList<OrderPayment>> paymentList = utility.getOrderPayment();
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order Management</a>");
		pw.print("</h2><div class='entry'>");
		
		pw.print("<h2><form name ='AddOrder' action='AddOrder' method='post'></h2>");
		pw.print("<td><input type='submit' name='AddOrder' value='Add Order' class='btnbuy'></td>");
		pw.print("</form>");
		
		pw.print("<table style='width:100%' class='gridtable'>");
		pw.print("<tr>");
		pw.print("<td>OrderId:</td>");
		pw.print("<td>RecipientName:</td>");
		pw.print("<td>RecipientAdd:</td></tr>");
//		pw.print("<td>CreateDate:</td></tr>");
//		pw.print("<td>DeliveryDate:</td></tr>");
		
		//int i = 0;
		
		Iterator map1it=paymentList.entrySet().iterator();
        while(map1it.hasNext())
        {
         Map.Entry<Integer, ArrayList<OrderPayment>> op = (Entry<Integer, ArrayList<OrderPayment>>) map1it.next();
         pw.print("<form name ='OrderList' action='RemoveOrder' method='post'>");
			pw.print("<tr>");		
			
			pw.print("<input type='hidden' name='orderId' value='"+op.getKey()+"'>");		
			for (int i = 0; i < op.getValue().size(); i++) {
				pw.print("<td>"+op.getKey()+"</td><td>"+op.getValue().get(i).getOrderName()+"</td>");	
				pw.print("<td>"+op.getValue().get(i).getUserAddress()+"</td>");
			}
//			pw.print("<td>"+op.getValue().get(i).getUserAddress()+"</td>");	
//			pw.print("<td>"+op.getValue().get(i).getUserAddress()+"</td>");	
			pw.print("<td><input type='submit' name='Order' value='Remove' class='btnbuy'></td>");
			pw.print("<td><input type='submit' name='Order' value='Update' class='btnbuy'></td>");
			pw.print("</tr>");
			pw.print("</form>");
        }
		
	}

}