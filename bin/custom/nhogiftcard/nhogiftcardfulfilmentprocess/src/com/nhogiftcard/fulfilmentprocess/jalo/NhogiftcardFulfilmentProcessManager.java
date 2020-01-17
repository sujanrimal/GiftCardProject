/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.nhogiftcard.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.nhogiftcard.fulfilmentprocess.constants.NhogiftcardFulfilmentProcessConstants;

public class NhogiftcardFulfilmentProcessManager extends GeneratedNhogiftcardFulfilmentProcessManager
{
	public static final NhogiftcardFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (NhogiftcardFulfilmentProcessManager) em.getExtension(NhogiftcardFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
