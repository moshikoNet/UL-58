package com.moshiko.logic;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moshiko.Dao.UsersDao;
import com.moshiko.beans.LoginInput;
import com.moshiko.beans.RegistrationInput;
import com.moshiko.beans.User;
import com.moshiko.enums.UserType;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.utils.BCrypt;
import com.moshiko.utils.GlobalConstants;
import com.moshiko.utils.MailUtil;
import com.moshiko.utils.Utils;

// Registration Controller is responsible for activating a new user

public class RegistrationController {

	UserController userController;


	public RegistrationController() { 

		userController= new UserController();

	}

	public void createNewUser(LoginInput newUserInput ) throws MyApplicationException, ServletException, IOException, AddressException, MessagingException
	{	
		Utils.isTheValueEqualsToNull(newUserInput);

		//input parameters from the users
		String newUserName=newUserInput.getUserName();
		UserType newUserType=UserType.valueOf(newUserInput.getUserTypeSTR());
		String newUseremail=newUserInput.getEmail();
		String unEncryptedPassword=newUserInput.getPassword();

		// generate hash for password
		String ecryptedPassword=BCrypt.hashpw(unEncryptedPassword,GlobalConstants.SALT);

		// generate random hash code for email verification

		String randomHash = Utils.prepareRandomString(30);
		System.out.println(randomHash);
		// generate hash for hashMailActivation

		String ecryptedHashActivation = BCrypt.hashpw(randomHash, GlobalConstants.SALT);

		long newUserID=0;

			User newUser=new User(newUserName, unEncryptedPassword, newUseremail,newUserType,ecryptedHashActivation);

			// create account , I allow multiple use of an email for different user type
			newUserID=userController.createUser(newUser);

			// send verification email (the unencrypted hash is send in the mail)
			MailUtil.sendEmailRegistrationLink(newUserID, newUseremail, randomHash,newUserType.name());
		
	}

	public void VerifyRegisteredEmailHash(RegistrationInput registrationInput,HttpServletResponse response,HttpServletRequest request) throws MyApplicationException, ServletException, IOException, AddressException, MessagingException

	{	UsersDao userDao=new UsersDao();

	Utils.isTheValueEqualsToNull(registrationInput);

	long userID =registrationInput.getUserID();
	String randomHash= registrationInput.getHash();
	String scope =registrationInput.getScope();
	UserType newUserType=UserType.valueOf(registrationInput.getUserTypeSTR());

	// get user Id and email verification code Hash code  
	String hashEncrypted = BCrypt.hashpw(randomHash, GlobalConstants.SALT);
	String message = null;


	// verify with database the hashActivation code and that the account needs to be activated

		if(userDao.verifyEmailHash(userID, hashEncrypted,newUserType) && scope.equals(GlobalConstants.ACTIVATION)) {

			//update status to active after verifying the hash code
			userDao.updateStatus(userID, GlobalConstants.ACTIVE,newUserType);

			message = "Email verified successfully. Account was activated. Click <a href=\"/couponProject/index.html\">here</a> to login";
		} else {

			//if the verification didn't succeed, increment verification attempts 

			userDao.incrementVerificationAttempts(userID,newUserType);

			int attempts = userDao.getVerficationAttempts(userID,newUserType);

			// reset verification code if attempts equal to 20 

			if (attempts == 20) {

				//create a new randomHash for new verification
				randomHash = Utils.prepareRandomString(30);

				hashEncrypted = BCrypt.hashpw(randomHash, GlobalConstants.SALT);

				userDao.updateEmailVerificationHash(userID, hashEncrypted, 0,newUserType);

				User user = userDao.getUser(userID,newUserType);

				MailUtil.sendEmailRegistrationLink(userID, user.getUserEmail(), randomHash ,newUserType.name());

				message = "20 times Wrong Email Validation Input Given. So we are sent new activation link to your Email";


			} else {
				message = "Wrong Email Validation Input";   
			}
		
	}
	
	if(message!=null) {
		request.setAttribute(GlobalConstants.MESSAGE, message);
		request.getRequestDispatcher("/messageToUser.jsp").forward(request, response);	
	} 
	
	}
	



}
