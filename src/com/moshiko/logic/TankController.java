package com.moshiko.logic;

import java.sql.SQLException;

import javax.rmi.CORBA.Util;

import com.moshiko.Dao.Ul58Dao;
import com.moshiko.beans.Bracing;
import com.moshiko.beans.BulkHead;
import com.moshiko.beans.Figure8_1WeldingDetails;
import com.moshiko.beans.Figure9_1WeldingDetails;
import com.moshiko.beans.HeadJointsRequirements;
import com.moshiko.beans.JointsRequirements;
import com.moshiko.beans.Head;
import com.moshiko.beans.ManHole;
import com.moshiko.beans.ShellJointRequirements;
import com.moshiko.beans.Tank;
import com.moshiko.beans.TankLayer;
import com.moshiko.beans.Type101BulkHead;
import com.moshiko.enums.ErrorType;
import com.moshiko.enums.Figure8_1TypeOfWeld;
import com.moshiko.enums.HeadSide;
import com.moshiko.enums.TypeOfHead;
import com.moshiko.enums.TypeOfBracing;
import com.moshiko.enums.TypeOfBulkhead;
import com.moshiko.enums.TypeOfGasket;
import com.moshiko.enums.TypeOfManHole;
import com.moshiko.enums.TypeOfTank;
import com.moshiko.enums.LogType;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.utils.GlobalConstants;
import com.moshiko.utils.Logger;
import com.moshiko.utils.Utils;
//TODO Daniel Is The King
public class TankController {

	float primaryShellActualThickness;
	float secondaryShellActualThickness;
	float primaryActualDiameter ;
	float primaryActualLenght ;

	String fileNameByTankProperties;


	public TankController(Tank tank) {

		this.primaryShellActualThickness = Utils.calculatedActualShellThickness(tank.getPrimaryTank());
		this.secondaryShellActualThickness = Utils.calculatedActualShellThickness(tank.getSeconderyTank());
		this.primaryActualDiameter = Utils.calculatedActualLayerDiameter(tank.getPrimaryTank());
		this.primaryActualLenght = Utils.calculatedActualLayerLenght(tank.getPrimaryTank());
		this.fileNameByTankProperties = Utils.fileNameByTankProperties(tank);

	}

	public boolean validateShellThickness(Tank tank) throws MyApplicationException{

		String conclusion;

		// tank type is null validation
		if (Utils.isTheValueEqualsToNull(tank.getTypeOfTank())==true){
			throw new MyApplicationException("ValidateShellThickness EXCEPTION:THE TANK TYPE NAME EQUALS TO NULL",ErrorType.TANK_GENERAL_CARITARISTICS_ERROR);
		}


		if (this.primaryShellActualThickness<GlobalConstants.minSteelThicknessPrimaryTankPrimaryShell) 
		{

			conclusion= "Thickness of the primary shell is not as required as required in clause 5.1.2, Min. steel thickness:"+GlobalConstants.minSteelThicknessPrimaryTankPrimaryShell+"[mm]"+" ,declared: "+this.primaryShellActualThickness;

			Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MISMATCH);


		}

