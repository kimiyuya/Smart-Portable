package com.yxiao23.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yxiao23.bean.FitnessWatches;
import com.yxiao23.bean.HeadPhones;
import com.yxiao23.bean.Laptops;
import com.yxiao23.bean.PetTracker;
import com.yxiao23.bean.SmartSpeaker;
import com.yxiao23.bean.SmartWatches;
import com.yxiao23.bean.VirtualReality;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {

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
		String username = utility.username();
		utility.printHtml("Header.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Add Product</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<form name ='AddProduct' action='AddProduct' method='post' enctype='multipart/form-data'>");
		pw.print("<table align=\"center\" border=\"1\" width=\"500px\"><tr>");
		
		
		pw.print("<tr><td>Product Name:</td><td><input type ='text' name = 'productName'/></td></tr>");
		pw.print("<tr><td>Product Type:</td><td><select name='productType'><option>Fitness Watches</option><option>HeadPhones</option><option>Laptops</option><option>PetTracker</option><option>Smart Speaker</option><option>Smart Watches</option><option>Virtual Reality</option><option>Phones</option></td>");
		pw.print("<tr><td>Product Price:</td><td><input type ='text' name = 'productPrice'/></td></tr>");
		//add image
		pw.print("<tr><td>Product Image:</td><td><input type ='file' id='photo' name = 'productImage'/></td></tr>");
		pw.print("<tr><td>Product Retailer:</td><td><input type ='text' name = 'productRetailer'/></td></tr>");
		pw.print("<tr><td>Product Condition:</td><td><label for='r5'><input type='radio' value='new' name='productCondition'>NEW</label><label for='r6'><input type='radio' value='used' name='productCondition'>USED</label></td></tr>");
		pw.print("<tr><td>Product Discount:</td><td><input type ='text' name = 'productDiscount'/></td></tr>");
		pw.print("<td><input type='reset' name='Reset' value='Reset' class='btnbuy'></td>");
		pw.print("<td><input type='submit' name='Submit' value='Submit' class='btnbuy'></td>");
		pw.print("</form></table></div></div></div>");
		utility.printHtml("Footer.html");
		
		// 数据的接收
				// 文件上传基本操作:
		try {
			// 定义一个Map集合用于保存接收到的数据:
			Map<String,String> map = new HashMap<String,String>();
			// 1.创建一个磁盘文件项工厂对象
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			// 2.创建一个核心解析类
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			// 3.解析request请求，返回的是List集合，List集合中存放的是FileItem对象
			List<FileItem> list = servletFileUpload.parseRequest(request);
			
			// 4.遍历集合，获得每个FileItem，判断是表单项还是文件上传项
			String url = null;
			String productType = null;
			for (FileItem fileItem : list) {
				// 判断是表单项还是文件上传项
				if(fileItem.isFormField()){
					// 普通表单项:
					// 接收表单项参数的值:
					String name = fileItem.getFieldName(); // 获得表单项的name属性的值
					String value = fileItem.getString("UTF-8");// 获得表单项的值
					if(name.equals("productType")) {
						productType = fileItem.getString("UTF-8");
					}
					System.out.println(name+"    "+value);
					map.put(name, value);
					
			}else {
				// 文件上传项:
				// 文件上传功能：
				// 获得文件上传的名称：
				String fileName = fileItem.getName();
				if(fileName !=null && !"".equals(fileName)){
					// 通过工具类获得唯一文件名:
					String uuidFileName = utility.getUUIDFileName(fileName);
					// 获得文件上传的数据：
					InputStream is = fileItem.getInputStream();
					// 获得文件上传的路径:
					String path = this.getServletContext().getRealPath("/img");
					// 将输入流对接到输出流就可以了:
					url = path+"/"+uuidFileName;
					OutputStream os = new FileOutputStream(url);
					int len = 0;
					byte[] b = new byte[1024];
					while((len = is.read(b))!=-1){
						os.write(b, 0, len);
					}
					is.close();
					os.close();	
					
					map.put("productImage", uuidFileName);
				}
			}
			}
				System.out.println(map);
		// 获得ServletContext对象:
		//List<User> userList = (List<User>) this.getServletContext().getAttribute("list");
		
		
		String id = map.get("productName");
		
		//default id = productName
//		String id = request.getParameter("productName");
//		String productName = request.getParameter("productName");
////		String productType = fileName;
////				request.getParameter("productType");
//		String productPrice = request.getParameter("productPrice");
//		String productRetailer = request.getParameter("productRetailer");
//		String productCondition = request.getParameter("productCondition");
//		String productDiscount = request.getParameter("productDiscount");
		
		if(productType.equals("Fitness Watches")) {		
			FitnessWatches fw = new FitnessWatches();
			fw.setId(id);
			fw.setName(map.get("productName"));
			fw.setPrice(Double.parseDouble(map.get("productPrice")));
			fw.setRetailer(map.get("productRetailer"));
			fw.setCondition(map.get("productCondition"));
			fw.setDiscount(Double.parseDouble(map.get("productDiscount")));
			fw.setImage(map.get("productImage"));
			utility.addProduct(id, fw,productType);	
		}
		if(productType.equals("HeadPhones")) {
			HeadPhones hp = new HeadPhones();
			hp.setId(id);
			hp.setName(map.get("productName"));
			hp.setPrice(Double.parseDouble(map.get("productPrice")));
			hp.setRetailer(map.get("productRetailer"));
			hp.setCondition(map.get("productCondition"));
			hp.setDiscount(Double.parseDouble(map.get("productDiscount")));
			hp.setImage(map.get("productImage"));
			utility.addProduct(id, hp,productType);	
		}
		if(productType.equals("Laptops")) {
			Laptops lt = new Laptops();
			lt.setId(id);
			lt.setName(map.get("productName"));
			lt.setPrice(Double.parseDouble(map.get("productPrice")));
			lt.setRetailer(map.get("productRetailer"));
			lt.setCondition(map.get("productCondition"));
			lt.setDiscount(Double.parseDouble(map.get("productDiscount")));
			lt.setImage(map.get("productImage"));
			utility.addProduct(id, lt,productType);
		}
		if(productType.equals("PetTracker")) {
			PetTracker pt = new PetTracker();
			pt.setId(id);
			pt.setName(map.get("productName"));
			pt.setPrice(Double.parseDouble(map.get("productPrice")));
			pt.setRetailer(map.get("productRetailer"));
			pt.setCondition(map.get("productCondition"));
			pt.setDiscount(Double.parseDouble(map.get("productDiscount")));
			pt.setImage(map.get("productImage"));
			utility.addProduct(id, pt,productType);	
		}
		if(productType.equals("Smart Speaker")) {
			SmartSpeaker ss = new SmartSpeaker();
			ss.setId(id);
			ss.setName(map.get("productName"));
			ss.setPrice(Double.parseDouble(map.get("productPrice")));
			ss.setRetailer(map.get("productRetailer"));
			ss.setCondition(map.get("productCondition"));
			ss.setDiscount(Double.parseDouble(map.get("productDiscount")));
			ss.setImage(map.get("productImage"));
			utility.addProduct(id, ss,productType);
		}
		if(productType.equals("Smart Watches")) {
			SmartWatches sw = new SmartWatches();
			sw.setId(id);
			sw.setName(map.get("productName"));
			sw.setPrice(Double.parseDouble(map.get("productPrice")));
			sw.setRetailer(map.get("productRetailer"));
			sw.setCondition(map.get("productCondition"));
			sw.setDiscount(Double.parseDouble(map.get("productDiscount")));
			sw.setImage(map.get("productImage"));
			utility.addProduct(id, sw,productType);
		}
		if(productType.equals("Virtual Reality")) {
			VirtualReality vr = new VirtualReality();
			vr.setId(id);
			vr.setName(map.get("productName"));
			vr.setPrice(Double.parseDouble(map.get("productPrice")));
			vr.setRetailer(map.get("productRetailer"));
			vr.setCondition(map.get("productCondition"));
			vr.setDiscount(Double.parseDouble(map.get("productDiscount")));
			vr.setImage(map.get("productImage"));
			utility.addProduct(id, vr,productType);
		}
		if(productType.equals("Phones")) {
			Phones phone = new Phones();
			phone.setId(id);
			phone.setName(map.get("productName"));
			phone.setPrice(Double.parseDouble(map.get("productPrice")));
			phone.setRetailer(map.get("productRetailer"));
			phone.setCondition(map.get("productCondition"));
			phone.setDiscount(Double.parseDouble(map.get("productDiscount")));
			phone.setImage(map.get("productImage"));
			utility.addProduct(id, phone,productType);	
		}
		response.sendRedirect("StoreManagement");
	}catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			}
		

}
