package com.codewindy.mongodb.pojo;

public class ServiceResponse extends ServiceObject {
	private static final long serialVersionUID = -557584792367648116L;
	private String message = null;
	private Throwable exception = null;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}
}