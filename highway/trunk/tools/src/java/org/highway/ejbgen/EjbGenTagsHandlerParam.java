package org.highway.ejbgen;

import java.util.Properties;

import org.highway.JavaHelper;

import xdoclet.XDocletException;
import xdoclet.tagshandler.ParameterTagsHandler;

/**
*
* Specialization of the class for processing tags on methods to
* add method to give type of value and return index
*/
public class EjbGenTagsHandlerParam extends ParameterTagsHandler {

	private int index= 0;
	
	
    /**
     * Evaluates the body if the return type of the current method equals the specified value.
     *
     * @param template              The body of the block tag
     * @param attributes            The attributes of the template tag
     * @exception XDocletException  Description of Exception
     * @doc.tag                     type="block"
     * @doc.param                   name="type" optional="false" description="The type to compare."
     */
    public void ifIsOfType(String template, Properties attributes) throws XDocletException {
        if (ifIsOfTypeImpl(template, attributes))
            generate(template);
    }

	private boolean ifIsOfTypeImpl(String template, Properties attributes) throws XDocletException {
		return methodParamType(attributes).equals(attributes.getProperty("type"));
	}


	public void ifTypePrimitive(String template, Properties attributes) throws XDocletException {
		if (JavaHelper.isPrimitiveType(this.methodParamType(attributes))) {
			generate(template);
		}
	}

	public void ifTypeObject(String template, Properties attributes) throws XDocletException {
		if (! JavaHelper.isPrimitiveType(this.methodParamType(attributes))){
			generate(template);
		}
	}
	
	public void forAllMethodParams(String template) throws XDocletException {
		index=0;
		super.forAllMethodParams(template);
		index=0;
	}
	
	/**
	 * Increments the index with 1 ans return the value
	 * @return incremented index.
	 */
	public String getIncrementIndex () throws XDocletException {
		String response =Integer.toString(index); 
		index++;
		return  response;
	}
	
	
}
