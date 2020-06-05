package com.solar.exception;

import javax.servlet.http.HttpServletRequest;

public class ErrorResponseEntity {

	private String exception;
	private String message;
	private String method;
	private String path;
	
	
	public ErrorResponseEntity(Exception exception, HttpServletRequest request) {
		this.exception = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		this.method = request.getMethod();
		this.path = request.getRequestURI().toString();
	}
	
	public ErrorResponseEntity(Exception exception, HttpServletRequest request, String message) {
		this.exception = exception.getClass().getSimpleName();
		this.message = message;
		this.method = request.getMethod();
		this.path = request.getRequestURI().toString();
	}
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
