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

import com.yxiao23.bean.HeadPhones;

@WebServlet("/HeadphoneList")

public class HeadphoneList extends HttpServlet {

	/* Games Page Displays all the Games and their Information in Game Speed */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Games type whether it is electronicArts or activision or takeTwoInteractive */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, HeadPhones> hm = new HashMap<String, HeadPhones>();
		
		if(CategoryName==null || CategoryName.equals("all"))
		{
			hm.putAll(SaxParserDataStore.headphoneMap);
			name = "";
		}
		else
		{
		  if(CategoryName.equals("Edifier"))
		  {
			for(Map.Entry<String,HeadPhones> entry : SaxParserDataStore.headphoneMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Edifier"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Edifier";
		  }
		  else if(CategoryName.equals("AILIHEN"))
		  {
			for(Map.Entry<String,HeadPhones> entry : SaxParserDataStore.headphoneMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("AILIHEN"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
			name = "AILIHEN";
		  }
		  else if(CategoryName.equals("Elecder"))
		  {
			for(Map.Entry<String,HeadPhones> entry : SaxParserDataStore.headphoneMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Elecder"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Elecder";
		  }
		}

		/* Header, Left Navigation Bar are Printed.

		All the Games and Games information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		if(CategoryName==null)
		{
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>"+name+" HeadPhones</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, HeadPhones> entry : hm.entrySet()){
				HeadPhones headphone = entry.getValue();
				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+headphone.getName()+"</h3>");
				pw.print("<strong>"+ "$" + headphone.getPrice() + "</strong><ul>");
				pw.print("<li id='item'><img src='img/"+headphone.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='headphones'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='headphones'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
					    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='headphones'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
					    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
				pw.print("</ul></div></td>");
				if(i%3==0 || i == size) pw.print("</tr>");
				i++;
			}
		} else {
			
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>"+name+" HeadPhones</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, HeadPhones> entry : hm.entrySet()){
				HeadPhones headphone = entry.getValue();
				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+headphone.getName()+"</h3>");
				pw.print("<strong>"+ "$" + headphone.getPrice() + "</strong><ul>");
				pw.print("<li id='item'><img src='img/"+headphone.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='headphones'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='headphones'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='headphones'>"+
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
