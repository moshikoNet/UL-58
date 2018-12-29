package com.moshiko.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.moshiko.enums.UserType;
import com.moshiko.utils.GlobalConstants;

@XmlRootElement
public class User {
	private long userID ;
	private String userName ;
	private String userPassword ;
	private String userEmail;
	private UserType userType;
	private String EMAIL_VERIFICATION_HASH;
	private int EMAIL_VERIFICATION_ATTEMPTS=0;
	private String STATUS=GlobalConstants.ACTIVATION;


	//default constructor
	public User() {
	}

	// constructor without the PK of UserID and Registration parameters other then hashActivation

	public User(String userName, String userPassword, String userEmail,UserType userType, String eMAIL_VERIFICATION_HASH) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userType=userType;
		EMAIL_VERIFICATION_HASH = eMAIL_VERIFICATION_HASH;
	}
	
	//full constructor
	
	public User(long userID, String userName, String userPassword, String userEmail,UserType userType, String eMAIL_VERIFICATION_HASH,
			int eMAIL_VERIFICATION_ATTEMPTS, String STATUS) {
		
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userType=userType;
		this.EMAIL_VERIFICATION_HASH = eMAIL_VERIFICATION_HASH;
		this.EMAIL_VERIFICATION_ATTEMPTS = eMAIL_VERIFICATION_ATTEMPTS;
		this.STATUS = STATUS;
	}


	//getters and setters

	public long getUserID() {
		return userID;
	}



	public void setUserID(long userID) {
		this.userID = userID;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getEMAIL_VERIFICATION_HASH() {
		return EMAIL_VERIFICATION_HASH;
	}

	public void setEMAIL_VERIFICATION_HASH(String eMAIL_VERIFICATION_HASH) {
		EMAIL_VERIFICATION_HASH = eMAIL_VERIFICATION_HASH;
	}

	public int getEMAIL_VERIFICATION_ATTEMPTS() {
		return EMAIL_VERIFICATION_ATTEMPTS;
	}

	public void setEMAIL_VERIFICATION_ATTEMPTS(int eMAIL_VERIFICATION_ATTEMPTS) {
		EMAIL_VERIFICATION_ATTEMPTS = eMAIL_VERIFICATION_ATTEMPTS;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	//overriding the toString() method

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userType=" + userType + ", EMAIL_VERIFICATION_HASH=" + EMAIL_VERIFICATION_HASH
				+ ", EMAIL_VERIFICATION_ATTEMPTS=" + EMAIL_VERIFICATION_ATTEMPTS + ", STATUS=" + STATUS + "]";
	}


}
