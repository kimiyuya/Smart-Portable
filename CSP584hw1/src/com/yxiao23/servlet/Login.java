package com.yxiao23.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxiao23.bean.Users;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		HashMap<String, Users> map = new HashMap<String, Users>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		
		try
		{		
          FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"//wtpwebapps//CSP584hw1//UserInfo.txt"));
          ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
		  map = (HashMap)objectInputStream.readObject();
		}
		catch(Exception e)
		{
				
		}
		Users user = map.get(username);
		if(null != user) {
			String userpswd = user.getUserPswd();
			if (userpswd.equals(password)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("username", user.getUserName());
				session.setAttribute("usertype", user.getUserType());
				if (usertype.equals("customer")) {
					System.out.println(user);
					response.sendRedirect("Home");
				}
				if (usertype.equals("storemanager")) {
					response.sendRedirect("/CSP584hw1/product/ProductManagement.html");
				}
				if (usertype.equals("salesman")) {
					response.sendRedirect("Home");
				}
				return;
			}
		}
		displayLogin(request, response, pw, true);
	}


	private void displayLogin(HttpServletRequest request, HttpServletResponse response, PrintWriter pw, boolean isError) {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if (isError)
			pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login_msg")!=null){			
			pw.print("<h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4>");
			session.removeAttribute("login_msg");
		}
		pw.print("<form method='post' action='Login'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='storemanager'>Store Manager</option><option value='salesman'>Salesman</option></select>"
				+ "</td></tr><tr><td></td><td>"
				+ "<input type='submit' class='btnbuy' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr><tr><td></td><td>"
				+ "<strong><a class='' href='Regist' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"
				+ "</td></tr></table>"
				+ "</form>" + "</div></div></div>");
		utility.printHtml("Footer.html");
		
	}

}
