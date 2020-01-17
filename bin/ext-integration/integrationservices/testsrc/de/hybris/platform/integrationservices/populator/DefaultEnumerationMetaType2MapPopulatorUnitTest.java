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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.enums.ClassificationAttributeTypeEnum;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Maps;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultEnumerationMetaType2MapPopulatorUnitTest
{
	private static final String QUALIFIER = "attributeType";
	private static final String ATTRIBUTE = "type";

	@InjectMocks
	private DefaultEnumerationMetaType2MapPopulator populator;
	@Mock
	private ModelService modelService;

	@Before
	public void setup()
	{
	}

	@Test
	public void shouldPopulateToMap()
	{
		final ItemToMapConversionContext itemToMapConversionContext = conversionContext(enumerationAttribute());
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(itemToMapConversionContext, map);

		assertThat((Map)map.get(ATTRIBUTE)).containsOnly(entry("enumCode", "string"));
	}

	@Test
	public void shouldNotPopulate_whenAttributeIsPrimitive()
	{
		final ItemToMapConversionContext itemToMapConversionContext = conversionContext(primitiveAttribute());
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(itemToMapConversionContext, map);

		assertThat(map).isEmpty();
	}

	@Test
	public void shouldNotPopulate_whenAttributeIsComplexType()
	{
		final ItemToMapConversionContext itemToMapConversionContext = conversionContext(itemReferenceAttribute());
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(itemToMapConversionContext, map);

		assertThat(map).isEmpty();
	}

	@Test
	public void shouldNotPopulate_whenAttributeIsNull()
	{
		final Map<String, Object> map = Maps.newHashMap();
		final ItemToMapConversionContext context = conversionContext(enumerationAttribute(), itemModel(null));

		populator.populate(context, map);

		assertThat(map).isEmpty();
	}

	private ItemToMapConversionContext conversionContext(final TypeAttributeDescriptor attribute)
	{
		return conversionContext(attribute, itemModel());
	}

	private ItemToMapConversionContext conversionContext(final TypeAttributeDescriptor attribute, final ItemModel value)
	{
		final TypeDescriptor type = mock(TypeDescriptor.class);
		doReturn(Collections.singleton(attribute)).when(type).getAttributes();

		final ItemToMapConversionContext context = mock(ItemToMapConversionContext.class);
		doReturn(type).when(context).getTypeDescriptor();
		doReturn(value).when(context).getItemModel();
		return context;
	}

	private ItemModel itemModel()
	{
		return itemModel(ClassificationAttributeTypeEnum.STRING);
	}

	private ItemModel itemModel(final Object qualifierValue)
	{
		final ItemModel item = mock(ItemModel.class);
		doReturn(qualifierValue).when(modelService).getAttributeValue(item, QUALIFIER);
		return item;
	}

	private TypeAttributeDescriptor primitiveAttribute()
	{
		final TypeAttributeDescriptor attr = attribute(primitiveType());
		doReturn(true).when(attr).isPrimitive();
		return attr;
	}

	private TypeAttributeDescriptor itemReferenceAttribute()
	{
		final TypeAttributeDescriptor attribute = attribute(compositeType());
		doReturn(false).when(attribute).isPrimitive();
		return attribute;
	}

	private TypeAttributeDescriptor enumerationAttribute()
	{
		final TypeAttributeDescriptor attribute = attribute(enumerationType());
		doReturn(false).when(attribute).isPrimitive();
		return attribute;
	}

	private TypeDescriptor compositeType()
	{
		final TypeDescriptor type = mock(TypeDescriptor.class);
		doReturn(false).when(type).isEnumeration();
		doReturn(false).when(type).isPrimitive();
		return type;
	}

	private TypeDescriptor enumerationType()
	{
		final TypeAttributeDescriptor enumAttribute = mock(TypeAttributeDescriptor.class, "code");
		doReturn("code").when(enumAttribute).getQualifier();
		doReturn("enumCode").when(enumAttribute).getAttributeName();

		final TypeDescriptor type = mock(TypeDescriptor.class);
		doReturn(true).when(type).isEnumeration();
		doReturn(Collections.singleton(enumAttribute)).when(type).getAttributes();
		return type;
	}

	private TypeDescriptor primitiveType()
	{
		final TypeDescriptor type = mock(TypeDescriptor.class);
		doReturn(true).when(type).isPrimitive();
		return type;
	}

	private TypeAttributeDescriptor attribute(final TypeDescriptor attrType)
	{
		final TypeAttributeDescriptor attr = mock(TypeAttributeDescriptor.class);
		doReturn(QUALIFIER).when(attr).getQualifier();
		doReturn(ATTRIBUTE).when(attr).getAttributeName();
		doReturn(attrType).when(attr).getAttributeType();
		return attr;
	}
}
