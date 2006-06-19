package com.manpower.socle.javadoc;

import java.lang.reflect.Method;
import java.util.Iterator;

import junit.framework.TestCase;

import org.highway.javadoc.JavadocHome;

public class MetagenJavadocTest extends TestCase {
	
	   public void testReturnStringParamString() throws Exception
	   {
			MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
			Class[] classs =  new Class[1];
			classs[0] = String.class;
			Method methods = metagenJavadoc.getClass().getMethod("returnStringParamString", classs);
			assertTrue(": a string".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
			assertTrue("s: string".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param")));
	   } 

	   public void testReturnArrayParamString() throws Exception
	   {
			MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
			Class[] classs =  new Class[1];
			classs[0] = String.class;
			Method methods = metagenJavadoc.getClass().getMethod("returnArrayParamString", classs);
			assertTrue(": a string array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
			assertTrue("s: string".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param")));
	   } 

	   public void testReturnStringEnterArray() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Class[] classs =  new Class[1];
		   classs[0] = String[].class;
		   Method methods = metagenJavadoc.getClass().getMethod("returnStringEnterArray", classs);
		   assertTrue(": a string".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
		   assertTrue("s: string array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param")));
	   } 

	   public void testReturnArrayEnterArray() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Class[] classs =  new Class[1];
		   classs[0] = String[].class;
		   Method methods = metagenJavadoc.getClass().getMethod("returnArrayEnterArray", classs);
		   assertTrue(": a string array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
		   assertTrue("s: string array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param")));
	   } 

	   public void testReturnPrimitiveParamPrimitive() throws Exception
	   {
			MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
			Class[] classs =  new Class[1];
			classs[0] = int.class;
			Method methods = metagenJavadoc.getClass().getMethod("returnPrimitiveParamPrimitive", classs);
			assertTrue(": a primitive".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
			assertTrue("s: primitive".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param")));
	   } 

	   public void testReturnPrimitiveArrayParamPrimitive() throws Exception
	   {
			MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
			Class[] classs =  new Class[1];
			classs[0] = int.class;
			Method methods = metagenJavadoc.getClass().getMethod("returnPrimitiveArrayParamPrimitive", classs);
			assertTrue(": a primitive array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
			assertTrue("s: primitive".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param")));
	   } 

	   public void testReturnPrimitiveEnterPrimitiveArray() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Class[] classs =  new Class[1];
		   classs[0] = int[].class;
		   Method methods = metagenJavadoc.getClass().getMethod("returnPrimitiveEnterPrimitiveArray", classs);
		   assertTrue(": a primitive".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
		   assertTrue("s: primitive array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param")));
	   } 

	   public void testReturnPrimitiveArrayEnterPrimitiveArray() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Class[] classs =  new Class[1];
		   classs[0] = int[].class;
		   Method methods = metagenJavadoc.getClass().getMethod("returnPrimitiveArrayEnterPrimitiveArray", classs);
		   assertTrue(": a primitive array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
		   assertTrue("s: primitive array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param")));
	   } 

	   public void testReturnPrimitiveArrayEnterPrimitiveArrayAndString() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Class[] classs =  new Class[2];
		   classs[0] = int.class;
		   classs[1] = String.class;
		   Method methods = metagenJavadoc.getClass().getMethod("returnPrimitiveParamPrimitiveAndString", classs);
		   assertTrue(": a primitive".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
		   assertTrue("i".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param1")));
		   assertTrue("s".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param2")));
	   } 

	   public void testReturnPrimitiveArrayParamPrimitiveAndStringArray() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Class[] classs =  new Class[2];
		   classs[0] = int.class;
		   classs[1] = String[].class;
		   Method methods = metagenJavadoc.getClass().getMethod("returnPrimitiveArrayParamPrimitiveAndStringArray", classs);
		   assertTrue(": a primitive array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
		   assertTrue("i".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param1")));
		   assertTrue("s".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param2")));
	   } 

	   public void testReturnPrimitiveEnterPrimitiveArrayAndString() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Class[] classs =  new Class[2];
		   classs[0] = int[].class;
		   classs[1] = String.class;
		   Method methods = metagenJavadoc.getClass().getMethod("returnPrimitiveEnterPrimitiveArrayAndString", classs);
		   assertTrue(": a primitive".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
		   assertTrue("i".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param1")));
		   assertTrue("s".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param2")));
	   } 

	   public void testReturnPrimitiveArrayEnterPrimitiveArrayAndStringArray() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Class[] classs =  new Class[2];
		   classs[0] = int[].class;
		   classs[1] = String[].class;
		   Method methods = metagenJavadoc.getClass().getMethod("returnPrimitiveArrayEnterPrimitiveArrayAndStringArray", classs);
		   assertTrue(": a primitive array".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"return")));
		   assertTrue("i".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param1")));
		   assertTrue("s".equalsIgnoreCase(JavadocHome.getMethodTag(methods,"param2")));
	   }
	   
	   public void testGetExistingClassTag() throws Exception
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   String classTag = JavadocHome.getClassTag(metagenJavadoc.getClass(), "author");
		   assertNotNull(classTag);
	   }

	   public void testGetNotExistingClassTag()
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   String classTag = JavadocHome.getClassTag(metagenJavadoc.getClass(), "@auteur");
		   assertNull(classTag);
	   }

	   public void testGetExistingFieldTag()
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   String classTag = JavadocHome.getFieldTag(metagenJavadoc.getClass(),"listeners","test1");
		   assertNotNull(classTag);
	   }

	   public void testGetNotExistingFieldTag()
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   String classTag = JavadocHome.getFieldTag(metagenJavadoc.getClass(), "listeners","titi");
		   assertNull(classTag);
	   }
	   
	   public void testGetAllTagsFromField()
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Iterator it = JavadocHome.getFieldTagNames(metagenJavadoc.getClass(), "listeners");
		   while (it.hasNext()){
			   String tagName= (String)it.next();
			   assertTrue("test1".equals(tagName) || "test2".equals(tagName));
			   assertFalse("test3".equals(tagName));
		   }   
	   }
	   
	   public void testGetAllTagsFromClass()
	   {
		   MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
		   Iterator it = JavadocHome.getClassTagNames(metagenJavadoc.getClass());
		   while (it.hasNext()){
			   String tagName= (String)it.next();
			   assertTrue("author".equals(tagName) || "test".equals(tagName));
			   assertFalse("test3".equals(tagName));
		   }   
	   }

	   public void testGetAllTagsFromMethod() throws Exception
	   {
			MetagenJavadoc metagenJavadoc = new MetagenJavadoc();
			Method method = metagenJavadoc.getClass().getMethod("getAllTagsMethod", null);
			Iterator it=  JavadocHome.getMethodTagNames(method);
			while (it.hasNext()){
				String tagName = (String)it.next();
				   assertTrue("tagParam".equals(tagName) || "exception".equals(tagName));
				   assertFalse("test3".equals(tagName));
			}
	   }
}
