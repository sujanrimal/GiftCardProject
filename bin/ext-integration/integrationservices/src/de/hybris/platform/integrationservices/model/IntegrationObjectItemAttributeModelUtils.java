/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.integrationservices.model;

public class IntegrationObjectItemAttributeModelUtils
{
	/**
	 * Returns false if the value is null, otherwise the value is returned
	 * @param value boolean value for the attribute's property that is being evaluated
	 * @return value if non-null, otherwise false
	 */
	public static boolean falseIfNull(final Boolean value)
	{
		return value == null ? false : value;
	}
}
