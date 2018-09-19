package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveCart
 */
@WebServlet("/RemoveCart")
public class RemoveCart extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	response.setContentType("text/html");
	        PrintWriter pw = response.getWriter();

	        /* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

	        Utilities utility = new Utilities(request, pw);
	        String name[] = request.getParameterValues("checkbox");
	        if(name != null && name.length > 0) {
	        	for(int i = 0;i < name.length;i++) {
	        		  utility.deleteCustomerOrders(name[i]);
	        	}
	        }
	        		//request.getParameter("checkbox");
	       // utility.deleteCustomerOrders(name);
	        /* StoreProduct Function stores the Purchased product in Orders HashMap.*/

	        response.sendRedirect("Cart");
	        return;

	}

}
