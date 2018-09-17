package com.yxiao23.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxiao23.bean.Products;

/**
 * Servlet implementation class SearchProduct
 */
@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//receive data
		String productName = request.getParameter("productName");
		Map<String, Products> proMap = (Map<String, Products>) this.getServletContext().getAttribute("map");
		for (Products p : proMap.values()) {
			if (p.getProductName().equals(productName)){
				request.getSession().setAttribute("product", p);
				response.sendRedirect(request.getContextPath()+"/product/ProductView.jsp");
				return;
			}
		}
		request.getSession().setAttribute("product", null);
		response.sendRedirect(request.getContextPath()+"/product/ProductView.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
