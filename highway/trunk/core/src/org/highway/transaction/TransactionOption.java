package org.highway.transaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used in the service interfaces to indicate the
 * transactional behavior of all the services (if used as type annotation) or of
 * a service (if used as a method annotation).<br>
 * <br>
 * Example:<br>
 * <br>
 * <tt>@TransactionOption(Required)
 * </tt>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface TransactionOption
{
	TransactionOptions value();

	public static enum TransactionOptions
	{

		/**
		 * The MANDATORY setting (value is {@value #MANDATORY}) indicates that
		 * the service must always execute within the context of the client's
		 * transaction. If the client does not have a transaction when it calls
		 * the service, a TransactionRequiredException is thrown and the request
		 * fails.
		 */
		MANDATORY,

		/**
		 * The NEVER setting (value is {@value #NEVER}) indicates that the
		 * service cannot execute within the context of a transaction. If a
		 * client has a transaction when it calls the service, an exception is
		 * thrown.
		 */
		NEVER,

		/**
		 * The NOT_SUPPORTED setting (value is {@value #NOT_SUPPORTED})
		 * indicates that the service cannot execute within the context of a
		 * transaction. If a client has a transaction when it calls the service,
		 * the transaction is suspended for the duration of the method call.
		 */
		NOT_SUPPORTED,

		/**
		 * The REQUIRED setting (value is {@value #REQUIRED}) indicates that
		 * the service must execute within the context of a transaction. If a
		 * client has a transaction when it calls the service, the method will
		 * join the client's transaction context. If the client does not have a
		 * transaction, a new transaction is automatically started for the
		 * method.
		 */
		REQUIRED,

		/**
		 * The REQUIRES_NEW setting (value is {@value #REQUIRES_NEW}) indicates
		 * that the service must execute within the context of a new
		 * transaction. A new transaction is always started for the method. If
		 * the client has a transaction when it calls the service, the client's
		 * transaction is suspended for the duration of the method call.
		 */
		REQUIRES_NEW,

		/**
		 * The SUPPORTS setting (value is {@value #SUPPORTS}) indicates that
		 * the service can run with or without a transaction context. If a
		 * client has a transaction when it calls the service, the method will
		 * join the client's transaction context. If the client does not have a
		 * transaction, the method will run without a transaction.
		 */
		SUPPORTS
	};
}
