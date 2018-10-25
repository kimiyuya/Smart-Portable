package com.yxiao23.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxiao23.bean.Users;



@WebServlet("/SalesmanHome")
public class SalesmanHome extends HttpServlet {
	private String error_msg;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        displaySalesmanHome(request, response, pw, "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

        //创建customer的表格
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        //创建order的表格
        String customerName = request.getParameter("customerName");
        String itemName = request.getParameter("itemName");
        String itemCatalog = request.getParameter("itemCatalog");
        String creditCardNo = request.getParameter("creditCardNo");
        String customerAddress = request.getParameter("customerAddress");


        HashMap<String, Users> hm = new HashMap<String, Users>();
        String TOMCAT_HOME = System.getProperty("catalina.home");

        //get the user details from file
        try {
        	hm = MySqlDataStoreUtilities.selectUser();
        } catch (Exception e) {

        }

        if (request.getParameter("customer") != null && request.getParameter("customer").equals("Create Customer")) {
            //提交的是创建customer的表格
            if (!password.equals(repassword)) {
                error_msg = "Passwords doesn't match!";  //已完成测试
                displaySalesmanHome(request, response, pw, "customer");
            } else {

                // if the user already exist show error that already exist
                if (hm.containsKey(username)) {
                    error_msg = "Username already exist."; //已完成测试
                    displaySalesmanHome(request, response, pw, "customer");
                } else {
                	MySqlDataStoreUtilities.insertUser(username, password,repassword,"customer");

                    HttpSession session = request.getSession(true);
                    session.setAttribute("login_msg", "The customer account created successfully.");

                    //创建customer成功
                    error_msg = "The customer account created successfully.";
                    displaySalesmanHome(request, response, pw, "customer");  //已完成测试
                }

            }
        } else if (request.getParameter("order") != null && request.getParameter("order").equals("Create Order")) {
            //提交的是创建order的表格
            if (!hm.containsKey(customerName)) { //已完成测试
                error_msg = "Cannot found this customer.";
                displaySalesmanHome(request, response, pw, "order");
            } else {
                double totalPrice;
                if (utility.isContainsStr(request.getParameter("totalPrice"))) {
                    //含有字母，报错
                    error_msg = "Please type in number!";  //已完成测试
                    displaySalesmanHome(request, response, pw, "order");
                    return;
                } else {
                    totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
                }
                if (utility.isItemExist(itemCatalog, itemName)) { //已完成测试  // 判断商品是否存在的功能没有完成
                    SimpleDateFormat df = new SimpleDateFormat("HHmmss");//设置日期格式
                    int orderId = Integer.parseInt(df.format(new Date()));  //设置订单号为当前下单时间的时分秒
                    utility.storeNewOrder(orderId, itemName, customerName, totalPrice, customerAddress, creditCardNo);
                    //创建order成功
                    error_msg = "The order created successfully.";
                    displaySalesmanHome(request, response, pw, "order");
                } else { //已完成测试
                    error_msg = "Cannot found this item.";
                    displaySalesmanHome(request, response, pw, "order");
                }
            }
        }
    }

    protected void displaySalesmanHome(HttpServletRequest request,
                                       HttpServletResponse response, PrintWriter pw, String flag)  //error: true代表有错误，false代表没有错误
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        
        
        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Create New Customer</a>");
        pw.print("</h2>");
        pw.print("<div class='entry'>");

