package com.moshiko.beans;

public class WeldingWithBackUpBar {

	private float lenghtOfBackUpBar=0;
	private float weldSeparation=0;
	private float backUpBarThickness=0;
	
	public WeldingWithBackUpBar(float lenghtOfBackUpBar, float weldSeparation, float backUpBarThickness) {
		this.lenghtOfBackUpBar = lenghtOfBackUpBar;
		this.weldSeparation = weldSeparation;
		this.backUpBarThickness = backUpBarThickness;
	}

	public WeldingWithBackUpBar() {
	}

	public float getLenghtOfBackUpBar() {
		return lenghtOfBackUpBar;
	}

	public void setLenghtOfBackUpBar(float lenghtOfBackUpBar) {
		this.lenghtOfBackUpBar = lenghtOfBackUpBar;
	}

	public float getWeldSeparation() {
		return weldSeparation;
	}

	public void setWeldSeparation(float weldSeparation) {
		this.weldSeparation = weldSeparation;
	}

	public float getBackUpBarThickness() {
		return backUpBarThickness;
	}

	public void setBackUpBarThickness(float backUpBarThickness) {
		this.backUpBarThickness = backUpBarThickness;
	}

	@Override
	public String toString() {
		return "WeldingWithBackUpBar [lenghtOfBackUpBar=" + lenghtOfBackUpBar + ", weldSeparation=" + weldSeparation
				+ ", backUpBarThickness=" + backUpBarThickness + "]";
	}

	

}
