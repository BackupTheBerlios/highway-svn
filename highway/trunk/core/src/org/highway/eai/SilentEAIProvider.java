package org.highway.eai;

/**
 * Silent implementation of the EAIProvider.<br>
 * This provider does nothing.<br>
 * 
 * @author Jérôme RAULINE
 * @since 1.4.7
 */
public class SilentEAIProvider implements EAIProvider
{

	/**
	 * @see org.highway.eai.EAIProvider#integrate(java.lang.Object,
	 * java.lang.Object, java.lang.String)
	 */
	public void integrate(Object object, Object key, String verb)
	{
		// Nothing to do
	}

}
