/*
 * Created on 27 mai 2005
 */
package org.highway.servicetest.access.firme;

import java.util.ArrayList;
import java.util.List;

import org.highway.servicetest.access.facture.FactureEcheance;
import org.highway.vo.Enum;

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
