/*
 * [y] hybris Platform
 *
 * Copyright (c) 2019 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.integrationservices.integrationkey.impl;

import de.hybris.platform.integrationservices.integrationkey.KeyValue;
import de.hybris.platform.integrationservices.model.KeyDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;

import java.util.Map;
import java.util.regex.Pattern;

public class DefaultMapToIntegrationKeyGenerator extends AbstractIntegrationKeyGenerator<TypeDescriptor, Map<String, Object>>
{
	protected static final Pattern pattern = Pattern.compile("/Date\\((\\d+)\\)/");

	@Override
	public String generate(final TypeDescriptor itemType, final Map<String, Object> entry)
	{
		final KeyDescriptor keyDescriptor = itemType.getKeyDescriptor();
		final KeyValue key = keyDescriptor.calculateKey(entry);

		return serializeKeyToString(key);
	}

	@Override
	protected String getTypeCode(final TypeDescriptor type)
	{
		return type.getItemCode();
	}

	@Override
	protected Object getProperty(final Map<String, Object> entry, final String propertyName)
	{
		return entry.get(propertyName);
	}

	@Override
	protected String transformValueToString(final Object attributeValue)
	{
		if (attributeValue instanceof String && pattern.matcher((String) attributeValue).matches())
		{
			final String date = attributeValue.toString();
			return date.substring(6, date.length() - 2);
		}
		return String.valueOf(attributeValue);
	}
}
