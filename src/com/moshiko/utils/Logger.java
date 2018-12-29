package com.moshiko.utils;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import com.moshiko.enums.ErrorType;
import com.moshiko.enums.LogType;
import com.moshiko.exceptions.MyApplicationException;

public class Logger {

	private static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	// a method that log into a txt file different types of logging data, 
	//such as: exceptions, validation errors, removed engineers, removed inspectors etc.

	private static Logger logger =new Logger();

	/* A private Constructor prevents any other
	 * class from instantiating.
	 */
	private Logger() { 
	}

	/* Static 'instance' method */
	public static Logger getInstance( ) {
		return logger;
	}

	//logging a given string in a given file's path by using a defined logging type
	public void logToFile (String paths,String logMe,LogType logtype) throws MyApplicationException{

		Path path= Paths.get(paths);

		Date date = new Date();
		String loggedData="Date & Time stemp: "+Utils.FormatDateToString(date,DATE_AND_TIME_FORMAT)+"  ";

		logMe=logMe+"\n";

		loggedData="\n"+loggedData +logtype.toString()+" "+logMe;

		//getBytes()
		//This method encodes this String into a sequence of bytes using the platform's default charset,
		//storing the result into a new byte array. 
		//encoding the string by UTF-8 OR OTHER

		byte data[] =loggedData.getBytes();

		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE, APPEND))) {
			out.write(data, 0, data.length);
			System.out.println("LOGGED TO FILE!");

		} catch (IOException x) {
			throw new MyApplicationException("LOGGER EXCEPTION,COULD NOT WRITE TO FILE", ErrorType.LOGGER_ERROR);
		}

	}



}

