<@file name="jboss.xml" package="${cfgDir}">
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE jboss PUBLIC "-//JBoss//DTD JBOSS 4.0//EN" "http://www.jboss.org/j2ee/dtd/jboss_4_0.dtd">

<jboss>
   <enterprise-beans>
<@forAllTypes var="type" annotation="org.highway.service.ejb.GenerateEjb" includeInterfaces="true">
      <session>
         <ejb-name>${type.generatedShortClassName}</ejb-name>
         <jndi-name>ejbhome/${type.package.qualifiedName}.${type.generatedShortClassName}</jndi-name>
      </session>
</@forAllTypes>
   </enterprise-beans>

   <resource-managers>
   </resource-managers>
</jboss>
</@file>