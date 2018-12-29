package com.moshiko.logic;

import java.util.List;

import com.moshiko.Dao.UsersDao;
import com.moshiko.beans.User;
import com.moshiko.enums.ErrorType;
import com.moshiko.enums.UserType;
import com.moshiko.enums.LogType;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.utils.DaoFactory;
import com.moshiko.utils.Logger;
import com.moshiko.utils.Utils;

public class UserController {
	private static final String PASSWORD_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private UsersDao userDao = new UsersDao();

	//default constructor
	public UserController() {

	}

	//every type of user implements differently the remove method
	//a method that remove the chosen user data from the sql data base, by a given input UserID

	public void removeUser(long userID,UserType userType) throws MyApplicationException{
		
		//Design pattern FACTORY
		UsersDao removedUserDao = DaoFactory.get(userType);

		if(removedUserDao==null){				
			throw new MyApplicationException("removeUser EXCEPTION: NO SUCH USER TYPE EXSITS", ErrorType.USER_CARITARISTICS_ERROR);
		}
		//check if the chosen user's ID exists
		if (removedUserDao.isUserIDExists(userID,userType)==true){

			//log the removed chosen user in the log file
			Logger.getInstance().logToFile("logging.txt",userDao.getUser(userID, userType).toString(),LogType.ADMIN_REMOVE);	

			removedUserDao.removeUser(userID, userType);
		}
		else{
			throw new MyApplicationException("removeUser EXCEPTION,THE CHOSEN USER'S ID: \""+userID+"\" DOES NOT EXIST", ErrorType.USER_CARITARISTICS_ERROR);
		}

	}

	//a method of creating a user in the sql data base by a given input User Object

	public long createUser(User user) throws MyApplicationException {

		//validator will throw an exception if something is not valid.

		createUserValidator(user);

		return userDao.createUser(user);

	}

	// a validation method that prevent invalid parameters from been inserted into the data base

