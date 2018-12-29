package com.moshiko.utils;

import java.lang.Math;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.moshiko.Dao.Ul58Dao;
import com.moshiko.beans.BulkHead;
import com.moshiko.beans.Figure8_1WeldingDetails;
import com.moshiko.beans.Figure9_1WeldingDetails;
import com.moshiko.beans.HeadJointsRequirements;
import com.moshiko.beans.Head;
import com.moshiko.beans.Shell;
import com.moshiko.beans.ManHole;
import com.moshiko.beans.ShellJointRequirements;
import com.moshiko.beans.Tank;
import com.moshiko.beans.TankLayer;
import com.moshiko.enums.ErrorType;
import com.moshiko.enums.HeadSide;
import com.moshiko.enums.TypeOfGasket;
import com.moshiko.enums.TypeOfManHole;
import com.moshiko.enums.LogType;
import com.moshiko.exceptions.MyApplicationException;

public class Utils {

	private static final char[] symbols;

	//a utility method that checks if the String id NULL
	public static boolean isTheValueEqualsToNull(Object object) throws MyApplicationException {

		if (object!=null){
			return false;
		}
		else throw new MyApplicationException("isTheValueEqualsToNull EXCEPTION, THE OBJECT EQUAL TO NULL", ErrorType.GENERAL_ERROR);

	}


	public static float calculatedRorakPressureTypeI(Shell secondaryShell,TankLayer tankLayerPrimary){

		float radius =tankLayerPrimary.getLayerInsideDiameter()/2;
		float lenght =tankLayerPrimary.getLayerLength();
		float toleranceDiameter=tankLayerPrimary.getLayerInsideDiameterTolerance();
		float toleranceLenght=tankLayerPrimary.getLayerLengthTolerance();
		float actualLenght=toleranceLenght+lenght;
		float actualRadius=toleranceDiameter/2+radius;

		System.out.println("actual Lenght: "+actualLenght);
		System.out.println("actual Raduis: "+actualRadius);
		System.out.println("Possion Ratio ST37: "+GlobalConstants.possionRatioST37);
		System.out.println("module Of Elasticity ST37: "+GlobalConstants.moduleOfElasticityST37);

		float actualThicknessOfSecondaryTank=secondaryShell.getShellThickness()-secondaryShell.getShellThicknessTolerance();
		float actualThicknessOfPrimaryTank=tankLayerPrimary.getLayerShell().getShellThickness()-tankLayerPrimary.getLayerShell().getShellThicknessTolerance();

		float EquivilateThickness=(float) Math.pow(Math.pow(actualThicknessOfSecondaryTank, 2.5)+Math.pow(actualThicknessOfPrimaryTank, 2.5),0.4);

		System.out.println("Equivilate Thickness of TYPE I tank as defined in clause 5.2: "+EquivilateThickness);


		float rorakPressure = (float) ((0.807 * GlobalConstants.moduleOfElasticityST37 * Math.pow(EquivilateThickness, 2))/(actualLenght*actualRadius)*Math.pow(((Math.pow((1-Math.pow(GlobalConstants.possionRatioST37,2)),-3) * Math.pow((EquivilateThickness/actualRadius),2))),0.25));

		System.out.println("rorak Pressure of TYPE I tank as defined in clause 5.2: "+rorakPressure);

		return rorakPressure;

	}

	public static float calculatedRorakPressureTypeII(TankLayer tankLayer){


		float actualLenght=tankLayer.getLayerLengthTolerance()+tankLayer.getLayerLength();
		float actualRadius=(tankLayer.getLayerInsideDiameterTolerance()+tankLayer.getLayerInsideDiameter())/2;;

		System.out.println("actual Lenght of tank's layer: "+actualLenght);
		System.out.println("actual Raduis Primary tank: "+actualRadius);

		System.out.println("Possion Ratio ST37: " + GlobalConstants.possionRatioST37);
		System.out.println("Module Of Elasticity ST37: " + GlobalConstants.moduleOfElasticityST37);

		float actualThicknessOfTankLayer= tankLayer.getLayerShell().getShellThickness() - tankLayer.getLayerShell().getShellThicknessTolerance();

		float rorakPressureTankLayer = (float) ((0.807 * GlobalConstants.moduleOfElasticityST37 * Math.pow(actualThicknessOfTankLayer, 2))/(actualLenght*actualRadius)*Math.pow(((Math.pow((1-Math.pow(GlobalConstants.possionRatioST37,2)),-3) * Math.pow((actualThicknessOfTankLayer/actualRadius),2))),0.25));

		System.out.println("rorak Pressure of tank's layer of TYPE II  as defined in clause 5.2: "+rorakPressureTankLayer);

		return rorakPressureTankLayer;

	}

