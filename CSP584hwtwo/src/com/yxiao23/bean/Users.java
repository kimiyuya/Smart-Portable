package com.yxiao23.bean;

import java.io.Serializable;

public class Users implements Serializable{
	private String userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userPswd;
	private String userType;//customer,salesman,manager
	private String userEnrollTime;//user's enroll time
	
	

	public Users(String userName, String userPswd, String userType) {
		super();
		this.userName = userName;
		this.userPswd = userPswd;
		this.userType = userType;
	}

	public Users(String userId, String userName, String userEmail, String userPhone, String userPswd, String userType,
			String userEnrollTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userPswd = userPswd;
		this.userType = userType;
		this.userEnrollTime = userEnrollTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		userName = userName;
	}

	public String getUserAdd() {
		return userEmail;
	}

	public void setUserAdd(String userEmail) {
		userEmail = userEmail;
	}


	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		userPhone = userPhone;
	}

	public String getUserPswd() {
		return userPswd;
	}

	public void setUserPswd(String userPswd) {
		userPswd = userPswd;
	}

	public String getUserEnrollTime() {
		return userEnrollTime;
	}

	public void setUserEnrollTime(String userEnrollTime) {
		this.userEnrollTime = userEnrollTime;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
