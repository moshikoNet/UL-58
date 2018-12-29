package com.moshiko.exceptions;

import com.moshiko.enums.ErrorType;

public class MyApplicationException extends Exception {


		private ErrorType errorType;

		// When a 3rd party throws an exception, we want to wrap it and so this exception
		// gets as a parameter the 3rd party exception, and stores it using the 
		// super constructor (the constructor of its parent class).

		// constructor num. 1
		public MyApplicationException(Exception e, ErrorType errorType,String msg) {
			super (msg,e); 													//stores the exception 
			this.errorType = errorType;
		}

		// When the programmer decides to throw an exception, he will provide the ErrorType
		// he wants the user's error message to be derived from, and that's all.

		public MyApplicationException(ErrorType errorType){	// constructor num. 2
			this.errorType = errorType;
		}

		public MyApplicationException(String msg,ErrorType errorType){	// constructor num. 3
			super (msg); 
			this.errorType = errorType;
		}
		
		public ErrorType getErrorType() {
			return errorType;
		}


	}

