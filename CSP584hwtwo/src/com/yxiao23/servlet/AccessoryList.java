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

import com.yxiao23.bean.Accessory;

@WebServlet("/AccessoryList")

public class AccessoryList extends HttpServlet {

	/* Accessory Page Displays all the Accessories and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Console maker whether it is microsft or sony or nintendo 
		   Add the respective product value to hashmap  */
		String name = null;
		String CategoryName = request.getParameter("maker");

		HashMap<String, Phones> hm = new HashMap<String, Phones>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.phonesMap);
			name = "";
		}
		else {
			
			if(CategoryName.equals("Samsung"))
			{
				for(Map.Entry<String,Phones> entry : SaxParserDataStore.phonesMap.entrySet())
				{	
					if(entry.getValue().getRetailer().equals("Samsung"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				
			}
			else if(CategoryName.equals("Apple"))
			{	
				for(Map.Entry<String,Phones> entry : SaxParserDataStore.phonesMap.entrySet())
				{	
					if(entry.getValue().getRetailer().equals("Apple"))
					{ 
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
			}
			else if(CategoryName.equals("Sony"))
			{
				for(Map.Entry<String,Phones> entry : SaxParserDataStore.phonesMap.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Sony"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}	
			}
		}
			
//		Console console = hm.get(ConsoleName);
				
		/* Header, Left Navigation Bar are Printed.

		All the Accessories and Accessories information are dispalyed in the Content Section

		and then Footer is Printed*/

		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+ name +": Accessories</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; 
		int size= hm.size();
		for(Map.Entry<String, Phones> entry : hm.entrySet())
		{
			Phones phone = entry.getValue();
//			Console console = entry.getValue();
			for(Map.Entry<String, String> acc:phone.getAccessories().entrySet())
			{
		        
				Accessory accessory = SaxParserDataStore.accessories.get(acc.getValue());
				if(i%2==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+accessory.getName()+"</h3>");
				pw.print("<strong>"+accessory.getPrice()+"$</strong><ul>");
				pw.print("<li id='item'><img src='img/"+accessory.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+acc.getValue()+"'>"+
						"<input type='hidden' name='type' value='accessories'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='access' value='"+phone.getName()+"'>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				 pw.print("<li><form method='post' action='WriteReview'>" + "<input type='hidden' name='name' value='" + acc + "'>" +
	                        "<input type='hidden' name='type' value='accessories'>" +
	                        "<input type='hidden' name='maker' value='" + accessory.getRetailer() + "'>" +
	                        "<input type='hidden' name='access' value='" + accessory.getName() + "'>" +
	                        "<input type='hidden' name='price' value='"+accessory.getPrice()+"'>" +
	                        "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
	                pw.print("<li><form method='post' action='ViewReview'>" + "<input type='hidden' name='name' value='" + acc + "'>" +
	                        "<input type='hidden' name='type' value='accessories'>" +
	                        "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
	                        "<input type='hidden' name='access' value='" + accessory.getName() + "'>" +
	                        "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
		
				pw.print("</ul></div></td>");
				if(i%2==0 || i == size) pw.print("</tr>");
				i++;
			
			}	
		}	
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
	}
}
