package com.moshiko.beans;

import com.moshiko.enums.Figure8_1TypeOfWeld;

public class Figure8_1WeldingDetails extends WeldingDetails  {
	
	private Figure8_1TypeOfWeld figure8_1TypeOfWeld;
	
	private float overlapD;//num 2,5,7
	private WeldingWithBackUpBar weldingWithBackUpBar;//num 8
	private LockWeld lockWeld;//num 6
	private float overlapE;//num 6

	
	public Figure8_1WeldingDetails(Boolean continuousWeld, Boolean continuousFilletWeld, float overlapB,
			TackWeld tackWeld, Figure8_1TypeOfWeld figure8_1TypeOfWeld, float overlapD,
			WeldingWithBackUpBar weldingWithBackUpBar, LockWeld lockWeld, float overlapE) {
		super(continuousWeld, continuousFilletWeld, overlapB, tackWeld);
		this.figure8_1TypeOfWeld = figure8_1TypeOfWeld;
		this.overlapD = overlapD;
		this.weldingWithBackUpBar = weldingWithBackUpBar;
		this.lockWeld = lockWeld;
		this.overlapE = overlapE;
	}
	
	
	
	public Figure8_1TypeOfWeld getFigure8_1TypeOfWeld() {
		return figure8_1TypeOfWeld;
	}
	public void setFigure8_1TypeOfWeld(Figure8_1TypeOfWeld figure8_1TypeOfWeld) {
		this.figure8_1TypeOfWeld = figure8_1TypeOfWeld;
	}
	public float getOverlapD() {
		return overlapD;
	}
	public void setOverlapD(float overlapD) {
		this.overlapD = overlapD;
	}
	public WeldingWithBackUpBar getWeldingWithBackUpBar() {
		return weldingWithBackUpBar;
	}
	public void setWeldingWithBackUpBar(WeldingWithBackUpBar weldingWithBackUpBar) {
		this.weldingWithBackUpBar = weldingWithBackUpBar;
	}
	public LockWeld getLockWeld() {
		return lockWeld;
	}
	public void setLockWeld(LockWeld lockWeld) {
		this.lockWeld = lockWeld;
	}
	
	
	
	public float getOverlapE() {
		return overlapE;
	}



	public void setOverlapE(float overlapE) {
		this.overlapE = overlapE;
	}



	@Override
	public String toString() {
		return "Figure8_1WeldingDetails [figure8_1TypeOfWeld=" + figure8_1TypeOfWeld + ", overlapD=" + overlapD
				+ ", weldingWithBackUpBar=" + weldingWithBackUpBar + ", lockWeld=" + lockWeld + ", overlapE=" + overlapE
				+ ", getContinuousWeld()=" + getContinuousWeld() + ", getContinuousFilletWeld()="
				+ getContinuousFilletWeld() + ", getOverlapB()=" + getOverlapB() + ", getTackWeld()=" + getTackWeld()
				+ "]";
	}

	
	




}
