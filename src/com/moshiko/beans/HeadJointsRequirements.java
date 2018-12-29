package com.moshiko.beans;


public class HeadJointsRequirements extends JointsRequirements {


	private Boolean isFlangeRequired;//all except num 10,11,13,14
	private Boolean isWeldTipRequired;//num 19 & num 20






	public HeadJointsRequirements(Boolean isContinuousWeldRequired, Boolean isContinuousFilletWeldRequired,
			Boolean isOverlapBRequired, Boolean isTackWeldRequired, Boolean isFlangeRequired,
			Boolean isWeldTipRequired) {
		super(isContinuousWeldRequired, isContinuousFilletWeldRequired, isOverlapBRequired, isTackWeldRequired);
		this.isFlangeRequired = isFlangeRequired;
		this.isWeldTipRequired = isWeldTipRequired;
	}



	public HeadJointsRequirements() {
	}



	public Boolean getIsFlangeRequired() {
		return isFlangeRequired;
	}



	public void setIsFlangeRequired(Boolean isFlangeRequired) {
		this.isFlangeRequired = isFlangeRequired;
	}



	public Boolean getIsWeldTipRequired() {
		return isWeldTipRequired;
	}



	public void setIsWeldTipRequired(Boolean isWeldTipRequired) {
		this.isWeldTipRequired = isWeldTipRequired;
	}



	@Override
	public String toString() {
		return "HeadJointsRequirements [isFlangeRequired=" + isFlangeRequired + ", isWeldTipRequired="
				+ isWeldTipRequired + ", getIsContinuousWeldRequired()=" + getIsContinuousWeldRequired()
				+ ", getIsContinuousFilletWeldRequired()=" + getIsContinuousFilletWeldRequired()
				+ ", getIsOverlapBRequired()=" + getIsOverlapBRequired() + ", getIsTackWeldRequired()="
				+ getIsTackWeldRequired() + "]";
	}

	
	
}