package com.moshiko.beans;


public class JointsRequirements {


	private Boolean isContinuousWeldRequired;
	private Boolean isContinuousFilletWeldRequired;
	private Boolean isOverlapBRequired;//num 3,12,15,16,17,18,23,24
	private Boolean isTackWeldRequired; //num 5,24
	
	
	public JointsRequirements(Boolean isContinuousWeldRequired, Boolean isContinuousFilletWeldRequired,
			Boolean isOverlapBRequired, Boolean isTackWeldRequired) {
		this.isContinuousWeldRequired = isContinuousWeldRequired;
		this.isContinuousFilletWeldRequired = isContinuousFilletWeldRequired;
		this.isOverlapBRequired = isOverlapBRequired;
		this.isTackWeldRequired = isTackWeldRequired;
	}

	public JointsRequirements(){
		
	}

	public Boolean getIsContinuousWeldRequired() {
		return isContinuousWeldRequired;
	}

	public void setIsContinuousWeldRequired(Boolean isContinuousWeldRequired) {
		this.isContinuousWeldRequired = isContinuousWeldRequired;
	}

	public Boolean getIsContinuousFilletWeldRequired() {
		return isContinuousFilletWeldRequired;
	}

	public void setIsContinuousFilletWeldRequired(Boolean isContinuousFilletWeldRequired) {
		this.isContinuousFilletWeldRequired = isContinuousFilletWeldRequired;
	}

	public Boolean getIsOverlapBRequired() {
		return isOverlapBRequired;
	}

	public void setIsOverlapBRequired(Boolean isOverlapBRequired) {
		this.isOverlapBRequired = isOverlapBRequired;
	}

	public Boolean getIsTackWeldRequired() {
		return isTackWeldRequired;
	}

	public void setIsTackWeldRequired(Boolean isTackWeldRequired) {
		this.isTackWeldRequired = isTackWeldRequired;
	}

	@Override
	public String toString() {
		return "JointsRequirements [isContinuousWeldRequired=" + isContinuousWeldRequired
				+ ", isContinuousFilletWeldRequired=" + isContinuousFilletWeldRequired + ", isOverlapBRequired="
				+ isOverlapBRequired + ", isTackWeldRequired=" + isTackWeldRequired + "]";
	}
	
	
	
}
