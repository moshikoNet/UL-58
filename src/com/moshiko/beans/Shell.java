package com.moshiko.beans;


public class Shell {

	private float shellThickness;
	private float shellThicknessTolerance;
	
	private Figure8_1WeldingDetails shellLongitudinalWeld;
	private Figure8_1WeldingDetails shellCircumferentialWeld;
	
	public Shell() {
	}

	public Shell(float shellThickness, float shellThicknessTolerance,
			Figure8_1WeldingDetails typeOfShellLongitudinalWeld,
			Figure8_1WeldingDetails typeOfShellCircumferentialWeld) {
		this.shellThickness = shellThickness;
		this.shellThicknessTolerance = shellThicknessTolerance;
		this.shellLongitudinalWeld = typeOfShellLongitudinalWeld;
		this.shellCircumferentialWeld = typeOfShellCircumferentialWeld;
	}

	public float getShellThickness() {
		return shellThickness;
	}

	public void setShellThickness(float shellThickness) {
		this.shellThickness = shellThickness;
	}

	public float getShellThicknessTolerance() {
		return shellThicknessTolerance;
	}

	public void setShellThicknessTolerance(float shellThicknessTolerance) {
		this.shellThicknessTolerance = shellThicknessTolerance;
	}

	public Figure8_1WeldingDetails getTypeOfShellLongitudinalWeld() {
		return shellLongitudinalWeld;
	}

	public void setTypeOfShellLongitudinalWeld(Figure8_1WeldingDetails typeOfShellLongitudinalWeld) {
		this.shellLongitudinalWeld = typeOfShellLongitudinalWeld;
	}

	public Figure8_1WeldingDetails getTypeOfShellCircumferentialWeld() {
		return shellCircumferentialWeld;
	}

	public void setTypeOfShellCircumferentialWeld(Figure8_1WeldingDetails typeOfShellCircumferentialWeld) {
		this.shellCircumferentialWeld = typeOfShellCircumferentialWeld;
	}

	@Override
	public String toString() {
		return "LayerShell [shellThickness=" + shellThickness + ", shellThicknessTolerance=" + shellThicknessTolerance
				+ ", typeOfShellLongitudinalWeld=" + shellLongitudinalWeld + ", typeOfShellCircumferentialWeld="
				+ shellCircumferentialWeld + "]";
	}
	
	

}
