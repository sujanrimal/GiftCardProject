/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 17, 2020 10:18:03 AM                    ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.apiregistryservices.jalo;

import de.hybris.platform.apiregistryservices.constants.ApiregistryservicesConstants;
import de.hybris.platform.apiregistryservices.jalo.AbstractCredential;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.apiregistryservices.jalo.ConsumedOAuthCredential ConsumedOAuthCredential}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedConsumedOAuthCredential extends AbstractCredential
{
	/** Qualifier of the <code>ConsumedOAuthCredential.clientId</code> attribute **/
	public static final String CLIENTID = "clientId";
	/** Qualifier of the <code>ConsumedOAuthCredential.oAuthUrl</code> attribute **/
	public static final String OAUTHURL = "oAuthUrl";
	/** Qualifier of the <code>ConsumedOAuthCredential.clientSecret</code> attribute **/
	public static final String CLIENTSECRET = "clientSecret";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractCredential.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CLIENTID, AttributeMode.INITIAL);
		tmp.put(OAUTHURL, AttributeMode.INITIAL);
		tmp.put(CLIENTSECRET, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedOAuthCredential.clientId</code> attribute.
	 * @return the clientId
	 */
	public String getClientId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLIENTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedOAuthCredential.clientId</code> attribute.
	 * @return the clientId
	 */
	public String getClientId()
	{
		return getClientId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedOAuthCredential.clientId</code> attribute. 
	 * @param value the clientId
	 */
	public void setClientId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLIENTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedOAuthCredential.clientId</code> attribute. 
	 * @param value the clientId
	 */
	public void setClientId(final String value)
	{
		setClientId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedOAuthCredential.clientSecret</code> attribute.
	 * @return the clientSecret
	 */
	public String getClientSecret(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLIENTSECRET);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedOAuthCredential.clientSecret</code> attribute.
	 * @return the clientSecret
	 */
	public String getClientSecret()
	{
		return getClientSecret( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedOAuthCredential.clientSecret</code> attribute. 
	 * @param value the clientSecret
	 */
	public void setClientSecret(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLIENTSECRET,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedOAuthCredential.clientSecret</code> attribute. 
	 * @param value the clientSecret
	 */
	public void setClientSecret(final String value)
	{
		setClientSecret( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedOAuthCredential.oAuthUrl</code> attribute.
	 * @return the oAuthUrl
	 */
	public String getOAuthUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OAUTHURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedOAuthCredential.oAuthUrl</code> attribute.
	 * @return the oAuthUrl
	 */
	public String getOAuthUrl()
	{
		return getOAuthUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedOAuthCredential.oAuthUrl</code> attribute. 
	 * @param value the oAuthUrl
	 */
	public void setOAuthUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OAUTHURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedOAuthCredential.oAuthUrl</code> attribute. 
	 * @param value the oAuthUrl
	 */
	public void setOAuthUrl(final String value)
	{
		setOAuthUrl( getSession().getSessionContext(), value );
	}
	
}
