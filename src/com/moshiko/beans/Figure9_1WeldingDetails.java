package com.moshiko.beans;

import com.moshiko.enums.Figure9_1TypeOfWeld;

public class Figure9_1WeldingDetails extends WeldingDetails  {

	private Figure9_1TypeOfWeld figure9_1TypeOfWeld;
	
	private float headFlangeDepth;//all except num 10,11,13,14
	private float weldTip;//num 19 & num 20
	
	
	public Figure9_1WeldingDetails(Boolean continuousWeld, Boolean continuousFilletWeld, float overlapB,
			TackWeld tackWeld, Figure9_1TypeOfWeld figure9_1TypeOfWeld, float headFlange, float weldTip) {
		
		super(continuousWeld, continuousFilletWeld, overlapB, tackWeld);
		this.figure9_1TypeOfWeld = figure9_1TypeOfWeld;
		this.headFlangeDepth = headFlange;
		this.weldTip = weldTip;
	}
	public Figure9_1TypeOfWeld getFigure9_1TypeOfWeld() {
		return figure9_1TypeOfWeld;
	}
	public void setFigure9_1TypeOfWeld(Figure9_1TypeOfWeld figure9_1TypeOfWeld) {
		this.figure9_1TypeOfWeld = figure9_1TypeOfWeld;
	}
	public float getHeadFlange() {
		return headFlangeDepth;
	}
	public void setHeadFlange(float headFlange) {
		this.headFlangeDepth = headFlange;
	}
	public float getWeldTip() {
		return weldTip;
	}
	public void setWeldTip(float weldTip) {
		this.weldTip = weldTip;
	}
	@Override
	public String toString() {
		return "Figure9_1WeldingDetails [headFlange=" + headFlangeDepth + ", weldTip=" + weldTip + ", getContinuousWeld()="
				+ getContinuousWeld() + ", getContinuousFilletWeld()=" + getContinuousFilletWeld() + ", getOverlapB()="
				+ getOverlapB() + ", getTackWeld()=" + getTackWeld() + "]";
	}
	
	

}
