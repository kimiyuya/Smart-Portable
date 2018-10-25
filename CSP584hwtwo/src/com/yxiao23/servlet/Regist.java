package com.yxiao23.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
 * Servlet implementation class Regist
 */
@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}

	/*   Username,Password,Usertype information are Obtained from HttpServletRequest variable and validates whether
		 the User Credential Already Exists or else User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String usertype = "customer";
		if(!utility.isLoggedin())
			usertype = request.getParameter("usertype");

		//if password and repassword does not match show error message

		if(!password.equals(repassword))
		{
			error_msg = "Passwords doesn't match!";
		}
		else
		{
			HashMap<String, Users> hm=new HashMap<String, Users>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			System.out.println(TOMCAT_HOME);

			//get the user details from file 

			try
			{
				hm = MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
				
			}

			// if the user already exist show error that already exist

			if(hm.containsKey(username))
				error_msg = "Username already exist as " + usertype;
			else
			{
				/*create a user object and store details into hashmap
				store the user hashmap into file  */

				Users user = new Users(username,password,usertype);
				hm.put(username, user);
				 if (MySqlDataStoreUtilities.insertUser(username, password, repassword, usertype)) {
					 
					 HttpSession session = request.getSession(true);				
					 session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
					 if(!utility.isLoggedin()){
						 response.sendRedirect("Login"); return;
					 } else {
						 response.sendRedirect("Account"); return;
					 }
				 }
			}
		}

		//display the error message
		if(utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", error_msg);
			response.sendRedirect("Account"); return;
		}
		displayRegistration(request, response, pw, true);
		
	}

	/*  displayRegistration function displays the Registration page of New User */
	
	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		pw.print("<form method='post' action='Regist'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Re-Password</h3></td><td><input type='password' name='repassword' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='storemanager'>Store Manager</option><option value='salesman'>Salesman</option></select>"
				+ "</td></tr></table>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</div></div></div>");
		utility.printHtml("Footer.html");
	}
}
