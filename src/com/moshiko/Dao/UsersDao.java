package com.moshiko.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.moshiko.beans.User;
import com.moshiko.enums.ErrorType;
import com.moshiko.enums.UserType;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.utils.JdbcUtils;
import com.mysql.jdbc.Statement;

public class UsersDao {


	//Default constructor
	public  UsersDao(){

	}

	//every type of user implements differently the remove method
	public  void removeUser(long userID, UserType userType) throws MyApplicationException{}


	//creating a user by a given input User Object and retrieving the UserID from the DB.
	public long createUser(User user) throws MyApplicationException {

		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		long genretedKey=0;

		try {
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();

			//creating the SQL query
			String sqlQuery = "INSERT INTO " +user.getUserType().name()+" (USER_NAME, PASSWORD, EMAIL,EMAIL_VERIFICATION_HASH,EMAIL_VERIFICATION_ATTEMPTS,STATUS) VALUES (?,?,?,?,?,?)";

			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);

			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getUserPassword());
			preparedStatement.setString(3, user.getUserEmail());
			preparedStatement.setString(4, user.getEMAIL_VERIFICATION_HASH());
			preparedStatement.setInt(5, user.getEMAIL_VERIFICATION_ATTEMPTS());
			preparedStatement.setString(6, user.getSTATUS());

			//executing the update
			preparedStatement.executeUpdate();

