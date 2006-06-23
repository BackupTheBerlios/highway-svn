package com.manpower.servicetest.access.employe;

import java.util.ArrayList;
import java.util.List;

public class EmployeNiveau extends Enum {

    public static final EmployeNiveau DEBUTANT = new EmployeNiveau("DEBUTANT");
    public static final EmployeNiveau CONFIRME = new EmployeNiveau("CONFIRME");
    public static final EmployeNiveau SENIOR = new EmployeNiveau("SENIOR");
    public static final EmployeNiveau EXPERT = new EmployeNiveau("EXPERT");

    private EmployeNiveau(String code)
    {
        super(code);
    }
    
    public static List getAll()
    {
        return new ArrayList(getAll(EmployeNiveau.class));
    }

}