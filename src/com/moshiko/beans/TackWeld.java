package com.moshiko.beans;

public class TackWeld {

	
	private float spotsLenght=0;
	private int weldSegmentsSpacing=306;
	
	public TackWeld() {
	}

	public TackWeld(float spotsLenght, int weldSegmentsSpacing) {
		this.spotsLenght = spotsLenght;
		this.weldSegmentsSpacing = weldSegmentsSpacing;
	}

	public float getSpotsLenght() {
		return spotsLenght;
	}

	public void setSpotsLenght(float minSpotsLenght) {
		this.spotsLenght = minSpotsLenght;
	}

	public int getWeldSegmentsSapcing() {
		return weldSegmentsSpacing;
	}

	public void setWeldSegmentsSapcing(int maxWeldSegmentsSapcing) {
		this.weldSegmentsSpacing = maxWeldSegmentsSapcing;
	}

	@Override
	public String toString() {
		return "TackWeld [minSpotsLenght=" + spotsLenght + ", maxWeldSegmentsSapcing=" + weldSegmentsSpacing
				+ "]";
	}
	
	
	
	

}
