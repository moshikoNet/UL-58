package com.moshiko.beans;

import java.util.Date;

public class Marking {

	//clause 21.1 -  Each tank shall be legibly and permanently marked with:
	private Boolean declaredPrimaryTankCapacity;
	private Boolean nameOfManufacture;
	private Boolean designStandardNumber;
	private Boolean tankSerialNumber;
	private Boolean manufacturingDate;

	//clause 21.7 -  Each tank shall be marked with the following or equivalent:
	private Boolean installationInstructions;
	private Boolean liftingInstructions;

	/*clause 10.9 "All openings in a tank shall be closed with wooden plugs, metal covers,
	plastic thread protectors, or the
	equivalent, to protect the threads and exclude foreign matter while the tank is in storage or in transit."
	 */
	private Boolean protectingConnectionInstructions;

	private Boolean maxBurialDepthMarking;

	/*clause 12.1 - When a tank has a steel striker plate, under only one opening, the opening shall bemarked to indicate that dipstick
	 * measurements shall be made only at that location.*/
	private Boolean oneOpeningStrikerPlateMarking;

	public Marking(Boolean declaredPrimaryTankCapacity, Boolean nameOfManufacture, Boolean designStandardNumber,
			Boolean tankSerialNumber, Boolean manufacturingDate, Boolean installationInstructions,
			Boolean liftingInstructions, Boolean protectingConnectionInstructions, Boolean maxBurialDepthMarking,
			Boolean oneOpeningStrikerPlateMarking) {
		this.declaredPrimaryTankCapacity = declaredPrimaryTankCapacity;
		this.nameOfManufacture = nameOfManufacture;
		this.designStandardNumber = designStandardNumber;
		this.tankSerialNumber = tankSerialNumber;
		this.manufacturingDate = manufacturingDate;
		this.installationInstructions = installationInstructions;
		this.liftingInstructions = liftingInstructions;
		this.protectingConnectionInstructions = protectingConnectionInstructions;
		this.maxBurialDepthMarking = maxBurialDepthMarking;
		this.oneOpeningStrikerPlateMarking = oneOpeningStrikerPlateMarking;
	}

	public Boolean getDeclaredPrimaryTankCapacity() {
		return declaredPrimaryTankCapacity;
	}

	public void setDeclaredPrimaryTankCapacity(Boolean declaredPrimaryTankCapacity) {
		this.declaredPrimaryTankCapacity = declaredPrimaryTankCapacity;
	}

	public Boolean getNameOfManufacture() {
		return nameOfManufacture;
	}

	public void setNameOfManufacture(Boolean nameOfManufacture) {
		this.nameOfManufacture = nameOfManufacture;
	}

	public Boolean getDesignStandardNumber() {
		return designStandardNumber;
	}

	public void setDesignStandardNumber(Boolean designStandardNumber) {
		this.designStandardNumber = designStandardNumber;
	}

	public Boolean getTankSerialNumber() {
		return tankSerialNumber;
	}

	public void setTankSerialNumber(Boolean tankSerialNumber) {
		this.tankSerialNumber = tankSerialNumber;
	}

	public Boolean getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(Boolean manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public Boolean getInstallationInstructions() {
		return installationInstructions;
	}

	public void setInstallationInstructions(Boolean installationInstructions) {
		this.installationInstructions = installationInstructions;
	}

	public Boolean getLiftingInstructions() {
		return liftingInstructions;
	}

	public void setLiftingInstructions(Boolean liftingInstructions) {
		this.liftingInstructions = liftingInstructions;
	}

	public Boolean getProtectingConnectionInstructions() {
		return protectingConnectionInstructions;
	}

	public void setProtectingConnectionInstructions(Boolean protectingConnectionInstructions) {
		this.protectingConnectionInstructions = protectingConnectionInstructions;
	}

	public Boolean getMaxBurialDepthMarking() {
		return maxBurialDepthMarking;
	}

	public void setMaxBurialDepthMarking(Boolean maxBurialDepthMarking) {
		this.maxBurialDepthMarking = maxBurialDepthMarking;
	}

	public Boolean getOneOpeningStrikerPlateMarking() {
		return oneOpeningStrikerPlateMarking;
	}

	public void setOneOpeningStrikerPlateMarking(Boolean oneOpeningStrikerPlateMarking) {
		this.oneOpeningStrikerPlateMarking = oneOpeningStrikerPlateMarking;
	}
	
	





}
