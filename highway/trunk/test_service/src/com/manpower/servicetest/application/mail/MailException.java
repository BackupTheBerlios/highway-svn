package com.manpower.servicetest.application.mail;

import org.highway.exception.FunctionalException;

public class MailException extends FunctionalException
{
	public MailException()
	{
		super();
	}

	public MailException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public MailException(String message)
	{
		super(message);
	}

	public MailException(Throwable cause)
	{
		super(cause);
	}
}
