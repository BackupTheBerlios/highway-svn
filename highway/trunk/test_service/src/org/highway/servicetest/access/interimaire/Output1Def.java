package org.highway.servicetest.access.interimaire;

import java.util.Date;

import org.highway.vo.ValueObject;

/**
 * 
 * @since 1.3
 * 
 */
@org.highway.annotation.ValueObject
public interface Output1Def extends ValueObject
{
    /**
     * 
     * @socle.legacy.converter.name STRING_10
     * @return
     */
    public String getHeader();

    /**
     * the property type will be used...
     * 
     * @socle.legacy.length 40
     * @socle.legacy.padding.strategy right
     * @socle.legacy.padding.value space
     * 
     * @return
     */
    public String getAD_EMAIL();

    /**
     * 
     * @socle.legacy.converter.name STRING_20
     * @return
     */
    public String getCMT_EMAIL();

    /**
     * 
     * @socle.legacy.converter.name STRING_15
     * @return
     */
    public String getCMT_TEL_INT_1();

    /**
     * 
     * @socle.legacy.converter.name STRING_15
     * @return
     */
    public String getCMT_TEL_INT_2();

    /**
     * 
     * @socle.legacy.converter.name STRING_15
     * @return
     */
    public String getCMT_TEL_INT_3();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getCOD_DEPL_ETR();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getCOD_GXP();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getCOD_HAN_INT();

    /**e
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getCOD_INV_MPF();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getCOD_N_SS_INT();

    /**
     * 
     * @socle.legacy.converter.name STRING_3
     * @return
     */
    public String getCOD_NAL_INT();

    /**
     * 
     * @socle.legacy.converter.name STRING_2
     * @return
     */
    public String getCOD_NB_KM_MAX();

    /**
     * 
     * @socle.legacy.converter.name STRING_2
     * @return
     */
    public String getCOD_ORGN_CAN();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getCOD_SOC();

    /**
     * 
     * @socle.legacy.converter.name STRING_2
     * @return
     */
    public String getCOD_TPS_TRJ_MAX();

    /**
     * 
     * @socle.legacy.converter.name STRING_2
     * @return
     */
    public String getCOD_TYP_TEL_1();

    /**
     * 
     * @socle.legacy.converter.name STRING_2
     * @return
     */
    public String getCOD_TYP_TEL_2();

    /**
     * 
     * @socle.legacy.converter.name STRING_2
     * @return
     */
    public String getCOD_TYP_TEL_3();

    /**
     * 
     * @socle.legacy.converter.name STRING_32
     * @return
     */
    public String getCOM_AD();

    /**
     * 
     * @socle.legacy.converter.name STRING_40
     * @return
     */
    public String getCOMP_AD();

    /**
     * 
     * @socle.legacy.converter.name STRING_5
     * @return
     */
    public String getCP_AD();

    /**
     * 
     * @socle.legacy.converter.name STRING_5
     * @return
     */
    public String getCP_NAIS_INT();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getDEST_PAI_INT();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDSS_DER_PAI_INT();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_CRE_PRENR();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_DER_CTACT();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_DISP_CAN();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_FIN_NTF_INV();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_IMMT_INT();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_INV_TRV_INT();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_MAJ_COD_GXP();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_MAJ_DOS();

    /**
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_MAJ_EXP();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_NAIS_INT();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_PSG_INT();

    /**
     * 
     * @socle.legacy.converter.name DATE
     * @return
     */
    public Date getDTS_VIS_EMB();

    /**
     * 
     * @socle.legacy.converter.name INTEGER_2
     * @return
     */
    public Integer getENFT_CHRG_INT();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_AUTN_TRS();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_DOS_CMP();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_DOS_SOM();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_DRT_GCH();

    /**
     * 
     * @socle.legacy.converter.name STRING_5
     * @return
     */
    public String getID_ENT_NRCMD();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_INT_FO();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_INT_RCRT_GES();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_MAJOR();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_NRCMD();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_OBL_MIL();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_POST_NQLF();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_POST_PER();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_RSV();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_SA();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_SHT_FO();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getID_VTG();

    /**
     * 
     * @socle.legacy.converter.name STRING_1
     * @return
     */
    public String getMD_PAI_INT();

    /**
     * 
     * @socle.legacy.converter.name STRING_9
     * @return
     */
    public String getMT_TROP_PERCU();

    /**
     * 
     * @socle.legacy.converter.name STRING_2
     * @return
     */
    public String getNIV_INV_TRV_INT();

    /**
     * 
     * @socle.legacy.converter.name STRING_22
     * @return
     */
    public String getNM_INT();

    /**
     * 
     * @socle.legacy.converter.name STRING_22
     * @return
     */
    public String getNM_JF_INT();

    /**
     * 
     * @socle.legacy.converter.name STRING_32
     * @return
     */
    public String getNM_PRS_ACCDT();

    /**
     * 
     * @socle.legacy.converter.name STRING_18
     * @return
     */
    public String getNM_SGNT_DOS();
}
