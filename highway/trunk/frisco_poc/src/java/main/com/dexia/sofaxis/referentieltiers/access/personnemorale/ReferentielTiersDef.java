package com.dexia.sofaxis.referentieltiers.access.personnemorale;


import org.highway.bean.ValueObject;
import org.highway.database.DiscriminatorColumn;
import org.highway.database.Identity;
import org.highway.database.MappedOn;


@MappedOn("REFERENTIEL_TIERS")
@DiscriminatorColumn(column="TYPETIERS", type="java.lang.String")
public interface ReferentielTiersDef extends ValueObject {

	@Identity
	@MappedOn("TIERSID")
    public String getTiersId();
    @MappedOn("IDENTIFIANTFONCTIONNEL")
    public String getIdentifiantFonctionnel();
    @MappedOn("NOMCOURT")
    public String getNomCourt();   
    @MappedOn("ADRESSEID")
    public String getAdresseId();
    @MappedOn("RIBID")
    public String getRibId();
    
}
