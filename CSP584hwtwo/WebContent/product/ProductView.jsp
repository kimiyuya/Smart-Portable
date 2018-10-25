<%@page import="com.yxiao23.bean.Products"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(session.getAttribute("product") != null){
			Products p = (Products)session.getAttribute("product");
			//System.out.println(p.getPath());
			int idx = p.getImageUrl().lastIndexOf("//");
			// 获得文件上传的唯一文件名：
			String fileName = p.getImageUrl().substring(idx+1);
			// 获得文件上传的唯一文件名：
	%>
		<table align="center">
				<tr>
					<td align="center"><img src="/CSP584hw1/upload/<%= fileName %>" /></td>
				</tr>
				<tr>
					<td align="center">PRODUCT NAME: <%= p.getProductName() %></td>
					<td align="center">PRODUCT PRICE: <%= p.getProductPrice() %></td>
					<td align="center">PRODUCT AdditionalOption: <%= p.getAdditionalOption() %></td>
					<td align="center">PRODUCT Category: <%= p.getProductCategory() %></td>
					<td align="center">PRODUCT ID: <%= p.getProductId() %></td>
					<td align="center">PRODUCT Information: <%= p.getProductInfo() %></td>
					<td align="center">PRODUCT Status: <%= p.getProductStatus() %></td>
					<td align="center">PRODUCT Stock: <%= p.getProductStock() %></td>
					
				</tr>
			</table>
	<%
		}else{
	%>
	<h1>do not found any product</h1>
	<%
		}
	%>
</body>
</html>