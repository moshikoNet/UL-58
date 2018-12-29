package com.moshiko.beans;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class LoginInput {

	
	private String userName;
	private String userTypeSTR;
	private String password;
	private String email;

	
	//default constructor
		public LoginInput() {
	  	}
		
		
		// full constructor

	public LoginInput(String userName, String userTypeSTR, String password,String email) {
		this.userName = userName;
		this.userTypeSTR = userTypeSTR;
		this.password = password;
		this.email = email;

	}

	//getters and setters

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserTypeSTR() {
		return userTypeSTR;
	}


	public void setUserTypeSTR(String userTypeSTR) {
		this.userTypeSTR = userTypeSTR;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "LoginInput [userName=" + userName + ", userTypeSTR=" + userTypeSTR + ", password=" + password
				+ ", email=" + email + "]";
	}
		

	
	
}
