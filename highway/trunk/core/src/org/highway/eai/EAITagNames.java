package org.highway.eai;

/**
 * Defines the javadoc tags the EAI component framework uses.
 * 
 * @author Jérôme RAULINE
 * @since 1.4.7
 */
public interface EAITagNames
{

	/**
	 * This tag is used in value objects interface to indicate the integration
	 * request method. The method is defined by any EAIProvider implementation
	 * that needs it to work. See your implementation for details.<br>
	 * <br>
	 * Example :<br>
	 * 
	 * <pre>
	 *     <code>@</code>highway.eai.method JDBC
	 * </pre>
	 */
	String REQUEST_METHOD = "highway.eai.request.method";

	/**
	 * This tag is used in value objects interface to indicate the priority of
	 * the integration request. See your implementation for details about
	 * supported values.<br>
	 * <br>
	 * Example :<br>
	 * 
	 * <pre>
	 *     <code>@</code>highway.eai.request.priority 5
	 * </pre>
	 */
	String REQUEST_PRIORITY = "highway.eai.request.priority";

	/**
	 * This tag is used in value objects interface to indicate an optinal
	 * identifier of the broker in charge of the integration request. See your
	 * implementation for details about supported values.<br>
	 * <br>
	 * Example :<br>
	 * 
	 * <pre>
	 *     <code>@</code>highway.eai.request.broker myBroker002
	 * </pre>
	 */
	String REQUEST_BROKER_ID = "highway.eai.request.broker";

	/**
	 * This tag is used in value objects interface to indicate the entity name
	 * of the value object known by the integration provider.<br>
	 * <br>
	 * Example :<br>
	 * 
	 * <pre>
	 *     <code>@</code>highway.eai.object.name Web_Candidat
	 * </pre>
	 */
	String OBJECT_NAME = "highway.eai.object.name";

}
