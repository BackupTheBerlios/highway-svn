package org.highway.eai;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.init.InitException;

/**
 * Home class of the EAI package. It provides static methods to set and access
 * the default EAIProvider. Do not instantiate this class.<br>
 * <br>
 * Applications should create, configure and set the default EAIProvider at init
 * time. Use the <code>setEAIProvider</code> to set the default EAIProvider.
 * 
 * @author Jérôme RAULINE
 * @since 1.4.7
 */
public class EAIHome
{

	// -------------------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------------------

	/**
	 * EAIProvider object.
	 */
	protected static EAIProvider eaiProvider;

	// -------------------------------------------------------------------------
	// Public methods
	// -------------------------------------------------------------------------

	/**
	 * Sets the default EAIProvider.
	 */
	public static synchronized void setEAIProvider(EAIProvider newEAIProvider)
	{
		eaiProvider = newEAIProvider;
		DebugHome.getDebugLog().info(
				"Default EAIProvider is set to " + newEAIProvider);
	}

	/**
	 * Delegates to the default EAIProvider.
	 * 
	 * @throws InitException if no default EAIProvider is set.
	 * @see EAIProvider#integrate(Object, Object, String)
	 */
	public static void integrate(Object object, Object key, String verb)
			throws InitException
	{
		getSafeEAIProvider().integrate(object, key, verb);
	}

	// -------------------------------------------------------------------------
	// Non public methods
	// -------------------------------------------------------------------------

	/**
	 * Do not instantiate this class.
	 */
	protected EAIHome()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Returns the default EAIProvider.
	 * 
	 * @throws InitException if no default EAIProvider is set.
	 */
	protected static EAIProvider getSafeEAIProvider() throws InitException
	{
		if (eaiProvider == null)
		{
			throw new InitException("no default EAIProvider set");
		}

		return eaiProvider;
	}

}
