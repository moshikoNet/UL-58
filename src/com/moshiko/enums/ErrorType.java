package com.moshiko.enums;

//giving a defined list of exceptions 

public enum ErrorType {

	TANK_GENERAL_CARITARISTICS_ERROR (400),
	INSPECTOR_CARITARISTICS_ERROR (400),
	ENGINEER_CARITARISTICS_ERROR(400),
	USER_CARITARISTICS_ERROR (400),
	ADMIN_CARITARISTICS_ERROR(400),
	PARSE_ERROR(304),
	USER_ERROR(400),
	LOGGER_ERROR(507),
	LOGIN_ERROR(401),
	PERMISSION_ERROR(500),
	GENERAL_ERROR(500);
	
	//final parameter
	private final int InternalErrorCode;
	
	//private Constructor 
	private ErrorType(int internalErrorCode) {
		this.InternalErrorCode = internalErrorCode;
	}
	
	public int getInternalErrorCode() {
		
		return InternalErrorCode;
	}
	
}
