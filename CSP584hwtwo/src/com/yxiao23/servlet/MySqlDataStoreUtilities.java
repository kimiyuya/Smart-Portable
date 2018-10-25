package com.yxiao23.servlet;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.yxiao23.bean.Accessory;
import com.yxiao23.bean.FitnessWatches;
import com.yxiao23.bean.HeadPhones;
import com.yxiao23.bean.Laptops;
import com.yxiao23.bean.PetTracker;
import com.yxiao23.bean.SmartSpeaker;
import com.yxiao23.bean.SmartWatches;
import com.yxiao23.bean.Users;
import com.yxiao23.bean.VirtualReality;

public class MySqlDataStoreUtilities {
    static Connection conn = null;

    public static void getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw2?useUnicode=true&characterEncoding=utf8", "root", "yanxue520a");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static boolean deleteOrder(int orderId) {
        try {

            getConnection();
            String deleteOrderQuery = "Delete from orders where OrderId=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1, orderId);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void insertOrder(int orderId, String userName, String orderName, double orderPrice, String userAddress, String creditCardNo) {
        try {

            //生成日期对象
            Date current_date = new Date();
            //设置日期格式化样式为：yyyy-MM-dd
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            getConnection();

            String insertIntoCustomerOrderQuery = "insert into orders (orderID, userName, orderName, orderPrice, userAddress, creditCardNo, orderTime) VALUES (?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderId);
            pst.setString(2, userName);
            pst.setString(3, orderName);
            pst.setDouble(4, orderPrice);
            pst.setString(5, userAddress);
            pst.setString(6, creditCardNo);
            pst.setString(7, SimpleDateFormat.format(current_date.getTime()));
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() {

        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();

        try {

            getConnection();
            //select the table
            String selectOrderQuery = "select * from orders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<OrderPayment> orderList = new ArrayList<OrderPayment>();
            while (rs.next()) {
                if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));
                System.out.println("data is" + rs.getInt("OrderId") + orderPayments.get(rs.getInt("OrderId")));

                //add to orderpayment hashmap
                OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("userName"), rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getString("userAddress"), rs.getString("creditCardNo"));
                listOrderPayment.add(order);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return orderPayments;
    }

    public static HashMap<String, OrderPayment> selectDailyTransaction() {
        HashMap<String, OrderPayment> hm = new HashMap<String, OrderPayment>();
        try {
            getConnection();

            String selectAcc = "SELECT count(orderTime) as soldAmount, orderTime from orders group by orderTime";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            ResultSet rs = pst.executeQuery();

            int i = 0;
            while (rs.next()) {
                OrderPayment orderPayment = new OrderPayment(rs.getInt("soldAmount"), rs.getDate("orderTime"));
                i++;
                hm.put(String.valueOf(i), orderPayment);
                //orderPayment.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static ArrayList<OrderPayment> selectDailyTransactionForChart() {
        ArrayList<OrderPayment> orderPaymentArrayList = new ArrayList<OrderPayment>();
        try {
            getConnection();

            String selectAcc = "SELECT count(orderTime) as soldAmount, orderTime from orders group by orderTime";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                OrderPayment orderPayment = new OrderPayment(rs.getInt("soldAmount"), rs.getDate("orderTime"));
                orderPaymentArrayList.add(orderPayment);
            }
        } catch (Exception e) {
        }
        return orderPaymentArrayList;
    }


    public static boolean insertUser(String username, String password, String rePassword, String userType) {
        try {

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO user(username,password,repassword,usertype) "
                    + "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, rePassword);
            pst.setString(4, userType);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }


    public static HashMap<String, Users> selectUser() {
        HashMap<String, Users> hm = new HashMap<String, Users>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "select * from user";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while (rs.next()) {
                Users user = new Users(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
                hm.put(rs.getString("username"), user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hm;
    }

    public static void insertProducts() {
        try {

            getConnection();
            String insertProductQuery = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
                    "VALUES (?,?,?,?,?,?,?,?);";
//            for (Map.Entry<String, Accessory> entry : SaxParserDataStore.accessories.entrySet()) {
//                String name = "accessories";
//                Accessory acc = entry.getValue();
//
//                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
//                pst.setString(1, name);
//                pst.setString(2, acc.getId());
//                pst.setString(3, acc.getName());
//                pst.setDouble(4, acc.getPrice());
//                pst.setString(5, acc.getImage());
//                pst.setString(6, acc.getRetailer());
//                pst.setString(7, acc.getCondition());
//                pst.setDouble(8, acc.getDiscount());
//
//                pst.executeUpdate();
//            }

            for (Map.Entry<String, FitnessWatches> entry : SaxParserDataStore.fitnesswatchMap.entrySet()) {
                String name = "FitnessWatch";
                FitnessWatches fitnessWatch = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
                pst.setString(1, name);
                pst.setString(2, fitnessWatch.getId());
                pst.setString(3, fitnessWatch.getName());
                pst.setDouble(4, fitnessWatch.getPrice());
                pst.setString(5, fitnessWatch.getImage());
                pst.setString(6, fitnessWatch.getRetailer());
                pst.setString(7, fitnessWatch.getCondition());
                pst.setDouble(8, fitnessWatch.getDiscount());

                pst.executeUpdate();


            }

            for (Map.Entry<String, HeadPhones> entry : SaxParserDataStore.headphoneMap.entrySet()) {
                String name = "Headphone";
                HeadPhones headphone = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
                pst.setString(1, name);
                pst.setString(2, headphone.getId());
                pst.setString(3, headphone.getName());
                pst.setDouble(4, headphone.getPrice());
                pst.setString(5, headphone.getImage());
                pst.setString(6, headphone.getRetailer());
                pst.setString(7, headphone.getCondition());
                pst.setDouble(8, headphone.getDiscount());

                pst.executeUpdate();


            }

            for (Map.Entry<String, Laptops> entry : SaxParserDataStore.laptopsMap.entrySet()) {
                String name = "Laptop";
                Laptops laptop = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
                pst.setString(1, name);
                pst.setString(2, laptop.getId());
                pst.setString(3, laptop.getName());
                pst.setDouble(4, laptop.getPrice());
                pst.setString(5, laptop.getImage());
                pst.setString(6, laptop.getRetailer());
                pst.setString(7, laptop.getCondition());
                pst.setDouble(8, laptop.getDiscount());

                pst.executeUpdate();


            }

            for (Map.Entry<String, PetTracker> entry : SaxParserDataStore.pTMap.entrySet()) {
                String name = "PetTracker";
                PetTracker petTracker = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
                pst.setString(1, name);
                pst.setString(2, petTracker.getId());
                pst.setString(3, petTracker.getName());
                pst.setDouble(4, petTracker.getPrice());
                pst.setString(5, petTracker.getImage());
                pst.setString(6, petTracker.getRetailer());
                pst.setString(7, petTracker.getCondition());
                pst.setDouble(8, petTracker.getDiscount());

                pst.executeUpdate();


            }

            for (Map.Entry<String, Phones> entry : SaxParserDataStore.phonesMap.entrySet()) {
                String name = "Phone";
                Phones phone = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
                pst.setString(1, name);
                pst.setString(2, phone.getId());
                pst.setString(3, phone.getName());
                pst.setDouble(4, phone.getPrice());
                pst.setString(5, phone.getImage());
                pst.setString(6, phone.getRetailer());
                pst.setString(7, phone.getCondition());
                pst.setDouble(8, phone.getDiscount());

                pst.executeUpdate();

                try {
                    HashMap<String, String> acc = phone.getAccessories();
                    String insertAccessoryQuery = "INSERT INTO Product_accessories(productName,accessoriesName)" +
                            "VALUES (?,?);";
                    for (Map.Entry<String, String> accentry : acc.entrySet()) {
                        PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQuery);
                        pstacc.setString(1, phone.getId());
                        pstacc.setString(2, accentry.getValue());
                        pstacc.executeUpdate();
                    }
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }

            for (Map.Entry<String, SmartWatches> entry : SaxParserDataStore.smartwatchMap.entrySet()) {
                String name = "SmartWatch";
                SmartWatches smartWatch = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
                pst.setString(1, name);
                pst.setString(2, smartWatch.getId());
                pst.setString(3, smartWatch.getName());
                pst.setDouble(4, smartWatch.getPrice());
                pst.setString(5, smartWatch.getImage());
                pst.setString(6, smartWatch.getRetailer());
                pst.setString(7, smartWatch.getCondition());
                pst.setDouble(8, smartWatch.getDiscount());

                pst.executeUpdate();


            }

            for (Map.Entry<String, VirtualReality> entry : SaxParserDataStore.vrMap.entrySet()) {
                String name = "VirtualReality";
                VirtualReality virtualReality = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
                pst.setString(1, name);
                pst.setString(2, virtualReality.getId());
                pst.setString(3, virtualReality.getName());
                pst.setDouble(4, virtualReality.getPrice());
                pst.setString(5, virtualReality.getImage());
                pst.setString(6, virtualReality.getRetailer());
                pst.setString(7, virtualReality.getCondition());
                pst.setDouble(8, virtualReality.getDiscount());

                pst.executeUpdate();


            }

            for (Map.Entry<String, SmartSpeaker> entry : SaxParserDataStore.smartspeakersMap.entrySet()) {
                String name = "VoiceAssistant";
                SmartSpeaker voiceAssistant = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQuery);
                pst.setString(1, name);
                pst.setString(2, voiceAssistant.getId());
                pst.setString(3, voiceAssistant.getName());
                pst.setDouble(4, voiceAssistant.getPrice());
                pst.setString(5, voiceAssistant.getImage());
                pst.setString(6, voiceAssistant.getRetailer());
                pst.setString(7, voiceAssistant.getCondition());
                pst.setDouble(8, voiceAssistant.getDiscount());

                pst.executeUpdate();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Phones> getAccessorys() {
        HashMap<String, Phones> hm = new HashMap<String, Phones>();
        try {
            getConnection();

            String selectConsole = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectConsole);
            pst.setString(1, "Phone");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Phones phone = new Phones(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), phone);
                phone.setId(rs.getString("Id"));
                System.out.println(rs.getString("Id"));
                try {
                    String selectaccessory = "Select * from Product_accessories where productName=?";
                    PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
                    pstacc.setString(1, rs.getString("Id"));
                    ResultSet rsacc = pstacc.executeQuery();
                    //System.out.print("assccececeec" + rsacc.getString("accessoriesName"));
                    HashMap<String, String> acchashmap = new HashMap<String, String>();
                    while (rsacc.next()) {
                        if (rsacc.getString("accessoriesName") != null) {
                            System.out.print("acc");
                            acchashmap.put(rsacc.getString("accessoriesName"), rsacc.getString("accessoriesName"));
                            phone.setAccessories(acchashmap);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        }
        return hm;
    }


    public static HashMap<String, FitnessWatches> getFitnessWatches() {
        HashMap<String, FitnessWatches> hm = new HashMap<String, FitnessWatches>();
        try {
            getConnection();

            String selectFitnessWatch = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectFitnessWatch);
            pst.setString(1, "FitnessWatches");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                FitnessWatches fitnessWatch = new FitnessWatches(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), fitnessWatch);
                fitnessWatch.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, HeadPhones> getHeadphones() {
        HashMap<String, HeadPhones> hm = new HashMap<String, HeadPhones>();
        try {
            getConnection();

            String selectHeadphone = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectHeadphone);
            pst.setString(1, "Headphones");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	HeadPhones headphone = new HeadPhones(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), headphone);
                headphone.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, Laptops> getLaptops() {
        HashMap<String, Laptops> hm = new HashMap<String, Laptops>();
        try {
            getConnection();

            String selectLaptop = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectLaptop);
            pst.setString(1, "Laptops");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Laptops laptop = new Laptops(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), laptop);
                laptop.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, PetTracker> getPetTrackers() {
        HashMap<String, PetTracker> hm = new HashMap<String, PetTracker>();
        try {
            getConnection();

            String selectPetTracker = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectPetTracker);
            pst.setString(1, "PetTrackers");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                PetTracker petTracker = new PetTracker(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), petTracker);
                petTracker.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, SmartWatches> getSmartWatches() {
        HashMap<String, SmartWatches> hm = new HashMap<String, SmartWatches>();
        try {
            getConnection();

            String selectSmartWatch = "select * from Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectSmartWatch);
            pst.setString(1, "SmartWatches");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	SmartWatches smartWatch = new SmartWatches(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), smartWatch);
                smartWatch.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, VirtualReality> getVirtualRealitys() {
        HashMap<String, VirtualReality> hm = new HashMap<String, VirtualReality>();
        try {
            getConnection();

            String selectVirtualReality = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectVirtualReality);
            pst.setString(1, "VirtualRealitys");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                VirtualReality virtualReality = new VirtualReality(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), virtualReality);
                virtualReality.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, SmartSpeaker> getVoiceAssistants() {
        HashMap<String, SmartSpeaker> hm = new HashMap<String, SmartSpeaker>();
        try {
            getConnection();

            String selectVoiceAssistant = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectVoiceAssistant);
            pst.setString(1, "VoiceAssistants");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	SmartSpeaker voiceAssistant = new SmartSpeaker(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), voiceAssistant);
                voiceAssistant.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }


    public static HashMap<String, Accessory> getAccessories() {
        HashMap<String, Accessory> hm = new HashMap<String, Accessory>();
        try {
            getConnection();

            String selectAcc = "select * from Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            pst.setString(1, "accessories");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Accessory acc = new Accessory(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), acc);
                acc.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static String addproducts(String producttype, String productId, String productName, double productPrice, String productImage, String productManufacturer, String productCondition, double productDiscount, String prod) {
        String msg = "Product is added successfully";
        try {

            getConnection();
            String addProductQurey = "INSERT INTO Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
                    "VALUES (?,?,?,?,?,?,?,?);";

            String name = producttype;

            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1, name);
            pst.setString(2, productId);
            pst.setString(3, productName);
            pst.setDouble(4, productPrice);
            pst.setString(5, productImage);
            pst.setString(6, productManufacturer);
            pst.setString(7, productCondition);
            pst.setDouble(8, productDiscount);

            pst.executeUpdate();
            try {
                if (!prod.isEmpty()) {
                    String addaprodacc = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
                            "VALUES (?,?);";
                    PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
                    pst1.setString(1, prod);
                    pst1.setString(2, productId);
                    pst1.executeUpdate();

                }
            } catch (Exception e) {
                msg = "Error while adding the product";
                e.printStackTrace();

            }


        } catch (Exception e) {
            msg = "Error while adding the product";
            e.printStackTrace();

        }
        return msg;
    }

    public static String updateproducts(String producttype, String productId, String productName, double productPrice, String productImage, String productManufacturer, String productCondition, double productDiscount) {
        String msg = "Product is updated successfully";
        try {

            getConnection();
            String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;";


            PreparedStatement pst = conn.prepareStatement(updateProductQurey);

            pst.setString(1, productName);
            pst.setDouble(2, productPrice);
            pst.setString(3, productImage);
            pst.setString(4, productManufacturer);
            pst.setString(5, productCondition);
            pst.setDouble(6, productDiscount);
            pst.setString(7, productId);
            pst.executeUpdate();


        } catch (Exception e) {
            msg = "Product cannot be updated";
            e.printStackTrace();

        }
        return msg;
    }

    public static String deleteproducts(String productId) {
        String msg = "Product is deleted successfully";
        try {

            getConnection();
            String deleteproductsQuery = "Delete from Productdetails where Id=?";
            PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
            pst.setString(1, productId);

            pst.executeUpdate();
        } catch (Exception e) {
            msg = "Product cannot be deleted";
        }
        return msg;
    }

    public static HashMap<String, Product> selectInventory() {
        HashMap<String, Product> hm = new HashMap<String, Product>();
        try {
            getConnection();

            String selectAcc = "select * from Productdetails";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Integer.parseInt(rs.getString("inventory")));
                hm.put(rs.getString("Id"), product);
                product.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, Product> selectOnSale() {
        HashMap<String, Product> hm = new HashMap<String, Product>();
        try {
            getConnection();

            String selectAcc = "select * from Productdetails where productCondition = ?";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            pst.setString(1, "1");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Integer.parseInt(rs.getString("inventory")));
                hm.put(rs.getString("Id"), product);
                product.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, Product> selectRebate() {
        HashMap<String, Product> hm = new HashMap<String, Product>();
        try {
            getConnection();

            String selectAcc = "select * from Productdetails where productDiscount > 0";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Double.parseDouble(rs.getString("productDiscount")));
                hm.put(rs.getString("Id"), product);
                product.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }


    public static HashMap<String, OrderPayment> selectSaleAmount() {
        HashMap<String, OrderPayment> hm = new HashMap<String, OrderPayment>();
        try {
            getConnection();

            String selectAcc = "select DISTINCT(temp.orderName),temp.saleAmount,orders.orderPrice from orders, (select orderName, count(orderName) as saleAmount from orders group by orderName) as temp where orders.orderName = temp.orderName";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            ResultSet rs = pst.executeQuery();

            int i = 0;
            while (rs.next()) {
                OrderPayment orderPayment = new OrderPayment(rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getInt("saleAmount"));
                i++;
                hm.put(String.valueOf(i), orderPayment);
                //orderPayment.setOrderId(Integer.parseInt(rs.getString("Id")));
            }
        } catch (Exception e) {
        }
        return hm;
    }


}	