package com.watercloud.webmagic.common.exception;

public class CloudWebmagicException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CloudWebmagicException(){

	}
	public CloudWebmagicException(String message){
		super(message);
	}
	
	public CloudWebmagicException(Throwable cause)
	{
		super(cause);
	}
	
	public CloudWebmagicException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
