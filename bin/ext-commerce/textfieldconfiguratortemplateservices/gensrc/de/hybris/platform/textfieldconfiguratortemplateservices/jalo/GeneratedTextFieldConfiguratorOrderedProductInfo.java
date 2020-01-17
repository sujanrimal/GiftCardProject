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
package de.hybris.platform.textfieldconfiguratortemplateservices.jalo;

import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.order.jalo.AbstractOrderEntryProductInfo;
import de.hybris.platform.textfieldconfiguratortemplateservices.constants.TextfieldconfiguratortemplateservicesConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.textfieldconfiguratortemplateservices.jalo.TextFieldConfiguratorOrderedProductInfo TextFieldConfiguredProductInfo}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTextFieldConfiguratorOrderedProductInfo extends AbstractOrderEntryProductInfo
{
	/** Qualifier of the <code>TextFieldConfiguredProductInfo.configurationLabel</code> attribute **/
	public static final String CONFIGURATIONLABEL = "configurationLabel";
	/** Qualifier of the <code>TextFieldConfiguredProductInfo.configurationValue</code> attribute **/
	public static final String CONFIGURATIONVALUE = "configurationValue";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractOrderEntryProductInfo.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CONFIGURATIONLABEL, AttributeMode.INITIAL);
		tmp.put(CONFIGURATIONVALUE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguredProductInfo.configurationLabel</code> attribute.
	 * @return the configurationLabel - Text fiel label
	 */
	public String getConfigurationLabel(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CONFIGURATIONLABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguredProductInfo.configurationLabel</code> attribute.
	 * @return the configurationLabel - Text fiel label
	 */
	public String getConfigurationLabel()
	{
		return getConfigurationLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguredProductInfo.configurationLabel</code> attribute. 
	 * @param value the configurationLabel - Text fiel label
	 */
	public void setConfigurationLabel(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CONFIGURATIONLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguredProductInfo.configurationLabel</code> attribute. 
	 * @param value the configurationLabel - Text fiel label
	 */
	public void setConfigurationLabel(final String value)
	{
		setConfigurationLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguredProductInfo.configurationValue</code> attribute.
	 * @return the configurationValue - Text field value
	 */
	public String getConfigurationValue(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CONFIGURATIONVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguredProductInfo.configurationValue</code> attribute.
	 * @return the configurationValue - Text field value
	 */
	public String getConfigurationValue()
	{
		return getConfigurationValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguredProductInfo.configurationValue</code> attribute. 
	 * @param value the configurationValue - Text field value
	 */
	public void setConfigurationValue(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CONFIGURATIONVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguredProductInfo.configurationValue</code> attribute. 
	 * @param value the configurationValue - Text field value
	 */
	public void setConfigurationValue(final String value)
	{
		setConfigurationValue( getSession().getSessionContext(), value );
	}
	
}
