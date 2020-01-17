/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.sap.productconfig.runtime.pci.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.platform.sap.productconfig.runtime.pci.constants.SapproductconfigruntimepciConstants;


/**
 * Jalo Manager class for <code>sapproductconfigruntimepci</code> extension.
 */
public class SapproductconfigruntimepciManager extends GeneratedSapproductconfigruntimepciManager
{

	/**
	 * factory-method for this class
	 *
	 * @return manager instance
	 */
	public static final SapproductconfigruntimepciManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (SapproductconfigruntimepciManager) em.getExtension(SapproductconfigruntimepciConstants.EXTENSIONNAME);
	}

}
