package com.yxiao23.bean;

import java.io.Serializable;

public class Orders implements Serializable{
	private String orderId;
	private String orderName;
	private String userName;//name of recipient
	private String userAddress;
	private String userCdCard;//user credit card number
	private String orderStatus;
	private double orderTotalPrice;
	private String orderCreateTime;
	
	public Orders(String orderId, String orderName, String userName, String userAddress, String userCdCard,
			String orderStatus, double orderTotalPrice, String orderCreateTime) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userCdCard = userCdCard;
		this.orderStatus = orderStatus;
		this.orderTotalPrice = orderTotalPrice;
		this.orderCreateTime = orderCreateTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public String getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserCdCard() {
		return userCdCard;
	}
	public void setUserCdCard(String userCdCard) {
		this.userCdCard = userCdCard;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
