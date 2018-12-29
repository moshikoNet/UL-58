package com.moshiko.beans;

import com.moshiko.enums.TypeOfTank;
import com.moshiko.utils.GlobalConstants;

public class Tank {

	// required parameters
	//order general information
	private double NominalCapacity;
	private String orderNumber;
	private String drawingsNumber;
	private String manufacture;

	private float maxBurialDepth=GlobalConstants.minBurialDepth;
	private float declaredVentOpening;

	//Type Of Tank : type I or type II 
	private TypeOfTank typeOfTank=TypeOfTank.TYPE_I;

	//15. CONSTRACTION : IS THE SECONDARY CONTAINMENT HEAD IN DIRECT CONTACT WITH THE PRIMARY HEAD
	private boolean contactBetweenLeftHeads=false;
	private boolean contactBetweenRightHeads=false;

	private float degreesSecondaryWrapeAroundPrimary=0;

	private int numberOfCompartments=1;

	private TankLayer tankPrimaryLayer;
	private TankLayer tankSeconderyLayer;

	//optional parameters
	private ManHole manHole;
	private boolean istankHasAManhole=false;

	public Tank() {
	}


	public Tank(double nominalCapacity, String orderNumber, String drawingsNumber, String manufacture,
			float maxBurialDepth, float declaredVentOpening, TypeOfTank typeOfTank, boolean contactBetweenLeftHeads,
			boolean contactBetweenRightHeads, float degreesSecondaryWrapeAroundPrimary, ManHole manHole,
			boolean istankHasAManhole, int numberOfCompartments, TankLayer tankPrimaryLayer,
			TankLayer tankSeconderyLayer) {
		NominalCapacity = nominalCapacity;
		this.orderNumber = orderNumber;
		this.drawingsNumber = drawingsNumber;
		this.manufacture = manufacture;
		this.maxBurialDepth = maxBurialDepth;
		this.declaredVentOpening = declaredVentOpening;
		this.typeOfTank = typeOfTank;
		this.contactBetweenLeftHeads = contactBetweenLeftHeads;
		this.contactBetweenRightHeads = contactBetweenRightHeads;
		this.degreesSecondaryWrapeAroundPrimary = degreesSecondaryWrapeAroundPrimary;
		this.manHole = manHole;
		this.istankHasAManhole = istankHasAManhole;
		this.numberOfCompartments = numberOfCompartments;
		this.tankPrimaryLayer = tankPrimaryLayer;
		this.tankSeconderyLayer = tankSeconderyLayer;
	}





	public float getDegreesSecondaryWrapeAroundPrimary() {
		return degreesSecondaryWrapeAroundPrimary;
	}


	public void setDegreesSecondaryWrapeAroundPrimary(float degreesSecondaryWrapeAroundPrimary) {
		this.degreesSecondaryWrapeAroundPrimary = degreesSecondaryWrapeAroundPrimary;
	}


	public double getNominalCapacity() {
		return NominalCapacity;
	}

	public void setNominalCapacity(double NominalCapacity) {
		this.NominalCapacity = NominalCapacity;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDrawingsNumber() {
		return drawingsNumber;
	}

	public void setDrawingsNumber(String drawingsNumber) {
		this.drawingsNumber = drawingsNumber;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public float getMaxBurialDepth() {
		return maxBurialDepth;
	}

	public void setMaxBurialDepth(float maxBurialDepth) {
		this.maxBurialDepth = maxBurialDepth;
	}

	public TypeOfTank getTypeOfTank() {
		return typeOfTank;
	}

	public void setTypeOfTank(TypeOfTank typeOfTank) {
		this.typeOfTank = typeOfTank;
	}

	public int getNumberOfCompartments() {
		return numberOfCompartments;
	}

	public void setNumberOfCompartments(int numberOfCompartments) {
		this.numberOfCompartments = numberOfCompartments;
	}

	public TankLayer getPrimaryTank() {
		return tankPrimaryLayer;
	}

	public void setPrimaryTank(TankLayer primaryTank) {
		this.tankPrimaryLayer = primaryTank;
	}

	public TankLayer getSeconderyTank() {
		return tankSeconderyLayer;
	}

	public void setSeconderyTank(TankLayer seconderyTank) {
		this.tankSeconderyLayer = seconderyTank;
	}



	public float getDeclaredVentOpening() {
		return declaredVentOpening;
	}



	public void setDeclaredVentOpening(float declaredVentOpening) {
		this.declaredVentOpening = declaredVentOpening;
	}



	public Boolean getContactBetweenLeftHeads() {
		return contactBetweenLeftHeads;
	}



	public void setContactBetweenLeftHeads(Boolean contactBetweenLeftHeads) {
		this.contactBetweenLeftHeads = contactBetweenLeftHeads;
	}



	public Boolean getContactBetweenRightHeads() {
		return contactBetweenRightHeads;
	}



	public void setContactBetweenRightHeads(Boolean contactBetweenRightHeads) {
		this.contactBetweenRightHeads = contactBetweenRightHeads;
	}



	public TankLayer getTankPrimaryLayer() {
		return tankPrimaryLayer;
	}



	public void setTankPrimaryLayer(TankLayer tankPrimaryLayer) {
		this.tankPrimaryLayer = tankPrimaryLayer;
	}



	public TankLayer getTankSeconderyLayer() {
		return tankSeconderyLayer;
	}



	public void setTankSeconderyLayer(TankLayer tankSeconderyLayer) {
		this.tankSeconderyLayer = tankSeconderyLayer;
	}




	public ManHole getManHole() {
		return manHole;
	}


	public void setManHole(ManHole manHole) {
		this.manHole = manHole;
	}


	public boolean isIstankHasAManhole() {
		return istankHasAManhole;
	}


	public void setIstankHasAManhole(boolean istankHasAManhole) {
		this.istankHasAManhole = istankHasAManhole;
	}


	public void setContactBetweenLeftHeads(boolean contactBetweenLeftHeads) {
		this.contactBetweenLeftHeads = contactBetweenLeftHeads;
	}


	public void setContactBetweenRightHeads(boolean contactBetweenRightHeads) {
		this.contactBetweenRightHeads = contactBetweenRightHeads;
	}


	@Override
	public String toString() {
		return "Tank [NominalCapacity=" + NominalCapacity + ", orderNumber=" + orderNumber + ", drawingsNumber="
				+ drawingsNumber + ", manufacture=" + manufacture + ", maxBurialDepth=" + maxBurialDepth
				+ ", declaredVentOpening=" + declaredVentOpening + ", typeOfTank=" + typeOfTank
				+ ", contactBetweenLeftHeads=" + contactBetweenLeftHeads + ", contactBetweenRightHeads="
				+ contactBetweenRightHeads + ", degreesSecondaryWrapeAroundPrimary="
				+ degreesSecondaryWrapeAroundPrimary + ", manHole=" + manHole + ", istankHasAManhole="
				+ istankHasAManhole + ", numberOfCompartments=" + numberOfCompartments + ", tankPrimaryLayer="
				+ tankPrimaryLayer + ", tankSeconderyLayer=" + tankSeconderyLayer + "]";
	}




}
