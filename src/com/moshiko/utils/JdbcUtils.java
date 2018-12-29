package com.moshiko.utils;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class JdbcUtils {

	static {
		try { //load the driver to system , you need to import the bin file to every project in order to work
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ul58?useSSL=false", "root",
				"root");
		// localhost means "THIS (LOCAL) COMPUTER"
		// SCHEMA_NAME_YOU_NEED_TO_RENAME is the DB name, also known as Schema
		// name.
		// in your DB you will need to SCHEMA_NAME_YOU_NEED_TO_RENAME
		// into to YOUR schema name

		return connection;
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {

			// Write to log that we have a resource leak
			e.printStackTrace();
		}

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Write to log that we have a resource leak
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {

			// Write to log that we have a resource leak
			e.printStackTrace();
		}

		closeResources(connection, preparedStatement);
	}

}
