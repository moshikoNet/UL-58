package com.moshiko.beans;

import com.moshiko.enums.TypeOfHead;
import com.moshiko.enums.Figure9_1TypeOfWeld;
import com.moshiko.enums.HeadSide;
import com.moshiko.enums.Figure8_1TypeOfWeld;

public class Head {
	
	// required parameters
	private float headThickness;
	private float headThicknessTolerance;
	private TypeOfHead headShape;
	private float knuckleRadius;
	private int numberOfHeadPieces;
	private HeadSide headSide;
	private Figure8_1WeldingDetails headLongitudinalWeld;
	private Figure9_1WeldingDetails headCircumferentialWeld;
	private Bracing headBracing;

	
	public Head() {
	}


	public Head(float headThickness, float headThicknessTolerance, TypeOfHead headShape, float knuckleRadius,
			int numberOfHeadPieces, HeadSide headSide, Figure8_1WeldingDetails headLongitudinalWeld,
			Figure9_1WeldingDetails headCircumferentialWeld, Bracing headBracing) {
		this.headThickness = headThickness;
		this.headThicknessTolerance = headThicknessTolerance;
		this.headShape = headShape;
		this.knuckleRadius = knuckleRadius;
		this.numberOfHeadPieces = numberOfHeadPieces;
		this.headSide = headSide;
		this.headLongitudinalWeld = headLongitudinalWeld;
		this.headCircumferentialWeld = headCircumferentialWeld;
		this.headBracing = headBracing;
	}


	public float getHeadThickness() {
		return headThickness;
	}


	public void setHeadThickness(float headThickness) {
		this.headThickness = headThickness;
	}


	public float getHeadThicknessTolerance() {
		return headThicknessTolerance;
	}


	public void setHeadThicknessTolerance(float headThicknessTolerance) {
		this.headThicknessTolerance = headThicknessTolerance;
	}


	public TypeOfHead getHeadShape() {
		return headShape;
	}


	public void setHeadShape(TypeOfHead headShape) {
		this.headShape = headShape;
	}


	public float getKnuckleRadius() {
		return knuckleRadius;
	}


	public void setKnuckleRadius(float knuckleRadius) {
		this.knuckleRadius = knuckleRadius;
	}


	public int getNumberOfHeadPieces() {
		return numberOfHeadPieces;
	}


	public void setNumberOfHeadPieces(int numberOfHeadPieces) {
		this.numberOfHeadPieces = numberOfHeadPieces;
	}


	public HeadSide getHeadSide() {
		return headSide;
	}


	public void setHeadSide(HeadSide headSide) {
		this.headSide = headSide;
	}


	public Figure8_1WeldingDetails getHeadLongitudinalWeld() {
		return headLongitudinalWeld;
	}


	public void setHeadLongitudinalWeld(Figure8_1WeldingDetails headLongitudinalWeld) {
		this.headLongitudinalWeld = headLongitudinalWeld;
	}


	public Figure9_1WeldingDetails getHeadCircumferentialWeld() {
		return headCircumferentialWeld;
	}


	public void setHeadCircumferentialWeld(Figure9_1WeldingDetails headCircumferentialWeld) {
		this.headCircumferentialWeld = headCircumferentialWeld;
	}


	public Bracing getHeadBracing() {
		return headBracing;
	}


	public void setHeadBracing(Bracing headBracing) {
		this.headBracing = headBracing;
	}


	@Override
	public String toString() {
		return "Head [headThickness=" + headThickness + ", headThicknessTolerance=" + headThicknessTolerance
				+ ", headShape=" + headShape + ", knuckleRadius=" + knuckleRadius + ", numberOfHeadPieces="
				+ numberOfHeadPieces + ", headSide=" + headSide + ", headLongitudinalWeld=" + headLongitudinalWeld
				+ ", headCircumferentialWeld=" + headCircumferentialWeld + ", headBracing=" + headBracing + "]";
	}





}
