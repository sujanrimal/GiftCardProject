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

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.textfieldconfiguratortemplateservices.constants.TextfieldconfiguratortemplateservicesConstants;
import de.hybris.platform.textfieldconfiguratortemplateservices.jalo.TextFieldConfiguratorOrderedProductInfo;
import de.hybris.platform.textfieldconfiguratortemplateservices.jalo.TextFieldConfiguratorSetting;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>TextfieldconfiguratortemplateservicesManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTextfieldconfiguratortemplateservicesManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public TextFieldConfiguratorSetting createTextFieldConfiguratorSetting(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TextfieldconfiguratortemplateservicesConstants.TC.TEXTFIELDCONFIGURATORSETTING );
			return (TextFieldConfiguratorSetting)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating TextFieldConfiguratorSetting : "+e.getMessage(), 0 );
		}
	}
	
	public TextFieldConfiguratorSetting createTextFieldConfiguratorSetting(final Map attributeValues)
	{
		return createTextFieldConfiguratorSetting( getSession().getSessionContext(), attributeValues );
	}
	
	public TextFieldConfiguratorOrderedProductInfo createTextFieldConfiguredProductInfo(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TextfieldconfiguratortemplateservicesConstants.TC.TEXTFIELDCONFIGUREDPRODUCTINFO );
			return (TextFieldConfiguratorOrderedProductInfo)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating TextFieldConfiguredProductInfo : "+e.getMessage(), 0 );
		}
	}
	
	public TextFieldConfiguratorOrderedProductInfo createTextFieldConfiguredProductInfo(final Map attributeValues)
	{
		return createTextFieldConfiguredProductInfo( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return TextfieldconfiguratortemplateservicesConstants.EXTENSIONNAME;
	}
	
}
