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
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.product.jalo.AbstractConfiguratorSetting;
import de.hybris.platform.textfieldconfiguratortemplateservices.constants.TextfieldconfiguratortemplateservicesConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.textfieldconfiguratortemplateservices.jalo.TextFieldConfiguratorSetting TextFieldConfiguratorSetting}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTextFieldConfiguratorSetting extends AbstractConfiguratorSetting
{
	/** Qualifier of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute **/
	public static final String TEXTFIELDLABEL = "textFieldLabel";
	/** Qualifier of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute **/
	public static final String TEXTFIELDDEFAULTVALUE = "textFieldDefaultValue";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractConfiguratorSetting.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TEXTFIELDLABEL, AttributeMode.INITIAL);
		tmp.put(TEXTFIELDDEFAULTVALUE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute.
	 * @return the textFieldDefaultValue - Default value of the text field
	 */
	public String getTextFieldDefaultValue(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedTextFieldConfiguratorSetting.getTextFieldDefaultValue requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXTFIELDDEFAULTVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute.
	 * @return the textFieldDefaultValue - Default value of the text field
	 */
	public String getTextFieldDefaultValue()
	{
		return getTextFieldDefaultValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute. 
	 * @return the localized textFieldDefaultValue - Default value of the text field
	 */
	public Map<Language,String> getAllTextFieldDefaultValue(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXTFIELDDEFAULTVALUE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute. 
	 * @return the localized textFieldDefaultValue - Default value of the text field
	 */
	public Map<Language,String> getAllTextFieldDefaultValue()
	{
		return getAllTextFieldDefaultValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute. 
	 * @param value the textFieldDefaultValue - Default value of the text field
	 */
	public void setTextFieldDefaultValue(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedTextFieldConfiguratorSetting.setTextFieldDefaultValue requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXTFIELDDEFAULTVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute. 
	 * @param value the textFieldDefaultValue - Default value of the text field
	 */
	public void setTextFieldDefaultValue(final String value)
	{
		setTextFieldDefaultValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute. 
	 * @param value the textFieldDefaultValue - Default value of the text field
	 */
	public void setAllTextFieldDefaultValue(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXTFIELDDEFAULTVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguratorSetting.textFieldDefaultValue</code> attribute. 
	 * @param value the textFieldDefaultValue - Default value of the text field
	 */
	public void setAllTextFieldDefaultValue(final Map<Language,String> value)
	{
		setAllTextFieldDefaultValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute.
	 * @return the textFieldLabel - Label of the text field
	 */
	public String getTextFieldLabel(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedTextFieldConfiguratorSetting.getTextFieldLabel requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXTFIELDLABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute.
	 * @return the textFieldLabel - Label of the text field
	 */
	public String getTextFieldLabel()
	{
		return getTextFieldLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute. 
	 * @return the localized textFieldLabel - Label of the text field
	 */
	public Map<Language,String> getAllTextFieldLabel(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXTFIELDLABEL,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute. 
	 * @return the localized textFieldLabel - Label of the text field
	 */
	public Map<Language,String> getAllTextFieldLabel()
	{
		return getAllTextFieldLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute. 
	 * @param value the textFieldLabel - Label of the text field
	 */
	public void setTextFieldLabel(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedTextFieldConfiguratorSetting.setTextFieldLabel requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXTFIELDLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute. 
	 * @param value the textFieldLabel - Label of the text field
	 */
	public void setTextFieldLabel(final String value)
	{
		setTextFieldLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute. 
	 * @param value the textFieldLabel - Label of the text field
	 */
	public void setAllTextFieldLabel(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXTFIELDLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TextFieldConfiguratorSetting.textFieldLabel</code> attribute. 
	 * @param value the textFieldLabel - Label of the text field
	 */
	public void setAllTextFieldLabel(final Map<Language,String> value)
	{
		setAllTextFieldLabel( getSession().getSessionContext(), value );
	}
	
}
