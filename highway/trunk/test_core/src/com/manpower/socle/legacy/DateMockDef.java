package com.manpower.socle.legacy;

import java.util.Date;

import com.manpower.socle.vo.ValueObject;

public interface DateMockDef extends ValueObject
{
    /**
     * @socle.legacy.date.format ddMMyyyy
     * 
     * @return
     */
    public Date getAttribute();
}