		if(this.secondaryShellActualThickness<GlobalConstants.minSteelThicknessSecondaryTankShell){

			conclusion= "Thickness of the secondary shell is not as required as required in clause 5.1.2, Min. steel thickness:"+GlobalConstants.minSteelThicknessSecondaryTankShell+"[mm]"+" ,declared: "+this.secondaryShellActualThickness;

			Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MISMATCH);

		}
		if (TypeOfTank.TYPE_I.name().equals(tank.getTypeOfTank().name())){

			//VALIDTAE!!

			double RorakPressureTypeI = Utils.calculatedRorakPressureTypeI(tank.getTankSeconderyLayer().getLayerShell(), tank.getPrimaryTank());
			double actualBurialPressure = Utils.calculatedBurialPressureTypeI(tank.getMaxBurialDepth(), tank.getSeconderyTank().getLayerShell(), tank.getTankPrimaryLayer());

			if (RorakPressureTypeI<actualBurialPressure){

				conclusion ="TYPE I tank as defined in clause 5.2 will collapse because of over buckling pressure ";

				Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MISMATCH);

				return false;

			}else{
				return true;
			}
		}

		if (TypeOfTank.TYPE_II.name().equals(tank.getTypeOfTank().name())){


			//VALIDTAE!!


			//PrimaryTank
			double RorakPressureTypeIIPrimary = Utils.calculatedRorakPressureTypeII(tank.getPrimaryTank());
			double actualBurialPressureTypeIIPrimary = Utils.calculatedBurialPressureTypeII(tank.getMaxBurialDepth(), tank.getTankPrimaryLayer());

			if (RorakPressureTypeIIPrimary < actualBurialPressureTypeIIPrimary){

				conclusion = "TYPE II tank as defined in clause 5.2 will collapse because of over buckling pressure of the Primary layer";

				Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MISMATCH);

				return false;
			}

			//Secondary Tank
			double RorakPressureTypeIISecondary = Utils.calculatedRorakPressureTypeII(tank.getSeconderyTank());
			double actualBurialPressureTypeIISecondary = Utils.calculatedBurialPressureTypeII(tank.getMaxBurialDepth(), tank.getTankSeconderyLayer());

			if (RorakPressureTypeIISecondary < actualBurialPressureTypeIISecondary){

				conclusion = "TYPE II tank as defined in clause 5.2 will collapse because of over buckling pressure of the Secondary layer";

				Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MISMATCH);

				return false;
			}
		}

		return false;

	}

	//clause 4.3 - calculate actual capacity
	public double calculateActualCapacity(TankLayer tankPrimaryLayer)
	{	

		System.out.println("calculate actual capacity, actual lenght of tank: "+this.primaryActualLenght+"[mm]");
		System.out.println("calculate actual capacity, actual diameter of tank: "+this.primaryActualDiameter+"[mm]");

		double calculatedCapacity=Math.PI*Math.pow(this.primaryActualDiameter, 2) * this.primaryActualLenght * Math.pow(10, -6)/4;

		return calculatedCapacity;

	}	

	//clause 4.3 - nominal capacity
	public boolean validateCapacity(Tank tank) throws MyApplicationException{

		double calculatedActualCapacity=calculateActualCapacity(tank.getPrimaryTank());
		double nominalCapacity= tank.getNominalCapacity();

		String conclusion;


		int roundedPercentage= (int) ((calculatedActualCapacity/nominalCapacity)*100);		
		if (roundedPercentage>=100 & roundedPercentage<=105){

			conclusion="the tank's declered capacity is as required in clause 4.3, calculated Capacity:" + calculatedActualCapacity+" ,rounded Percentage:"+roundedPercentage;

			Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MATCH);

			return true;
		}

		conclusion="the tank's declered capacity is Not as required in clause 4.3: calculated Capacity:" + calculatedActualCapacity+"[liter] ,rounded Percentage:"+roundedPercentage+"[liter]";

		Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MISMATCH);

		return false;

	}

	//clause 4.5 -  ratio D to L
	public  boolean validateRatio(TankLayer tankPrimaryLayer) throws MyApplicationException{

		String conclusion;

		System.out.println("ratio validation, actual lenght of tank: "+this.primaryActualLenght+"[mm]");
		System.out.println("ratio validation, actual diameter of tank: "+this.primaryActualDiameter+"[mm]");

		float ratio= (float) (this.primaryActualLenght/this.primaryActualDiameter);		

		if (ratio<=8){


			conclusion= "the tank's ratio between the diameter & lenght is as required in clause 4.4,calculated ratio :" + ratio;
			
			Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MATCH);

			return true;
		}

		conclusion= "the tank's ratio between the diameter & lenght is Not as required in clause 4.4, allowed ratio up to 8, calculated ratio:" + ratio ;

		Utils.printToConsoleAndLog(this.fileNameByTankProperties, conclusion, LogType.VALIDATION_MISMATCH);
		
		return false;

	}

	public boolean validateTankDataForDesign(Tank tank) throws MyApplicationException{


		return false;


	}

	//clause 5.1 -  Head Thickness
	public  boolean validateHeadsThickness(Tank tank) throws MyApplicationException, SQLException{

		boolean isheadsRequired;

		String info = "Primary Tank:\nLeft Head:\n";

		Utils.printToConsoleAndLog(this.fileNameByTankProperties, info, LogType.INFO);
		
		isheadsRequired = checkHeadThicknessTable7_1(tank.getPrimaryTank(), tank.getPrimaryTank().getLayerLeftHead());

		info="Right Head:";
		
		Utils.printToConsoleAndLog(this.fileNameByTankProperties, info, LogType.INFO);
		
		isheadsRequired = checkHeadThicknessTable7_1(tank.getPrimaryTank(), tank.getPrimaryTank().getLayerRightHead());

		if (TypeOfTank.TYPE_I.name().equals(tank.getTypeOfTank().name())){

			info = "Secondery Tank:\nLeft Head of TYPE_I:\n";
			
			Utils.printToConsoleAndLog(this.fileNameByTankProperties, info, LogType.INFO);
			
			isheadsRequired = checkHeadThicknessTable7_2(tank.getSeconderyTank(), tank.getSeconderyTank().getLayerLeftHead());

			info ="Right Head of TYPE_I:";

			Utils.printToConsoleAndLog(this.fileNameByTankProperties, info, LogType.INFO);isheadsRequired = checkHeadThicknessTable7_2(tank.getSeconderyTank(), tank.getSeconderyTank().getLayerRightHead());

			return isheadsRequired;
		}

		if (TypeOfTank.TYPE_II.name().equals(tank.getTypeOfTank().name())){

			info = "Secondery Tank:\nLeft Head of TYPE_II:\n";

			Utils.printToConsoleAndLog(this.fileNameByTankProperties, info, LogType.INFO);

			isheadsRequired = checkHeadThicknessTable7_1(tank.getSeconderyTank(), tank.getSeconderyTank().getLayerLeftHead());

			info ="Right Head of TYPE_II:";

			Utils.printToConsoleAndLog(this.fileNameByTankProperties, info, LogType.INFO);

			isheadsRequired = checkHeadThicknessTable7_1(tank.getSeconderyTank(), tank.getSeconderyTank().getLayerRightHead());

			return isheadsRequired;
		}
		return false;
	}

	// table7.1- Tank head thickness (type I - check primary only, type II - check primary and secondary head).
	//use LayerHead Object because we have right and left side
	public boolean checkHeadThicknessTable7_1(TankLayer tankLayer, Head layerHead) throws MyApplicationException, SQLException{

		Ul58Dao ul58Dao = new Ul58Dao();

		float actualLayerDiameter =Utils.calculatedActualLayerDiameter(tankLayer);
		float actualDeclaredHeadThickness =Utils.calculatedActualHeadThickness(layerHead);

		System.out.println("check Head Thickness according Table7_1, actual diameter of tank: "+actualLayerDiameter+"[mm]");

		float RequiredHeadThickness = ul58Dao.getRequiredHeadThicknessTable7_1(actualLayerDiameter);

		System.out.println("check Head Thickness according Table7_1, Required "+layerHead.getHeadSide().name()+"[mm] Head Thickness of tank: "+RequiredHeadThickness+"[mm]");

		if (actualDeclaredHeadThickness >= RequiredHeadThickness){
			System.out.println("the tank's "+layerHead.getHeadSide().name()+" head Thickness is as required in clause 5.1.3");

			return true;
		}

		System.out.println("the tank's "+layerHead.getHeadSide().name()+" head Thickness is not as required in clause 5.1.3, \n Min. head Thickness requirement: "+RequiredHeadThickness+"[mm] ,declared Head Thickness is: "+ actualDeclaredHeadThickness+"[mm]");

		return false;

	}

	// table7.2 - for Type I tank - secondary head thickness (NOT for Type II)
	public  boolean checkHeadThicknessTable7_2(TankLayer tankLayer, Head layerHead) throws MyApplicationException, SQLException{

		Ul58Dao ul58Dao = new Ul58Dao();

		float actualLayerDiameter =tankLayer.getLayerInsideDiameter() + tankLayer.getLayerInsideDiameterTolerance();
		float actualDeclaredHeadThickness =layerHead.getHeadThickness() + layerHead.getHeadThicknessTolerance();

		System.out.println("check Head Thickness Table7_2, actual diameter of tank: "+actualLayerDiameter);

		float RequiredHeadThickness = ul58Dao.getRequiredHeadThicknessTable7_2(actualLayerDiameter);

		System.out.println("check Head Thickness Table7_2, Required "+layerHead.getHeadSide().name()+"[mm] Head Thickness of tank: "+RequiredHeadThickness+"[mm]");

		if (actualDeclaredHeadThickness >= RequiredHeadThickness){
			System.out.println("the tank's "+layerHead.getHeadSide().name()+" head Thickness is as required in clause 5.1.3");
			return true;
		}

		System.out.println("the tank's "+layerHead.getHeadSide().name()+" head Thickness is not as required in clause 5.1.3 :\n Min. head Thickness requirement: "+RequiredHeadThickness+"[mm] ,declared Head Thickness is: "+ actualDeclaredHeadThickness+"[mm]");

		return false;

	}

	//clause 15.1& clause 9.1
	public boolean validateHeadsNumberOfPieces(Tank tank){
		boolean isHeadsNumberOfPiecesAsRequired = true;

		if(tank.getContactBetweenRightHeads()==true){
			System.out.println("\nPrimary Right Head:");
			isHeadsNumberOfPiecesAsRequired = checkNumberOfPiecesWithContact(tank.getPrimaryTank(),tank.getPrimaryTank().getLayerRightHead());

		}else{
			isHeadsNumberOfPiecesAsRequired = checkNumberOfHeadPiecesWithNoContact(tank.getPrimaryTank(),tank.getPrimaryTank().getLayerRightHead());
		}

		if(tank.getContactBetweenLeftHeads()==true){

			System.out.println("\nPrimary Left Head:");
			isHeadsNumberOfPiecesAsRequired = checkNumberOfPiecesWithContact(tank.getPrimaryTank(),tank.getPrimaryTank().getLayerLeftHead());

		}else{
			isHeadsNumberOfPiecesAsRequired = checkNumberOfHeadPiecesWithNoContact(tank.getPrimaryTank(),tank.getPrimaryTank().getLayerLeftHead());

		}

		System.out.println("\nSecondery Left Head:");
		isHeadsNumberOfPiecesAsRequired = checkNumberOfPiecesWithContact(tank.getSeconderyTank(),tank.getSeconderyTank().getLayerLeftHead());

		System.out.println("\nSecondery Right Head:");
		isHeadsNumberOfPiecesAsRequired = checkNumberOfPiecesWithContact(tank.getSeconderyTank(),tank.getSeconderyTank().getLayerRightHead());

		return isHeadsNumberOfPiecesAsRequired;

	}

	//clause 9.1 - inner head which is in direct contact with the outer head
	public int numberOfHeadPiecesWithContact(TankLayer tankLayer){

		int requiredNumberOfHeadPiecesWithContact=0;

		float actualLayerDiameter = Utils.calculatedActualLayerDiameter(tankLayer);

		if(actualLayerDiameter<=1220){
			requiredNumberOfHeadPiecesWithContact=2;
		}

		if( actualLayerDiameter>=1240 &  actualLayerDiameter<=2440){
			requiredNumberOfHeadPiecesWithContact=3;
		}
		if(actualLayerDiameter>=2460  & actualLayerDiameter<=3660 ){
			requiredNumberOfHeadPiecesWithContact=4;

		}
		return requiredNumberOfHeadPiecesWithContact; 
	}


	//clause 9.1 - inner head which is in direct contact with the outer head
	public boolean checkNumberOfPiecesWithContact(TankLayer tankLayer, Head layerHead){

		int requiredHeadNumberOfPiecesWithContact=numberOfHeadPiecesWithContact(tankLayer);

		if(layerHead.getNumberOfHeadPieces()<= requiredHeadNumberOfPiecesWithContact){
			System.out.println("the tank's "+layerHead.getHeadSide().name()+" head number of pieces is as required in clause 9.1");

			return true;
		}

		System.out.println("the tank's "+layerHead.getHeadSide().name()+" head number of pieces is not as required in clause 9.1\n Max. number of head pieces requirement: "+requiredHeadNumberOfPiecesWithContact+" ,declared number of Head pieces is: "+ layerHead.getNumberOfHeadPieces());

		return false;

	}

	//clause 15.1 - inner head which is not in direct contact with the outer head
	public boolean checkNumberOfHeadPiecesWithNoContact(TankLayer tankLayer, Head layerHead){

		float actualLayerDiameter = tankLayer.getLayerInsideDiameter()+tankLayer.getLayerInsideDiameterTolerance();

		int requiredHeadNumberOfHeadPiecesWithNoContact=0;

		if(actualLayerDiameter<=2400 & actualLayerDiameter>=1200){
			requiredHeadNumberOfHeadPiecesWithNoContact=3;
		}
		if(actualLayerDiameter<=3600 & actualLayerDiameter>=2420){
			requiredHeadNumberOfHeadPiecesWithNoContact=4;

		} 

		if(layerHead.getNumberOfHeadPieces()<= requiredHeadNumberOfHeadPiecesWithNoContact){

			System.out.println("the tank's "+layerHead.getHeadSide().name()+" head number of pieces is as required in clause 15.1");
			return true;
		}

		System.out.println("the tank's "+layerHead.getHeadSide().name()+" head number of pieces not as required in clause 15.1\n Max. number of head pieces requirement: "+requiredHeadNumberOfHeadPiecesWithNoContact+" ,declared number of Head pieces is: "+layerHead.getNumberOfHeadPieces() );

		return false;

	}

	//clause 6 - head Joints
	public  boolean validateHeadWeldingDetails(Tank tank) throws MyApplicationException, SQLException{

		boolean validateHeadWeldingDetails=false;

		//Primary Tank Heads
		System.out.println("\nPrimary Tank Heads:\n");

		System.out.println("\nLeft Head, Circumferential Weld Number: "+tank.getPrimaryTank().getLayerLeftHead().getHeadCircumferentialWeld().getFigure9_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure9_1WeldingDetails(tank.getPrimaryTank().getLayerLeftHead());

		System.out.println("\nLeft Head, Longitudinal Weld Number: "+tank.getPrimaryTank().getLayerLeftHead().getHeadLongitudinalWeld().getFigure8_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure8_1WeldingDetails(tank.getPrimaryTank(),tank.getPrimaryTank().getLayerLeftHead().getHeadLongitudinalWeld());

		System.out.println("\nRight Head, , Circumferential Weld Number: "+tank.getPrimaryTank().getLayerRightHead().getHeadCircumferentialWeld().getFigure9_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure9_1WeldingDetails(tank.getPrimaryTank().getLayerRightHead());

		System.out.println("\nRight Head, Longitudinal Weld Number:"+tank.getPrimaryTank().getLayerRightHead().getHeadLongitudinalWeld().getFigure8_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure8_1WeldingDetails(tank.getPrimaryTank(),tank.getPrimaryTank().getLayerRightHead().getHeadLongitudinalWeld());

		//Secondary Tank Heads
		System.out.println("\nSecondary Tank Heads:\n");

		System.out.println("\nLeft Head, Circumferential Weld Number: "+tank.getSeconderyTank().getLayerLeftHead().getHeadCircumferentialWeld().getFigure9_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure9_1WeldingDetails(tank.getSeconderyTank().getLayerLeftHead());

		System.out.println("\nLeft Head, Longitudinal Weld Number: "+tank.getSeconderyTank().getLayerLeftHead().getHeadLongitudinalWeld().getFigure8_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure8_1WeldingDetails(tank.getSeconderyTank(),tank.getSeconderyTank().getLayerLeftHead().getHeadLongitudinalWeld());

		System.out.println("\nRight Head, Circumferential Weld Number: "+tank.getSeconderyTank().getLayerRightHead().getHeadCircumferentialWeld().getFigure9_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure9_1WeldingDetails(tank.getSeconderyTank().getLayerRightHead());

		System.out.println("\nRight Head, Longitudinal Weld Number: "+tank.getSeconderyTank().getLayerRightHead().getHeadLongitudinalWeld().getFigure8_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure8_1WeldingDetails(tank.getSeconderyTank(),tank.getSeconderyTank().getLayerRightHead().getHeadLongitudinalWeld());

		return validateHeadWeldingDetails;
	}

	//clause 6 - Head Joints
	public  boolean checkFigure9_1WeldingDetails(Head layerHead) throws MyApplicationException, SQLException{

		Ul58Dao ul58Dao = new Ul58Dao();

		Figure9_1WeldingDetails figure9_1WeldingDetails = layerHead.getHeadCircumferentialWeld();

		HeadJointsRequirements headJointsRequirements;

		//HeadJointsRequirements headJointsCheckResults=new HeadJointsRequirements(true,true,true, true, true, true);

		boolean headJointsCheckResults=true;

		headJointsRequirements=ul58Dao.getHeadJointsRequirements(figure9_1WeldingDetails.getFigure9_1TypeOfWeld().name());

		if(headJointsRequirements.getIsContinuousWeldRequired()==true){
			if (figure9_1WeldingDetails.getContinuousWeld()==false){
				headJointsCheckResults=false;
				//headJointsCheckResults.setIsContinuousWeldRequired(false);
				System.out.println("\ncheckFigure9_1WeldingDetails, Continuous Weld of "+layerHead.getHeadSide().name()+" head side are not as required :\njoint with no Continuous Weld \"C\"  ");
			}
		}

		if(headJointsRequirements.getIsContinuousFilletWeldRequired()==true){
			if (figure9_1WeldingDetails.getContinuousFilletWeld()==false){
				headJointsCheckResults=false;
				//headJointsCheckResults.setIsContinuousFilletWeldRequired(false);		
				System.out.println("\ncheckFigure9_1WeldingDetails, Continuous Fillet Weld of "+layerHead.getHeadSide().name()+" head side are not as required :\njoint with no Continuous Fillet Weld \"CF\"  ");

			}
		}
		if(headJointsRequirements.getIsFlangeRequired()==true){
			float actualThickness = (float)(layerHead.getHeadThickness()-layerHead.getHeadThicknessTolerance());
			if (figure9_1WeldingDetails.getHeadFlange()<(5*actualThickness)){
				headJointsCheckResults=false;
				//headJointsCheckResults.setIsFlangeRequired(false);
				System.out.println("\ncheckFigure9_1WeldingDetails, Head Flange of "+layerHead.getHeadSide().name()+" head side are not as required :\nHead Flange less than 5 times the head thickness "+"required: "+5*actualThickness+"[mm] ,declared Head : "+figure9_1WeldingDetails.getHeadFlange()+"[mm]");

			}
		}

		if(headJointsRequirements.getIsOverlapBRequired()==true){

			if (figure9_1WeldingDetails.getOverlapB()<(GlobalConstants.minOverLapB)){
				headJointsCheckResults=false;
				//headJointsCheckResults.setIsOverlapBRequired(false);
				System.out.println("\ncheckFigure9_1WeldingDetails, Overlap B of "+layerHead.getHeadSide().name()+" head side are not as required :\njoint Overlap B less than: " + GlobalConstants.minOverLapB+"[mm] ,declared : "+figure9_1WeldingDetails.getOverlapB()+"[mm]");

			}
		}

		if(headJointsRequirements.getIsTackWeldRequired()==true){
			if (figure9_1WeldingDetails.getTackWeld().getSpotsLenght()<GlobalConstants.minTackWeldSpotsLenght){
				headJointsCheckResults=false;
				//headJointsCheckResults.setIsTackWeldRequired(false);
				System.out.println("\ncheckFigure9_1WeldingDetails, Tack Weld of "+layerHead.getHeadSide().name()+" head side are not as required :\n joint Tack Weld's Spots Lenght less than: " + GlobalConstants.minTackWeldSpotsLenght+"[mm] ,declared : "+ figure9_1WeldingDetails.getTackWeld().getSpotsLenght()+"[mm]");

			}
			if (figure9_1WeldingDetails.getTackWeld().getWeldSegmentsSapcing()>GlobalConstants.maxWeldSegmentsSapcing){
				headJointsCheckResults=false;
				//headJointsCheckResults.setIsTackWeldRequired(false);
				System.out.println("\ncheckFigure9_1WeldingDetails, Tack Weld of "+layerHead.getHeadSide().name()+" head side are not as required :\n joint Tack Weld's Segments Sapcing is more than: " + GlobalConstants.maxWeldSegmentsSapcing+"[mm] ,declared: "+figure9_1WeldingDetails.getTackWeld().getWeldSegmentsSapcing()+"[mm]");

			}

		}

		if(headJointsRequirements.getIsWeldTipRequired()==true){
			float actualThickness = (float)(layerHead.getHeadThickness()-layerHead.getHeadThicknessTolerance());
			if (figure9_1WeldingDetails.getWeldTip()<actualThickness){
				headJointsCheckResults=false;
				//headJointsCheckResults.setIsWeldTipRequired(false);
				System.out.println("\ncheckFigure9_1WeldingDetails, Weld Tip of "+layerHead.getHeadSide().name()+" head side are not as required :\n joint WeldTip's thickness is less than head thickness: " + actualThickness+"[mm] ,declared: "+figure9_1WeldingDetails.getWeldTip()+"[mm]");

			}
		}

		return headJointsCheckResults;
	}

	//clause 6 - Joints
	public  boolean validateShellsWeldingDetails(Tank tank) throws MyApplicationException, SQLException{

		boolean validateHeadWeldingDetails=false;

		//Primary Tank shell
		System.out.println("\nPrimary Tank:");

		System.out.println("\nPrimary shell, Longitudinal Weld Number: "+tank.getPrimaryTank().getLayerShell().getTypeOfShellLongitudinalWeld().getFigure8_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure8_1WeldingDetails(tank.getPrimaryTank(),tank.getPrimaryTank().getLayerShell().getTypeOfShellLongitudinalWeld());

		System.out.println("\nPrimary shell, Circumferential Weld Number: "+tank.getPrimaryTank().getLayerShell().getTypeOfShellCircumferentialWeld().getFigure8_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure8_1WeldingDetails(tank.getPrimaryTank(),tank.getPrimaryTank().getLayerShell().getTypeOfShellCircumferentialWeld());

		//Secondary Tank Heads
		System.out.println("\nSecondary Tank:");

		System.out.println("\nSecondary shell, Longitudinal Weld Number: "+tank.getSeconderyTank().getLayerShell().getTypeOfShellLongitudinalWeld().getFigure8_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure8_1WeldingDetails(tank.getSeconderyTank(),tank.getSeconderyTank().getLayerShell().getTypeOfShellLongitudinalWeld());

		System.out.println("\nSecondary shell, Circumferential Weld Number: "+tank.getSeconderyTank().getLayerShell().getTypeOfShellCircumferentialWeld().getFigure8_1TypeOfWeld().name()+"\n");
		validateHeadWeldingDetails=checkFigure8_1WeldingDetails(tank.getSeconderyTank(),tank.getSeconderyTank().getLayerShell().getTypeOfShellCircumferentialWeld());


		return validateHeadWeldingDetails;
	}

	//clause 6 - Joints
	public  boolean checkFigure8_1WeldingDetails(TankLayer tankLayer,Figure8_1WeldingDetails jointsWeldingDetails) throws MyApplicationException, SQLException{

		Ul58Dao ul58Dao = new Ul58Dao();

		Figure8_1WeldingDetails figure8_1WeldingDetails = jointsWeldingDetails;

		ShellJointRequirements shellJointRequirements;

		boolean shellJointsCheckResults=false;

		//ShellJointRequirements shellJointsCheckResults=new ShellJointRequirements(true,true,true, true, true, true, true);

		shellJointRequirements=ul58Dao.getShellJointsRequirements(figure8_1WeldingDetails.getFigure8_1TypeOfWeld().name());

		if(shellJointRequirements.getIsContinuousWeldRequired()==true){
			if (figure8_1WeldingDetails.getContinuousWeld()==false){
				shellJointsCheckResults=false;
				//shellJointsCheckResults.setIsContinuousWeldRequired(false);
				System.out.println("\ncheckFigure8_1WeldingDetails, Continuous Weld is not as required :\njoint with no Continuous Weld \"C\" ");
			}
		}

		if(shellJointRequirements.getIsContinuousFilletWeldRequired()==true){
			if (figure8_1WeldingDetails.getContinuousFilletWeld()==false){
				shellJointsCheckResults=false;
				//shellJointsCheckResults.setIsContinuousFilletWeldRequired(false);		
				System.out.println("\ncheckFigure8_1WeldingDetails, Continuous Fillet Weld is not as required :\njoint with no Continuous Fillet Weld \"CF\" ");

			}
		}

		if(shellJointRequirements.getIsOverlapBRequired()==true){

			if (figure8_1WeldingDetails.getOverlapB()<(GlobalConstants.minOverLapB)){
				shellJointsCheckResults=false;
				//shellJointsCheckResults.setIsOverlapBRequired(false);
				System.out.println("\ncheckFigure8_1WeldingDetails, Overlap B is not as required :\njoint Overlap B less than: " + GlobalConstants.minOverLapB+"[mm] ,declared : "+figure8_1WeldingDetails.getOverlapB()+"[mm]");

			}
		}

		//Tack Weld
		if(shellJointRequirements.getIsTackWeldRequired()==true){
			if (figure8_1WeldingDetails.getTackWeld().getSpotsLenght()<GlobalConstants.minTackWeldSpotsLenght){
				shellJointsCheckResults=false;
				//shellJointsCheckResults.setIsTackWeldRequired(false);
				System.out.println("\ncheckFigure8_1WeldingDetails,Tack Weld is not as required :\njoint Tack Weld's Spots Lenght less than: " + GlobalConstants.minTackWeldSpotsLenght+"[mm] ,declared : "+figure8_1WeldingDetails.getTackWeld().getSpotsLenght()+"[mm]");

			}
			if (figure8_1WeldingDetails.getTackWeld().getWeldSegmentsSapcing()>GlobalConstants.maxWeldSegmentsSapcing){
				shellJointsCheckResults=false;
				//shellJointsCheckResults.setIsTackWeldRequired(false);
				System.out.println("\ncheckFigure8_1WeldingDetails, Tack Weld is not as required :\njoint Tack Weld's Segments Sapcing is more than: " + GlobalConstants.maxWeldSegmentsSapcing+"[mm] ,declared : "+figure8_1WeldingDetails.getTackWeld().getWeldSegmentsSapcing()+"[mm]");
			}
		}

		if(shellJointRequirements.getIsOverlapDRequired()==true){
			float actualDiameter=tankLayer.getLayerInsideDiameter()-tankLayer.getLayerInsideDiameterTolerance();
			if(actualDiameter<=1200){
				if(figure8_1WeldingDetails.getOverlapD()< GlobalConstants.minOverLapDForDiameterUpTo1200){
					shellJointsCheckResults=false;
					//shellJointsCheckResults.setIsOverlapDRequired(false);
					System.out.println("\ncheckFigure8_1WeldingDetails, joint OverLapD is not as required :\njoint OverLapD  is less than: " + GlobalConstants.minOverLapDForDiameterUpTo1200+"[mm] ,declared: "+figure8_1WeldingDetails.getOverlapD()+"[mm]");

				}
			}else{
				if(figure8_1WeldingDetails.getOverlapD()< GlobalConstants.minOverLapDForDiameterOver1200){
					shellJointsCheckResults=false;
					//shellJointsCheckResults.setIsOverlapDRequired(false);
					System.out.println("\ncheckFigure8_1WeldingDetails, joint OverLapD is not as required :\njoint OverLapD  is less than: " + GlobalConstants.minOverLapDForDiameterOver1200+"[mm] ,declared: "+figure8_1WeldingDetails.getOverlapD()+"[mm]");

				}
			}
		}
		if (jointsWeldingDetails.getFigure8_1TypeOfWeld().equals(Figure8_1TypeOfWeld.NUM6)){

			if(shellJointRequirements.getIsOverlapERequired()==true){

				if (figure8_1WeldingDetails.getOverlapE()<(GlobalConstants.minOverLapE)){
					shellJointsCheckResults=false;
					//shellJointsCheckResults.setIsOverlapBRequired(false);
					System.out.println("\ncheckFigure8_1WeldingDetails, Overlap E is not as required :\njoint Overlap E less than: " + GlobalConstants.minOverLapE+"[mm] ,declared : "+figure8_1WeldingDetails.getOverlapE()+"[mm]");

				}
			}	

			//Lock Weld
			if(shellJointRequirements.getIsLockWeldRequired()==true){

				if (figure8_1WeldingDetails.getLockWeld().getMinSpotsLenght()<GlobalConstants.minLockWeldSpotsLenght){
					shellJointsCheckResults=false;
					//shellJointsCheckResults.setIsTackWeldRequired(false);
					System.out.println("\ncheckFigure8_1WeldingDetails,Lock Weld is not as required :\njoint Lock Weld's Spots Lenght less than: " + GlobalConstants.minLockWeldSpotsLenght +"[mm] ,declared : "+figure8_1WeldingDetails.getLockWeld().getMinSpotsLenght()+"[mm]");

				}

				if (figure8_1WeldingDetails.getLockWeld().getMaxWeldSegmentsSapcing()>GlobalConstants.maxWeldSegmentsSapcing){
					shellJointsCheckResults=false;
					//shellJointsCheckResults.setIsTackWeldRequired(false);
					System.out.println("\ncheckFigure8_1WeldingDetails, Lock Weld is not as required :\njoint Lock Weld's Segments Sapcing is more than: " + GlobalConstants.maxWeldSegmentsSapcing +"[mm] ,declared : "+figure8_1WeldingDetails.getLockWeld().getMaxWeldSegmentsSapcing()+"[mm]");
				}
			}

		}

		if (jointsWeldingDetails.getFigure8_1TypeOfWeld().equals(Figure8_1TypeOfWeld.NUM8)){

			if (figure8_1WeldingDetails.getWeldingWithBackUpBar().getBackUpBarThickness() < Utils.calculatedActualShellThickness(tankLayer)){
				shellJointsCheckResults=false;
				//shellJointsCheckResults.setIsOverlapBRequired(false);
				System.out.println("\ncheckFigure8_1WeldingDetails, Back Up Bar Thickness is not as required :\nBack Up Bar Thickness is less than the shell Thickness: " + Utils.calculatedActualShellThickness(tankLayer)+"[mm] ,declared : "+figure8_1WeldingDetails.getWeldingWithBackUpBar().getBackUpBarThickness()+"[mm]");

			}

			if (figure8_1WeldingDetails.getWeldingWithBackUpBar().getLenghtOfBackUpBar() < GlobalConstants.minLenghtOfBackUpBar){
				shellJointsCheckResults=false;
				//shellJointsCheckResults.setIsOverlapBRequired(false);
				System.out.println("\ncheckFigure8_1WeldingDetails, Back Up Bar Lenght is not as required :\nBack Up Bar Lenght is less than required: " +GlobalConstants.minLenghtOfBackUpBar+"[mm] ,declared : "+figure8_1WeldingDetails.getWeldingWithBackUpBar().getLenghtOfBackUpBar()+"[mm]");

			}

			if (figure8_1WeldingDetails.getWeldingWithBackUpBar().getWeldSeparation() < GlobalConstants.minWeldSeparation){
				shellJointsCheckResults=false;
				//shellJointsCheckResults.setIsOverlapBRequired(false);
				System.out.println("\ncheckFigure8_1WeldingDetails, Weld Separation is not as required :\nWeld Separation is less than required: " +GlobalConstants.minWeldSeparation+"[mm] ,declared : "+figure8_1WeldingDetails.getWeldingWithBackUpBar().getWeldSeparation()+"[mm]");

			}
		}	

		return shellJointsCheckResults;
	}

	//	clause 11 - Manhole
	public boolean validateManhole(Tank tank){

		System.out.println("\nvalidate Manhole:");

		if (tank.isIstankHasAManhole()==false){
			System.out.println("no requirement, the tank has no Manhole");
			return true;
		}

		ManHole manHole= tank.getManHole();
		boolean conclusion=true;
		int requiredNumberOfBolt = (int) (manHole.getNominalBoltCircleDimeter()*Math.PI/GlobalConstants.minManholeBoltSpacing);

		if (manHole.getManHoleContinuousFilletWeld()==false){
			System.out.println("\nvalidate Manhole, the welding of manhole is not Continuous fillet as required");
			conclusion=false;}

		if (manHole.getNumberOfBolts()<requiredNumberOfBolt){
			System.out.println("\nvalidate Manhole, required Number Of Bolt is: "+requiredNumberOfBolt+"\nManhole's number of bolts is :"+ manHole.getNumberOfBolts());
			conclusion=false;}

		if (manHole.getBoltDiameter()<GlobalConstants.minBoltDiameter){
			System.out.println("\nvalidate Manhole, required Bolt Diameter is: "+GlobalConstants.minBoltDiameter+"[mm]\nManhole's Diameter of bolts is :"+  manHole.getBoltDiameter()+"[mm]");
			conclusion=false;}

		if (manHole.getThicknessOfNeck()<GlobalConstants.minManholeNeckThickness){
			System.out.println("\nvalidate Manhole, required Manhole Neck Thickness is: "+ GlobalConstants.minManholeNeckThickness+"[mm]\nManhole's Neck Thickness is :"+ manHole.getThicknessOfBlindFlange()+"[mm]");
			conclusion=false;}

		if (manHole.getThicknessOfBlindFlange()<GlobalConstants.minManholeFlangeThickness){
			System.out.println("\nvalidate Manhole, required Manhole Flange Thickness is: "+ GlobalConstants.minManholeFlangeThickness +"[mm]\nManhole's Flange Thickness is : "+manHole.getThicknessOfNeck()+"[mm]" );
			conclusion=false;}

		if((tank.getPrimaryTank().getLayerInsideDiameter()-tank.getPrimaryTank().getLayerInsideDiameterTolerance())>=1800){
			if (manHole.getHeightOfFlangeFromTheTop()<GlobalConstants.minHeightOfManholeFlangeFromTheTop){
				System.out.println("\nvalidate Manhole, required Manhole Height Of Flange From The Top: "+GlobalConstants.minHeightOfManholeFlangeFromTheTop +"[mm]\nManhole's Height Of Flange From The Top is : "+ manHole.getHeightOfFlangeFromTheTop()+"[mm]" );
				conclusion=false;}
		}

		if (manHole.getTypeOfGasket().equals(TypeOfGasket.NONE)){
			System.out.println("\nvalidate Manhole, the manhole has no gasket");
			conclusion=false;
		}else{ if(manHole.getThicknessOfTheGasket()<GlobalConstants.minGasketThickness){
			System.out.println("\nvalidate Manhole, required Manhole Gasket's Thickness is: "+ manHole.getThicknessOfTheGasket()+"[mm]\nManhole's Gasket Thickness is :"+GlobalConstants.minGasketThickness+"[mm]");
			conclusion=false;}}

		if (manHole.getTypeOfManHole().equals(TypeOfManHole.NUM94)){
			if (manHole.getManHoleContinuousWeld()==false){
				System.out.println("\nvalidate Manhole, the welding of manhole of type number 94 is not Continuous as required");
				conclusion=false;
			}
		}
		if(conclusion==true){
			System.out.println("manHole is as require by clause 11.");
		}
		return conclusion;

	}

	//	clause 10.10 - vent size
	public boolean validateVentSize (Tank tank) throws MyApplicationException, SQLException{

		Ul58Dao ul58Dao = new Ul58Dao();

		double calculatedCapacity = calculateActualCapacity(tank.getPrimaryTank());


		if(tank.getDeclaredVentOpening()< ul58Dao.getRequiredVentSize(calculatedCapacity)){
			System.out.println("\nvalidate Vent Size, declared Vent Size is less than required\nrequired: "+ul58Dao.getRequiredVentSize(calculatedCapacity)+"[mm] ,declared: "+tank.getDeclaredVentOpening()+"[mm]");
		}else{
			System.out.println("\nVent Size as the standard require");

		}
		return false;

	}

	//	clause 3.2 - degrees
	public  boolean validateDegreesSecondaryWrapeAroundPrimary( Tank tank ){

		float degreesSecondaryWrapeAroundPrimary =tank.getDegreesSecondaryWrapeAroundPrimary();

		if (degreesSecondaryWrapeAroundPrimary>=300 & degreesSecondaryWrapeAroundPrimary<=360)
		{
			System.out.println("\nthe tank's secondary layer is wrapped around the primary tank as required");
			return true;
		}

		System.out.println("\nthe tank's secondary layer is not wrapped around the primary tank as stated in clause 3.2 :\nThe secondary tank wrapped around the primary:"+degreesSecondaryWrapeAroundPrimary+"[deg]");
		return false;

	}	

	//clause 9.4 - conical head requirement.
	public  boolean checkConicalHeadHeight(float actualDiameter,float conicalHeadHeight ){

		if (conicalHeadHeight>=((float) actualDiameter/12))
		{		
			System.out.println("the tank's conical head is as required in clause 9.4 :\nMin. head height requirement: "+((float) actualDiameter/12)+"[mm] head height is: "+ conicalHeadHeight+"[mm]");

			return true;
		}

		System.out.println("the tank's conical head is not as required in clause 9.4 :\nMin. head height requirement: "+((float) actualDiameter/12)+"[mm] head height is: "+ conicalHeadHeight+"[mm]");

		return false;

	}

	//clause 9.4 - conical head requirement.
	public  boolean validateConicalHeadDepth(Tank tank) throws MyApplicationException, SQLException{

		//Primary Tank Heads
		System.out.println("\nPrimary Tank Heads:\n");

		float actualPrimaryLayerDiameter =Utils.calculatedActualLayerDiameter(tank.getPrimaryTank());

		System.out.println("\nLeft Head:");

		if(tank.getTankPrimaryLayer().getLayerLeftHead().getHeadShape().equals(TypeOfHead.CONICAL)){

			checkConicalHeadHeight(actualPrimaryLayerDiameter, tank.getTankPrimaryLayer().getLayerLeftHead().getConicalHeadHeight());
		}else{
			System.out.println("No requirment, Head Shape is not conical");
		}

		System.out.println("\nRight Head:");

		if(tank.getTankPrimaryLayer().getLayerRightHead().getHeadShape().equals(TypeOfHead.CONICAL)){

			checkConicalHeadHeight(actualPrimaryLayerDiameter, tank.getTankPrimaryLayer().getLayerRightHead().getConicalHeadHeight());
		}else{
			System.out.println("No requirment, Head Shape is not conical");
		}

		//secondary Tank Heads
		System.out.println("\nsecondary Tank Heads:\n");

		float actualSecondaryLayerDiameter = Utils.calculatedActualLayerDiameter(tank.getTankSeconderyLayer());

		System.out.println("\nLeft Head:");
		if(tank.getTankSeconderyLayer().getLayerLeftHead().getHeadShape().equals(TypeOfHead.CONICAL)){
			checkConicalHeadHeight(actualSecondaryLayerDiameter, tank.getTankSeconderyLayer().getLayerLeftHead().getConicalHeadHeight());

		}else{
			System.out.println("No requirment, Head Shape is not conical");
		}


		System.out.println("\nRight Head:");
		if(tank.getTankSeconderyLayer().getLayerRightHead().getHeadShape().equals(TypeOfHead.CONICAL)){

			checkConicalHeadHeight(actualSecondaryLayerDiameter, tank.getTankSeconderyLayer().getLayerRightHead().getConicalHeadHeight());
		}else{
			System.out.println("No requirment, Head Shape is not conical");
		}

		return false;

	}

	//clause 15.5 - dished head requirement.
	public  boolean checkDishedHeadDepth(float actualDiameter,float dishedHeadDepth ) throws MyApplicationException, SQLException{

		Ul58Dao ul58Dao = new Ul58Dao();

		float RequiredDishedHeadDepth=ul58Dao.getRequiredDishedHeadDepth(actualDiameter);

		if (dishedHeadDepth>=RequiredDishedHeadDepth)
		{		System.out.println("the tank's Dished head is as required in clause 15.5 :\nhead Depth Min. requirement: "+RequiredDishedHeadDepth+"[mm] head height is: "+ dishedHeadDepth+"[mm]");

		return true;
		}

		System.out.println("the tank's Dished head is not as required in clause 15.5 :\nhead Depth Min. requirement: "+RequiredDishedHeadDepth+"[mm] head height is: "+ dishedHeadDepth+"[mm]");

		return false;

	}

	//clause 15.5 - dished head requirement.
	public  boolean validateDishedHeadDepth(Tank tank ) throws MyApplicationException, SQLException{

		//Primary Tank Heads
		System.out.println("\nPrimary Tank Heads:\n");


		System.out.println("\nLeft Head:");

		if(tank.getTankPrimaryLayer().getLayerLeftHead().getHeadShape().equals(TypeOfHead.DISHED)){

			checkDishedHeadDepth(this.primaryActualDiameter, tank.getTankPrimaryLayer().getLayerLeftHead().getDishedHeadDepth());
		}else{
			System.out.println("No requirment, Head Shape is not dished");
		}

		System.out.println("\nRight Head:");

		if(tank.getTankPrimaryLayer().getLayerRightHead().getHeadShape().equals(TypeOfHead.DISHED)){

			checkDishedHeadDepth(this.primaryActualDiameter, tank.getTankPrimaryLayer().getLayerRightHead().getDishedHeadDepth());
		}else{
			System.out.println("No requirment, Head Shape is not dished");
		}

		//secondary Tank Heads
		System.out.println("\nsecondary Tank Heads:\n");

		float actualSecondaryLayerDiameter = Utils.calculatedActualLayerDiameter(tank.getSeconderyTank());

		System.out.println("\nLeft Head:");
		if(tank.getTankSeconderyLayer().getLayerLeftHead().getHeadShape().equals(TypeOfHead.DISHED)){
			checkDishedHeadDepth(actualSecondaryLayerDiameter, tank.getTankSeconderyLayer().getLayerLeftHead().getDishedHeadDepth());

		}else{
			System.out.println("No requirment, Head Shape is not dished");
		}


		System.out.println("\nRight Head:");
		if(tank.getTankSeconderyLayer().getLayerRightHead().getHeadShape().equals(TypeOfHead.DISHED)){

			checkDishedHeadDepth(actualSecondaryLayerDiameter, tank.getTankSeconderyLayer().getLayerRightHead().getDishedHeadDepth());
		}else{
			System.out.println("No requirment, Head Shape is not dished");
		}

		return false;

	}

	//clause 15.3 - inner head which is not in direct contact with the outer head bracing
	public boolean isBracingRequired(float actualTankDiameter,  float actualHeadThickness,boolean areTheHeadscontactEachOther, String headType){

		if(headType.equals(TypeOfHead.FLANGE_FLAT.name())){

			if(areTheHeadscontactEachOther==false){

				if(GlobalConstants.minThicknessForRedundantBracing>actualHeadThickness & actualTankDiameter>1800){
					System.out.println("inner head bracing required due to diameter over 1,800[mm]: "+actualTankDiameter+"[mm] and insufficient thickness, under 7.67[mm] : "+actualHeadThickness+"[mm]");
					return true;
				}

				System.out.println("No requirment, Head bracing is not required:\ndiameter less than 1,800[mm]:"+actualTankDiameter+"[mm] or sufficient thickness, over 7.67[mm] : "+actualHeadThickness+"[mm]");

				return false;

			}else{
				System.out.println("No requirment, Head bracing is not required due to that the heads are in direct contact");
				return false;
			}
		}else{
			System.out.println("No requirment, Head bracing is not required due to that the head is not Flat Flange");
			return false;
		}
	}

	public boolean checkHeadBracing(Bracing headBracing, double actualPrimaryLayerDiameter) throws MyApplicationException, SQLException{

		boolean isHeadBracingAsRequired= true;

		Ul58Dao ul58Dao= new Ul58Dao();

		float requiredSectionModulusZOfBrace =  ul58Dao.getRequiredBracingTable13_1(actualPrimaryLayerDiameter, headBracing.getTypeOfBracing());

		if(headBracing.getDistanceFromShellX() > GlobalConstants.distanceFromTheShellX){

			System.out.println("head bracing is not as required :\ndistance From The Center line Of The Head Y axis requirement: "+GlobalConstants.distanceFromTheShellX+"[mm] declared distance is: "+ headBracing.getDistanceFromShellX()+"[mm]");
			isHeadBracingAsRequired= false;
		}

		if(headBracing.getBracingTackWeld().getSpotsLenght()< GlobalConstants.minTackWeldSpotsLenght){

			System.out.println("head bracing is not as required :\nmin Tack Weld Spots Lenght requirement: "+GlobalConstants.minTackWeldSpotsLenght+"[mm] declared Spots Lenght is: "+ headBracing.getBracingTackWeld().getSpotsLenght()+"[mm]");
			isHeadBracingAsRequired = false;
		}

		if(headBracing.getBracingTackWeld().getWeldSegmentsSapcing() > GlobalConstants.maxWeldSegmentsSapcing){

			System.out.println("head bracing is not as required  :\nmax Weld Segments Sapcing requirement: "+GlobalConstants.maxWeldSegmentsSapcing+"[mm] declared Spots Lenght is: "+ headBracing.getBracingTackWeld().getWeldSegmentsSapcing()+"[mm]");
			isHeadBracingAsRequired = false;
		}

		if(headBracing.getDistanceFromTheCenterLineOfTheHeadY()>GlobalConstants.distanceFromTheCenterlineOfTheHeadY){

			System.out.println("head bracing is not as required  :\ndistance From The Center line Of The Head Y axis requirement: "+GlobalConstants.distanceFromTheCenterlineOfTheHeadY+"[mm] declared distance is: "+ headBracing.getDistanceFromTheCenterLineOfTheHeadY()+"[mm]");
			isHeadBracingAsRequired = false;
		}


		if(headBracing.getSectionModulusZOfBrace() < requiredSectionModulusZOfBrace){
			System.out.println("head bracing is not as required :\nrequired Section Modulus Z Of Brace: "+requiredSectionModulusZOfBrace+"[inch^3], declared:  "+ headBracing.getSectionModulusZOfBrace()+"[inch^3]");
			isHeadBracingAsRequired= false;
		}

		if (isHeadBracingAsRequired==true){
			System.out.println("head bracing is as required");
		}

		return isHeadBracingAsRequired;	

	}

	//BRACING OF HEAD (clause 15.3 & figure 13.2 & table 13.1)
	public boolean validateHeadsBracing(Tank tank) throws MyApplicationException, SQLException{

		boolean isHeadsBracingAsRequired= true;

		float actualLeftHeadThickness =Utils.calculatedActualHeadThickness(tank.getPrimaryTank().getLayerLeftHead() );
		String leftHeadType=tank.getPrimaryTank().getLayerLeftHead().getHeadShape().name();

		float actualRightHeadThickness =Utils.calculatedActualHeadThickness(tank.getPrimaryTank().getLayerRightHead() );
		String rightHeadType=tank.getPrimaryTank().getLayerRightHead().getHeadShape().name();

		System.out.println("\nleft head:\n");

		if (isBracingRequired(this.primaryActualDiameter, actualLeftHeadThickness, tank.getContactBetweenLeftHeads(),leftHeadType )==true){

			Bracing leftHeadBracing = tank.getPrimaryTank().getLayerLeftHead().getHeadBracing();

			isHeadsBracingAsRequired=checkHeadBracing(leftHeadBracing,this.primaryActualDiameter);
		}

		System.out.println("\nright head:\n");

		if (isBracingRequired(this.primaryActualDiameter, actualRightHeadThickness, tank.getContactBetweenRightHeads(),rightHeadType)==true){

			Bracing rightHeadBracing = tank.getPrimaryTank().getLayerRightHead().getHeadBracing();

			isHeadsBracingAsRequired = checkHeadBracing(rightHeadBracing,this.primaryActualDiameter);

		}

		if (isHeadsBracingAsRequired==true){
			System.out.println("heads bracing are as required by clause 15.3");
		}
		return isHeadsBracingAsRequired;
	}

	//clause 9.3 & 15.4 - Head Knuckle Radius
	public boolean checkKnuckleRadius(Head layerHead,boolean isItAInnerHead) throws MyApplicationException, SQLException{

		float minRequiredKnuckleRadius=0;

		float actualHeadThickness =Utils.calculatedActualHeadThickness(layerHead);

		if (layerHead.getHeadShape().equals(TypeOfHead.FLANGE_FLAT) & isItAInnerHead==true){

			// minRequiredKnuckleRadiu s= thk. * 1.5
			minRequiredKnuckleRadius= (actualHeadThickness * GlobalConstants.knuckleRadiusFactorHeadInnerFlatFlange);
			if (layerHead.getKnuckleRadius() < minRequiredKnuckleRadius){
				System.out.println("head Knuckle Radius not as required in clause 15.4,\nmin. Required Knuckle Radius:"+minRequiredKnuckleRadius+"[mm]\ndeclared Knuckle Radius:"+layerHead.getKnuckleRadius()+"[mm]");
				return false;
			}else{
				System.out.println("head Knuckle Radius is as required in clause 15.4,\nmin. Required Knuckle Radius:"+minRequiredKnuckleRadius+"[mm]\ndeclared Knuckle Radius:"+layerHead.getKnuckleRadius()+"[mm]");

			}
		}else{
			// minRequiredKnuckleRadiu s= thk. * 2
			minRequiredKnuckleRadius= (actualHeadThickness * GlobalConstants.knuckleRadiusFactorHeadsExecptInnerFlatFlange);

			if (layerHead.getKnuckleRadius() < minRequiredKnuckleRadius){
				System.out.println("head Knuckle Radius not as required in clause 9.3,\nmin. Required Knuckle Radius:"+minRequiredKnuckleRadius+"[mm]\ndeclared Knuckle Radius:"+layerHead.getKnuckleRadius()+"[mm]");
				return false;
			}else{
				System.out.println("head Knuckle Radius is as required in clause 9.3,\nmin. Required Knuckle Radius:"+minRequiredKnuckleRadius+"[mm]\ndeclared Knuckle Radius:"+layerHead.getKnuckleRadius()+"[mm]");

			}
		}

		return true;

	}

	//clause 9.3 & 15.4 - Head Knuckle Radius
	public boolean validateKnuckleRadius(Tank tank) throws MyApplicationException, SQLException{

		//Primary Tank Heads
		System.out.println("\nPrimary Tank Heads:\n");
		boolean isItAInnerHead=true;

		System.out.println("\nLeft Head:");
		checkKnuckleRadius(tank.getPrimaryTank().getLayerLeftHead(), isItAInnerHead);

		System.out.println("\nRight Head:");
		checkKnuckleRadius(tank.getPrimaryTank().getLayerRightHead(), isItAInnerHead);

		//secondary Tank Heads
		System.out.println("\nsecondary Tank Heads:\n");
		isItAInnerHead=false;

		System.out.println("\nLeft Head:");
		checkKnuckleRadius(tank.getSeconderyTank().getLayerLeftHead(), isItAInnerHead);

		System.out.println("\nRight Head:");
		return checkKnuckleRadius(tank.getSeconderyTank().getLayerRightHead(), isItAInnerHead);


	}

	//clause 9.1 - inner head which is in direct contact with the outer head
	public boolean checkNumberOfPiecesType100BulkHead(TankLayer tankLayer, BulkHead bulkHead){

		int requiredHeadNumberOfPieces= numberOfHeadPiecesWithContact(tankLayer);

		if(bulkHead.getNumberOfBulkheadPieces() <= requiredHeadNumberOfPieces){
			System.out.println("checkNumberOfPiecesType100BulkHead, the number of pieces of type 100 BulkHead is as required in clause 13.3");

			return true;
		}

		System.out.println("checkNumberOfPiecesType100BulkHead, the number of pieces of type 100 BulkHead not as required in clause 13.3\nMax. number of head pieces requirement: "+requiredHeadNumberOfPieces+" ,declared number of Head pieces is: "+ bulkHead.getNumberOfBulkheadPieces());

		return false;

	}

	public boolean checkBulkHeadShape(BulkHead bulkHead) throws MyApplicationException, SQLException{

		if (bulkHead.getTypeOfBulkhead().equals(TypeOfBulkhead.NUM100)){
			System.out.println("checkBulkHeadShape, no requirement for Bulkhead shape for type is NUM 100");
			return true;

		}else{
			if(bulkHead.getBulkheadshape().equals(TypeOfHead.CONICAL) || bulkHead.getBulkheadshape().equals(TypeOfHead.DISHED)){
				System.out.println("checkBulkHeadShape, Bulkhead shape is as required, head shape is : " + bulkHead.getBulkheadshape());
				return true;

			}else {
				System.out.println("checkBulkHeadShape, Bulkhead shape is not as required, head shape is : " + bulkHead.getBulkheadshape());
				return false;

			}
		}
	}

	public boolean checkBulkHeadNumOfPieces(BulkHead bulkHead,TankLayer tankLayer) throws MyApplicationException, SQLException{

		if (bulkHead.getTypeOfBulkhead().equals(TypeOfBulkhead.NUM100)){

			return checkNumberOfPiecesType100BulkHead(tankLayer, bulkHead);

		}else{
			if(bulkHead.getNumberOfBulkheadPieces() > GlobalConstants.maxNumOfPiecesBulkheadsType101and102){
				System.out.println("checkBulkHeadNumOfPieces, the number of pieces of type "+bulkHead.getTypeOfBulkhead().name()+" BulkHead is not as required\nBulkHead number of pieces is more than one, declared: "+bulkHead.getNumberOfBulkheadPieces());
				return false;
			}else{
				System.out.println("checkBulkHeadNumOfPieces, the number of pieces of type "+bulkHead.getTypeOfBulkhead().name()+" BulkHead is as required.");
				return true;
			}
		}

	}

	public boolean checkBulkHeadType101Dimensions(Type101BulkHead bulkHead,float actualPrimaryLayerDiameter) throws MyApplicationException, SQLException{

		boolean isBulkHeadType101DimensionsAsRequired=true;

		if (bulkHead.getTypeOfBulkhead().equals(TypeOfBulkhead.NUM101)){
			if(bulkHead.getLenghtDOfStrightFlange() < GlobalConstants.minLenghtDOfStrightFlange){
				System.out.println("\ncheckBulkHeadType101Dimensions, Bulkhead OverLapD is not as required :\nBulkhead OverLap D  is less than: " + GlobalConstants.minLenghtDOfStrightFlange+"[mm] ,declared: "+bulkHead.getLenghtDOfStrightFlange()+"[mm]");
				isBulkHeadType101DimensionsAsRequired=false;
			}else{
				System.out.println("\ncheckBulkHeadType101Dimensions, Bulkhead OverLapD is as required :\nrequired Bulkhead OverLap D: " + GlobalConstants.minLenghtDOfStrightFlange+"[mm] ,declared: "+bulkHead.getLenghtDOfStrightFlange()+"[mm]");

			}

			if(bulkHead.getOverLapBBulkheadAndShell()<GlobalConstants.minOverLapBBulkheadAndShell){
				System.out.println("\ncheckBulkHeadType101Dimensions, OverLap B between Bulkhead And Shell is not as required :\nOverLap B between Bulkhead And Shell  is less than: " + GlobalConstants.minOverLapBBulkheadAndShell+"[mm] ,declared: "+bulkHead.getOverLapBBulkheadAndShell()+"[mm]");
				isBulkHeadType101DimensionsAsRequired=false;

			}else{
				System.out.println("\ncheckBulkHeadType101Dimensions, OverLap B between Bulkhead And Shell is as required :\nrequired OverLap B between Bulkhead And Shell: " + GlobalConstants.minOverLapBBulkheadAndShell+"[mm] ,declared: "+bulkHead.getOverLapBBulkheadAndShell()+"[mm]");

			}

			if(bulkHead.getDisatnceCBetweenTwoShells()<GlobalConstants.minDisatnceCBetweenTwoShells){
				System.out.println("\ncheckBulkHeadType101Dimensions, Disatnce C Between Two Shells is not as required :\nDisatnce C Between Two Shells  is less than: " + GlobalConstants.minDisatnceCBetweenTwoShells+"[mm] ,declared: "+bulkHead.getDisatnceCBetweenTwoShells()+"[mm]");
				isBulkHeadType101DimensionsAsRequired=false;

			}else{
				System.out.println("\ncheckBulkHeadType101Dimensions, Disatnce C Between Two Shells is as required :\nrequired Disatnce C Between Two Shells: " + GlobalConstants.minDisatnceCBetweenTwoShells+"[mm] ,declared: "+bulkHead.getDisatnceCBetweenTwoShells()+"[mm]");

			}
		}else{
			System.out.println("no requirement not of type 101 Bulkhead");

		}

		return isBulkHeadType101DimensionsAsRequired;
	}

	public boolean checkBulkHeadThicknessTable7_1(TankLayer tankLayer, BulkHead bulkHead) throws MyApplicationException, SQLException{

		Ul58Dao ul58Dao = new Ul58Dao();

		float actualLayerDiameter =Utils.calculatedActualLayerDiameter(tankLayer);
		float actualDeclaredBulkHeadThickness =Utils.calculatedActualBulkHeadThickness(bulkHead);

		System.out.println("check BulkHead Thickness according Table7_1, actual diameter of tank: "+actualLayerDiameter+"[mm]");

		float RequiredBulkHeadThickness = ul58Dao.getRequiredHeadThicknessTable7_1(actualLayerDiameter);

		System.out.println("check Head Thickness according Table7_1, Required : "+RequiredBulkHeadThickness+"[mm]");

		if (actualDeclaredBulkHeadThickness >= RequiredBulkHeadThickness){
			System.out.println("BulkHead Thickness is as required in clause 13.4");

			return true;
		}

		System.out.println("BulkHead Thickness is not as required in clause 13.4, \n Min. BulkHead Thickness requirement: "+RequiredBulkHeadThickness+"[mm] ,declared Head Thickness is: "+ actualDeclaredBulkHeadThickness+"[mm]");

		return false;

	}


}
