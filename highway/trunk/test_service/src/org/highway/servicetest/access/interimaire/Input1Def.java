package org.highway.servicetest.access.interimaire;

import org.highway.vo.ValueObject;

/**
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
@org.highway.annotation.ValueObject
public interface Input1Def extends ValueObject
{
    /**
     * @socle.legacy.converter.name INTEGER_4
     * @return
     */
    public int getVersion();

    /**
     * @socle.legacy.converter.name STRING_8
     * @return
     */
    public String getModule();

    /**
     * @socle.legacy.converter.name STRING_2
     * @return
     */
    public String getCodeFonction();

    /**
     * @socle.legacy.converter.name INTEGER_5
     * @return
     */
    public int getParamLength();

    /**
     * @socle.legacy.converter.name INTEGER_10
     * @return
     */
    public int getParam();
}