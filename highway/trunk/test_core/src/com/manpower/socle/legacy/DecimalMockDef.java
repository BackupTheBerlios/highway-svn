package com.manpower.socle.legacy;

import com.manpower.socle.vo.Decimal;
import com.manpower.socle.vo.ValueObject;

public interface DecimalMockDef extends ValueObject
{
    /**
     * @socle.legacy.int.part.length 6
     * @socle.legacy.dec.part.length 5
     * @socle.legacy.separator .
     * @socle.legacy.padding.value 0
     * 
     * @return
     */
    public Decimal getAttribute();

    /**
     * @socle.legacy.int.part.length 6
     * @socle.legacy.dec.part.length 5
     * @socle.legacy.padding.value 0
     * 
     * @return
     */
    public Decimal getAttributeWithNoSepValue();

    /**
     * @socle.legacy.int.part.length 6
     * @socle.legacy.dec.part.length 5
     * @socle.legacy.separator none
     * @socle.legacy.padding.value 0
     * 
     * @return
     */
    public Decimal getAttributeWithSepValue();
}
