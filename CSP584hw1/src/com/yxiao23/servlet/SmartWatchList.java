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

import com.yxiao23.bean.SmartWatches;

@WebServlet("/SmartWatchList")

public class SmartWatchList extends HttpServlet {

	/* Games Page Displays all the Games and their Information in Game Speed */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Games type whether it is electronicArts or activision or takeTwoInteractive */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, SmartWatches> hm = new HashMap<String, SmartWatches>();
		
		if(CategoryName==null || CategoryName.equals("all"))
		{
			hm.putAll(SaxParserDataStore.smartwatchMap);
			name = "";
		}
		else
		{
		  if(CategoryName.equals("Garmin"))
		  {
			for(Map.Entry<String,SmartWatches> entry : SaxParserDataStore.smartwatchMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Garmin"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Garmin";
		  }
		  else if(CategoryName.equals("MBHB"))
		  {
			for(Map.Entry<String,SmartWatches> entry : SaxParserDataStore.smartwatchMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("MBHB"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
			name = "MBHB";
		  }
		  else if(CategoryName.equals("GOKOO"))
		  {
			for(Map.Entry<String,SmartWatches> entry : SaxParserDataStore.smartwatchMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("GOKOO"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "GOKOO";
		  }
		  else if(CategoryName.equals("Ticwatch"))
		  {
			for(Map.Entry<String,SmartWatches> entry : SaxParserDataStore.smartwatchMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Ticwatch"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Ticwatch";
		  }
		}

		/* Header, Left Navigation Bar are Printed.

		All the Games and Games information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		if(CategoryName==null)
		{
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>"+name+" SmartWatches</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, SmartWatches> entry : hm.entrySet()){
				SmartWatches smartwatch = entry.getValue();
				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+smartwatch.getName()+"</h3>");
				pw.print("<strong>"+ "$" + smartwatch.getPrice() + "</strong><ul>");
				pw.print("<li id='item'><img src='img/"+smartwatch.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='smartwatches'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='smartwatches'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
					    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='smartwatches'>"+
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
			pw.print("<a style='font-size: 24px;'>"+name+" SmartWatches</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, SmartWatches> entry : hm.entrySet()){
				SmartWatches smartwatch = entry.getValue();
				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+smartwatch.getName()+"</h3>");
				pw.print("<strong>"+ "$" + smartwatch.getPrice() + "</strong><ul>");
				pw.print("<li id='item'><img src='img/"+smartwatch.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='smartwatches'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='smartwatches'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='smartwatches'>"+
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

