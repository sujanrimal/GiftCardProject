/**
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.hybris.merchandising.storefrontsampledata.jalo;

import com.hybris.merchandising.storefrontsampledata.constants.MerchandisingstorefrontsampledataaddonConstants;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;

public class MerchandisingstorefrontsampledataaddonManager extends GeneratedMerchandisingstorefrontsampledataaddonManager
{
	public static final MerchandisingstorefrontsampledataaddonManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (MerchandisingstorefrontsampledataaddonManager) em.getExtension(MerchandisingstorefrontsampledataaddonConstants.EXTENSIONNAME);
	}
	
}
