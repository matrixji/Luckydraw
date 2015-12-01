package com.luckydraw.exception;

public class FlagNullException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlagNullException() {
		super("标识不存在");
	}

}
