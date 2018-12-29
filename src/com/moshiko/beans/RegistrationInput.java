package com.moshiko.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class RegistrationInput {
	
	private long userID;
	private String userTypeSTR;
	private String scope;
	private String hash;

	//default constructor

	public RegistrationInput() {
	}
	
	
	// full constructor

	public RegistrationInput(long userID, String userTypeSTR, String scope, String hash) {
	
		this.userID = userID;
		this.userTypeSTR = userTypeSTR;
		this.scope = scope;
		this.hash = hash;
	}



	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserTypeSTR() {
		return userTypeSTR;
	}

	public void setUserTypeSTR(String userTypeSTR) {
		this.userTypeSTR = userTypeSTR;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "RegistrationInput [userID=" + userID + ", userTypeSTR=" + userTypeSTR + ", scope=" + scope + ", hash="
				+ hash + "]";
	}
	
	
	
}
