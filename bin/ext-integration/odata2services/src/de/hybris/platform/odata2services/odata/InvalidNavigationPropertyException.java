/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.odata2services.odata;

import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.odata2services.odata.persistence.InvalidDataException;

public class InvalidNavigationPropertyException extends InvalidDataException
{
	private static final String CODE = "invalid_property_definition";
	private final String propertyName;
	private final String entityType;

	private InvalidNavigationPropertyException(final String message, final String entityType, final String propertyName)
	{
		super(CODE, String.format(message, entityType, propertyName));
		this.propertyName = propertyName;
		this.entityType = entityType;
	}

	public InvalidNavigationPropertyException(final String message, final TypeAttributeDescriptor descriptor)
	{
		this(message, descriptor.getTypeDescriptor().getTypeCode(), descriptor.getAttributeName());
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public String getEntityType()
	{
		return entityType;
	}
}
