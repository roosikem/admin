package com.serosoft.admin.generic;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -6854945379036729034L;
	private int exceptionType = 0;
	public final static int EXCEPTION_VALIDATION = 99;
	public final static int EXCEPTION_PAYMENT_DECLINED = 100;
	public final static int EXCEPTION_TRANSACTION_DECLINED = 101;
	
	private String messageCode = null;
    
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public ServiceException() {
		super();
	}
	
	public ServiceException(String messageCode) {
		super();
		this.messageCode = messageCode;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(int exceptionType) {
		super();
		this.exceptionType = exceptionType;
	}
	
	public ServiceException(int exceptionType, String message) {
		super(message);
		this.exceptionType = exceptionType;
	}
	
	public ServiceException(int exceptionType, String message, String messageCode) {
		super(message);
		this.messageCode = messageCode;
		this.exceptionType = exceptionType;
	}
	
	public int getExceptionType() {
		return exceptionType;
	}
	
	public void setExceptionType(int exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	public String getMessageCode() {
		return messageCode;
	}

}
