package com.moshiko.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.moshiko.enums.ErrorType;
import com.moshiko.enums.UserType;
import com.moshiko.exceptions.MyApplicationException;
import com.moshiko.utils.JdbcUtils;

public class AdminsDao extends UsersDao {

	
	//remove administrator for the data base

	@Override
	public void removeUser(long userID, UserType userType) throws MyApplicationException {
				

					//creating the resources
					PreparedStatement preparedStatement = null;
					
					Connection connection = null;

					try {

						//getting a connection from the connection manager
						connection = JdbcUtils.getConnection();

						String sqlQuery = "DELETE FROM ADMIN WHERE USERID= ?";

						preparedStatement = connection.prepareStatement(sqlQuery);

						preparedStatement.setLong(1, userID);

						preparedStatement.executeUpdate();

					} 

					//if there was an exception in the "try" block above, it is caught here and notifies a level above.
					catch (Exception e) {
						throw new MyApplicationException(e, ErrorType.GENERAL_ERROR, "COULD NOT REMOVE USER OF TYPE "+ userType.name()+" BY GIVEN USERID: \"" + userID +
								"\" FROM THE DB");
					} 

					finally {

						//closing the resources
						JdbcUtils.closeResources(connection, preparedStatement);
					}
				}

		
	

}


