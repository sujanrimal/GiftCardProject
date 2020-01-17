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
import de.hybris.platform.apiregistryservices.jalo.AbstractDestination;
import de.hybris.platform.apiregistryservices.jalo.events.EventConfiguration;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.apiregistryservices.jalo.DestinationTarget DestinationTarget}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedDestinationTarget extends GenericItem
{
	/** Qualifier of the <code>DestinationTarget.id</code> attribute **/
	public static final String ID = "id";
	/** Qualifier of the <code>DestinationTarget.destinationChannel</code> attribute **/
	public static final String DESTINATIONCHANNEL = "destinationChannel";
	/** Qualifier of the <code>DestinationTarget.eventConfigurations</code> attribute **/
	public static final String EVENTCONFIGURATIONS = "eventConfigurations";
	/** Qualifier of the <code>DestinationTarget.destinations</code> attribute **/
	public static final String DESTINATIONS = "destinations";
	/**
	* {@link OneToManyHandler} for handling 1:n EVENTCONFIGURATIONS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<EventConfiguration> EVENTCONFIGURATIONSHANDLER = new OneToManyHandler<EventConfiguration>(
	ApiregistryservicesConstants.TC.EVENTCONFIGURATION,
	false,
	"destinationTarget",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n DESTINATIONS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<AbstractDestination> DESTINATIONSHANDLER = new OneToManyHandler<AbstractDestination>(
	ApiregistryservicesConstants.TC.ABSTRACTDESTINATION,
	false,
	"destinationTarget",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ID, AttributeMode.INITIAL);
		tmp.put(DESTINATIONCHANNEL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DestinationTarget.destinationChannel</code> attribute.
	 * @return the destinationChannel
	 */
	public EnumerationValue getDestinationChannel(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, DESTINATIONCHANNEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DestinationTarget.destinationChannel</code> attribute.
	 * @return the destinationChannel
	 */
	public EnumerationValue getDestinationChannel()
	{
		return getDestinationChannel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DestinationTarget.destinationChannel</code> attribute. 
	 * @param value the destinationChannel
	 */
	public void setDestinationChannel(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, DESTINATIONCHANNEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DestinationTarget.destinationChannel</code> attribute. 
	 * @param value the destinationChannel
	 */
	public void setDestinationChannel(final EnumerationValue value)
	{
		setDestinationChannel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DestinationTarget.destinations</code> attribute.
	 * @return the destinations
	 */
	public Collection<AbstractDestination> getDestinations(final SessionContext ctx)
	{
		return DESTINATIONSHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DestinationTarget.destinations</code> attribute.
	 * @return the destinations
	 */
	public Collection<AbstractDestination> getDestinations()
	{
		return getDestinations( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DestinationTarget.destinations</code> attribute. 
	 * @param value the destinations
	 */
	public void setDestinations(final SessionContext ctx, final Collection<AbstractDestination> value)
	{
		DESTINATIONSHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DestinationTarget.destinations</code> attribute. 
	 * @param value the destinations
	 */
	public void setDestinations(final Collection<AbstractDestination> value)
	{
		setDestinations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to destinations. 
	 * @param value the item to add to destinations
	 */
	public void addToDestinations(final SessionContext ctx, final AbstractDestination value)
	{
		DESTINATIONSHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to destinations. 
	 * @param value the item to add to destinations
	 */
	public void addToDestinations(final AbstractDestination value)
	{
		addToDestinations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from destinations. 
	 * @param value the item to remove from destinations
	 */
	public void removeFromDestinations(final SessionContext ctx, final AbstractDestination value)
	{
		DESTINATIONSHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from destinations. 
	 * @param value the item to remove from destinations
	 */
	public void removeFromDestinations(final AbstractDestination value)
	{
		removeFromDestinations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DestinationTarget.eventConfigurations</code> attribute.
	 * @return the eventConfigurations
	 */
	public Collection<EventConfiguration> getEventConfigurations(final SessionContext ctx)
	{
		return EVENTCONFIGURATIONSHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DestinationTarget.eventConfigurations</code> attribute.
	 * @return the eventConfigurations
	 */
	public Collection<EventConfiguration> getEventConfigurations()
	{
		return getEventConfigurations( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DestinationTarget.eventConfigurations</code> attribute. 
	 * @param value the eventConfigurations
	 */
	public void setEventConfigurations(final SessionContext ctx, final Collection<EventConfiguration> value)
	{
		EVENTCONFIGURATIONSHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DestinationTarget.eventConfigurations</code> attribute. 
	 * @param value the eventConfigurations
	 */
	public void setEventConfigurations(final Collection<EventConfiguration> value)
	{
		setEventConfigurations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to eventConfigurations. 
	 * @param value the item to add to eventConfigurations
	 */
	public void addToEventConfigurations(final SessionContext ctx, final EventConfiguration value)
	{
		EVENTCONFIGURATIONSHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to eventConfigurations. 
	 * @param value the item to add to eventConfigurations
	 */
	public void addToEventConfigurations(final EventConfiguration value)
	{
		addToEventConfigurations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from eventConfigurations. 
	 * @param value the item to remove from eventConfigurations
	 */
	public void removeFromEventConfigurations(final SessionContext ctx, final EventConfiguration value)
	{
		EVENTCONFIGURATIONSHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from eventConfigurations. 
	 * @param value the item to remove from eventConfigurations
	 */
	public void removeFromEventConfigurations(final EventConfiguration value)
	{
		removeFromEventConfigurations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DestinationTarget.id</code> attribute.
	 * @return the id
	 */
	public String getId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DestinationTarget.id</code> attribute.
	 * @return the id
	 */
	public String getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DestinationTarget.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DestinationTarget.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final String value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
}
