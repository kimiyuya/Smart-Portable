package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {
	
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
		
		utility.updatePayment(op);
		response.sendRedirect("SalesmanHome");
 		
	}

}
