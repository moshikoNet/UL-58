package com.moshiko.main;

import java.sql.SQLException;
import java.util.*;

import com.moshiko.beans.Bracing;
import com.moshiko.beans.BulkHead;
import com.moshiko.beans.Figure8_1WeldingDetails;
import com.moshiko.beans.Figure9_1WeldingDetails;
import com.moshiko.beans.Head;
import com.moshiko.beans.Shell;
import com.moshiko.beans.LockWeld;
import com.moshiko.beans.ManHole;
import com.moshiko.beans.TackWeld;
import com.moshiko.beans.Tank;
import com.moshiko.beans.TankLayer;
import com.moshiko.beans.Testing;
import com.moshiko.beans.Type101BulkHead;
import com.moshiko.beans.User;
import com.moshiko.beans.WeldingWithBackUpBar;
import com.moshiko.enums.TypeOfHead;
import com.moshiko.enums.TypeOfBracing;
import com.moshiko.enums.TypeOfBulkhead;
import com.moshiko.enums.Figure9_1TypeOfWeld;
import com.moshiko.enums.HeadSide;
import com.moshiko.enums.Figure8_1TypeOfWeld;
import com.moshiko.enums.TypeOfGasket;
import com.moshiko.enums.TypeOfLayer;
import com.moshiko.enums.TypeOfManHole;
import com.moshiko.enums.TypeOfTank;
import com.moshiko.enums.UserType;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.logic.TankController;
import com.moshiko.logic.UserController;
import com.moshiko.utils.Utils;

public class test {

	private static final float dishedHeadDepthSPrimary = 0;

