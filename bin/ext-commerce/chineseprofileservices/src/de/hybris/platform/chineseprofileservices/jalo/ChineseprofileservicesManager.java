/*
 *  
 * [y] hybris Platform
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.chineseprofileservices.jalo;

import de.hybris.platform.chineseprofileservices.constants.ChineseprofileservicesConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class ChineseprofileservicesManager extends GeneratedChineseprofileservicesManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( ChineseprofileservicesManager.class.getName() );
	
	public static final ChineseprofileservicesManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (ChineseprofileservicesManager) em.getExtension(ChineseprofileservicesConstants.EXTENSIONNAME);
	}
	
}
