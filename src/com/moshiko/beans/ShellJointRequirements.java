package com.moshiko.beans;


public class ShellJointRequirements extends JointsRequirements {


	
	private Boolean isOverlapDRequired;//num 2,5,7
	private Boolean isBackUpBarRequired;//num 8
	private Boolean isLockWeldRequired;//num 6
	private Boolean overlapERequired;//num 6

	


	public ShellJointRequirements(Boolean isContinuousWeldRequired, Boolean isContinuousFilletWeldRequired,
			Boolean isOverlapBRequired, Boolean isTackWeldRequired, Boolean isOverlapDRequired,
			Boolean isBackUpBarRequired, Boolean isLockWeldRequired, Boolean isoverlapERequired) {
		super(isContinuousWeldRequired, isContinuousFilletWeldRequired, isOverlapBRequired, isTackWeldRequired);
		this.isOverlapDRequired = isOverlapDRequired;
		this.isBackUpBarRequired = isBackUpBarRequired;
		this.isLockWeldRequired = isLockWeldRequired;
		this.overlapERequired = isoverlapERequired;
	}

	public ShellJointRequirements() {
	}

	
	
	public Boolean getIsOverlapERequired() {
		return overlapERequired;
	}

	public void setIsOverlapERequired(Boolean overlapE) {
		this.overlapERequired = overlapE;
	}

	public Boolean getIsOverlapDRequired() {
		return isOverlapDRequired;
	}

	public void setIsOverlapDRequired(Boolean isOverlapDRequired) {
		this.isOverlapDRequired = isOverlapDRequired;
	}

	public Boolean getIsBackUpBarRequired() {
		return isBackUpBarRequired;
	}

	public void setIsBackUpBarRequired(Boolean isBackUpBarRequired) {
		this.isBackUpBarRequired = isBackUpBarRequired;
	}

	public Boolean getIsLockWeldRequired() {
		return isLockWeldRequired;
	}

	public void setIsLockWeldRequired(Boolean isLockWeldRequired) {
		this.isLockWeldRequired = isLockWeldRequired;
	}

	@Override
	public String toString() {
		return "ShellJointRequirements [isOverlapDRequired=" + isOverlapDRequired + ", isBackUpBarRequired="
				+ isBackUpBarRequired + ", isLockWeldRequired=" + isLockWeldRequired + ", overlapE=" + overlapERequired
				+ ", getIsContinuousWeldRequired()=" + getIsContinuousWeldRequired()
				+ ", getIsContinuousFilletWeldRequired()=" + getIsContinuousFilletWeldRequired()
				+ ", getIsOverlapBRequired()=" + getIsOverlapBRequired() + ", getIsTackWeldRequired()="
				+ getIsTackWeldRequired() + "]";
	}

	
}
