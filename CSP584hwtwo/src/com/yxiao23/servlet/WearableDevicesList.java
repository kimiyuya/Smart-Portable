package com.yxiao23.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WearableDevicesList
 */
@WebServlet("/WearableDevicesList")
public class WearableDevicesList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FitnessWatchList fw = new FitnessWatchList();
		SmartWatchList sw = new SmartWatchList();
		HeadphoneList hp = new HeadphoneList();
		VirtualRealityList vr = new VirtualRealityList();
		PetTrackerList pt = new PetTrackerList();
		
		fw.doGet(request, response);
		sw.doGet(request, response);
		hp.doGet(request, response);
		vr.doGet(request, response);
		pt.doGet(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
