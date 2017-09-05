
package com.pwi.exception;

/**
 * This is a top level custom exception for PwiException. As per requirement and clearance purpose this class should be 
 * used and extended.
 * 
 */

public class PwiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5407044129113318045L;
	
	/**
	 * @param String message
	 * This constructor accepts the message occured causing the exception
	 */
	public PwiException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public PwiException(String message, Throwable cause) {
		super(message,cause);
	}

}
