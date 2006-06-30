package org.highway.bean;

import java.lang.annotation.Annotation;

public interface BeanMetadataManager {

	<A extends Annotation> A getBeanAnnotation(Class beanClass,
			Class<A> annotationType);

	<A extends Annotation> A getPropertyAnnotation(Class beanClass,
			String propertyName, Class<A> annotationType);

}