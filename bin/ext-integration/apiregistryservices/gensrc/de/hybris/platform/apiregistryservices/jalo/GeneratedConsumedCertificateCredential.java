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
 * Generated class for type {@link de.hybris.platform.apiregistryservices.jalo.ConsumedCertificateCredential ConsumedCertificateCredential}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedConsumedCertificateCredential extends AbstractCredential
{
	/** Qualifier of the <code>ConsumedCertificateCredential.certificateData</code> attribute **/
	public static final String CERTIFICATEDATA = "certificateData";
	/** Qualifier of the <code>ConsumedCertificateCredential.privateKey</code> attribute **/
	public static final String PRIVATEKEY = "privateKey";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractCredential.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CERTIFICATEDATA, AttributeMode.INITIAL);
		tmp.put(PRIVATEKEY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedCertificateCredential.certificateData</code> attribute.
	 * @return the certificateData - Serialized authentication object
	 */
	public String getCertificateData(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CERTIFICATEDATA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedCertificateCredential.certificateData</code> attribute.
	 * @return the certificateData - Serialized authentication object
	 */
	public String getCertificateData()
	{
		return getCertificateData( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedCertificateCredential.certificateData</code> attribute. 
	 * @param value the certificateData - Serialized authentication object
	 */
	public void setCertificateData(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CERTIFICATEDATA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedCertificateCredential.certificateData</code> attribute. 
	 * @param value the certificateData - Serialized authentication object
	 */
	public void setCertificateData(final String value)
	{
		setCertificateData( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedCertificateCredential.privateKey</code> attribute.
	 * @return the privateKey - Serialized authentication object
	 */
	public String getPrivateKey(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PRIVATEKEY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsumedCertificateCredential.privateKey</code> attribute.
	 * @return the privateKey - Serialized authentication object
	 */
	public String getPrivateKey()
	{
		return getPrivateKey( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedCertificateCredential.privateKey</code> attribute. 
	 * @param value the privateKey - Serialized authentication object
	 */
	public void setPrivateKey(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PRIVATEKEY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsumedCertificateCredential.privateKey</code> attribute. 
	 * @param value the privateKey - Serialized authentication object
	 */
	public void setPrivateKey(final String value)
	{
		setPrivateKey( getSession().getSessionContext(), value );
	}
	
}
