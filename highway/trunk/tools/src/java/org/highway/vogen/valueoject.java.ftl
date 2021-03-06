<@forAllTypes var="type" includeInterfaces="true">
<@javaSource name="${type.generatedClassName}">
/*
 * Genarated by vogen. Do not modify.
 */
package ${type.package.qualifiedName};

import org.highway.helper.ValueHelper;
import org.highway.bean.ValueObjectAbstract;

public <@ifHasAnnotation annotation="org.highway.vogen.GenerateAbstract">abstract</@ifHasAnnotation> class ${type.generatedShortClassName}
	extends ${type.superClassName}
	implements ${type.qualifiedName}
{
	<@ifHasAnnotation var="ann" annotation="org.highway.vogen.SerialVersionUID">
	static final long serialVersionUID = ${ann.value}L;
	</@ifHasAnnotation>

	<@forAllMethods var="method">
	<#assign propertyName = "${method.propertyName}">
	 /**
	 * Property ${propertyName}
	 */ 
	 private ${method.returnTypeQualifiedName} ${propertyName};
	 
	 public static final String ${method.constantName} = "${propertyName}";
	 	
	 public ${method.returnTypeQualifiedName} ${method.simpleName}()
	 {
		return ${propertyName};
	 }
	 
	 public void ${method.setMethodName}(${method.returnTypeQualifiedName} newValue) 
	 {
		${method.setMethodName}_0(newValue);
	 }
	 
	 protected final void ${method.setMethodName}_0(${method.returnTypeQualifiedName} newValue)
	 {
		${method.returnTypeQualifiedName} oldValue = ${propertyName};
		if (!ValueHelper.equals(oldValue, newValue))
		{
			${propertyName} = newValue;
			firePropertyChange(${method.constantName}, oldValue, newValue);
		}
	 }
	 
	 </@forAllMethods>
	 
	 protected boolean equals2(ValueObjectAbstract obj)
	 {
		${type.generatedShortClassName} vo = (${type.generatedShortClassName}) obj;
		
		return super.equals2(vo)
			<@forAllMethods var="method"><#assign propertyName = "${method.propertyName}">
			&& ValueHelper.equals(${propertyName}, vo.${propertyName})
			</@forAllMethods>;
	 }
	
	 public int hashCode()
	 {
		return super.hashCode()
			<@forAllMethods var="method">+ ValueHelper.hashCode(${method.propertyName})
			</@forAllMethods>;
	 }
}
</@javaSource>
</@forAllTypes> 