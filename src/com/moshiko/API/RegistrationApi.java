package com.moshiko.API;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.moshiko.beans.LoginInput;
import com.moshiko.beans.RegistrationInput;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.logic.RegistrationController;

//The first step is to specify a path for the web service 
//using @Path annotation to the CustomerApi.
@Path("/registration")

//using the @Produces to set the sent data of the response to JSON format
//using the @Consumes to set the received data of the request to JSON format
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationApi {
	
	
	
	public RegistrationApi() {
		
	}


	@POST
	@Path("/createNewUser")
	public void createNewUser( LoginInput newUserInput ) throws MyApplicationException, ServletException, IOException, AddressException, MessagingException
	{		
	
		RegistrationController registrationController =new  RegistrationController();

	System.out.println(newUserInput);

	registrationController.createNewUser( newUserInput);
			
}
	
	@GET
	@Path("/VerifyRegisteredEmailHash")
	public void VerifyRegisteredEmailHash(  final  @Context HttpServletRequest request, final  @Context HttpServletResponse response, @QueryParam("userTypeSTR")String userTypeSTR, @QueryParam("scope")String scope, @QueryParam("hash")String hash, @QueryParam("userId")long userId) throws MyApplicationException, ServletException, IOException, AddressException, MessagingException
	{		
		RegistrationInput registrationInput = new RegistrationInput();

		registrationInput.setUserID(userId);
		registrationInput.setHash(hash);
		registrationInput.setScope(scope);
		registrationInput.setUserTypeSTR(userTypeSTR);
		
		System.out.println(registrationInput);
		RegistrationController registrationController =new  RegistrationController();

		
	registrationController.VerifyRegisteredEmailHash(registrationInput,response,request);
			
}	
	
}
