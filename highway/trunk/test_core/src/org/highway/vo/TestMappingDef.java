package org.highway.vo;

import org.highway.annotation.VoSerialVersionUID;
import org.highway.database.Mapped;
import org.highway.database.DiscriminatorColumn;
import org.highway.database.DiscriminatorValue;
import org.highway.database.IdentityGeneratorParam;
import org.highway.database.Identity;
import org.highway.database.VoMappingProperty;
import org.highway.service.ejb.GenerateEjb;

@GenerateEjb
@VoSerialVersionUID(value=123L)
@Mapped(table="nomTable")
@DiscriminatorValue("VO_HIGHWAY")
@DiscriminatorColumn(column="type_vo")

public interface TestMappingDef extends org.highway.bean.ValueObject {
	
	@IdentityGeneratorParam(name="paramName1", value="paramValue1")
	@Identity(column="myColumn", type="string", generatorClass="native")
	@VoMappingProperty(type="type_hibernate_chose2", column="myColumn2")
	public String getNom();
	
	@VoMappingProperty(type="type_hibernate_chose2", column="myColumn2")
	public String getPrenom();
	
}
