/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package de.hybris.platform.odata2services.odata.persistence.exception;

import de.hybris.platform.odata2services.odata.persistence.InvalidDataException;

public class MissingNavigationPropertyException extends InvalidDataException
{
	private static final String ERROR_CODE = "missing_nav_property";
	private static final String MISSING_ENTITY_TYPE_MESSAGE = "Required navigationProperty for EntityType [%s] does not exist in the System";

	public MissingNavigationPropertyException(final String entityType, final String propertyName)
	{
		super(ERROR_CODE, "Missing [" + propertyName + "]. " + String.format(MISSING_ENTITY_TYPE_MESSAGE, entityType));
	}

	public MissingNavigationPropertyException(final String entityType)
	{
		super(ERROR_CODE, String.format(MISSING_ENTITY_TYPE_MESSAGE, entityType));
	}
}
