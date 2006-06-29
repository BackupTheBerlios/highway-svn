package com.dexia.sofaxis.referentieltiers.access.personnemorale;


import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingDiscriminator;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vo.ValueObject;


@org.highway.annotation.ValueObject
@VoMapping(table="REFERENTIELTIERS")
@VoMappingDiscriminator(column="TYPETIERS", type="string")
public interface ReferentielTiersDef extends ValueObject {

	@VoMappingId(column="TIERSID")
    public String getTiersId();
    @VoMappingProperty(column="IDENTIFIANTFONCTIONNEL")
    public String getIdentifiantFonctionnel();
    @VoMappingProperty(column="NOMCOURT")
    public String getNomCourt();   
    @VoMappingProperty(column="ADRESSEID")
    public String getAdresseId();
    @VoMappingProperty(column="RIBID")
    public String getRibId();
    
}