	public static float calculatedBurialPressureTypeI(float maxBurialDepth,Shell secondaryShell,TankLayer tankLayerPrimary){

		float actualBurialPressure;

		float actualThicknessOfSecondaryTank=secondaryShell.getShellThickness()-secondaryShell.getShellThicknessTolerance();
		float actualThicknessOfPrimaryTank=tankLayerPrimary.getLayerShell().getShellThickness()-tankLayerPrimary.getLayerShell().getShellThicknessTolerance();
		float actualInsideDiameter=tankLayerPrimary.getLayerInsideDiameter()+ tankLayerPrimary.getLayerInsideDiameterTolerance();
		float burialDepth=Math.max(GlobalConstants.minBurialDepth, maxBurialDepth);
		actualBurialPressure=(float) ((actualInsideDiameter+2*actualThicknessOfPrimaryTank+burialDepth+2*actualThicknessOfSecondaryTank) * 9.8227 * Math.pow(10, -6));

		System.out.println("actual Burial Pressure Pressure of of type I tank as defined in clause 5.2: "+ actualBurialPressure);

		return actualBurialPressure;


	}

	public static float calculatedBurialPressureTypeII(float maxBurialDepth,TankLayer tankLayer){

		float actualBurialPressure;

		Shell layerShell = tankLayer.getLayerShell();
		float actualThicknessOfLayerTank=layerShell.getShellThickness()-layerShell.getShellThicknessTolerance();
		float actualInsideDiameter=tankLayer.getLayerInsideDiameter()+ tankLayer.getLayerInsideDiameterTolerance();
		float burialDepth=Math.max(GlobalConstants.minBurialDepth, maxBurialDepth);
		actualBurialPressure=(float) ((actualInsideDiameter+2*actualThicknessOfLayerTank+burialDepth) * 9.8227 * Math.pow(10, -6));

		System.out.println("actual Burial Pressure Pressure of type II tank as defined in clause 5.2: "+ actualBurialPressure);

		return actualBurialPressure;


	}



	public static float calculatedActualLayerLenght(TankLayer tankLayer){

		float actualLenght = tankLayer.getLayerLength() + tankLayer.getLayerLengthTolerance();

		return actualLenght;

	}

	public static float calculatedActualLayerDiameter(TankLayer tankLayer){

		float actualDiameter = tankLayer.getLayerInsideDiameter() + tankLayer.getLayerInsideDiameterTolerance();

		return actualDiameter;

	}

	public static float calculatedActualShellThickness(TankLayer tankLayer){

		float actualShellThickness = tankLayer.getLayerShell().getShellThickness() - tankLayer.getLayerShell().getShellThicknessTolerance();

		return actualShellThickness;

	}

	public static float calculatedActualHeadThickness(Head layerHead){

		float actualDeclaredHeadThickness = 0;

		actualDeclaredHeadThickness = layerHead.getHeadThickness() - layerHead.getHeadThicknessTolerance();

		return actualDeclaredHeadThickness;

	}

	public static float calculatedActualBulkHeadThickness(BulkHead bulkHead){

		float actualDeclaredBulkHeadThickness = 0;

		actualDeclaredBulkHeadThickness = bulkHead.getThicknessOfBulkhead() - bulkHead.getToleranceOfBulkheadThickness();

		return actualDeclaredBulkHeadThickness;

	}

	static {
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch)
			tmp.append(ch);
		for (char ch = 'a'; ch <= 'z'; ++ch)
			tmp.append(ch);
		symbols = tmp.toString().toCharArray();
	}   
	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */

	private static final Random random = new Random();


	public static Boolean PatternValidator(final String hex, String patternToCompare)
	{
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(patternToCompare);
		matcher = pattern.matcher(hex);

		return matcher.matches();

	}

	//an utility method that Parse a given input string into a date defined format
	public static Date parsingStringToDate(String stringDate,String stringFormat) throws MyApplicationException {
		try{	
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(stringFormat);

			//check if the date is according to calendar
			simpleDateFormat.setLenient(false);

			Date parseDate = simpleDateFormat.parse(stringDate);
			return parseDate;
		}

		catch (ParseException e) {
			throw new MyApplicationException(e, ErrorType.PARSE_ERROR, "parsingStringToDate EXCEPTION,COULD NOT PARSE THE DATE");
		}
	}

	//an utility method that format a date defined to a string 
	public static String FormatDateToString(Date date,String stringFormat)  {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(stringFormat);

		simpleDateFormat.setLenient(false);

		String stringDate = simpleDateFormat.format(date);
		return stringDate;

	}



	public static boolean isLoggedUserOrAdmin(String loggedUserType,String requestedInformationAboutUserType,Long loggedID,Long requestedInformationAboutUserID) 
	{
		if (Utils.isAdmin(loggedUserType)==true)
		{
			return true;
		}
		else{ if(!loggedUserType.equals(requestedInformationAboutUserType)||!(loggedID==requestedInformationAboutUserID))
		{	
			return false;
		}

		else{return true;} 
		}
	}

	//a utility method that checks if the USER type is ADMIN

	public static boolean isAdmin(String loggedUserType) {

		if (loggedUserType.equals("ADMIN")){
			return true;
		}
		else return false;
	}

	public static String prepareRandomString(int lenght) {
		char[] buf = new char[lenght];
		for (int idx = 0; idx < buf.length; ++idx) 
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}	

	public static String fileNameByTankProperties(Tank tank) {

		String fileName=tank.getOrderNumber()+" DWG. "+tank.getDrawingsNumber()+".txt";

		return fileName;
	}

	public static void  printToConsoleAndLog(String fileNameByTankProperties, String string,LogType logType) throws MyApplicationException {

		System.out.println(string);

		Logger.getInstance().logToFile(fileNameByTankProperties,string,logType);	

	}
}