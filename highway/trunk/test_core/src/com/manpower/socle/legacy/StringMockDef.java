package org.highway.legacy;

import org.highway.vo.ValueObject;
@org.highway.annotation.ValueObject
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
