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
@VoMapping(table="MAPPING_TEST")
public interface MappingTestVoDef extends ValueObject
{
	/**
	 * @socle.mapping.id column="PRIMITIVE_LONG"
	 */
	 @VoMappingId(column="PRIMITIVE_LONG")
	long getPrimitiveLongProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_BOOLEAN"
	 */
	 @VoMappingProperty(column="PRIMITIVE_BOOLEAN")
	boolean getPrimitiveBooleanProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_CHAR"
	 */
	 @VoMappingProperty(column="PRIMITIVE_CHAR")
	char getPrimitiveCharProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_DOUBLE"
	 */
	 @VoMappingProperty(column="PRIMITIVE_DOUBLE")
	double getPrimitiveDoubleProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_FLOAT"
	 */
	 @VoMappingProperty(column="PRIMITIVE_FLOAT")
	float getPrimitiveFloatProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_INT"
	 */
	 @VoMappingProperty(column="PRIMITIVE_INT")
	int getPrimitiveIntProperty();
	
	/**
	 * @socle.mapping.property column="PRIMITIVE_SHORT"
	 */
	 @VoMappingProperty(column="PRIMITIVE_SHORT")
	short getPrimitiveShortProperty();
	
	/**
	 * @socle.mapping.property column="LONG"
	 */
	 @VoMappingProperty(column="LONG")
	Long getLongProperty();
	
	/**
	 * @socle.mapping.property column="BOOLEAN"
	 */
	 @VoMappingProperty(column="BOOLEAN")
	Boolean getBooleanProperty();
	
	/**
	 * @socle.mapping.property column="CHARACTER"
	 */
	 @VoMappingProperty(column="CHARACTER")
	Character getCharacterProperty();
	
	/**
	 * @socle.mapping.property column="DOUBLE"
	 */
	 @VoMappingProperty(column="DOUBLE")
	Double getDoubleProperty();
	
	/**
	 * @socle.mapping.property column="FLOAT"
	 */
	 @VoMappingProperty(column="FLOAT")
	Float getFloatProperty();
	
	/**
	 * @socle.mapping.property column="INTEGER"
	 */
	 @VoMappingProperty(column="INTEGER")
	Integer getIntegerProperty();
	
	/**
	 * @socle.mapping.property column="SHORT"
	 */
	 @VoMappingProperty(column="SHORT")
	Short getShortProperty();
	
	/**
	 * @socle.mapping.property column="STRING"
	 */
	 @VoMappingProperty(column="STRING")
	String getStringProperty();
	
	/**
	 * @socle.mapping.property column="SQL_DATE"
	 */
	 @VoMappingProperty(column="SQL_DATE")
	java.sql.Date getSqlDateProperty();
	
	/**
	 * @socle.mapping.property column="UTIL_DATE"
	 */
	 @VoMappingProperty(column="UTIL_DATE")
	java.util.Date getUtilDateProperty();
	
	/**
	 * @socle.mapping.property column="TIME"
	 */
	 @VoMappingProperty(column="TIME")
	Time getTimeProperty();
	
	/**
	 * @socle.mapping.property column="TIMESTAMP"
	 */
	 @VoMappingProperty(column="TIMESTAMP")
	Timestamp getTimestampProperty();
	
	/**
	 * @socle.mapping.property column="BIG_DECIMAL"
	 */
	 @VoMappingProperty(column="BIG_DECIMAL")
	BigDecimal getBigDecimalProperty();
	
	/*
	 * @socle.mapping.property column="BIG_INTEGER"
	 */
	 @VoMappingProperty(column="BIG_INTEGER")
	BigInteger getBigIntegerProperty();
	
	/**
	 * @socle.mapping.property column="DECIMAL"
	 */
	@VoMappingProperty(column="DECIMAL")
	Decimal getDecimalProperty();
	
	/*
	 * @socle.mapping.property column="CHAR_ENUM"
	 */
	@VoMappingProperty(column="CHAR_ENUM")
	MappingTestCharEnum getCharEnumProperty();
	
	/**
	 * @socle.mapping.property column="SHORT_ENUM"
	 */
	@VoMappingProperty(column="SHORT_ENUM")
	MappingTestShortEnum getShortEnumProperty();
	
	/**
	 * @socle.mapping.property column="STRING_ENUM"
	 */
	@VoMappingProperty(column="STRING_ENUM")
	MappingTestStringEnum getStringEnumProperty();
	
	/**
	 * @socle.mapping.property column="BLOB" 
	 *                         type="org.highway.database.hibernate.ByteArray2BlobHibernateType"
	 */
	@VoMappingProperty(column="BLOB")
	byte[] getByteArrayBlobProperty();
	
	/**
	 * @socle.mapping.property column="CLOB"
	 *                         type="org.highway.database.hibernate.String2ClobHibernateType"
	 */
	@VoMappingProperty(column="CLOB")
	String getStringClobProperty();
}
