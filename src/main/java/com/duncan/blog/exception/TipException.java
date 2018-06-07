package com.duncan.blog.exception;

public class TipException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TipException() {
		super();
	}

	public TipException(String message, Throwable cause) {
		super(message, cause);
	}

	public TipException(String message) {
		super(message);
	}

	public TipException(Throwable cause) {
		super(cause);
	}
	
	

}
