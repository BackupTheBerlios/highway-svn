package org.highway.eai;

/**
 * An EAIProvider provides the capabilitie to send events to an integration
 * agent / broker.
 * 
 * @author Jérôme RAULINE
 * @since 1.4.7
 */
public interface EAIProvider
{

	/**
	 * Integrate the object with the specified verb.
	 * 
	 * @param object the object to integrate (mandatory).
	 * @param key the object key (mandatory). A <code>String</code>
	 * representation of this object will be used, using the
	 * <code>toString()</code> method.
	 * @param verb the verb associated to the object (mandatory).
	 */
	void integrate(Object object, Object key, String verb);

}
