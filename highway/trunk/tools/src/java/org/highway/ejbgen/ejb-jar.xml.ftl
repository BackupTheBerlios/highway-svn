<?xml version="1.0" encoding="<XDtConfig:configParameterValue paramName='Xmlencoding'/>"?>

<!DOCTYPE ejb-jar PUBLIC
	"-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN"
	"http://java.sun.com/dtd/ejb-jar_2_0.dtd">

<!-- File generated by ejbgen. Do not modify manually. -->

<ejb-jar id="ejb-jar_ID">

   <enterprise-beans>

      <!-- session beans -->
<#list declaration.sessionBeans as bean>
      <session id="${bean.simpleName}">
         <ejb-name>${declaration.beanFullClassName(bean)}</ejb-name>
         <home>${declaration.homeFullClassName(bean)}</home>
         <remote>${declaration.remoteFullClassName(bean)}</remote>
         <ejb-class>${declaration.beanFullClassName(bean)}</ejb-class>
         <session-type>Stateless</session-type>
         <transaction-type>Bean</transaction-type>
      </session>

</#list>
   </enterprise-beans>

</ejb-jar>
