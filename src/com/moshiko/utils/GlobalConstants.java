package com.moshiko.utils;

public class GlobalConstants {

	//clause 5.2
	public static final double moduleOfElasticityST37 = 203448;
	public static final float  possionRatioST37 = (float) 0.287;

	public static final float  minBurialDepth  = (float) 1520;
	
	//clause 5.2.1
	public static final float  minSteelThicknessPrimaryTankPrimaryShell  = (float) 3.12;
	public static final float  minSteelThicknessSecondaryTankShell  = (float) 2.36;

	//FIGURE 11.1- manhole
	public static final float  minBoltDiameter  = (float) 12.7;
	public static final float  minGasketThickness  = (float) 3.2;
	public static final float  minManholeNeckThickness  = (float) 4.5;
	public static final float  minManholeFlangeThickness  = (float) 4.5;
	public static final int  minHeightOfManholeFlangeFromTheTop = 51;
	public static final int  minManholeBoltSpacing = 102;
	public static final float  minWeepHoleDiameter  = (float) 6.4;

	//FIGURE 9.2, FIGURE 9.1
	public static float minTackWeldSpotsLenght=(float) 25.4;
	public static float minLockWeldSpotsLenght=(float) 12.7;
	public static float minOverLapB=(float) 12.7;
	public static float minOverLapDForDiameterUpTo1200 =(float) 12.7;
	public static float minOverLapDForDiameterOver1200 =(float) 19.1;

	public static int maxWeldSegmentsSapcing= 305;
	
	//FIGURE 8.1 - E
	public static float minOverLapE=(float) (3 * 12.7);
	public static float minLenghtOfBackUpBar=(float) 38.1;
	public static float minWeldSeparation=(float) 4.0;

	//FIGURE 13.2 - Bracing
	public static int distanceFromTheCenterlineOfTheHeadY= 152;
	public static int distanceFromTheShellX= 102;

	//clause 9.3
	public static int knuckleRadiusFactorHeadsExecptInnerFlatFlange=2;
	
	//clause 15.4
	public static float knuckleRadiusFactorHeadInnerFlatFlange=(float) 1.5;

	//clause 15.3
	public static float minThicknessForRedundantBracing =(float) 7.67;
	
	//FIGURE 13.1 - Bulkheads for compartment tanks
	public static float minOverLapBBulkheadAndShell=(float) 12.7;
	public static float minDisatnceCBetweenTwoShells=(float) 19.1;
	public static float minLenghtDOfStrightFlange=(float) 31.8;
	public static short maxNumOfPiecesBulkheadsType101and102=1;

	//users
	public static final String MESSAGE = "message";
	public static final String SALT = "$2a$10$DOWSDz/CyKaJ40hslrk5fe";
	public static final String USER = "user";
	public static final String ACTIVE = "active";
	public static final String NEW = "new";
	public static final String IN_RESET_PASSWORD = "inResetPassword";
	public static final String IS_RESET_PASSWORD_VERIFIED = "isResetPasswordVerified";
	public static final String RESET_PASSWORD = "resetPassword";
	public static final String ACTIVATION = "activation";
	public static final String YES = "yes";
	public static final String USER_NAME = "userName";
}
