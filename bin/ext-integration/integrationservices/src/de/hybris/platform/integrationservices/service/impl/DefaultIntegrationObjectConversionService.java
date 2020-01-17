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
package de.hybris.platform.integrationservices.service.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectItemModel;
import de.hybris.platform.integrationservices.populator.ItemToMapConversionContext;
import de.hybris.platform.integrationservices.service.IntegrationObjectConversionService;
import de.hybris.platform.integrationservices.service.IntegrationObjectNotFoundException;
import de.hybris.platform.integrationservices.service.IntegrationObjectService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Preconditions;

/**
 * The default implementation of IntegrationObjectConversionService.
 */
public class DefaultIntegrationObjectConversionService implements IntegrationObjectConversionService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultIntegrationObjectConversionService.class);

	private IntegrationObjectService integrationObjectService;
	private Converter<ItemToMapConversionContext, Map<String, Object>> itemToIntegrationObjectMapConverter;

	@Override
	public Map<String, Object> convert(final ItemModel itemModel, final String integrationObjectCode)
	{
		Preconditions.checkArgument(itemModel != null, "item cannot be null");

		checkIfIntegrationObjectExists(integrationObjectCode);
		final IntegrationObjectItemModel integrationObjectItemModel =
				getIntegrationObjectService().findIntegrationObjectItemByTypeCode(integrationObjectCode, itemModel.getItemtype());
		final ItemToMapConversionContext conversionContext = getConversionContext(itemModel, integrationObjectItemModel);
		return convert(conversionContext);
	}

	@Override
	public Map<String, Object> convert(final ItemToMapConversionContext context)
	{
		final Map<String, Object> result = context.getConversionResult();
		if (result == null)
		{
			final Map<String, Object> target = new HashMap<>(context.getTypeDescriptor().getAttributes().size());
			context.setConversionResult(target);
			return getItemToIntegrationObjectMapConverter().convert(context, target);
		}
		return result;
	}

	protected ItemToMapConversionContext getConversionContext(final ItemModel item, final IntegrationObjectItemModel objItemModel)
	{
		return new ItemToMapConversionContext(item, objItemModel);
	}

	protected void checkIfIntegrationObjectExists(final String integrationObjectCode)
	{
		try
		{
			getIntegrationObjectService().findIntegrationObject(integrationObjectCode);
		}
		catch(final ModelNotFoundException e)
		{
			final RuntimeException exception = new IntegrationObjectNotFoundException(integrationObjectCode);
			LOGGER.error(exception.getMessage());
			throw exception;
		}
	}

	protected Converter<ItemToMapConversionContext, Map<String, Object>> getItemToIntegrationObjectMapConverter()
	{
		return itemToIntegrationObjectMapConverter;
	}

	@Required
	public void setItemToIntegrationObjectMapConverter(
			final Converter<ItemToMapConversionContext, Map<String, Object>> itemToIntegrationObjectMapConverter)
	{
		this.itemToIntegrationObjectMapConverter = itemToIntegrationObjectMapConverter;
	}

	protected IntegrationObjectService getIntegrationObjectService()
	{
		return integrationObjectService;
	}

	@Required
	public void setIntegrationObjectService(final IntegrationObjectService integrationObjectService)
	{
		this.integrationObjectService = integrationObjectService;
	}
}
