package com.oracat.model;

public class User {
	
	private Integer userId;
	private String userName;
	private String userPassword;
	private String loginmessage;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getLoginmessage() {
		return loginmessage;
	}

	public void setLoginmessage(String loginmessage) {
		this.loginmessage = loginmessage;
	}
}