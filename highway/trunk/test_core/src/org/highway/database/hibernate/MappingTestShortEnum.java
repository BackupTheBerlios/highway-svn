package org.highway.database.hibernate;


public enum MappingTestShortEnum {
	VALUE_1 ((short)1),
	VALUE_2 ((short)2),
	VALUE_3 ((short)3),
	VALUE_4 ((short)4);
	private short code;
	private MappingTestShortEnum(short code)
	{
		this.code = code;
	}
	public short getCode(){
		return code;
	}
	public String toString(){
		return ""+code;
	}


}
