package org.highway.vo;

import org.highway.bean.ValueObject;
import org.highway.database.DiscriminatorColumn;
import org.highway.database.DiscriminatorValue;
import org.highway.database.Identity;
import org.highway.database.IdentityGenerator;
import org.highway.database.IdentityGeneratorParam;
import org.highway.database.MappedOn;
import org.highway.vogen.SerialVersionUID;

@SerialVersionUID(value = 123L)
@MappedOn("nomTable")
@DiscriminatorValue("VO_HIGHWAY")
@DiscriminatorColumn(column = "type_vo")
public interface TestMappingDef extends ValueObject {

	@Identity
	@MappedOn("myColumn")
	@IdentityGenerator("native")
	@IdentityGeneratorParam(name = "paramName1", value = "paramValue1")
	public String getNom();

	@MappedOn("myColumn2")
	public String getPrenom();

}
