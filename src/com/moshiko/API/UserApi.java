package com.moshiko.API;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.moshiko.beans.User;
import com.moshiko.enums.UserType;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.logic.LoginController;
import com.moshiko.logic.UserController;

//The first step is to specify a path for the web service 
//using @Path annotation to the UserApi.
@Path("/afterLogging")

//using the @Produces to set the sent data of the response to JSON format
//using the @Consumes to set the received data of the request to JSON format
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserApi {

	private UserController userController;

	private LoginController loginController;

	//Constructor
	public UserApi() {

		userController = new UserController();

		loginController = new LoginController();
	}


	@POST
	public long createUser(User user,final  @Context HttpServletRequest httpServletRequest) throws MyApplicationException {

		return userController.createUser(user);
	}


	@DELETE
	@Path("/{userType}/{userID}")
	public void removeUser(@PathParam("userID") long userID,@PathParam("userType") String userTypeSTR,final  @Context HttpServletRequest httpServletRequest) throws MyApplicationException {

		// removeUser ACTION PREMISSION : ADMIN AND THE LOGGEDIN USER

		loginController.userOrAdminPermissionOnly( userTypeSTR,userID ,httpServletRequest);

		UserType userType =UserType.valueOf(userTypeSTR);

		userController.removeUser(userID,userType);
	}


	@PUT
	@Path("/{userID}")
	public void updateUser(@PathParam("userID") long userID, User user,final  @Context HttpServletRequest httpServletRequest) throws MyApplicationException {

		// updateUser ACTION PREMISSION : ADMIN AND THE LOGGEDIN USER
		

		loginController.userOrAdminPermissionOnly( user.getUserType().name(),userID ,httpServletRequest);

		userController.updateUser(userID, user);

	}


	@GET
	@Path("/{userType}/{userID}")
	public User getUser(@PathParam("userID") long userID,@PathParam("userType") String userTypeSTR,final  @Context HttpServletRequest httpServletRequest) throws MyApplicationException {

		//ACTION PREMISSION :  ADMIN AND THE LOGGEDIN USER

		loginController.userOrAdminPermissionOnly( userTypeSTR,userID ,httpServletRequest);

		UserType userType =UserType.valueOf(userTypeSTR);

		return userController.getUser(userID, userType);

	}

	@GET
	@Path("/UserByName/{userType}")
	public User getUserByName(final  @Context HttpServletRequest httpServletRequest, @QueryParam("userName") String userName,@PathParam("userType") String userTypeSTR) throws MyApplicationException {

		UserType userType =UserType.valueOf(userTypeSTR);

		long userID= userController.getUserIDByName(userName,userType);

		// getUserByName ACTION PREMISSION : ADMIN AND THE LOGGEDIN COMPANY

		loginController.userOrAdminPermissionOnly( userTypeSTR,userID ,httpServletRequest);

		System.out.println( "get"+ userController.getUser(userID,userType));

		return userController.getUser(userID,userType);

	}


	@GET
	@Path("/{userType}")
	public List<User> getAllUsers(@PathParam("userType") String userTypeSTR, final  @Context HttpServletRequest httpServletRequest) throws MyApplicationException {

		UserType userType =UserType.valueOf(userTypeSTR);

		//getAllUsers ACTION PREMISSION : ONLY ADMIN

		loginController.adminPermissionOnly(httpServletRequest);
		
		return userController.getAllUserByType(userType);

	}
}
