package com.yxiao23.bean;

import java.util.HashMap;

public class VirtualReality {
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	HashMap<String,String> accessories;
	
	public VirtualReality(String id, String name, double price, String image, String retailer, String condition,
			double discount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.retailer = retailer;
		this.condition = condition;
		this.discount = discount;
		this.accessories = new HashMap<String,String>();
	}
	
	 public VirtualReality(String name, double price, String image, String retailer, String condition, double discount) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	        this.image = image;
	        this.condition = condition;
	        this.discount = discount;
	        this.retailer = retailer;
	    }

	
	public VirtualReality() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	HashMap<String, String> getAccessories() {
		return accessories;
	}

	public void setAccessories(HashMap<String, String> accessories) {
		this.accessories = accessories;
	}
	
	
	
}