			//extract the new ID
			resultSet=preparedStatement.getGeneratedKeys();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()){

				genretedKey=resultSet.getLong(1);
			}
			return genretedKey;
		} 

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		catch (Exception e) {
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULD NOT CREATE THE NEW USER OF TYPE "+ user.getUserType().name()+" IN THE DB");
		} 

		finally {

			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	// update user's password and email


	public void updateUser(long userID,User updatedUser) throws MyApplicationException {

		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;


		try {
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();

			//creating the SQL query
			String sqlQuery = "UPDATE  " +updatedUser.getUserType().name()+"  SET PASSWORD=?,EMAIL=? WHERE USERID= ?";

			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sqlQuery);

			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setString(1, updatedUser.getUserPassword());
			preparedStatement.setString(2, updatedUser.getUserEmail());
			preparedStatement.setLong(3, userID);

			//executing the update
			preparedStatement.executeUpdate();

		} 

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		catch (Exception e) {
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULD NOT UPDATE USER FOR USERID: \"" + userID +
					"\" FROM THE DB");
		} 

		finally {

			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}	

	}

	//Extract user by ID

	public User getUser(long userID, UserType userType) throws MyApplicationException {

		//creating the resources
		User requiredUser = new User();

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();

			String sqlQuery = "SELECT * FROM  " +userType.name()+"  WHERE USERID=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setLong(1, userID);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){

				return null;
			}

			requiredUser = extractDataFromTableToUser(resultSet);

			return requiredUser;

		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIVE THE USER'S OF TYPE "+ userType.name()+" BY THE USERID: \"" + userID+"\"");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}

	}

	//utility method for extracting the user's data and placing it in the USER Object

	private User extractDataFromTableToUser(ResultSet resultSet) throws MyApplicationException{
		User user=new User();
		try{

			//must be as written in the columns in the DB

			user.setUserEmail(resultSet.getString("EMAIL"));
			user.setUserID(resultSet.getLong("USERID"));
			user.setUserPassword(resultSet.getString("PASSWORD"));
			user.setUserName(resultSet.getString("USER_NAME"));
			user.setEMAIL_VERIFICATION_ATTEMPTS(resultSet.getInt("EMAIL_VERIFICATION_ATTEMPTS"));
			user.setSTATUS(resultSet.getString("STATUS"));
			user.setEMAIL_VERIFICATION_HASH("not visible");
			return user;

		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULD NOT EXTRACT USER'S DATA OF TYPE "+ user.getUserType().name()+" FROM RESULTSET OBJECT");
		}

	}

	//Extract all users by type

	public List<User> getAllUserByType(UserType userType) throws MyApplicationException {

		//creating the resources
		List <User> allUsersByType= new ArrayList<>();

		User requiredUser = new User();

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();


			String sqlQuery = "SELECT * FROM users."+ userType.name();

			preparedStatment = connection.prepareStatement(sqlQuery);

			resultSet = preparedStatment.executeQuery();
			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward

			while (resultSet.next()){

				requiredUser=extractDataFromTableToUser(resultSet);
				requiredUser.setUserType(userType);

				allUsersByType.add(requiredUser);
			}

			return allUsersByType ;

		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIVE THE USERS OF TYPE "+ userType.name()+" FROM THE DB");
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}

	}

	// checks if a user exists by given name and password

	public boolean isUserPassowrdAndNameMatchToDB(String userName, String password, UserType userType) throws MyApplicationException {

		//creating the resources

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();

			String sqlQuery = "SELECT * FROM  " + userType.name() +"  WHERE USER_NAME=? AND PASSWORD=?";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setString(1, userName);
			preparedStatment.setString(2, password);


			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){

				return false;
			}


		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIVE USER'S DATA OF TYPE "+ userType.name());
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}

		return true;

	}

	//checking if a user exists by given user's name


	public boolean isUserNameExists(String userName, UserType userType) throws MyApplicationException {

		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();

			String sqlQuery = "SELECT * FROM  " +userType.name()+"  WHERE USER_NAME=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setString(1, userName);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward

			if (resultSet.next()==false){

				return false;
			}


		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIVE USERS'S DATA OF TYPE "+ userType.name() );
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}
		return true;

	}

	//checking if a user exists by given userID


	public boolean isUserIDExists(Long userID, UserType userType) throws MyApplicationException {

		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();

			String sqlQuery = "SELECT * FROM  "+userType.name()+"  WHERE USERID=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setLong(1, userID);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward

			if (resultSet.next()==false){

				return false;
			}


		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIVE USER'S DATA OF TYPE "+ userType.name()+" " );
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}
		return true;

	}

	//Retrieve user's ID by given user's name


	public long getUserIDByName(String userName, UserType userType) throws MyApplicationException {

		long userID;

		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try{

			connection = JdbcUtils.getConnection();

			String sqlQuery = "SELECT USERID FROM  " +userType.name()+"  WHERE USER_NAME=? ";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setString(1, userName);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward

			if (resultSet.next()==false){

				throw new MyApplicationException("getUserIDByName EXCEPTION:THE USER NAME OF TYPE "+ userType.name()+" DOES NOT EXIST",ErrorType.USER_CARITARISTICS_ERROR);
			}

			userID=resultSet.getLong("USERID");

		}catch (Exception e){

			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIVE COMPANY'S DATA" );
		}

		finally{

			JdbcUtils.closeResources(connection, preparedStatment, resultSet);
		}


		return userID;
	}

	//verify the mail response from the user by comparing the hashActivation
	//for the response to the one in the data base


	public boolean verifyEmailHash(Long userID, String hash, UserType userType) throws MyApplicationException
	{
		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		boolean verified = false;

		try {

			connection = JdbcUtils.getConnection();

			String sqlQuery = "SELECT * FROM  " +userType.name()+"  WHERE USERID = ? and EMAIL_VERIFICATION_HASH = ?";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setLong(1, userID);
			preparedStatment.setString(2, hash);

			//executing the update
			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==true){

				verified=true;
			}
			return verified;
		} 

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		catch (Exception e) {
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULD NOT VERIFY THE USER OF TYPE "+ userType.name()+" BY USERID: \"" + userID +
					"\" IN THE DB");
		} 

		finally {

			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatment);
		}	

	}

	//update the account status for given userID.


	public void updateStatus(Long userID, String status, UserType userType) throws MyApplicationException {

		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;


		try {

			connection = JdbcUtils.getConnection();

			String sqlQuery = "UPDATE " +userType.name()+" SET STATUS = ? where USERID = ?";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setString(1, status);
			preparedStatment.setLong(2, userID);

			//executing the update
			preparedStatment.executeUpdate();

		} 

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		catch (Exception e) {
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT RETRIVE THE USER OF TYPE "+ userType.name()+" EMAIL_VERIFICATION_ATTEMPTS BY USERID: \"" + userID +
					"\" IN THE DB");
		} 

		finally {

			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatment);
		}	

	}

	// get the verification status of account by a given userID


	public String getVerificationStatus(long userID, UserType userType) throws MyApplicationException{

		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try {

			connection = JdbcUtils.getConnection();

			String sqlQuery = "SELECT STATUS FROM " +userType.name()+" WHERE USERID=?";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setLong(1, userID);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){

				return "NEW";
			}
			return resultSet.getString(1);

		} 

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		catch (Exception e) {
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULD NOT RETRIVE THE USE OF TYPE "+ userType.name()+" STATUS BY USERID: \"" + userID +
					"\" IN THE DB");
		} 

		finally {

			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatment);
		}	

	}

	// get the number of verification attempts of account by a given userID


	public int getVerficationAttempts(long userID, UserType userType) throws MyApplicationException{

		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;

		//for extracting
		ResultSet resultSet = null;

		try {

			connection = JdbcUtils.getConnection();

			String sqlQuery = "SELECT EMAIL_VERIFICATION_ATTEMPTS FROM " +userType.name()+" WHERE USERID=?";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setLong(1, userID);

			resultSet = preparedStatment.executeQuery();

			//check if i get values from DB , if not =false --> null, if yes has value and cursor move forward
			if (resultSet.next()==false){

				return -1;
			}
			return resultSet.getInt(1);

		} 

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		catch (Exception e) {
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULD NOT RETRIVE THE USER OF TYPE "+ userType.name()+" EMAIL_VERIFICATION_ATTEMPTS BY USERID: \"" + userID +
					"\" IN THE DB");
		} 

		finally {

			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatment);
		}	

	}

	//increment the number of attempts of the user trying to verify his account

	public void incrementVerificationAttempts(long userID, UserType userType) throws MyApplicationException{

		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;


		try {

			connection = JdbcUtils.getConnection();

			String sqlQuery = "UPDATE " +userType.name()+" SET EMAIL_VERIFICATION_ATTEMPTS = EMAIL_VERIFICATION_ATTEMPTS + 1 where USERID=?";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setLong(1, userID);

			//executing the update
			preparedStatment.executeUpdate();

		} 

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		catch (Exception e) {
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULD NOT UPDATE THE USER OF TYPE "+ userType.name()+" EMAIL_VERIFICATION_ATTEMPTS BY USERID: \"" + userID +
					"\" IN THE DB");
		} 

		finally {

			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatment);
		}	

	}

	//	update the registration parameters for given userID & hash & number of attempts

	public void updateEmailVerificationHash(Long userID, String hash,int attempts, UserType userType) throws MyApplicationException  {

		//creating the resources	

		PreparedStatement preparedStatment = null;

		Connection connection = null;


		try {

			connection = JdbcUtils.getConnection();

			String sqlQuery = "UPDATE " +userType.name()+" SET EMAIL_VERIFICATION_HASH = ?, EMAIL_VERIFICATION_ATTEMPTS = ? WHERE USERID = ?";

			preparedStatment = connection.prepareStatement(sqlQuery);

			preparedStatment.setString(1, hash);
			preparedStatment.setInt(2, attempts);
			preparedStatment.setLong(3, userID);

			//executing the update
			preparedStatment.executeUpdate();

		} 

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		catch (Exception e) {
			throw new MyApplicationException(e, ErrorType.GENERAL_ERROR,"COULD NOT UPDATE THE USER OF TYPE "+ userType.name()+" VERIFICATION BY USERID: \"" + userID +
					"\" IN THE DB");
		} 

		finally {

			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatment);
		}	

	}

}

