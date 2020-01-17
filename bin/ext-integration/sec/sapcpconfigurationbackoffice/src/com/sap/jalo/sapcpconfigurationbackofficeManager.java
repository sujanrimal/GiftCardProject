package com.sap.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;

import org.apache.log4j.Logger;

import com.sap.constants.SapcpconfigurationbackofficeConstants;


@SuppressWarnings("PMD")
public class sapcpconfigurationbackofficeManager extends GeneratedsapcpconfigurationbackofficeManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(sapcpconfigurationbackofficeManager.class.getName());

	public static final sapcpconfigurationbackofficeManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (sapcpconfigurationbackofficeManager) em.getExtension(SapcpconfigurationbackofficeConstants.EXTENSIONNAME);
	}

}