	private void createUserValidator(User user) throws MyApplicationException{


		// user's name is null validation
		if (Utils.isTheValueEqualsToNull(user.getUserName())==true){
			throw new MyApplicationException("createUserValidator EXCEPTION:THE USER NAME EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}

		//user's name existence in DB
		if (userDao.isUserNameExists(user.getUserName(),user.getUserType())==true){
			throw new MyApplicationException("createUserValidator EXCEPTION,THE NAME OF USER: \""+user.getUserName()+"\" EXISTS IN THE DATA BASE", ErrorType.USER_CARITARISTICS_ERROR);
		}
		// Password is null validation
		if (Utils.isTheValueEqualsToNull(user.getUserPassword())==true){
			throw new MyApplicationException("createUserValidator EXCEPTION:THE PASSWORD EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}
		//Password Pattern validation
		if(Utils.PatternValidator(user.getUserPassword(),PASSWORD_PATTERN)==false){
			throw new MyApplicationException("createUser EXCEPTION,THE USER'S PASSWORD: \""+user.getUserPassword()+"\" THAT WAS PROVIDED IS INVALID", ErrorType.USER_CARITARISTICS_ERROR);
		}

		//Email Pattern validation
		if(Utils.PatternValidator(user.getUserEmail(),EMAIL_PATTERN)==false){
			throw new MyApplicationException("createUser EXCEPTION,THE USER'S E-MAIL ADDRESS: \""+user.getUserEmail()+"\" THAT WAS PROVIDED IS INVALID", ErrorType.USER_CARITARISTICS_ERROR);
		}

		// hashActivationCode equals to null validation
		if (Utils.isTheValueEqualsToNull(user.getEMAIL_VERIFICATION_HASH())==true ){
			throw new MyApplicationException("CreateUser EXCEPTION:THE ACTIVATION HASH CODE OF USER EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}

	}

	//a method that update the user data(email) in the sql data base, by a given input UserID
	
	public void updateUser(long userID, User user) throws MyApplicationException {

		//check if there is a User that has the supplied userID,if not throws an exception
		if (userDao.isUserIDExists(userID,user.getUserType())==true){

			//Password Pattern validation
			if(Utils.PatternValidator(user.getUserPassword(),PASSWORD_PATTERN)==false){
				throw new MyApplicationException("updateUser EXCEPTION,THE USER'S PASSWORD: \""+user.getUserPassword()+"\" THAT WAS PROVIDED IS INVALID", ErrorType.USER_CARITARISTICS_ERROR);
			}

			//Email Pattern validation
			if(Utils.PatternValidator(user.getUserEmail(),EMAIL_PATTERN)==false){
				throw new MyApplicationException("updateUser EXCEPTION,THE USER'S E-MAIL ADDRESS: \""+user.getUserEmail()+"\" THAT WAS PROVIDED IS INVALID", ErrorType.USER_CARITARISTICS_ERROR);
			}

			userDao.updateUser(userID,user);
		}
		else{
			throw new MyApplicationException("updateUser EXCEPTION,THE USER'S ID: \""+userID+"\" DOES NOT EXIST", ErrorType.USER_CARITARISTICS_ERROR);
		}		
	}

	//a method that return the user data from the sql data base, by a given input UserID
	
	public User getUser(long userID,UserType userType) throws MyApplicationException {

		//check if there is a user that has the supplied userID, if yes then retrieve the company
		if (userDao.isUserIDExists(userID,userType)==true){
			return userDao.getUser(userID,userType);
		}
		else{
			throw new MyApplicationException("getUser EXCEPTION,THE USER'S ID: \""+userID+"\" DOES NOT EXIST", ErrorType.USER_CARITARISTICS_ERROR);
		}
	}

	//a method that return all the users data from the sql data base by type.
	public List<User> getAllUserByType(UserType userType) throws MyApplicationException {
		return userDao.getAllUserByType(userType);
	}

	//method that validate the given userName&password and then try to login to the system
	public boolean isUserPassowrdAndNameMatchToDB(String userName, String password,UserType userType) throws MyApplicationException {


		// user's name is null validation
		if (Utils.isTheValueEqualsToNull(userName)==true){
			throw new MyApplicationException("UserlogIntoSystem EXCEPTION:THE USER NAME EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}

		if (userDao.isUserNameExists(userName,userType)==false){
			throw new MyApplicationException("UserlogIntoSystem EXCEPTION,THE USER: \""+userName+"\" IS NOT REGISTERED", ErrorType.USER_CARITARISTICS_ERROR);
		}

		// Password is null validation
		if (Utils.isTheValueEqualsToNull(password)==true){
			throw new MyApplicationException("UserlogIntoSystem EXCEPTION:THE PASSWORD EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}

		//Password Pattern validation
		if(Utils.PatternValidator(password,PASSWORD_PATTERN)==false){
			throw new MyApplicationException("UserlogIntoSystem EXCEPTION,THE PASSWORD: \""+password+"\" THAT WAS PROVIDED IS INVALID", ErrorType.USER_CARITARISTICS_ERROR);
		}

		//check if the user's password matches
		if (userDao.isUserPassowrdAndNameMatchToDB(userName, password,userType)==true){
			return true;
		}
		else{ 

			return false;

		}
	}

	// a validation method that prevent invalid parameters from been inserted into the data base
	public long getUserIDByName(String userName,UserType userType) throws MyApplicationException {


		// user's name is null validation
		if (Utils.isTheValueEqualsToNull(userName) ==true){
			throw new MyApplicationException("getUserIDByName EXCEPTION:THE USER NAME EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}

		//user's name existence in DB
		if (userDao.isUserNameExists(userName,userType)==true){
			return userDao.getUserIDByName(userName,userType);
		}

		else{	
			throw new MyApplicationException("getUserIDByName EXCEPTION,THE NAME OF USER: \" "+userName+" \"DOESNT EXISTS IN THE DATA BASE", ErrorType.USER_CARITARISTICS_ERROR);
		}

	}

	//verify the mail response from the user by comparing the hashActivation
	//for the response to the one in the data base.

	public boolean verifyEmailHash(Long userID, String hash, UserType userType) throws MyApplicationException
	{
		if (Utils.isTheValueEqualsToNull(hash) ==true){
			throw new MyApplicationException("verifyEmailHash EXCEPTION:THE HASH CODE EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}

		//user's ID existence in DB
		if (userDao.isUserIDExists(userID,userType)==true){
			return userDao.verifyEmailHash(userID,hash,userType);
		}

		else{	
			throw new MyApplicationException("verifyEmailHash EXCEPTION,THE ID OF USER: \" "+userID+" \"DOESNT EXISTS IN THE DATA BASE", ErrorType.USER_CARITARISTICS_ERROR);
		}
	}

	//	update the registration parameters for given userID & hash & number of attempts

	public void updateEmailVerificationHash(Long userID, String hash,int attempts,UserType userType) throws MyApplicationException  {


		if (attempts<0 ){
			throw new MyApplicationException("updateEmailVerificationHash EXCEPTION:THE NUMBER of ATTEMPTS IS INVALID",ErrorType.USER_CARITARISTICS_ERROR);
		}
		if (Utils.isTheValueEqualsToNull(hash) ==true){
			throw new MyApplicationException("updateEmailVerificationHash EXCEPTION:THE HASH CODE EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}

		//user's ID existence in DB
		if (userDao.isUserIDExists(userID, userType)==true){
			userDao.updateEmailVerificationHash(userID,hash,attempts,userType);
		}

		else{	
			throw new MyApplicationException("updateEmailVerificationHash EXCEPTION,THE ID OF USER: \" "+userID+" \"DOESNT EXISTS IN THE DATA BASE", ErrorType.USER_CARITARISTICS_ERROR);
		}
	}

	//update the account status for given userID.

	public void updateStatus(Long userID, String status,UserType userType) throws MyApplicationException
	{
		if (Utils.isTheValueEqualsToNull(status) ==true){
			
			throw new MyApplicationException("updateStatus EXCEPTION:THE STATUS EQUALS TO NULL",ErrorType.USER_CARITARISTICS_ERROR);
		}

		//user's ID existence in DB
		if (userDao.isUserIDExists(userID,userType)==true){
			
			userDao.updateStatus(userID,status,userType);
		}

		else{	
			throw new MyApplicationException("updateStatus EXCEPTION,THE ID OF USER: \" "+userID+" \"DOESNT EXISTS IN THE DATA BASE", ErrorType.USER_CARITARISTICS_ERROR);
		}
	}

	public String getVerificationStatus(long userID, UserType userType) throws MyApplicationException{


		//user's ID existence in DB
		if (userDao.isUserIDExists(userID,userType)==true){
			
			return userDao.getVerificationStatus(userID,userType);
		}

		else{	
			throw new MyApplicationException("getVerificationStatus EXCEPTION,THE ID OFUSER: \" "+userID+" \"DOESNT EXISTS IN THE DATA BASE", ErrorType.USER_CARITARISTICS_ERROR);
		}
	}

	// get the number of verification attempts of account by a given userID & type

	public int getVerficationAttempts(long userID, UserType userType) throws MyApplicationException{

		//user's ID existence in DB
		if (userDao.isUserIDExists(userID,userType)==true){
			
			return userDao.getVerficationAttempts(userID,userType);
		}

		else{	
			throw new MyApplicationException("getVerficationAttempts EXCEPTION,THE ID OF USER: \" "+userID+" \"DOESNT EXISTS IN THE DATA BASE", ErrorType.USER_CARITARISTICS_ERROR);
		}
	}

	//increment the number of attempts of the user trying to verify his account

	public void incrementVerificationAttempts(long userID, UserType userType) throws MyApplicationException{

		//user's ID existence in DB
		if (userDao.isUserIDExists(userID,userType)==true){
			
			userDao.incrementVerificationAttempts(userID,userType);
		}

		else{	
			throw new MyApplicationException("incrementVerificationAttempts EXCEPTION,THE ID OF USER: \" "+userID+" \"DOESNT EXISTS IN THE DATA BASE", ErrorType.USER_CARITARISTICS_ERROR);
		}
	}

}
