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

import com.yxiao23.bean.FitnessWatches;

@WebServlet("/FitnessWatchList")

public class FitnessWatchList extends HttpServlet {

	/* Games Page Displays all the Games and their Information in Game Speed */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Games type whether it is electronicArts or activision or takeTwoInteractive */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, FitnessWatches> hm = new HashMap<String, FitnessWatches>();
		
		if(CategoryName==null || CategoryName.equals("all"))
		{
			hm.putAll(SaxParserDataStore.fitnesswatchMap);
			name = "";
		}
		else
		{
		  if(CategoryName.equals("Lankey"))
		  {
			for(Map.Entry<String,FitnessWatches> entry : SaxParserDataStore.fitnesswatchMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Lankey"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Lankey";
		  }
		  else if(CategoryName.equals("wesso"))
		  {
			for(Map.Entry<String,FitnessWatches> entry : SaxParserDataStore.fitnesswatchMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("wesso"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
			name = "wesso";
		  }
		  else if(CategoryName.equals("LETSCOM"))
		  {
			for(Map.Entry<String,FitnessWatches> entry : SaxParserDataStore.fitnesswatchMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("LETSCOM"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "LETSCOM";
		  }
		  else if(CategoryName.equals("KARSEEN"))
		  {
			for(Map.Entry<String,FitnessWatches> entry : SaxParserDataStore.fitnesswatchMap.entrySet())
				{
				if(entry.getValue().getRetailer().equals("KARSEEN"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "KARSEEN";
		  }
		}

		/* Header, Left Navigation Bar are Printed.

		All the Games and Games information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		if(CategoryName==null)
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>"+name+" FitnessWatches</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, FitnessWatches> entry : hm.entrySet()){
				FitnessWatches fitnesswatch = entry.getValue();
				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+fitnesswatch.getName()+"</h3>");
				pw.print("<strong>"+ "$" + fitnesswatch.getPrice() + "</strong><ul>");
				pw.print("<li id='item'><img src='img/"+fitnesswatch.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='fitnesswatches'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='fitnesswatches'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
					    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='fitnesswatches'>"+
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
			pw.print("<a style='font-size: 24px;'>"+name+" FitnessWatches</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, FitnessWatches> entry : hm.entrySet()){
				FitnessWatches fitnesswatch = entry.getValue();
				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+fitnesswatch.getName()+"</h3>");
				pw.print("<strong>"+ "$" + fitnesswatch.getPrice() + "</strong><ul>");
				pw.print("<li id='item'><img src='img/"+fitnesswatch.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='fitnesswatches'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='fitnesswatches'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='fitnesswatches'>"+
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
