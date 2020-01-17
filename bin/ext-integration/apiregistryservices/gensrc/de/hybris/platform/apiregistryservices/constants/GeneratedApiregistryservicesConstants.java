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
package de.hybris.platform.apiregistryservices.constants;

/**
 * @deprecated since ages - use constants in Model classes instead
 */
@Deprecated
@SuppressWarnings({"unused","cast","PMD"})
public class GeneratedApiregistryservicesConstants
{
	public static final String EXTENSIONNAME = "apiregistryservices";
	public static class TC
	{
		public static final String ABSTRACTCREDENTIAL = "AbstractCredential".intern();
		public static final String ABSTRACTDESTINATION = "AbstractDestination".intern();
		public static final String BASICCREDENTIAL = "BasicCredential".intern();
		public static final String CONSUMEDCERTIFICATECREDENTIAL = "ConsumedCertificateCredential".intern();
		public static final String CONSUMEDDESTINATION = "ConsumedDestination".intern();
		public static final String CONSUMEDOAUTHCREDENTIAL = "ConsumedOAuthCredential".intern();
		public static final String DESTINATIONCHANNEL = "DestinationChannel".intern();
		public static final String DESTINATIONTARGET = "DestinationTarget".intern();
		public static final String ENDPOINT = "Endpoint".intern();
		public static final String EVENTCONFIGURATION = "EventConfiguration".intern();
		public static final String EVENTEXPORTDEADLETTER = "EventExportDeadLetter".intern();
		public static final String EVENTMAPPINGCONSTRAINT = "EventMappingConstraint".intern();
		public static final String EVENTMAPPINGTYPE = "EventMappingType".intern();
		public static final String EVENTPRIORITY = "EventPriority".intern();
		public static final String EVENTPROPERTYCONFIGURATION = "EventPropertyConfiguration".intern();
		public static final String EXPOSEDDESTINATION = "ExposedDestination".intern();
		public static final String EXPOSEDOAUTHCREDENTIAL = "ExposedOAuthCredential".intern();
		public static final String PROCESSEVENTCONFIGURATION = "ProcessEventConfiguration".intern();
	}
	public static class Attributes
	{
		public static class OAuthClientDetails
		{
			public static final String OAUTHURL = "oAuthUrl".intern();
		}
	}
	public static class Enumerations
	{
		public static class DestinationChannel
		{
			public static final String KYMA = "KYMA".intern();
		}
		public static class EventMappingType
		{
			public static final String GENERIC = "GENERIC".intern();
			public static final String BEAN = "BEAN".intern();
			public static final String PROCESS = "PROCESS".intern();
		}
		public static class EventPriority
		{
			public static final String CRITICAL = "CRITICAL".intern();
			public static final String HIGH = "HIGH".intern();
			public static final String MEDIUM = "MEDIUM".intern();
			public static final String LOW = "LOW".intern();
		}
	}
	public static class Relations
	{
		public static final String DESTINATIONTARGET2DESTINATION = "DestinationTarget2Destination".intern();
		public static final String DESTINATIONTARGET2EVENTCONFIGURATION = "DestinationTarget2EventConfiguration".intern();
		public static final String ENDPOINT2ABSTRACTDESTINATION = "Endpoint2AbstractDestination".intern();
		public static final String EVENTCONFIGURATION2EVENTPROPERTYCONFIGURATION = "EventConfiguration2EventPropertyConfiguration".intern();
	}
	
	protected GeneratedApiregistryservicesConstants()
	{
		// private constructor
	}
	
	
}
