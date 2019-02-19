package com.qunar.fintech.plat.admin.query.exception;

public class FTPClientException extends RuntimeException{

	private static final long serialVersionUID = 7556571947462996181L;

	public FTPClientException() {
		super();
	}

	public FTPClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public FTPClientException(String message) {
		super(message);
	}

	public FTPClientException(Throwable cause) {
		super(cause);
	}
}
