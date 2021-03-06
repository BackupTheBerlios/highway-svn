package org.highway.servicetest.access.employe;

import java.util.ArrayList;
import java.util.List;

import org.highway.bean.Enum;

public class EmployeSexe extends Enum 
{
    public static final EmployeSexe MASCULIN = new EmployeSexe((short)0, "MASCULIN");
    public static final EmployeSexe FEMININ = new EmployeSexe((short)1, "FEMININ");

    private EmployeSexe(short code, String description)
    {
        super(code, description);
    }
    
    public static List getAll()
    {
        return new ArrayList(getAll(EmployeSexe.class));
    }
}
