package org.highway.servicetest.access.interimaire;

import javax.resource.cci.InteractionSpec;

import org.highway.legacy.LegacyRequest;
import org.highway.legacy.LegacyResponse;
import org.highway.legacy.LegacySession;

import org.highway.servicetest.access.LegacyAccessImplAbstract;

public class InterimaireAccess1Impl extends LegacyAccessImplAbstract
	implements InterimaireAccess1
{
    public Output1 lectureInterimaire(Input1 input)
    {
        LegacySession session = getSession();
        LegacyRequest request = session.newRequest();
        request.setFunctionName("MEVAPI");
        request.setInteractionVerb(InteractionSpec.SYNC_SEND_RECEIVE);
        request.setTPNName("INCS");
        request.setTranName("INCS");

        request.write(input, Input1.VERSION);
        request.write(input, Input1.MODULE);
        request.write(input, Input1.CODE_FONCTION);
        request.write(input, Input1.PARAM_LENGTH);
        request.write(input, Input1.PARAM);
        request.setCommareaLength(1000);
        
        LegacyResponse response = request.execute();
		
        // récupération du flux de sortie
        Output1 output = new Output1();
        response.read(output, Output1.HEADER);
        response.read(output, Output1.A_D__E_M_A_I_L);
        response.read(output, Output1.C_M_T__E_M_A_I_L);
        response.read(output, Output1.C_M_T__T_E_L__I_N_T_1);
        response.read(output, Output1.C_M_T__T_E_L__I_N_T_2);
        response.read(output, Output1.C_M_T__T_E_L__I_N_T_3);
        response.read(output, Output1.C_O_D__D_E_P_L__E_T_R);
        response.read(output, Output1.C_O_D__G_X_P);
        response.read(output, Output1.C_O_D__H_A_N__I_N_T);
        response.read(output, Output1.C_O_D__I_N_V__M_P_F);
        response.read(output, Output1.C_O_D__N__S_S__I_N_T);
        response.read(output, Output1.C_O_D__N_A_L__I_N_T);
        response.read(output, Output1.C_O_D__N_B__K_M__M_A_X);
        response.read(output, Output1.C_O_D__O_R_G_N__C_A_N);
        response.read(output, Output1.C_O_D__S_O_C);
        response.read(output, Output1.C_O_D__T_P_S__T_R_J__M_A_X);
        response.read(output, Output1.C_O_D__T_Y_P__T_E_L_1);
        response.read(output, Output1.C_O_D__T_Y_P__T_E_L_2);
        response.read(output, Output1.C_O_D__T_Y_P__T_E_L_3);
        response.read(output, Output1.C_O_M__A_D);
        response.read(output, Output1.C_O_M_P__A_D);
        response.read(output, Output1.C_P__A_D);
        response.read(output, Output1.C_P__N_A_I_S__I_N_T);
        response.read(output, Output1.D_E_S_T__P_A_I__I_N_T);
        response.read(output, Output1.D_S_S__D_E_R__P_A_I__I_N_T);
        response.read(output, Output1.D_T_S__C_R_E__P_R_E_N_R);
        response.read(output, Output1.D_T_S__D_E_R__C_T_A_C_T);
        response.read(output, Output1.D_T_S__D_I_S_P__C_A_N);
        response.read(output, Output1.D_T_S__F_I_N__N_T_F__I_N_V);
        response.read(output, Output1.D_T_S__I_M_M_T__I_N_T);
        response.read(output, Output1.D_T_S__I_N_V__T_R_V__I_N_T);
        response.read(output, Output1.D_T_S__M_A_J__C_O_D__G_X_P);
        response.read(output, Output1.D_T_S__M_A_J__D_O_S);
        response.read(output, Output1.D_T_S__M_A_J__E_X_P);
        response.read(output, Output1.D_T_S__N_A_I_S__I_N_T);
        response.read(output, Output1.D_T_S__P_S_G__I_N_T);
        response.read(output, Output1.D_T_S__V_I_S__E_M_B);
        response.read(output, Output1.E_N_F_T__C_H_R_G__I_N_T);
        response.read(output, Output1.I_D__A_U_T_N__T_R_S);
        response.read(output, Output1.I_D__D_O_S__C_M_P);
        response.read(output, Output1.I_D__D_O_S__S_O_M);
        response.read(output, Output1.I_D__D_R_T__G_C_H);
        response.read(output, Output1.I_D__I_N_T__F_O);
        response.read(output, Output1.I_D__I_N_T__R_C_R_T__G_E_S);
        response.read(output, Output1.I_D__M_A_J_O_R);
        response.read(output, Output1.I_D__N_R_C_M_D);
        response.read(output, Output1.I_D__O_B_L__M_I_L);
        response.read(output, Output1.I_D__P_O_S_T__N_Q_L_F);
        response.read(output, Output1.I_D__P_O_S_T__P_E_R);
        response.read(output, Output1.I_D__R_S_V);
        response.read(output, Output1.I_D__S_A);
        response.read(output, Output1.I_D__S_H_T__F_O);
        response.read(output, Output1.I_D__V_T_G);
        response.read(output, Output1.M_D__P_A_I__I_N_T);
        response.read(output, Output1.M_T__T_R_O_P__P_E_R_C_U);
        response.read(output, Output1.N_I_V__I_N_V__T_R_V__I_N_T);
        response.read(output, Output1.N_M__I_N_T);
        response.read(output, Output1.N_M__J_F__I_N_T);
        response.read(output, Output1.N_M__P_R_S__A_C_C_D_T);
        response.read(output, Output1.N_M__S_G_N_T__D_O_S);
        
        return output;
    }
}
