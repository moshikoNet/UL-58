package com.moshiko.beans;

public class LockWeld {

	
	private float minSpotsLenght=(float) 12.0;
	private int maxWeldSegmentsSapcing= 400;

	
	
	public LockWeld() {
	}



	public LockWeld(float minSpotsLenght, int maxWeldSegmentsSapcing) {
		this.minSpotsLenght = minSpotsLenght;
		this.maxWeldSegmentsSapcing = maxWeldSegmentsSapcing;
	}



	public float getMinSpotsLenght() {
		return minSpotsLenght;
	}



	public void setMinSpotsLenght(float minSpotsLenght) {
		this.minSpotsLenght = minSpotsLenght;
	}



	public int getMaxWeldSegmentsSapcing() {
		return maxWeldSegmentsSapcing;
	}



	public void setMaxWeldSegmentsSapcing(int maxWeldSegmentsSapcing) {
		this.maxWeldSegmentsSapcing = maxWeldSegmentsSapcing;
	}



	@Override
	public String toString() {
		return "LockWeld [minSpotsLenght=" + minSpotsLenght + ", maxWeldSegmentsSapcing=" + maxWeldSegmentsSapcing
				+ "]";
	}
	
	
}
