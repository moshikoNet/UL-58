package com.moshiko.beans;

import com.moshiko.enums.TypeOfLayer;


public class TankLayer {
	
	// required parameters
	private float layerInsideDiameter=0;
	private float layerInsideDiameterTolerance=0;
	private float layerLength=0;
	private float layerLengthTolerance=0;

	private Shell layerShell;
	private Head LeftHead;
	private Head RightHead;

	private TypeOfLayer typeOfLayer;



	public TankLayer(float layerInsideDiameter, float layerInsideDiameterTolerance, float layerLenght,
			float layerLenghtTolerance, Shell layerShell, Head leftHead, Head rightHead,
			TypeOfLayer typeOfLayer) {
		this.layerInsideDiameter = layerInsideDiameter;
		this.layerInsideDiameterTolerance = layerInsideDiameterTolerance;
		this.layerLength = layerLenght;
		this.layerLengthTolerance = layerLenghtTolerance;
		this.layerShell = layerShell;
		this.LeftHead = leftHead;
		this.RightHead = rightHead;
		this.typeOfLayer = typeOfLayer;
	}

	public TankLayer() {

	}

	public float getLayerInsideDiameter() {
		return layerInsideDiameter;
	}

	public void setLayerInsideDiameter(float layerDiameter) {
		this.layerInsideDiameter = layerDiameter;
	}

	public float getLayerInsideDiameterTolerance() {
		return layerInsideDiameterTolerance;
	}

	public void setLayerInsideDiameterTolerance(float layerDiameterTolerance) {
		this.layerInsideDiameterTolerance = layerDiameterTolerance;
	}

	public float getLayerLength() {
		return layerLength;
	}

	public void setLayerLength(float layerLenght) {
		this.layerLength = layerLenght;
	}

	public float getLayerLengthTolerance() {
		return layerLengthTolerance;
	}

	public void setLayerLengthTolerance(float layerLenghtTolerance) {
		this.layerLengthTolerance = layerLenghtTolerance;
	}

	public Shell getLayerShell() {
		return layerShell;
	}

	public void setLayerShell(Shell layerShell) {
		this.layerShell = layerShell;
	}



	public Head getLayerLeftHead() {
		return LeftHead;
	}

	public void setLayerLeftHead(Head layerLeftHead) {
		this.LeftHead = layerLeftHead;
	}

	public Head getLayerRightHead() {
		return RightHead;
	}

	public void setLayerRightHead(Head layerRightHead) {
		this.RightHead = layerRightHead;
	}

	public TypeOfLayer getTypeOfLayer() {
		return typeOfLayer;
	}

	public void setTypeOfLayer(TypeOfLayer typeOfLayer) {
		this.typeOfLayer = typeOfLayer;
	}

	@Override
	public String toString() {
		return "TankLayer [layerInsideDiameter=" + layerInsideDiameter + ", layerInsideDiameterTolerance="
				+ layerInsideDiameterTolerance + ", layerLenght=" + layerLength + ", layerLenghtTolerance="
				+ layerLengthTolerance + ", layerShell=" + layerShell + ", layerLeftHead=" + LeftHead
				+ ", layerRightHead=" + RightHead + ", typeOfLayer=" + typeOfLayer + "]";
	}



	
	

}
