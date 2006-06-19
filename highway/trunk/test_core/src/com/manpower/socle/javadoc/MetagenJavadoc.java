package com.manpower.socle.javadoc;

import java.util.List;

/**
 * 
 * @author yannick plourin
 * Classe de test de la generation des mete data
 * @test this is a test
 */
public class MetagenJavadoc {

	
	public  MetagenJavadoc() {
		
	}
	/**
	 * @test1 essai1
	 * @test2 essai2 
	 */
	public List listeners;
	
	/**
	 * @param s: string
	 * @return : a string
	 */
	public String returnStringParamString(String s){
		return "essai";	
	}

	/**
	 * @param s: string
	 * @return : a string array
	 */
	public String[] returnArrayParamString(String s){
		String[] essaiArray = {"test","test2"};
		return essaiArray;
	}
	
	/**
	 * 
	 * @param s: string array
	 * @return : a string
	 */
	public String returnStringEnterArray(String[] s){
		return s[0];
	}

	/**
	 * @param s: string array
	 * @return : a string array
	 */
	public String[] returnArrayEnterArray(String[] s){
		return s;
	}


	/**
	 * @param s: primitive
	 * @return : a primitive
	 */
	public int returnPrimitiveParamPrimitive(int i){
		return i;	
	}

	/**
	 * @param s: primitive
	 * @return : a primitive array
	 */
	public int[] returnPrimitiveArrayParamPrimitive(int s){
		int[] essaiArray = {1,2};
		return essaiArray;
	}
	
	/**
	 * 
	 * @param s: primitive array
	 * @return : a primitive
	 */
	public int returnPrimitiveEnterPrimitiveArray(int[] s){
		return s[0];
	}

	/**
	 * @param s: primitive array
	 * @return : a primitive array
	 */
	public int[] returnPrimitiveArrayEnterPrimitiveArray(int[] s){
		return s;
	}
	

	/**
	 * 
	 * @param1 i
	 * @param2 s
	 * @return : a primitive
	 */
	public int returnPrimitiveParamPrimitiveAndString(int i, String s){
		return i;	
	}

	/**
	 * 
	 * @param1 i
	 * @param2 s
	 * @return : a primitive array
	 */
	public int[] returnPrimitiveArrayParamPrimitiveAndStringArray(int i, String[] s){
		int[] essaiArray = {1,2};
		return essaiArray;
	}
	
	/**
	 * 
	 * @param1 i
	 * @param2 s
	 * @return : a primitive
	 */
	public int returnPrimitiveEnterPrimitiveArrayAndString(int[] i, String s){
		return i[0];
	}

	/**
	 * 
	 * @param1 i
	 * @param2 s
	 * @return : a primitive array
	 */
	public int[] returnPrimitiveArrayEnterPrimitiveArrayAndStringArray(int[] i, String[] s){
		return i;
	}
	
	
	/**
	 * 
	 * @tagParam param
	 * @exception exception
	 */
	public void getAllTagsMethod(){
		
	}
}
