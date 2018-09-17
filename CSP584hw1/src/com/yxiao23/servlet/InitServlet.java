package com.yxiao23.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxiao23.bean.Products;


/**
 * Servlet implementation class InitServlet
 */
//@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {


	@Override
	public void init() throws ServletException {
		Map<String, Products> map = new HashMap<String, Products>();
		map.put("1000", new Products("1000","fff","1","apple","food","11","22","/Users/kimiyuya/Downloads/tomcat7/apache-tomcat-7.0.90/wtpwebapps/CSP584hw1/upload//d9b30a75d2634b0699eea4b7d7a992dc.jpeg","abc"));
		//save list to the domain of ServletContext;
		this.getServletContext().setAttribute("map", map);
	}
	


}
