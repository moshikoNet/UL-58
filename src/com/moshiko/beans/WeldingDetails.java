package com.moshiko.beans;

public class WeldingDetails {

	
	private Boolean continuousWeld;
	private Boolean continuousFilletWeld;
	private float overlapB;//num 3,12,15,16,17,18,23,24
	private TackWeld tackWeld;//num 5,24
	
	public WeldingDetails(Boolean continuousWeld, Boolean continuousFilletWeld, float overlapB, TackWeld tackWeld) {
		this.continuousWeld = continuousWeld;
		this.continuousFilletWeld = continuousFilletWeld;
		this.overlapB = overlapB;
		this.tackWeld = tackWeld;
	}

	public WeldingDetails() {
	}

	public Boolean getContinuousWeld() {
		return continuousWeld;
	}

	public void setContinuousWeld(Boolean continuousWeld) {
		this.continuousWeld = continuousWeld;
	}

	public Boolean getContinuousFilletWeld() {
		return continuousFilletWeld;
	}

	public void setContinuousFilletWeld(Boolean continuousFilletWeld) {
		this.continuousFilletWeld = continuousFilletWeld;
	}

	public float getOverlapB() {
		return overlapB;
	}

	public void setOverlapB(float overlapB) {
		this.overlapB = overlapB;
	}

	public TackWeld getTackWeld() {
		return tackWeld;
	}

	public void setTackWeld(TackWeld tackWeld) {
		this.tackWeld = tackWeld;
	}

	@Override
	public String toString() {
		return "WeldingDetails [continuousWeld=" + continuousWeld + ", continuousFilletWeld=" + continuousFilletWeld
				+ ", overlapB=" + overlapB + ", tackWeld=" + tackWeld + "]";
	}

	
	
}
