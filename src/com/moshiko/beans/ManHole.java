package com.moshiko.beans;

import com.moshiko.enums.TypeOfGasket;
import com.moshiko.enums.TypeOfManHole;

public class ManHole {

	// required parameters
	private TypeOfManHole typeOfManHole;
	private float sizeOfManHole;
	private float nominalBoltCircleDimeter;
	private int numberOfBolts;
	private float boltDiameter;
	private float thicknessOfNeck;
	private float thicknessOfBlindFlange;
	private float heightOfFlangeFromTheTop;
	private Boolean manHoleContinuousWeld;
	private Boolean manHoleContinuousFilletWeld;
	private TypeOfGasket typeOfGasket;
	private float thicknessOfTheGasket;
	
	public ManHole(TypeOfManHole typeOfManHole, float sizeOfManHole, float nominalBoltCircleDimeter, int numberOfBolts,
			float boltDiameter, float thicknessOfNeck, float thicknessOfBlindFlange, float heightOfFlangeFromTheTop,
			Boolean manHoleContinuousWeld, Boolean manHoleContinuousFilletWeld, TypeOfGasket typeOfGasket,
			float thicknessOfTheGasket) {
		this.typeOfManHole = typeOfManHole;
		this.sizeOfManHole = sizeOfManHole;
		this.nominalBoltCircleDimeter = nominalBoltCircleDimeter;
		this.numberOfBolts = numberOfBolts;
		this.boltDiameter = boltDiameter;
		this.thicknessOfNeck = thicknessOfNeck;
		this.thicknessOfBlindFlange = thicknessOfBlindFlange;
		this.heightOfFlangeFromTheTop = heightOfFlangeFromTheTop;
		this.manHoleContinuousWeld = manHoleContinuousWeld;
		this.manHoleContinuousFilletWeld = manHoleContinuousFilletWeld;
		this.typeOfGasket = typeOfGasket;
		this.thicknessOfTheGasket = thicknessOfTheGasket;
	}
	
	public ManHole() {
	}

	public TypeOfManHole getTypeOfManHole() {
		return typeOfManHole;
	}

	public void setTypeOfManHole(TypeOfManHole typeOfManHole) {
		this.typeOfManHole = typeOfManHole;
	}

	public float getSizeOfManHole() {
		return sizeOfManHole;
	}

	public void setSizeOfManHole(float sizeOfManHole) {
		this.sizeOfManHole = sizeOfManHole;
	}

	public float getNominalBoltCircleDimeter() {
		return nominalBoltCircleDimeter;
	}

	public void setNominalBoltCircleDimeter(float nominalBoltCircleDimeter) {
		this.nominalBoltCircleDimeter = nominalBoltCircleDimeter;
	}

	public int getNumberOfBolts() {
		return numberOfBolts;
	}

	public void setNumberOfBolts(int numberOfBolts) {
		this.numberOfBolts = numberOfBolts;
	}

	public float getBoltDiameter() {
		return boltDiameter;
	}

	public void setBoltDiameter(float boltDiameter) {
		this.boltDiameter = boltDiameter;
	}

	public float getThicknessOfNeck() {
		return thicknessOfNeck;
	}

	public void setThicknessOfNeck(float thicknessOfNeck) {
		this.thicknessOfNeck = thicknessOfNeck;
	}

	public float getThicknessOfBlindFlange() {
		return thicknessOfBlindFlange;
	}

	public void setThicknessOfBlindFlange(float thicknessOfBlindFlange) {
		this.thicknessOfBlindFlange = thicknessOfBlindFlange;
	}

	public float getHeightOfFlangeFromTheTop() {
		return heightOfFlangeFromTheTop;
	}

	public void setHeightOfFlangeFromTheTop(float heightOfFlangeFromTheTop) {
		this.heightOfFlangeFromTheTop = heightOfFlangeFromTheTop;
	}

	public Boolean getManHoleContinuousWeld() {
		return manHoleContinuousWeld;
	}

	public void setManHoleContinuousWeld(Boolean manHoleContinuousWeld) {
		this.manHoleContinuousWeld = manHoleContinuousWeld;
	}

	public Boolean getManHoleContinuousFilletWeld() {
		return manHoleContinuousFilletWeld;
	}

	public void setManHoleContinuousFilletWeld(Boolean manHoleContinuousFilletWeld) {
		this.manHoleContinuousFilletWeld = manHoleContinuousFilletWeld;
	}

	public TypeOfGasket getTypeOfGasket() {
		return typeOfGasket;
	}

	public void setTypeOfGasket(TypeOfGasket typeOfGasket) {
		this.typeOfGasket = typeOfGasket;
	}

	public float getThicknessOfTheGasket() {
		return thicknessOfTheGasket;
	}

	public void setThicknessOfTheGasket(float thicknessOfTheGasket) {
		this.thicknessOfTheGasket = thicknessOfTheGasket;
	}

	@Override
	public String toString() {
		return "ManHole [typeOfManHole=" + typeOfManHole + ", sizeOfManHole=" + sizeOfManHole
				+ ", nominalBoltCircleDimeter=" + nominalBoltCircleDimeter + ", numberOfBolts=" + numberOfBolts
				+ ", boltDiameter=" + boltDiameter + ", thicknessOfNeck=" + thicknessOfNeck
				+ ", thicknessOfBlindFlange=" + thicknessOfBlindFlange + ", heightOfFlangeFromTheTop="
				+ heightOfFlangeFromTheTop + ", manHoleContinuousWeld=" + manHoleContinuousWeld
				+ ", manHoleContinuousFilletWeld=" + manHoleContinuousFilletWeld + ", typeOfGasket=" + typeOfGasket
				+ ", thicknessOfTheGasket=" + thicknessOfTheGasket + "]";
	}
	
	

}
