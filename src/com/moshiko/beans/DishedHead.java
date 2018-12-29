package com.moshiko.beans;

import com.moshiko.enums.TypeOfHead;
import com.moshiko.enums.Figure9_1TypeOfWeld;
import com.moshiko.enums.HeadSide;
import com.moshiko.enums.Figure8_1TypeOfWeld;

public class DishedHead extends Head{
	
	// required parameters

	private float dishedHeadDepth;	

	
	public DishedHead() {
	}


	public DishedHead(float dishedHeadDepth) {
		this.dishedHeadDepth = dishedHeadDepth;
	}




	public DishedHead(float headThickness, float headThicknessTolerance, TypeOfHead headShape, float knuckleRadius,
			int numberOfHeadPieces, HeadSide headSide, Figure8_1WeldingDetails headLongitudinalWeld,
			Figure9_1WeldingDetails headCircumferentialWeld, Bracing headBracing) {
		super(headThickness, headThicknessTolerance, headShape, knuckleRadius, numberOfHeadPieces, headSide,
				headLongitudinalWeld, headCircumferentialWeld, headBracing);
	}


	public float getDishedHeadDepth() {
		return dishedHeadDepth;
	}


	public void setDishedHeadDepth(float dishedHeadDepth) {
		this.dishedHeadDepth = dishedHeadDepth;
	}


	@Override
	public String toString() {
		return "DishedHead [dishedHeadDepth=" + dishedHeadDepth + ", getHeadThickness()=" + getHeadThickness()
				+ ", getHeadThicknessTolerance()=" + getHeadThicknessTolerance() + ", getHeadShape()=" + getHeadShape()
				+ ", getKnuckleRadius()=" + getKnuckleRadius() + ", getNumberOfHeadPieces()=" + getNumberOfHeadPieces()
				+ ", getHeadSide()=" + getHeadSide() + ", getHeadLongitudinalWeld()=" + getHeadLongitudinalWeld()
				+ ", getHeadCircumferentialWeld()=" + getHeadCircumferentialWeld() + ", getHeadBracing()="
				+ getHeadBracing() + "]";
	}







}
