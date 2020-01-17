package com.sap.hybris.cec.sso.jalo;

import com.sap.hybris.cec.sso.constants.CecssoConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class CecssoManager extends GeneratedCecssoManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( CecssoManager.class.getName() );
	
	public static final CecssoManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (CecssoManager) em.getExtension(CecssoConstants.EXTENSIONNAME);
	}
	
}
