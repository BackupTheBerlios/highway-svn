package org.highway.database.hibernate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.Decimal;
import org.highway.vo.ValueObject;

/**
 * @socle.mapping table="MAPPING_TEST"
 */
@org.highway.annotation.ValueObject
@VoMapping(table="MAPPING_TEST")
public interface MappingTestVoDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="PRIMITIVE_LONG"
	 */
	 @VoMappingId(column="PRIMITIVE_LONG", type="long")
	long getPrimitiveLongProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_BOOLEAN"
	 */
	 @VoMappingProperty(column="PRIMITIVE_BOOLEAN", type="boolean")
	boolean getPrimitiveBooleanProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_CHAR"
	 */
	 @VoMappingProperty(column="PRIMITIVE_CHAR", type="char")
	char getPrimitiveCharProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_DOUBLE"
	 */
	 @VoMappingProperty(column="PRIMITIVE_DOUBLE", type="double")
	double getPrimitiveDoubleProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_FLOAT"
	 */
	 @VoMappingProperty(column="PRIMITIVE_FLOAT", type="float")
	float getPrimitiveFloatProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_INT"
	 */
	 @VoMappingProperty(column="PRIMITIVE_INT", type="int")
	int getPrimitiveIntProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_SHORT"
	 */
	 @VoMappingProperty(column="PRIMITIVE_SHORT", type="short")
	short getPrimitiveShortProperty();
	
	/**
	 * @socle.mapping.property column="LONG"
	 */
	 @VoMappingProperty(column="LONG", type="Long")
	Long getLongProperty();
	
	/**
	 * @socle.mapping.property column="BOOLEAN"
	 */
	 @VoMappingProperty(column="BOOLEAN", type="Boolean")
	Boolean getBooleanProperty();
	
	/**
	 * @socle.mapping.property column="CHARACTER"
	 */
	 @VoMappingProperty(column="CHARACTER", type="Character")
	Character getCharacterProperty();
	
	/**
	 * @socle.mapping.property column="DOUBLE"
	 */
	 @VoMappingProperty(column="DOUBLE", type="Double")
	Double getDoubleProperty();
	
	/**
	 * @socle.mapping.property column="FLOAT"
	 */
	 @VoMappingProperty(column="FLOAT", type="Float")
	Float getFloatProperty();
	
	/**
	 * @socle.mapping.property column="INTEGER"
	 */
	 @VoMappingProperty(column="INTEGER", type="Integer")
	Integer getIntegerProperty();
	
	/**
	 * @socle.mapping.property column="SHORT"
	 */
	 @VoMappingProperty(column="SHORT", type="Short")
	Short getShortProperty();
	
	/**
	 * @socle.mapping.property column="STRING"
	 */
	 @VoMappingProperty(column="STRING", type="String")
	String getStringProperty();
	
	/**
	 * @socle.mapping.property column="SQL_DATE"
	 */
	 @VoMappingProperty(column="SQL_DATE", type="java.sql.Date")
	java.sql.Date getSqlDateProperty();
	
	/**
	 * @socle.mapping.property column="UTIL_DATE"
	 */
	 @VoMappingProperty(column="UTIL_DATE", type="java.util.Date")
	java.util.Date getUtilDateProperty();
	
	/**
	 * @socle.mapping.property column="TIME"
	 */
	 @VoMappingProperty(column="TIME", type="Time ")
	Time getTimeProperty();
	
	/**
	 * @socle.mapping.property column="TIMESTAMP"
	 */
	 @VoMappingProperty(column="TIMESTAMP", type="Timestamp")
	Timestamp getTimestampProperty();
	
	/**
	 * @socle.mapping.property column="BIG_DECIMAL"
	 */
	 @VoMappingProperty(column="BIG_DECIMAL", type="BigDecimal")
	BigDecimal getBigDecimalProperty();
	
	/*
	 * @socle.mapping.property column="BIG_INTEGER"
	 */
	 @VoMappingProperty(column="BIG_INTEGER", type="BigInteger")
	BigInteger getBigIntegerProperty();
	
	/**
	 * @socle.mapping.property column="DECIMAL"
	 */
	@VoMappingProperty(column="DECIMAL", type="Decimal")
	Decimal getDecimalProperty();
	
	/*
	 * @socle.mapping.property column="CHAR_ENUM"
	 */
	@VoMappingProperty(column="CHAR_ENUM", type="MappingTestCharEnum")
	MappingTestCharEnum getCharEnumProperty();
	
	/**
	 * @socle.mapping.property column="SHORT_ENUM"
	 */
	@VoMappingProperty(column="SHORT_ENUM", type="MappingTestShortEnum")
	MappingTestShortEnum getShortEnumProperty();
	
	/**
	 * @socle.mapping.property column="STRING_ENUM"
	 */
	@VoMappingProperty(column="STRING_ENUM", type="MappingTestStringEnum")
	MappingTestStringEnum getStringEnumProperty();
	
	/**
	 * @socle.mapping.property column="BLOB" 
	 *                         type="org.highway.database.hibernate.ByteArray2BlobHibernateType"
	 */
	@VoMappingProperty(column="BLOB", type="org.highway.database.hibernate.ByteArray2BlobHibernateType")
	byte[] getByteArrayBlobProperty();
	
	/**
	 * @socle.mapping.property column="CLOB"
	 *                         type="org.highway.database.hibernate.String2ClobHibernateType"
	 */
	@VoMappingProperty(column="CLOB", type="org.highway.database.hibernate.String2ClobHibernateType")
	String getStringClobProperty();
}
