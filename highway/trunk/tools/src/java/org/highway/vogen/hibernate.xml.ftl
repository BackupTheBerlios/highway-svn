<@forAllTypes var="type" annotation="org.highway.database.MappedOn" includeInterfaces="true">
<@file name="${type.generatedShortClassName}.hbm.xml" package="${type.package.qualifiedName}">

<!DOCTYPE hibernate-mapping PUBLIC
	"-//Hibernate/Hibernate Mapping DTD 3.0//EN	"
    "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd "
    >

<!-- File generated by vogen. Do not modify manually. -->
<hibernate-mapping>
	<#if type.ifIsClass>
    <class
     	name="${type.generatedClassName}"
    	<@ifHasAnnotation var="ann" annotation="org.highway.database.MappedOn">
        table="${ann.value}"
        </@ifHasAnnotation>
        <@ifHasAnnotation var="ann" annotation="org.highway.database.DiscriminatorValue">
        discriminator-value="${ann.value}"
        </@ifHasAnnotation>
     >
     	
     	<#if type.hasPrimitiveId>
     	<@forAllMethods annotation="org.highway.database.Identity" var="method" annotationVar="ann">
        <id
            name="${method.propertyName}"
            column="<@annotationValue declaration=method annotation="org.highway.database.MappedOn" default="${method.propertyName}"/>"
            type="<@annotationValue declaration=method annotation="org.highway.database.MappingSpecialType" default="${method.returnType}"/>"
            <generator class="${ann.generatorClass}">
			<@forAllMethods declaration=type annotation="org.highway.database.IdentityGeneratorParam" var="generatorMethod" annotationVar="annGenerator">
			<param name="${annGenerator.name}">${annGenerator.value}</param>
			</@forAllMethods>
            </generator>
        </id>
	    </@forAllMethods>
        <#elseif type.hasCompositeId>
        <composite-id>
        	<@forAllMethods annotation="org.highway.database.Identity" var="method" annotationVar="ann">
            <key-property
                name="${method.propertyName}"
                column="<@annotationValue declaration=method annotation="org.highway.database.MappedOn" default="${method.propertyName}"/>"
                type="<@annotationValue declaration=method annotation="org.highway.database.MappingSpecialType" default="${method.propertyHibernateType}"/>"
            />
            </@forAllMethods>
        </composite-id>
		</#if>
        
        <@ifHasAnnotation var="ann" annotation="org.highway.database.DiscriminatorColumn">
        <discriminator
                column="${ann.column}"
                type="${ann.type}"
                force="${ann.force}"
                insert="${ann.insert}"
        />
        </@ifHasAnnotation>
        <@forAllMethods annotation="org.highway.database.MappedOn" var="method" annotationVar="ann">
        <property 
            name="${method.propertyName}"
            type="<@annotationValue declaration=method annotation="org.highway.database.MappingSpecialType" default="${method.propertyHibernateType}"/>"
            column="${ann.value}"
            insert=<@annotationValue declaration=method annotation="org.highway.database.DoNotInsert" default="true"/>"
            update=<@annotationValue declaration=method annotation="org.highway.database.DoNotUpdate" default="true"/>"
        />
        </@forAllMethods>
	</class>
	<#elseif type.ifIsSubClass>
    <subclass
        name="${type.entityClassName}"
        extends="${type.superClassName}"
        <@ifHasAnnotation var="ann" annotation="org.highway.database.DiscriminatorValue">
        discriminator-value="${ann.value}"
        </@ifHasAnnotation>
    >
   
       <@forAllMethods annotation="org.highway.database.MappedOn" var="method" annotationVar="ann">
        <property
            name="${method.propertyName}"
            type="<@annotationValue declaration=method annotation="org.highway.database.MappingSpecialType" default="${method.propertyHibernateType}"/>"
            column="${ann.value}"
            insert=<@annotationValue declaration=method annotation="org.highway.database.DoNotInsert" default="true"/>"
            update=<@annotationValue declaration=method annotation="org.highway.database.DoNotUpdate" default="true"/>"
        />
       </@forAllMethods>
      </subclass>

   	<#elseif type.ifIsJoinedSubClass>
    <joined-subclass
        name="${type.entityClassName}"
        extends="${type.superClassName}"
    	<@ifHasAnnotation var="annMapping" annotation="org.highway.database.MappedOn">
        table="${annMapping.value}"
        </@ifHasAnnotation>
    >
		<key column="<@annotationValue declaration=method annotation="org.highway.annotation.VoMappingKeyColumn" default="${type.keyColumn}"/>"/>

       <@forAllMethods annotation="org.highway.database.MappedOn" var="method" annotationVar="ann">
        <property
            name="${method.propertyName}"
            type="<@annotationValue declaration=method annotation="org.highway.database.MappingSpecialType" default="${method.propertyHibernateType}"/>"
            column="${ann.value}"
            insert=<@annotationValue declaration=method annotation="org.highway.database.DoNotInsert" default="true"/>"
            update=<@annotationValue declaration=method annotation="org.highway.database.DoNotUpdate" default="true"/>"

         />
       </@forAllMethods>
      
    </joined-subclass>
	</#if>
</hibernate-mapping>
</@file>
</@forAllTypes>