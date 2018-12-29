package com.moshiko.API;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.moshiko.beans.LoginInput;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.logic.LoginController;
import com.moshiko.logic.UserController;

//The first step is to specify a path for the web service 
//using @Path annotation to the CustomerApi.
@Path("/login")

//using the @Produces to set the sent data of the response to JSON format
//using the @Consumes to set the received data of the request to JSON format
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginApi {

	LoginController loginController;
	UserController userController;
	
	public LoginApi() {
		
		loginController=new LoginController();
		userController = new UserController();
	}


	@POST
	public void validateUserIdentity( final  @Context HttpServletRequest httpServletRequest,LoginInput loginInput,final  @Context HttpServletResponse httpServletResponse ) throws MyApplicationException, ServletException, IOException
	{
		System.out.println(loginInput.toString());
		
		loginController.loggingInTheUser(httpServletRequest, loginInput);
			
}
	

	@PUT
	public void logOut( final  @Context  HttpServletRequest httpServletRequest) throws MyApplicationException{
	
	loginController.logOut(httpServletRequest);
	}
	

}
