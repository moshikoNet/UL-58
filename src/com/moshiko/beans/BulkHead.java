package com.moshiko.beans;

import com.moshiko.enums.TypeOfHead;
import com.moshiko.enums.TypeOfBulkhead;

//clause 13 Compartment Tanks

public class BulkHead {

	// required parameters
	private TypeOfBulkhead typeOfBulkhead;
	private float thicknessOfBulkhead;
	private float toleranceOfBulkheadThickness;
	private int numberOfBulkheadPieces;
	private Figure8_1WeldingDetails figure8_1WeldingDetail;
	
	private Bracing bulkheaBracing;
	
	private TypeOfHead Bulkheadshape;

	public BulkHead(){
		
	}
	

	
	public BulkHead(TypeOfBulkhead typeOfBulkhead, float thicknessOfBulkhead, float toleranceOfBulkheadThickness,
			int numberOfBulkheadPieces, Figure8_1WeldingDetails figure8_1WeldingDetail, Bracing bulkheaBracing,
			TypeOfHead bulkheadshape) {
		this.typeOfBulkhead = typeOfBulkhead;
		this.thicknessOfBulkhead = thicknessOfBulkhead;
		this.toleranceOfBulkheadThickness = toleranceOfBulkheadThickness;
		this.numberOfBulkheadPieces = numberOfBulkheadPieces;
		this.figure8_1WeldingDetail = figure8_1WeldingDetail;
		this.bulkheaBracing = bulkheaBracing;
		Bulkheadshape = bulkheadshape;
	}


	public TypeOfBulkhead getTypeOfBulkhead() {
		return typeOfBulkhead;
	}


	public void setTypeOfBulkhead(TypeOfBulkhead typeOfBulkhead) {
		this.typeOfBulkhead = typeOfBulkhead;
	}


	public float getThicknessOfBulkhead() {
		return thicknessOfBulkhead;
	}


	public void setThicknessOfBulkhead(float thicknessOfBulkhead) {
		this.thicknessOfBulkhead = thicknessOfBulkhead;
	}


	public float getToleranceOfBulkheadThickness() {
		return toleranceOfBulkheadThickness;
	}


	public void setToleranceOfBulkheadThickness(float toleranceOfBulkheadThickness) {
		this.toleranceOfBulkheadThickness = toleranceOfBulkheadThickness;
	}


	public int getNumberOfBulkheadPieces() {
		return numberOfBulkheadPieces;
	}


	public void setNumberOfBulkheadPieces(int numberOfBulkheadPieces) {
		this.numberOfBulkheadPieces = numberOfBulkheadPieces;
	}


	public Figure8_1WeldingDetails getFigure8_1WeldingDetail() {
		return figure8_1WeldingDetail;
	}


	public void setFigure8_1WeldingDetail(Figure8_1WeldingDetails figure8_1WeldingDetail) {
		this.figure8_1WeldingDetail = figure8_1WeldingDetail;
	}


	public Bracing getBulkheaBracing() {
		return bulkheaBracing;
	}


	public void setBulkheaBracing(Bracing bulkheaBracing) {
		this.bulkheaBracing = bulkheaBracing;
	}


	public TypeOfHead getBulkheadshape() {
		return Bulkheadshape;
	}


	public void setBulkheadshape(TypeOfHead bulkheadshape) {
		Bulkheadshape = bulkheadshape;
	}


	@Override
	public String toString() {
		return "BulkHead [typeOfBulkhead=" + typeOfBulkhead + ", thicknessOfBulkhead=" + thicknessOfBulkhead
				+ ", toleranceOfBulkheadThickness=" + toleranceOfBulkheadThickness + ", numberOfBulkheadPieces="
				+ numberOfBulkheadPieces + ", figure8_1WeldingDetail=" + figure8_1WeldingDetail + ", bulkheaBracing="
				+ bulkheaBracing + ", Bulkheadshape=" + Bulkheadshape + ", getTypeOfBulkhead()=" + getTypeOfBulkhead()
				+ ", getThicknessOfBulkhead()=" + getThicknessOfBulkhead() + ", getToleranceOfBulkheadThickness()="
				+ getToleranceOfBulkheadThickness() + ", getNumberOfBulkheadPieces()=" + getNumberOfBulkheadPieces()
				+ ", getFigure8_1WeldingDetail()=" + getFigure8_1WeldingDetail() + ", getBulkheaBracing()="
				+ getBulkheaBracing() + ", getBulkheadshape()=" + getBulkheadshape() + "]";
	}
	
	


}
