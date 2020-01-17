/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices.model.impl;

import de.hybris.platform.integrationservices.integrationkey.KeyValue;
import de.hybris.platform.integrationservices.model.KeyDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;

import java.util.Map;

/**
 * A key descriptor for types, which do not have primary keys. For example, primitive entity types, enumeration types, etc.
 * Instead of returning {@code null} from {@link TypeDescriptor#getKeyDescriptor()}, this "Null Object" is used in those cases.
 */
class NullKeyDescriptor implements KeyDescriptor
{
	private static final KeyValue EMPTY_KEY_VALUE = new KeyValue();

	@Override
	public KeyValue calculateKey(final Map<String, Object> item)
	{
		return EMPTY_KEY_VALUE;
	}

	@Override
	public boolean isKeyAttribute(final String attr)
	{
		return false;
	}
}
