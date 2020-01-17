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
package de.hybris.platform.integrationservices.populator;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


/**
 * Populate the atomic type of item model's attribute to Map.
 */
public class DefaultAtomicType2MapPopulator extends AbstractItem2MapPopulator<Object>
{
	private static final String DATE_FORMAT = "/Date(%s)/";
	private static final String BIGDECIMAL_FORMAT = "##0.##E0";

	@Override
	protected void populateToMap(
			final TypeAttributeDescriptor attribute,
			final ItemToMapConversionContext context,
			final Map<String, Object> target)
	{
		final ItemModel itemModel = context.getItemModel();
		final Object value = getAttributeValue(itemModel, attribute);

		if (value == null)
		{
			return;
		}

		final String attributeName = attribute.getAttributeName();
		if (value instanceof Date)
		{
			target.put(attributeName, String.format(DATE_FORMAT, ((Date) value).getTime()));
		}
		else if (value instanceof Long)
		{
			target.put(attributeName, value.toString());
		}
		else if (value instanceof BigDecimal)
		{
			final DecimalFormat df = new DecimalFormat(BIGDECIMAL_FORMAT);
			df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

			target.put(attributeName, df.format(value));
		}
		else
		{
			target.put(attributeName, value);
		}
	}

	@Override
	protected boolean isApplicable(final TypeAttributeDescriptor attributeDescriptor)
	{
		return attributeDescriptor.isPrimitive();
	}
}