	public static void main(String[] args) throws MyApplicationException, SQLException {

		// initializing a test tank 

		//Primary - HEAD
		float headThicknessPrimary=(float) 5.0;
		float headThicknessTolerancePrimary=(float) 0.0;
		TypeOfHead headShapePrimary=TypeOfHead.FLANGE_FLAT;
		float headFlangeDepthPrimaryTolerance=(float) 5.0;
		float knuckleRadiusPrimary=1;
		float knuckleRadiusTolerancePrimary=2;
		int numberOfHeadPiecesPrimary=23;
		float conicalHeadHeightPrimary=0;
		float dishedHeadDepthPrimary=210;

		Figure9_1TypeOfWeld headTypeOfWeldCircumferentialPrimary=Figure9_1TypeOfWeld.NUM24;

		//Primary - HEAD weld circumferential 
		Boolean continuousCircumferentialWeldHeadPrimary=false;
		Boolean continuousCircumferentialFilletWeldHeadPrimary=false;
		float overlapBHeadPrimary=(float)12.6;
		float weldTipHeadPrimary= 0;
		float headFlangeDepthPrimary=(float) 20.0;
		TackWeld tackWeldHeadPrimary=new TackWeld();

		Figure8_1TypeOfWeld headTypeOfWeldLongitudinalPrimary=Figure8_1TypeOfWeld.NUM6;

		//Primary - HEAD weld longitudinal 
		Boolean continuousLongitudinalWeldHeadPrimary=false;
		Boolean continuousLongitudinalFilletWeldHeadPrimary=false;
		float overlapBHeadLongitudinalPrimary=(float)1.1;
		float overlapDLongitudinalPrimary=(float) 1.1;
		WeldingWithBackUpBar weldingWithBackUpBarHeadLongitudinalPrimary=new WeldingWithBackUpBar();
		LockWeld lockWeldLongitudinalHeadPrimary =new LockWeld();
		TackWeld tackWeldHeadLongitudinalPrimary =new TackWeld();
		float overlapEHeadLongitudinalPrimary=(float)8.1;

		Figure9_1WeldingDetails WeldingDetailsCircumferentialPrimaryHead = new Figure9_1WeldingDetails(continuousCircumferentialWeldHeadPrimary, continuousCircumferentialFilletWeldHeadPrimary, overlapBHeadPrimary, tackWeldHeadPrimary, headTypeOfWeldCircumferentialPrimary, headFlangeDepthPrimary, weldTipHeadPrimary);
		Figure8_1WeldingDetails WeldingDetailsLongitudinalPrimaryHead = new Figure8_1WeldingDetails(continuousLongitudinalWeldHeadPrimary, continuousLongitudinalFilletWeldHeadPrimary, overlapBHeadLongitudinalPrimary, tackWeldHeadLongitudinalPrimary, headTypeOfWeldLongitudinalPrimary, overlapDLongitudinalPrimary, weldingWithBackUpBarHeadLongitudinalPrimary, lockWeldLongitudinalHeadPrimary, overlapEHeadLongitudinalPrimary);

		//Head - Secondary
		float headThicknessSecondary=(float) 6.0;
		float headThicknessToleranceSecondary=0;
		TypeOfHead headShapeSecondary=TypeOfHead.FLANGE_FLAT;
		float headFlangeDepthSecondaryTolerance=(float) 5.0;
		float knuckleRadiusSecondary=30;
		float knuckleRadiusToleranceSecondary=(float) 1.0;
		int numberOfHeadPiecesSecondary=6;
		float conicalHeadHeightSecondary=165;
		float dishedHeadDepthSecondary=20;

		Figure9_1TypeOfWeld headTypeOfCircumferentialWeldSecondary=Figure9_1TypeOfWeld.NUM20;

		//Secondary - HEAD weld circumferential 
		Boolean continuousCircumferentialWeldHeadSecondary=false;
		Boolean continuousCircumferentialFilletWeldHeadSecondary=false;
		float overlapBHeadSecondary=(float)12.0;
		float weldTipHeadSecondary=0;
		float headFlangeDepthSecondary=(float) 11.0;
		TackWeld tackWeldHeadSecondary=null;

		Figure8_1TypeOfWeld headTypeOfWeldLongitudinalSecondary=Figure8_1TypeOfWeld.NUM7;

		//Secondary - HEAD weld longitudinal 
		Boolean continuousLongitudinalWeldHeadSecondary=false;
		Boolean continuousLongitudinalFilletWeldHeadSecondary=false;
		float overlapBHeadLongitudinalSecondary=(float)1.1;
		float overlapDLongitudinalSecondary=(float) 2.1;
		WeldingWithBackUpBar weldingWithBackUpBarHeadLongitudinalSecondary=new WeldingWithBackUpBar();
		LockWeld lockWeldLongitudinalHeadSecondary =new LockWeld();
		TackWeld tackWeldHeadLongitudinalSecondary=new TackWeld();
		float overlapEHeadLongitudinalSecondary=(float)1.1;


		Figure9_1WeldingDetails WeldingDetailsCircumferentialSecondaryHead = new Figure9_1WeldingDetails(continuousCircumferentialWeldHeadSecondary, continuousCircumferentialFilletWeldHeadSecondary, overlapBHeadSecondary, tackWeldHeadSecondary, headTypeOfCircumferentialWeldSecondary, headFlangeDepthSecondary, weldTipHeadSecondary);
		Figure8_1WeldingDetails WeldingDetailsLongitudinalSecondaryHead = new Figure8_1WeldingDetails(continuousLongitudinalWeldHeadSecondary, continuousLongitudinalFilletWeldHeadSecondary, overlapBHeadLongitudinalSecondary, tackWeldHeadLongitudinalSecondary, headTypeOfWeldLongitudinalSecondary, overlapDLongitudinalSecondary, weldingWithBackUpBarHeadLongitudinalSecondary, lockWeldLongitudinalHeadSecondary, overlapEHeadLongitudinalSecondary);

		//Inner Heads fBracing
		TypeOfBracing typeOfBracingInnerHeads=TypeOfBracing.CHANNEL;
		float distanceFromShellXInnerHeads=100;
		float distanceFromTheCenterlineOfTheHeadYInnerHeads=152;
		TackWeld bracingTackWeldInnerHeads=new TackWeld();
		float sectionModulusZOfBraceInnerHeads=(float) 1.0;
		int upnNumberInnerHeads = 100;

		Bracing bracingInnerHeads = new Bracing(typeOfBracingInnerHeads, distanceFromShellXInnerHeads, distanceFromTheCenterlineOfTheHeadYInnerHeads, bracingTackWeldInnerHeads, sectionModulusZOfBraceInnerHeads, upnNumberInnerHeads);


		Head secondaryLeftHead =new Head(headThicknessSecondary, headThicknessToleranceSecondary, headShapeSecondary, conicalHeadHeightSecondary, dishedHeadDepthSecondary, knuckleRadiusSecondary, numberOfHeadPiecesSecondary, HeadSide.LEFT, bracingInnerHeads, WeldingDetailsLongitudinalSecondaryHead, WeldingDetailsCircumferentialSecondaryHead);

		Head secondaryRightHead =new Head(headThicknessSecondary, headThicknessToleranceSecondary, headShapeSecondary, conicalHeadHeightSecondary, dishedHeadDepthSecondary, knuckleRadiusSecondary, numberOfHeadPiecesSecondary, HeadSide.RIGHT, bracingInnerHeads, WeldingDetailsLongitudinalSecondaryHead, WeldingDetailsCircumferentialSecondaryHead);

		Head primaryLeftHead =new Head(headThicknessPrimary, headThicknessTolerancePrimary, headShapePrimary, conicalHeadHeightPrimary, dishedHeadDepthPrimary, knuckleRadiusPrimary, numberOfHeadPiecesPrimary, HeadSide.LEFT, bracingInnerHeads, WeldingDetailsLongitudinalPrimaryHead, WeldingDetailsCircumferentialPrimaryHead);

		Head primaryRightHead =new Head(headThicknessPrimary, headThicknessTolerancePrimary, headShapePrimary, conicalHeadHeightPrimary, dishedHeadDepthPrimary, knuckleRadiusPrimary, numberOfHeadPiecesPrimary, HeadSide.RIGHT, bracingInnerHeads, WeldingDetailsLongitudinalPrimaryHead, WeldingDetailsCircumferentialPrimaryHead);


		float shellThicknessSecondary=(float) 3.0;
		float shellThicknessToleranceSecondary=0;
		Figure8_1TypeOfWeld typeOfLongitudinalWeldSecondary=Figure8_1TypeOfWeld.NUM8;
		Figure8_1TypeOfWeld typeOfCircumferentialWeldSecondary=Figure8_1TypeOfWeld.NUM8;

		//Secondary - SHELL weld longitudinal 
		Boolean continuousLongitudinalWeldShellSecondary=false;
		Boolean continuousLongitudinalFilletWeldShellSecondary=false;
		float overlapBShellLongitudinalSecondary=(float)1.2;
		float overlapDLongitudinaShelllSecondary=(float) 1.7;
		WeldingWithBackUpBar weldingWithBackUpBarShellLongitudinalSecondary=new WeldingWithBackUpBar();
		LockWeld lockWeldLongitudinalShellSecondary =new LockWeld();
		TackWeld tackWeldShellLongitudinalSecondary=new TackWeld();
		float overlapEShellLongitudinalSecondary=(float)12;

		//Secondary - SHELL weld circumferential 
		Boolean continuousCircumferentialWeldShellSecondary=false;
		Boolean continuousCircumferentialFilletWeldShellSecondary=false;
		float overlapBShellCircumferentialSecondary=(float)12;
		float overlapDCircumferentialShelllSecondary=(float) 2.7;
		WeldingWithBackUpBar weldingWithBackUpBarShellCircumferentialSecondary=new WeldingWithBackUpBar();
		LockWeld lockWeldCircumferentialShellSecondary= new LockWeld();
		TackWeld tackWeldShellCircumferentialSecondary=new TackWeld();
		float overlapEShellCircumferentialSecondary=(float)12;


		Figure8_1WeldingDetails WeldingDetailsCircumferentialSecondaryShell= new Figure8_1WeldingDetails(continuousCircumferentialWeldShellSecondary, continuousCircumferentialFilletWeldShellSecondary, overlapBShellCircumferentialSecondary, tackWeldShellCircumferentialSecondary, typeOfCircumferentialWeldSecondary, overlapDCircumferentialShelllSecondary, weldingWithBackUpBarShellCircumferentialSecondary, lockWeldCircumferentialShellSecondary, overlapEShellCircumferentialSecondary);
		Figure8_1WeldingDetails WeldingDetailsLongitudinalSecondaryShell= new Figure8_1WeldingDetails(continuousLongitudinalWeldShellSecondary, continuousLongitudinalFilletWeldShellSecondary, overlapBShellLongitudinalSecondary, tackWeldShellLongitudinalSecondary, typeOfLongitudinalWeldSecondary, overlapDLongitudinaShelllSecondary, weldingWithBackUpBarShellLongitudinalSecondary, lockWeldLongitudinalShellSecondary, overlapEShellLongitudinalSecondary);

		//Primary - SHELL
		float shellThicknessPrimary=(float) 6.0;
		float shellThicknessTolerancePrimary=(float) 0.3;

		Figure8_1TypeOfWeld typeOfLongitudinalWeldPrimaryShell=Figure8_1TypeOfWeld.NUM1;

		Figure8_1TypeOfWeld typeOfCircumferentialWeldPrimaryShell=Figure8_1TypeOfWeld.NUM5;

		//Primary - SHELL weld longitudinal 
		Boolean continuousLongitudinalWeldShellPrimary=false;
		Boolean continuousLongitudinalFilletWeldShellPrimary=false;
		float overlapBShellLongitudinalPrimary=(float)19.2;
		float overlapDLongitudinaShelllPrimary=(float) 12.7;
		WeldingWithBackUpBar weldingWithBackUpBarShellLongitudinalPrimary=null;
		LockWeld lockWeldLongitudinalShellPrimary=new LockWeld();
		TackWeld tackWeldShellLongitudinalPrimary=new TackWeld();
		float overlapEShellLongitudinalPrimary=(float)19.2;

		//Primary - SHELL weld circumferential 
		Boolean continuousCircumferentialWeldShellPrimary=false;
		Boolean continuousCircumferentialFilletWeldShellPrimary=false;
		float overlapBShellCircumferentialPrimary=(float)19.2;
		float overlapDCircumferentialShelllPrimary=(float) 12.7;
		WeldingWithBackUpBar weldingWithBackUpBarShellCircumferentialPrimary=null;
		LockWeld lockWeldCircumferentialShellPrimary=new LockWeld();
		TackWeld tackWeldShellCircumferentialPrimary=new TackWeld();
		float overlapEShellCircumferentialPrimary=(float)19.2;


		Figure8_1WeldingDetails WeldingDetailsCircumferentialPrimaryShell= new Figure8_1WeldingDetails(continuousCircumferentialWeldShellPrimary, continuousCircumferentialFilletWeldShellPrimary, overlapBShellCircumferentialPrimary, tackWeldShellCircumferentialPrimary, typeOfCircumferentialWeldPrimaryShell, overlapDCircumferentialShelllPrimary, weldingWithBackUpBarShellCircumferentialPrimary, lockWeldCircumferentialShellPrimary, overlapEShellCircumferentialPrimary);
		Figure8_1WeldingDetails WeldingDetailsLongitudinalPrimaryShell= new Figure8_1WeldingDetails(continuousLongitudinalWeldShellPrimary, continuousLongitudinalFilletWeldShellPrimary, overlapBShellLongitudinalPrimary, tackWeldShellLongitudinalPrimary, typeOfLongitudinalWeldPrimaryShell, overlapDLongitudinaShelllPrimary, weldingWithBackUpBarShellLongitudinalPrimary, lockWeldLongitudinalShellPrimary, overlapEShellLongitudinalPrimary);

		Shell primaryLayerShell = new Shell(shellThicknessPrimary, shellThicknessTolerancePrimary, WeldingDetailsLongitudinalPrimaryShell, WeldingDetailsCircumferentialPrimaryShell);

		Shell secondaryLayerShell = new Shell(shellThicknessSecondary, shellThicknessToleranceSecondary, WeldingDetailsLongitudinalSecondaryShell, WeldingDetailsCircumferentialSecondaryShell);

		float primaryLayerInsideDiameter=1000;
		float primaryLayerInsideDiameterTolerance=1;
		float primaryLayerLenght=6630;
		float primaryLayerLenghtTolerance=150;

		TypeOfLayer primaryL=TypeOfLayer.PRIMARY;

		float secondaryLayerInsideDiameter=1000;
		float secondaryLayerInsideDiameterTolerance=20;
		float secondaryLayerLenght=6700;
		float secondaryLayerLenghtTolerance=150;

		TypeOfLayer secondaryL=TypeOfLayer.SECONDARY;

		TankLayer tankSecondaryLayer =new TankLayer(secondaryLayerInsideDiameter, secondaryLayerInsideDiameterTolerance, secondaryLayerLenght, secondaryLayerLenghtTolerance, secondaryLayerShell, secondaryLeftHead, secondaryRightHead, secondaryL);

		TankLayer tankPrimaryLayer =new TankLayer(primaryLayerInsideDiameter, primaryLayerInsideDiameterTolerance, primaryLayerLenght, primaryLayerLenghtTolerance, primaryLayerShell, primaryRightHead, primaryLeftHead, primaryL);

		//general details
		double nominalCapacity=40000;
		String orderNumber="9813220540";
		String drawingsNumber="15-143 rev 0/15-139 rev 0";
		String manufacture="MATHET HASHRON";
		float maxBurialDepth=5000;
		TypeOfTank typeOfTank=TypeOfTank.TYPE_I;
		int numberOfCompartments=1;
		float declaredVentOpening=(float) 50.8;
		float degreesSecondaryWrapeAroundPrimary=290;
		boolean contactBetweenLeftHeads=true;
		boolean contactBetweenRightHeads=false;
		boolean istankHasAManhole=true;

		//ManHole
		TypeOfManHole typeOfManHole=TypeOfManHole.NUM93;
		float sizeOfManHole=(float) 609.6;
		float nominalBoltCircleDimeter=780;
		int numberOfBolts=24;
		float boltDiameter=(float) 12.7;
		float thicknessOfNeck=(float) 4.5;
		float thicknessOfBlindFlange=(float) 4.5;
		float heightOfFlangeFromTheTop=100;
		Boolean manHoleContinuousWeld=false;
		Boolean manHoleContinuousFilletWeld=true;
		TypeOfGasket typeOfGasket= TypeOfGasket.FACE;
		float thicknessOfTheGasket=(float) 3.2;

		ManHole manHoleTest = new ManHole(typeOfManHole, sizeOfManHole, nominalBoltCircleDimeter, numberOfBolts, boltDiameter, thicknessOfNeck, thicknessOfBlindFlange, heightOfFlangeFromTheTop, manHoleContinuousWeld, manHoleContinuousFilletWeld, typeOfGasket, thicknessOfTheGasket);

		Tank testTank= new Tank(nominalCapacity, orderNumber, drawingsNumber, manufacture, maxBurialDepth, declaredVentOpening, typeOfTank, contactBetweenLeftHeads, contactBetweenRightHeads, degreesSecondaryWrapeAroundPrimary, manHoleTest, istankHasAManhole, numberOfCompartments, tankPrimaryLayer, tankSecondaryLayer);
		
		//Bulkhead
		TypeOfBulkhead typeOfBulkhead = TypeOfBulkhead.NUM101;
		float thicknessOfBulkhead=(float) 6.0;
		float toleranceOfBulkheadThickness=(float) 0.10;
		int numberOfBulkheadPieces=1;
		TypeOfHead bulkheadshape= TypeOfHead.CONICAL;
		
		//Bulkhead Bracing
		TypeOfBracing typeOfBracingBulkhead=TypeOfBracing.CHANNEL;
		float distanceFromShellXBulkhead=100;
		float distanceFromTheCenterlineOfTheBulkheadY=152;
		TackWeld bracingTackWeldBulkhead=new TackWeld();
		float sectionModulusZOfBraceBulkhead=(float) 1.0;
		int upnNumberBulkhead = 100;
		
		float overLapBBulkheadAndShell= (float) 12.0;
		float disatnceCBetweenTwoShells= (float) 3.5;
		float lenghtDOfStrightFlange= (float) 60.2;
		
		Bracing bulkheaBracing = new  Bracing(typeOfBracingBulkhead, distanceFromShellXBulkhead, distanceFromTheCenterlineOfTheBulkheadY, bracingTackWeldBulkhead, sectionModulusZOfBraceBulkhead, upnNumberBulkhead);

		Type101BulkHead bulkHead = new Type101BulkHead(typeOfBulkhead, thicknessOfBulkhead, toleranceOfBulkheadThickness, numberOfBulkheadPieces, WeldingDetailsCircumferentialSecondaryShell, bulkheaBracing, bulkheadshape, overLapBBulkheadAndShell, disatnceCBetweenTwoShells, lenghtDOfStrightFlange);

		//System.out.println(testTank);
		TankController tankController = new TankController();

		//tankController.validateShellThickness(testTank);
		//tankController.validateCapacity(testTank);
		//tankController.validateRatio(testTank.getPrimaryTank());
		//tankController.validateHeadsThickness(testTank);
		//tankController.validateHeadsNumberOfPieces(testTank);
		//tankController.validateHeadWeldingDetails(testTank);
		//tankController.validateManhole(testTank);
		//tankController.validateVentSize(testTank);
		//tankController.validateDegreesSecondaryWrapeAroundPrimary(testTank);
		// tankController.validateShellsWeldingDetails(testTank);

		//tankController.validateDishedHeadDepth(testTank);
		//tankController.validateConicalHeadDepth(testTank);
		//tankController.validateHeadsBracing(testTank);
		//tankController.validateKnuckleRadius(testTank);
		//tankController.checkBulkHeadNumOfPieces(bulkHead, tankPrimaryLayer);
		//tankController.checkBulkHeadType101Dimensions (bulkHead,Utils.calculatedActualLayerDiameter(tankPrimaryLayer));
		
		
		/*UserController userController = new UserController();
		List<User> allusers = new ArrayList<>();
		allusers=userController.getAllUserByType(UserType.ENGINEER);
		for  (User user : allusers) {
		System.out.println(user);	
		}
		 */

		Testing testing= new Testing();
		 
		 
		 Type101BulkHead type101BulkHead =new Type101BulkHead();
		 
		 type101BulkHead =(Type101BulkHead) testing.getChange().get(TypeOfBulkhead.NUM101);

		 
		 
	}


}
