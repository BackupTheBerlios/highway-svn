package com.manpower.socle.legacy;

import com.manpower.socle.vo.ValueObject;

public interface StringMockDef extends ValueObject
{
    /**
     * @socle.legacy.length 10
     * @socle.legacy.padding.value x
     * @socle.legacy.padding.strategy left
     * 
     * @return
     */
    public String getAttribute();
}
