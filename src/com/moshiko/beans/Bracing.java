package com.moshiko.beans;

import com.moshiko.enums.TypeOfBracing;

//BRACING OF HEAD (figure 13.2 & table 13.1)

public class Bracing {
	
	// required parameters
	private TypeOfBracing typeOfBracing;
	private float distanceFromShellX;
	private float distanceFromTheCenterlineOfTheHeadY;
	private TackWeld bracingTackWeld;
	private float sectionModulusZOfBrace;
	
	//only for channel beam - optional parameters
	private int UpnNumber;



	public Bracing() {
	}

	public Bracing(TypeOfBracing typeOfBracing, float distanceFromShellX, float distanceFromTheCenterlineOfTheHeadY,
			TackWeld bracingTackWeld, float sectionModulusZOfBrace, int upnNumber) {
		this.typeOfBracing = typeOfBracing;
		this.distanceFromShellX = distanceFromShellX;
		this.distanceFromTheCenterlineOfTheHeadY = distanceFromTheCenterlineOfTheHeadY;
		this.bracingTackWeld = bracingTackWeld;
		this.sectionModulusZOfBrace = sectionModulusZOfBrace;
		UpnNumber = upnNumber;
	}

	public TypeOfBracing getTypeOfBracing() {
		return typeOfBracing;
	}

	public void setTypeOfBracing(TypeOfBracing typeOfBracing) {
		this.typeOfBracing = typeOfBracing;
	}

	public float getDistanceFromShellX() {
		return distanceFromShellX;
	}

	public void setDistanceFromShellX(float distanceFromShell) {
		this.distanceFromShellX = distanceFromShell;
	}

	public float getDistanceFromTheCenterLineOfTheHeadY() {
		return distanceFromTheCenterlineOfTheHeadY;
	}

	public void setDistanceFromTheCenterLineOfTheHeadY(float distanceFromTheCenterLineOfTheHead) {
		this.distanceFromTheCenterlineOfTheHeadY = distanceFromTheCenterLineOfTheHead;
	}

	public TackWeld getBracingTackWeld() {
		return bracingTackWeld;
	}

	public void setBracingTackWeld(TackWeld bracingTackWeld) {
		this.bracingTackWeld = bracingTackWeld;
	}

	public float getSectionModulusZOfBrace() {
		return sectionModulusZOfBrace;
	}

	public void setSectionModulusZOfBrace(float sectionMoudolusOfBrace) {
		this.sectionModulusZOfBrace = sectionMoudolusOfBrace;
	}

	public int getUpnNumber() {
		return UpnNumber;
	}

	public void setUpnNumber(int upnNumber) {
		UpnNumber = upnNumber;
	}

	@Override
	public String toString() {
		return "Bracing [typeOfBracing=" + typeOfBracing + ", distanceFromShell=" + distanceFromShellX
				+ ", distanceFromTheCenterLineOfTheHead=" + distanceFromTheCenterlineOfTheHeadY + ", bracingTackWeld="
				+ bracingTackWeld + ", sectionMoudolusOfBrace=" + sectionModulusZOfBrace + ", UpnNumber=" + UpnNumber
				+ "]";
	}



}
