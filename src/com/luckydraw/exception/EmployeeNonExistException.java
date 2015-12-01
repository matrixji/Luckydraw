package com.luckydraw.exception;

public class EmployeeNonExistException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNonExistException(String message) {
		super(message);
	}

}
