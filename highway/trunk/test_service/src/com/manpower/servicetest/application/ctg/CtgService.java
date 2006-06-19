package com.manpower.servicetest.application.ctg;

import com.manpower.servicetest.application.ApplicationService;

/**
 * @socle.service.generate.ejbzip
 */
public interface CtgService extends ApplicationService
{
    /**
     * @socle.service.transaction NotSupported
     */
	Output1 lectureInterimaire(Input1 input);
}