        if (flag.equals("customer"))
            pw.print("<h4 style='color:red'>" + error_msg + "</h4>");
        //显示创建customer的表格
        pw.print("<form action='SalesmanHome' method='post'>");
        pw.print("<table style='width:100%'><tr><td>");
        pw.print("<h4>Username</h4></td><td><input type='text' name='username' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Password</h4></td><td><input type='password' name='password' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Re-Password</h4></td><td><input type='password' name='repassword' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<input type='submit' class='btnbuy' value='Create Customer' name='customer' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
        pw.print("</td></tr><tr><td></td><td>");
        pw.print("</td></tr></table>");
        pw.print("</form></div></div>");


        //显示创建订单的表格
        pw.print("<div class='post'>");
        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Create New Order</a>");
        pw.print("</h2>");
        pw.print("<div class='entry'>");
        if (flag.equals("order"))
            pw.print("<h4 style='color:red'>" + error_msg + "</h4>");
        pw.print("<form action='SalesmanHome' method='post'>");
        pw.print("<table style='width:100%'><tr><td>");
        pw.print("<h4>Customer name</h4></td><td><input type='text' name='customerName' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Item name</h4></td><td><input type='text' name='itemName' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");

        pw.print("<h4>Item catalog</h4><td><select name='itemCatalog' class='input'>" +
                "<option value='FitnessWatch' selected>Fitness watch</option>" +
                "<option value='SmartWatch'>Smart watch</option>" +
                "<option value='Headphone'>Headphone</option>" +
                "<option value='VirtualReality'>Virtual reality</option>" +
                "<option value='PetTracker'>Pet tracker</option>" +
                "<option value='Phone'>Phone</option>" +
                "<option value='Laptop'>Laptop</option>" +
                "<option value='SmartSpeaker'>Smart Speaker</option>" +
                "<option value='Accessory'>Accessory</option></select>");
        pw.print("</td></tr></td><tr><td>");
        pw.print("<h4>Total price</h4></td><td><input type='text' name='totalPrice' value='' class='input' required></input>");
        //   pw.print("<h4>Total price</h4></td><td><input type=\"text\" name=\"totalPrice\" onkeyup=\"this.value=this.value.replace(/[^\d]/g,\'\')\" onafterpaste=\"this.value=this.value.replace(/[^\d]/g,\'\')\"  value=\'\'</input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Credit/accountNo</h4></td><td><input type='text' name='creditCardNo' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Customer Address</h3></td><td><input type='text' name='customerAddress' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<input type='submit' class='btnbuy' value='Create Order' name='order' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
        pw.print("</td></tr><tr><td></td><td>");
        pw.print("</td></tr></table>");
        pw.print("</form></div></div>");


        //显示order的详细信息
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        String TOMCAT_HOME = System.getProperty("catalina.home");
        try {
        	 orderPayments = MySqlDataStoreUtilities.selectOrder();
        } catch (Exception ignored) {

        }

        pw.print("<div class='post'>");
        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>View Orders</a>");
        pw.print("</h2><div class='entry'>");

        pw.print("<table class='gridtable'>");
        pw.print("<tr>");
        pw.print("<td>Order Id:</td>");
        pw.print("<td>Username:</td>");
        pw.print("<td>Product Name:</td>");
        pw.print("<td>Price:</td></td>");
        pw.print("<td>Address:</td>");
        pw.print("<td>Credit Card:</td>");
        pw.print("</tr>");
        for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
            for (OrderPayment od : entry.getValue()) {
            	String a = String.valueOf(od.getOrderId());
            	System.out.println(a);
            	int apple = 10;
                pw.print("<form method='post' action='RemoveUpdateOrder' enctype='multipart/form-data>'");
                pw.print("<tr>");
                pw.print("<td>" + od.getOrderId() + "</td>" +
                        "<td>" + od.getUserName() + "</td>" +
                        "<td>" + od.getOrderName() + "</td>" +
                        "<td>" + od.getOrderPrice() + "</td>" +
                        "<td>" + od.getUserAddress() + "</td>" +
                        "<td>" + od.getCreditCardNo() + "</td>");
                pw.print("<input type='hidden' name='123' value='"+apple+"'>");
                pw.print("<input type='hidden' name='orderName' value='" + od.getOrderName() + "'>");
               // pw.print("<input type='hidden' name='orderId' value='" + od.getOrderId() + "'>");
               //id 穿不过去
                pw.print("<input type='hidden' name='b' value='"+a+"'>");
                pw.print("<input type='hidden' name='username' value='" + od.getUserName() + "'>");
                pw.print("<input type='hidden' name='productName' value='" + od.getOrderName() + "'>");
                pw.print("<input type='hidden' name='price' value='" + od.getOrderPrice() + "'>");
                pw.print("<input type='hidden' name='address' value='" + od.getUserAddress() + "'>");
                pw.print("<input type='hidden' name='creditCard' value='" + od.getCreditCardNo() + "'>");
                pw.print("<input type='hidden' name='userType' value='Salesman'>");
                pw.print("<td><input type='submit' name='Order' value='Cancel' class='btnbuy'></td>");
                pw.print("<td><input type='submit' name='Order' value='Update' class='btnbuy'></td>");
                pw.print("</tr>");
                pw.print("</form>");
            }
        }
        pw.print("</table>");
        pw.print("</h2></div></div></div>");
    }
	

}