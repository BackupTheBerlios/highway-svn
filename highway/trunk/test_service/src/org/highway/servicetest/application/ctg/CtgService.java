package org.highway.servicetest.application.ctg;

import org.highway.servicetest.application.ApplicationService;

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
