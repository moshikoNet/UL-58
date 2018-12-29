package com.moshiko.beans;

import com.moshiko.enums.TypeOfHead;
import com.moshiko.enums.TypeOfBulkhead;

//clause 13 Compartment Tanks

public class Type101BulkHead extends BulkHead {

	//for type 101 - required parameters
	private float overLapBBulkheadAndShell;
	private float disatnceCBetweenTwoShells;
	private float lenghtDOfStrightFlange;
	
	
	public Type101BulkHead(){
		super();
	}
	
	public Type101BulkHead(Type101BulkHead type101BulkHeadInputData){
		
		super(type101BulkHeadInputData.getTypeOfBulkhead(),type101BulkHeadInputData.getThicknessOfBulkhead(),
				type101BulkHeadInputData.getToleranceOfBulkheadThickness(),type101BulkHeadInputData.getNumberOfBulkheadPieces(),
				type101BulkHeadInputData.getFigure8_1WeldingDetail(),type101BulkHeadInputData.getBulkheaBracing(),
				type101BulkHeadInputData.getBulkheadshape());
		
		this.overLapBBulkheadAndShell = type101BulkHeadInputData.getOverLapBBulkheadAndShell();
		this.disatnceCBetweenTwoShells = type101BulkHeadInputData.getDisatnceCBetweenTwoShells();
		this.lenghtDOfStrightFlange = type101BulkHeadInputData.getLenghtDOfStrightFlange();
		
		
	}
	
	public Type101BulkHead(TypeOfBulkhead typeOfBulkhead, float thicknessOfBulkhead, float toleranceOfBulkheadThickness,
			int numberOfBulkheadPieces, Figure8_1WeldingDetails figure8_1WeldingDetail, Bracing bulkheaBracing,
			TypeOfHead bulkheadshape, float overLapBBulkheadAndShell, float disatnceCBetweenTwoShells,
			float lenghtDOfStrightFlange) {
		
		super(typeOfBulkhead, thicknessOfBulkhead, toleranceOfBulkheadThickness, numberOfBulkheadPieces,
				figure8_1WeldingDetail, bulkheaBracing, bulkheadshape);
		
		this.overLapBBulkheadAndShell = overLapBBulkheadAndShell;
		this.disatnceCBetweenTwoShells = disatnceCBetweenTwoShells;
		this.lenghtDOfStrightFlange = lenghtDOfStrightFlange;
	}


	public float getOverLapBBulkheadAndShell() {
		return overLapBBulkheadAndShell;
	}


	public void setOverLapBBulkheadAndShell(float overLapBBulkheadAndShell) {
		this.overLapBBulkheadAndShell = overLapBBulkheadAndShell;
	}


	public float getDisatnceCBetweenTwoShells() {
		return disatnceCBetweenTwoShells;
	}


	public void setDisatnceCBetweenTwoShells(float disatnceCBetweenTwoShells) {
		this.disatnceCBetweenTwoShells = disatnceCBetweenTwoShells;
	}


	public float getLenghtDOfStrightFlange() {
		return lenghtDOfStrightFlange;
	}


	public void setLenghtDOfStrightFlange(float lenghtDOfStrightFlange) {
		this.lenghtDOfStrightFlange = lenghtDOfStrightFlange;
	}

	

}
