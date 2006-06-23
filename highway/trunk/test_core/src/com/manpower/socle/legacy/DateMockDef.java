package org.highway.legacy;

import java.util.Date;

import org.highway.vo.ValueObject;

@org.highway.annotation.ValueObject
public interface DateMockDef extends ValueObject
{
    /**
     * @socle.legacy.date.format ddMMyyyy
     * 
     * @return
     */
    public Date getAttribute();
}
