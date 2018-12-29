package com.moshiko.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moshiko.beans.HeadJointsRequirements;
import com.moshiko.beans.JointsRequirements;
import com.moshiko.beans.ShellJointRequirements;
import com.moshiko.beans.Tank;
import com.moshiko.beans.TankLayer;
import com.moshiko.enums.ErrorType;
import com.moshiko.enums.TypeOfHead;
import com.moshiko.enums.TypeOfBracing;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.utils.JdbcUtils;

public class Ul58Dao  {

	//get table 5.1 relevant requirements for head thickness

	public float getRequiredHeadThicknessTable7_1(float actualDiameter) throws MyApplicationException, SQLException{


		//creating the resources

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();
			String sqlQuery;

			sqlQuery = "SELECT * FROM TABLE7_1 WHERE MINDIAMETER<=? AND MAXDIAMETER>=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setFloat(1,actualDiameter);
			preparedStatment.setFloat(2, actualDiameter);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){
				return 0;			}
			return resultSet.getFloat("MINTHICKNESS");
		}catch (Exception e){
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIEVE HEAD THICKNESS FOR TANK DIM.: \"" + actualDiameter +"\"");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}


	}

	public float getRequiredHeadThicknessTable7_2(float actualDiameter) throws MyApplicationException, SQLException{


		//creating the resources

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();
			String sqlQuery;

			sqlQuery = "SELECT * FROM TABLE7_2 WHERE MINDIAMETER<=? AND MAXDIAMETER>=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setFloat(1, actualDiameter);
			preparedStatment.setFloat(2, actualDiameter);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){
				return 0;			}
			return resultSet.getFloat("MINTHICKNESS");
		}catch (Exception e){
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIEVE HEAD THICKNESS FOR TANK DIM.: \"" + actualDiameter+"\"");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}


	}

	public float getRequiredDishedHeadDepth(float actualDiameter) throws MyApplicationException, SQLException{


		//creating the resources

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();
			String sqlQuery;

			sqlQuery = "SELECT * FROM table9_1 WHERE MINDIAMETER<=? AND MAXDIAMETER>=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setFloat(1, actualDiameter);
			preparedStatment.setFloat(2, actualDiameter);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){
				return 0;			}
			return resultSet.getFloat("MINDISH(MM)");

		}catch (Exception e){
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIEVE MIN. DISH Height FOR TANK DIM.: \"" + actualDiameter+"\"");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}


	}

	public float getRequiredVentSize(double calculatedCapacity) throws MyApplicationException, SQLException{


		//creating the resources

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();
			String sqlQuery;

			sqlQuery = "SELECT * FROM table10_2 WHERE MINCAPACITY<=? AND MAXCAPACITY>=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setDouble(1, calculatedCapacity);
			preparedStatment.setDouble(2, calculatedCapacity);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){
				return 0;			
			}
			return resultSet.getFloat("NOMINAL_PIPE_SIZE(MM)");

		}catch (Exception e){
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIEVE MIN. VENT SIZE FOR ACTUAL CALCULATED CAPACITY: \"" + calculatedCapacity+"\"[liter]");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}


	}

	public HeadJointsRequirements getHeadJointsRequirements(String weldJointNumber) throws MyApplicationException, SQLException{


		//creating the resources

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();
			String sqlQuery;

			sqlQuery = "SELECT * FROM figure9_1 WHERE jointNum=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setString(1, weldJointNumber);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){
				return null;			}
			return extractHeadJointRequirementsFromTable(resultSet);

		}catch (Exception e){
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIEVE head Joint Requirements for weld num: \"" + weldJointNumber +"\"");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}


	}

	public HeadJointsRequirements extractHeadJointRequirementsFromTable(ResultSet resultSet) throws MyApplicationException{

		HeadJointsRequirements headJointsRequirements=new HeadJointsRequirements();

		try{

			headJointsRequirements.setIsContinuousWeldRequired(resultSet.getBoolean("isContinuousWeldRequired"));
			headJointsRequirements.setIsContinuousFilletWeldRequired(resultSet.getBoolean("isContinuousFilletWeldRequired"));
			headJointsRequirements.setIsFlangeRequired(resultSet.getBoolean("isFlangeRequired"));
			headJointsRequirements.setIsOverlapBRequired(resultSet.getBoolean("isOverlapBRequired"));
			headJointsRequirements.setIsTackWeldRequired(resultSet.getBoolean("isTackWeldRequired"));
			headJointsRequirements.setIsWeldTipRequired(resultSet.getBoolean("isWeldTipRequired"));


			return headJointsRequirements;

		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULDN'T RETRIEVE head joints Requirements DATA FROM RESULTSET OBJECT");
		}

	}

	public ShellJointRequirements getShellJointsRequirements(String weldJointNumber) throws MyApplicationException, SQLException{


		//creating the resources

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();

			String sqlQuery;

			sqlQuery = "SELECT * FROM figure8_1 WHERE jointNum=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);


			preparedStatment.setString(1, weldJointNumber);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){
				return null;			}
			return extractShellJointRequirementsFromTable(resultSet);

		}catch (Exception e){
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIEVE Shell Joint Requirements for weld num: \"" + weldJointNumber +"\"");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}


	}


	public ShellJointRequirements extractShellJointRequirementsFromTable(ResultSet resultSet) throws MyApplicationException{

		ShellJointRequirements shellJointsRequirements=new ShellJointRequirements();

		try{

			shellJointsRequirements.setIsContinuousWeldRequired(resultSet.getBoolean("isContinuousWeldRequired"));
			shellJointsRequirements.setIsContinuousFilletWeldRequired(resultSet.getBoolean("isContinuousFilletWeldRequired"));
			shellJointsRequirements.setIsOverlapDRequired(resultSet.getBoolean("isOverlapDRequired"));
			shellJointsRequirements.setIsOverlapBRequired(resultSet.getBoolean("isOverlapBRequired"));
			shellJointsRequirements.setIsTackWeldRequired(resultSet.getBoolean("isTackWeldRequired"));
			shellJointsRequirements.setIsBackUpBarRequired(resultSet.getBoolean("isBackUpBarRequired"));
			shellJointsRequirements.setIsLockWeldRequired(resultSet.getBoolean("isLockWeldRequired"));
			shellJointsRequirements.setIsOverlapERequired(resultSet.getBoolean("isOverlapERequired"));

			return shellJointsRequirements;

		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULDN'T RETRIEVE shell joints Requirements DATA FROM RESULTSET OBJECT");
		}

	}

	//get table13.1- Surface bracing for flanged flat bulkheads//inner head no contact

	public float getRequiredBracingTable13_1(double actualDiameter,  TypeOfBracing typeOfBracing) throws MyApplicationException, SQLException{


		//creating the resources

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();
			String sqlQuery;

			sqlQuery = "SELECT * FROM TABLE13_1 WHERE MINDIAMETER<=? AND MAXDIAMETER>=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setDouble(1,actualDiameter);
			preparedStatment.setDouble(2, actualDiameter);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){
				return 0;			
			}
			if (typeOfBracing.equals(TypeOfBracing.CHANNEL)){
				return resultSet.getFloat("CHANNELS SECTION MODULUS(INCH^3)");
			}else{			
				return resultSet.getFloat("I-BEAMS SECTION MODULUS(INCH^3)");
			}

		}catch (Exception e){
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIEVE BRACING FOR TANK ACTUAL CALCULATED CAPACITY: \"" + actualDiameter +"\"[liter]");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}


	}
}
