package com.sap.platform.jalo;

import com.sap.platform.constants.SapcpconfigurationConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class SapcpconfigurationManager extends GeneratedSapcpconfigurationManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( SapcpconfigurationManager.class.getName() );
	
	public static final SapcpconfigurationManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (SapcpconfigurationManager) em.getExtension(SapcpconfigurationConstants.EXTENSIONNAME);
	}
	
}
