package com.qunar.fintech.plat.admin.query.exception;

public class FppException extends RuntimeException{

	private static final long serialVersionUID = 7556571947462996181L;

	public FppException() {
		super();
	}

	public FppException(String message, Throwable cause) {
		super(message, cause);
	}

	public FppException(String message) {
		super(message);
	}

	public FppException(Throwable cause) {
		super(cause);
	}

}
