/*
 * Created on 27 mai 2005
 */
package org.highway.servicetest.access.facture;

import java.util.ArrayList;
import java.util.List;

import org.highway.bean.Enum;

/**
 * @author PLOURIN-Y
 */
public class FactureEcheance extends Enum 
{
    public static final FactureEcheance TRENTE = new FactureEcheance((short)0, "30 jours");
    public static final FactureEcheance SOIXANTE = new FactureEcheance((short)1, "60 jours");

    private FactureEcheance(short code, String description)
    {
        super(code, description);
    }
    
    public static List getAll()
    {
        return new ArrayList(getAll(FactureEcheance.class));
    }
}
