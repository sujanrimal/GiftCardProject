/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.cmsfacades.types.populator;

import de.hybris.platform.cmsfacades.data.ComponentTypeAttributeData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.type.AttributeDescriptorModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Set;

import org.springframework.beans.factory.annotation.Required;


/**
 *	Populator that populates the containedTypes of the {@link ComponentTypeAttributeData} POJO.
 */
public class ContainedTypesComponentTypeAttributePopulator implements
		Populator<AttributeDescriptorModel, ComponentTypeAttributeData>
{
	private Set<String> containedTypes;

	@Override
	public void populate(final AttributeDescriptorModel source, final ComponentTypeAttributeData target) throws ConversionException
	{
		target.setContainedTypes(this.getContainedTypes());
	}

	protected Set<String> getContainedTypes()
	{
		return containedTypes;
	}

	@Required
	public void setContainedTypes(Set<String> containedTypes)
	{
		this.containedTypes = containedTypes;
	}
}