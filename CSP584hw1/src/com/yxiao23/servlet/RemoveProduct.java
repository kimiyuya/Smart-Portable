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
@WebServlet("/RemoveProduct")
public class RemoveProduct extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		 Utilities utility = new Utilities(request, pw);
		 	if (request.getParameter("Remove").equals("Remove")) {
		 		
		 		String id = request.getParameter("productId");
		 		
		 		utility.removeProduct(id);
		 		
		 		response.sendRedirect("StoreManagement");
		 		return;
		 	}
		 	if (request.getParameter("Update").equals("Update")) {
//		 		String id = request.getParameter("productId");
//		 		utility.updateProdcut(id);
		 		response.sendRedirect(arg0);
		 	}
		 	

	}

}
