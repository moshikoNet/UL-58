package com.moshiko.beans;

import com.moshiko.enums.TypeOfHead;
import com.moshiko.enums.TypeOfBulkhead;

//clause 13 Compartment Tanks

public class Type102BulkHead extends BulkHead {

	//for type 102 - required parameters
	private String size;
	
	
	public Type102BulkHead(){
		super();
	}
	
	public Type102BulkHead(TypeOfBulkhead typeOfBulkhead, float thicknessOfBulkhead, float toleranceOfBulkheadThickness,
			int numberOfBulkheadPieces, Figure8_1WeldingDetails figure8_1WeldingDetail, Bracing bulkheaBracing,
			TypeOfHead bulkheadshape) {
		super(typeOfBulkhead, thicknessOfBulkhead, toleranceOfBulkheadThickness, numberOfBulkheadPieces, figure8_1WeldingDetail,
				bulkheaBracing, bulkheadshape);
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	@Override
	public String toString() {
		return "Type102BulkHead [size=" + size + ", getTypeOfBulkhead()=" + getTypeOfBulkhead()
				+ ", getThicknessOfBulkhead()=" + getThicknessOfBulkhead() + ", getToleranceOfBulkheadThickness()="
				+ getToleranceOfBulkheadThickness() + ", getNumberOfBulkheadPieces()=" + getNumberOfBulkheadPieces()
				+ ", getFigure8_1WeldingDetail()=" + getFigure8_1WeldingDetail() + ", getBulkheaBracing()="
				+ getBulkheaBracing() + ", getBulkheadshape()=" + getBulkheadshape() + "]";
	}


	
	


	
	
	
	


}
