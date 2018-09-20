package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxiao23.bean.FitnessWatches;
import com.yxiao23.bean.HeadPhones;
import com.yxiao23.bean.Laptops;
import com.yxiao23.bean.PetTracker;
import com.yxiao23.bean.SmartSpeaker;
import com.yxiao23.bean.SmartWatches;
import com.yxiao23.bean.VirtualReality;


@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
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
		String username=utility.username();
		utility.printHtml("Header.html");
		
		HashMap<String, FitnessWatches> fwmap = utility.getFitnessWatches();
		HashMap<String, HeadPhones> hpmap = utility.getHeadPhones();
		HashMap<String, Laptops> ltmap = utility.getLaptops();
		HashMap<String, PetTracker> ptmap = utility.getPetTracker();
		HashMap<String, SmartSpeaker> ssmap = utility.getSmartSpeaker();
		HashMap<String, SmartWatches> swmap = utility.getSmartWatches();
		HashMap<String, VirtualReality> vrmap = utility.getVirtualReality();
		HashMap<String, Phones> phonemap = utility.getPhones();
		
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
	}

}
