package org.highway.vo;

import org.highway.annotation.GenerateEjb;
import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingDiscriminator;
import org.highway.annotation.VoMappingDiscriminatorValue;
import org.highway.annotation.VoMappingGeneratorParam;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.annotation.VoSerialVersionUID;

@GenerateEjb
@VoSerialVersionUID(value=123L)
@VoMapping(table="nomTable")
@VoMappingDiscriminatorValue("VO_HIGHWAY")
@VoMappingDiscriminator(column="type_vo")

public interface TestMappingDef extends org.highway.vo.ValueObject {
	
	@VoMappingGeneratorParam(name="paramName1", value="paramValue1")
	@VoMappingId(column="myColumn", type="string", generatorClass="native")
	@VoMappingProperty(type="type_hibernate_chose2", column="myColumn2")
	public String getNom();
	
	@VoMappingProperty(type="type_hibernate_chose2", column="myColumn2")
	public String getPrenom();
	
}
