<@file name="ejb-jar.xml">
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE ejb-jar PUBLIC
	"-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN"
	"http://java.sun.com/dtd/ejb-jar_2_0.dtd">

<!-- File generated by ejbgen. Do not modify manually. -->

<ejb-jar id="ejb-jar_ID">

   <enterprise-beans>

      <!-- session beans -->
<@forAllTypes var="bean" annotation="org.highway.service.ejb.GenerateEjb" includeInterfaces="true">
      <session id="${bean.simpleName}">
         <ejb-name>${bean.beanFullClassName}</ejb-name>
         <home>${bean.homeFullClassName}</home>
         <remote>${bean.remoteFullClassName}</remote>
         <ejb-class>${bean.beanFullClassName}</ejb-class>
         <session-type>Stateless</session-type>
         <transaction-type>Bean</transaction-type>
      </session>

</@forAllTypes>
   </enterprise-beans>

</ejb-jar>
</@file>