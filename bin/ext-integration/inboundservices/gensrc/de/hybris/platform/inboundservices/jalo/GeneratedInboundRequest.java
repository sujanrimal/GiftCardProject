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
package de.hybris.platform.inboundservices.jalo;

import de.hybris.platform.inboundservices.constants.InboundservicesConstants;
import de.hybris.platform.inboundservices.jalo.InboundRequestError;
import de.hybris.platform.integrationservices.jalo.MonitoredRequest;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem InboundRequest}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedInboundRequest extends MonitoredRequest
{
	/** Qualifier of the <code>InboundRequest.errors</code> attribute **/
	public static final String ERRORS = "errors";
	/**
	* {@link OneToManyHandler} for handling 1:n ERRORS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<InboundRequestError> ERRORSHANDLER = new OneToManyHandler<InboundRequestError>(
	InboundservicesConstants.TC.INBOUNDREQUESTERROR,
	true,
	"inboundRequest",
	null,
	false,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(MonitoredRequest.DEFAULT_INITIAL_ATTRIBUTES);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InboundRequest.errors</code> attribute.
	 * @return the errors
	 */
	public Set<InboundRequestError> getErrors(final SessionContext ctx)
	{
		return (Set<InboundRequestError>)ERRORSHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InboundRequest.errors</code> attribute.
	 * @return the errors
	 */
	public Set<InboundRequestError> getErrors()
	{
		return getErrors( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InboundRequest.errors</code> attribute. 
	 * @param value the errors
	 */
	public void setErrors(final SessionContext ctx, final Set<InboundRequestError> value)
	{
		ERRORSHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InboundRequest.errors</code> attribute. 
	 * @param value the errors
	 */
	public void setErrors(final Set<InboundRequestError> value)
	{
		setErrors( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to errors. 
	 * @param value the item to add to errors
	 */
	public void addToErrors(final SessionContext ctx, final InboundRequestError value)
	{
		ERRORSHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to errors. 
	 * @param value the item to add to errors
	 */
	public void addToErrors(final InboundRequestError value)
	{
		addToErrors( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from errors. 
	 * @param value the item to remove from errors
	 */
	public void removeFromErrors(final SessionContext ctx, final InboundRequestError value)
	{
		ERRORSHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from errors. 
	 * @param value the item to remove from errors
	 */
	public void removeFromErrors(final InboundRequestError value)
	{
		removeFromErrors( getSession().getSessionContext(), value );
	}
	
}
