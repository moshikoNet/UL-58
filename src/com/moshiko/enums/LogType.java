package com.moshiko.enums;

//giving a defined list of logging CARITARISTICS when creating a coupon

public enum LogType {

	INSPECTOR_REMOVE("THE FOLLOWING INSPECTOR HAS BEEN REMOVED FROM THE DB: "),
	ENGNIEER_REMOVE("THE FOLLOWING ENGNIEER HAS BEEN REMOVED FROM THE DB: "),
	ADMIN_REMOVE("THE FOLLOWING ADMINISTRATOR HAS BEEN REMOVED FROM THE DB: "),
	LOGGING("THE FOLLOWING USER HAS LOGGED TO THE SITE"),

	GOT_AN_EXCEPTION("RECEIVED THE FOLLOWING EXCEPTION: "),
	
	VALIDATION_MISMATCH("THE TANK HAS A VALIDATION MISMATCH:"),
	VALIDATION_MATCH("THE TANK HAS A VALIDATION MATCH:"),
	CALCULATION_RESULT("CALCULATION:"),
	INFO("");
	;

	//final parameter
	private final String text;

	//private Constructor 
	private LogType(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
