package com.yxiao23.servlet;

import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating
	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable {
    private String productName;
    private String userName;
    private String productType;
    private String productMaker;
    private String reviewRating;
    private String reviewDate;
    private String reviewText;
    private String retailerpin;
    private String price;
    private String retailercity;

    private String userAge;
    private String userGender;
    private String userOccupation;

    public String getRetailerpin() {
        return retailerpin;
    }

    public void setRetailerpin(String retailerpin) {
        this.retailerpin = retailerpin;
    }

    public String getRetailercity() {
        return retailercity;
    }

    public void setRetailercity(String retailercity) {
        this.retailercity = retailercity;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public Review(String productName, String userName, String productType, String productMaker,
                  String reviewRating, String reviewDate, String reviewText, String retailerpin,
                  String price, String retailercity, String userAge, String userGender, String userOccupation) {
        this.productName = productName;
        this.userName = userName;
        this.productType = productType;
        this.productMaker = productMaker;
        this.reviewRating = reviewRating;
        this.reviewDate = reviewDate;
        this.reviewText = reviewText;
        this.retailerpin = retailerpin;
        this.price = price;
        this.retailercity = retailercity;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userOccupation = userOccupation;
    }

    public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
        this.productName = productName;
        this.retailerpin = retailerpin;
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
    }

    public String getProductName() {
        return productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductMaker() {
        return productMaker;
    }

    public void setProductMaker(String productMaker) {
        this.productMaker = productMaker;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getRetailerPin() {
        return retailerpin;
    }

    public void setRetailerPin(String retailerpin) {
        this.retailerpin = retailerpin;
    }

    public String getRetailerCity() {
        return retailercity;
    }

    public void setRetailerCity(String retailercity) {
        this.retailercity = retailercity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}