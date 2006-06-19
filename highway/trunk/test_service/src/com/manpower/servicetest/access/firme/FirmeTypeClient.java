/*
 * Created on 27 mai 2005
 */
package com.manpower.servicetest.access.firme;

import java.util.ArrayList;
import java.util.List;

import com.manpower.servicetest.access.facture.FactureEcheance;

/**
 * @author PLOURIN-Y
 */
public class FirmeTypeClient extends Enum 
{
    public static final FirmeTypeClient NORMAL = new FirmeTypeClient('N', "Normal");
    public static final FirmeTypeClient DOUTEUX = new FirmeTypeClient('D', "Douteux");

    private FirmeTypeClient(char code, String description)
    {
        super(code, description);
    }
    
    public static List getAll()
    {
        return new ArrayList(getAll(FactureEcheance.class));
    }
}
