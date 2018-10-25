package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxiao23.bean.PetTracker;

@WebServlet("/PetTrackerList")

public class PetTrackerList extends HttpServlet {

	/* Games Page Displays all the Games and their Information in Game Speed */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Games type whether it is electronicArts or activision or takeTwoInteractive */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, PetTracker> hm = new HashMap<String, PetTracker>();
		
		if(CategoryName==null || CategoryName.equals("all"))
		{
			hm.putAll(SaxParserDataStore.pTMap);
			name = "";
		}
		else
		{
		  if(CategoryName.equals("Xingqijia"))
		  {
			for(Map.Entry<String,PetTracker> entry : SaxParserDataStore.pTMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Xingqijia"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Xingqijia";
		  }
		  else if(CategoryName.equals("Whistle"))
		  {
			for(Map.Entry<String,PetTracker> entry : SaxParserDataStore.pTMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Whistle"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
			name = "Whistle";
		  }
		  else if(CategoryName.equals("GBD"))
		  {
			for(Map.Entry<String,PetTracker> entry : SaxParserDataStore.pTMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("GBD"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "GBD";
		  }
		}

		/* Header, Left Navigation Bar are Printed.

		All the Games and Games information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);

		if(CategoryName==null)
		{
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>"+name+" PetTracker</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, PetTracker> entry : hm.entrySet()){
				PetTracker pettracker = entry.getValue();
				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+pettracker.getName()+"</h3>");
				pw.print("<strong>"+ "$" + pettracker.getPrice() + "</strong><ul>");
				pw.print("<li id='item'><img src='img/"+pettracker.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='pettrackers'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='pettrackers'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
					    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='pettrackers'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
					    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
				pw.print("</ul></div></td>");
				if(i%3==0 || i == size) pw.print("</tr>");
				i++;
			}	
			pw.print("</table></div></div></div>");		
			utility.printHtml("Footer.html");
		} else {
			
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>"+name+" PetTracker</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, PetTracker> entry : hm.entrySet()){
				PetTracker pettracker = entry.getValue();
				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+pettracker.getName()+"</h3>");
				pw.print("<strong>"+ "$" + pettracker.getPrice() + "</strong><ul>");
				pw.print("<li id='item'><img src='img/"+pettracker.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='pettrackers'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='pettrackers'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='hidden' name='price' value='"+pettracker.getPrice()+"'>" +
						"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='pettrackers'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' value='ViewReview' class='btnreview'></form></li>");
				pw.print("</ul></div></td>");
				if(i%3==0 || i == size) pw.print("</tr>");
				i++;
			}		
			pw.print("</table></div></div></div>");		
			utility.printHtml("Footer.html");
		}
		
	}

}
