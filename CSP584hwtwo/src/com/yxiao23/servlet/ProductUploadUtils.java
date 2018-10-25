package com.yxiao23.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yxiao23.bean.Products;

/**
 * Servlet implementation class ProductUploadUtils
 */
@WebServlet("/ProductUploadUtils")
public class ProductUploadUtils extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int initProductId = 10000;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//map集合存数据
			Map<String,String> map = new HashMap<String,String>();
			//磁盘文件项工厂对象
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			//核心解析类
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			//解析request请求，返回的是list集合
			//定义一个集合，保存多选复选框的值
			List<String> optionList = new ArrayList<String>();
			
			List<FileItem> list = servletFileUpload.parseRequest(request);
			
			//define the url of upload file
			String url = null;
			//遍历集合，获取每个fileitem,判断是表单项还是文件上传项
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					//普通表单项
					//接受表单项参数的值
					String name = fileItem.getFieldName(); //获得表单项name属性的值
					String value = fileItem.getString("UTF-8");//获得表单项的值
					//System.out.println(name + "  " + value);	
					if("additionalOption".equals(name)){
						optionList.add(fileItem.getString("UTF-8"));
						String mutiValue = optionList.toString().substring(1, optionList.toString().length()-1);
						//System.out.println(name + "    "+mutiValue);	
						map.put(name, mutiValue);
					} 
					else {
						map.put(name, value);
						
					}
				} else {
					//file upload
					String fileName = fileItem.getName();
					if (fileName != null && !"".equals(fileName)) {
						//use fileUpload utils to create a unique fileName--uuidFileName
						String uuidFileName = fileUpload.getUUIDFileName(fileName);
						InputStream is = (InputStream) fileItem.getInputStream();
						//save file in upload
						String path = this.getServletContext().getRealPath("/upload");
						//joint the inputStream to outputStream
						url = path+"//"+uuidFileName;
						OutputStream os = new FileOutputStream(url);
						int len = 0;
						byte[] b = new byte[1024];
						while ((len = is.read(b)) != -1) {
							os.write(b, 0, len);
						}
						is.close();
						os.close();
						
					}
					
				}
			}
			System.out.println(map);
			//List<Products> productList = (List<Products>) this.getServletContext().getAttribute("list");
			Map<String, Products> proMap = (Map<String, Products>) this.getServletContext().getAttribute("map");
			
			//完成数据的封装到Products对象中
			Products pro = new Products();
			int productId = initProductId ++;
			
			pro.setProductId(String.valueOf(productId));
			pro.setAdditionalOption(map.get("additionalOption"));
			pro.setImageUrl(url);
			pro.setProductCategory(map.get("productCategory"));
			pro.setProductInfo(map.get("productInfo"));
			pro.setProductName(map.get("productName"));
			pro.setProductPrice(map.get("productPrice"));
			pro.setProductStatus("1");
			pro.setProductStock(map.get("productStock"));
			
			proMap.put(pro.getProductId(), pro);
			//准备存入初始化的list集合中
			//获得servletContext对象
			//List<Products> proList = (List<Products>) this.getServletContext().getAttribute("list");
			this.getServletContext().setAttribute("map", proMap);
			for (Products value : proMap.values()) {
				System.out.println("value = " + value );
				
			}
		    
		    
		    response.sendRedirect(request.getContextPath()+"/product/ProductManagement.jsp");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
