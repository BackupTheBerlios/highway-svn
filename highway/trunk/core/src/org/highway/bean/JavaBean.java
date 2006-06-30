package org.highway.bean;

import java.io.Serializable;

/**
 * Use this interface to mark JavaBeans.
 * This marker interface is for example used in the validation
 * package to decide if the object to validate is a JavaBean.
 * 
 * @see org.highway.validate.JavaBeanGlobalValidator
 * 
 * 
 */
public interface JavaBean extends Serializable
{
}
