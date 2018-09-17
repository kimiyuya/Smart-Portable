package com.yxiao23.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//@WebServlet("/Startup")
@WebServlet(loadOnStartup=1,urlPatterns={"/Startup"})

/*  
startup servlet Intializes HashMap in SaxParserDataStore
*/

public class Startup extends HttpServlet
{

	public void init() throws ServletException
    {
		
		SaxParserDataStore.addHashmap();

    }
}
