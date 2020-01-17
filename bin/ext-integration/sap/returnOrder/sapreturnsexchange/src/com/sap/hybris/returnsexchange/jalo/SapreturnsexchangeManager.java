package com.sap.hybris.returnsexchange.jalo;

import com.sap.hybris.returnsexchange.constants.SapreturnsexchangeConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class SapreturnsexchangeManager extends GeneratedSapreturnsexchangeManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( SapreturnsexchangeManager.class.getName() );
	
	public static final SapreturnsexchangeManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (SapreturnsexchangeManager) em.getExtension(SapreturnsexchangeConstants.EXTENSIONNAME);
	}
	
}
