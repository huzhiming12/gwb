package com.gwb.interceptor;

public class NotLoginException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public NotLoginException()
	{
		super();
	}

	public NotLoginException(String msg)
	{
		super(msg);
	}

}
