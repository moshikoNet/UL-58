package com.moshiko.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.moshiko.beans.LoginInput;
import com.moshiko.enums.*;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.utils.GlobalConstants;
import com.moshiko.utils.Logger;
import com.moshiko.utils.Utils;

//the LoginController is responsible for the different actions, 
//such as: login, logout, action's permission check etc.

public class LoginController {

	UserController userController;

	public LoginController() { 

		userController= new UserController();

	}

	//Check if the user's account have been verified by checking verification status in the DB

	public boolean isUserHaveBeenVerified(LoginInput loginInput) throws MyApplicationException{

		Utils.isTheValueEqualsToNull(loginInput);

		String userName=loginInput.getUserName();
		UserType userType=UserType.valueOf(loginInput.getUserTypeSTR());

		long userID= userController.getUserIDByName(userName,userType);

		System.out.println(userController.getVerificationStatus(userID, userType));

		if ((userController.getVerificationStatus(userID, userType).equals(GlobalConstants.ACTIVE) || 
				userController.getVerificationStatus(userID, userType).equals(GlobalConstants.RESET_PASSWORD))==false){

			throw new MyApplicationException("LOGIN FAILURE DUE TO UNVERIFIED USER ACCOUNT ", ErrorType.LOGIN_ERROR);
		}
		else {
			return true;
		}
	}

	//check if user name and password valid against the DB

	public boolean isUserPassowrdAndNameValide(LoginInput loginInput) throws MyApplicationException{

		Utils.isTheValueEqualsToNull(loginInput);

		String userName=loginInput.getUserName();
		String password=loginInput.getPassword();
		UserType userType=UserType.valueOf(loginInput.getUserTypeSTR());

		if (userController.isUserPassowrdAndNameMatchToDB(userName, password, userType)==false){
			throw new MyApplicationException("LOGIN FAILURE DUE TO MISSMATCH PASSWORD OR USER ", ErrorType.LOGIN_ERROR);
		}

		else {
			return true;
		}

	}

	//check if the logged user is administrator before committing an action that is allowed only to an administrator

	public void adminPermissionOnly(HttpServletRequest httpServletRequest) throws MyApplicationException {

		HttpSession loggedInUser = httpServletRequest.getSession(false);

		Utils.isTheValueEqualsToNull(loggedInUser);
		
		String UserType=(String) loggedInUser.getAttribute("UserType");

		Utils.isTheValueEqualsToNull(UserType);

		if (Utils.isAdmin(UserType)==false){
			throw new MyApplicationException("ACTION IS NOT ALLOWED TO ANY ONE BUT ADMINISTRATOR ", ErrorType.PERMISSION_ERROR);}

	}


	//check if the logged user is an administrator or user that we want to do an action on his account that before committing an action

	public void userOrAdminPermissionOnly(String requestedInformationAboutUserType,Long requestedInformationAboutUserID,HttpServletRequest httpServletRequest) throws MyApplicationException{

		HttpSession loggedInUser = httpServletRequest.getSession(false);

		Utils.isTheValueEqualsToNull(loggedInUser);

		String loggedUserType = (String) loggedInUser.getAttribute("UserType");
		
		Utils.isTheValueEqualsToNull(loggedUserType);

		long loggediUserID = (long) loggedInUser.getAttribute("UserID");

		Utils.isTheValueEqualsToNull(loggediUserID);

		System.out.println(loggediUserID+""+requestedInformationAboutUserID);

		if (Utils.isLoggedUserOrAdmin(loggedUserType, requestedInformationAboutUserType, loggediUserID, requestedInformationAboutUserID) == false){
			
			throw new MyApplicationException("ACTION IS NOT ALLOWED TO ANY ONE BUT ADMINISTRATOR OR LOGGED USER", ErrorType.PERMISSION_ERROR);
		}

	}

	//logging out by terminating the session

	public void logOut(HttpServletRequest httpServletRequest) throws MyApplicationException{

		//closing the session = setting the expiration of a cookie to 0 seconds

		HttpSession loggedOutSession = httpServletRequest.getSession();

		Utils.isTheValueEqualsToNull(loggedOutSession);
		
		System.out.println(loggedOutSession.getId());

		loggedOutSession.invalidate();
	}



	public void loggingInTheUser(HttpServletRequest httpServletRequest,LoginInput loginInput) throws MyApplicationException{
		
		Utils.isTheValueEqualsToNull(loginInput);


		if(isUserPassowrdAndNameValide(loginInput)==true & isUserHaveBeenVerified(loginInput)==true)
		{

			HttpSession loggedInSession = httpServletRequest.getSession();

			Utils.isTheValueEqualsToNull(loggedInSession);

			UserType validUserType=UserType.valueOf(loginInput.getUserTypeSTR());

			//After a successful logging in we save the User type in as a session attribute.
			loggedInSession.setAttribute("UserType", loginInput.getUserTypeSTR());

			//After a successful logging in we save the User ID, by it's type, in as a session attribute.
			{

				long validUserID= userController.getUserIDByName(loginInput.getUserName(), validUserType);

				loggedInSession.setAttribute("UserID",validUserID);

				System.out.println("UserID:"+loggedInSession.getAttribute("UserID")+" UserType:"+loggedInSession.getAttribute("UserType"));

				try {
					Logger.getInstance().logToFile("logging.txt", userController.getUser(validUserID,validUserType).toString(),LogType.LOGGING);
				} 
				catch (MyApplicationException e1) {
					e1.printStackTrace();
				}

			}
		}
	}
}

