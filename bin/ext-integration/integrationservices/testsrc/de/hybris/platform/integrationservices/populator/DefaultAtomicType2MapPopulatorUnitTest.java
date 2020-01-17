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
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor;
import de.hybris.platform.integrationservices.model.TypeDescriptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Maps;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultAtomicType2MapPopulatorUnitTest
{
	private static final String ATTR_NAME = "codex";
	private static final String QUALIFIER = "qualifier";
	private static final Locale LOCALE = Locale.getDefault();
	private static final ItemModel itemModel = mock(ItemModel.class);

	@InjectMocks
	private DefaultAtomicType2MapPopulator populator;
	@Mock
	private ModelService modelService;

	@After
	public void tearDown()
	{
		Locale.setDefault(LOCALE);
	}

	@Test
	public void shouldPopulateToMap()
	{
		givenPropertyIs("test");
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(primitiveAttribute()), map);

		assertThat(map).containsOnly(entry(ATTR_NAME, "test"));
	}

	@Test
	public void shouldNotPopulate_whenAttributeValueIsNull()
	{
		givenPropertyIs(null);
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(primitiveAttribute()), map);

		assertThat(map).isEmpty();
	}

	@Test
	public void shouldNotPopulate()
	{
		givenPropertyIs("test");
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(nonPrimitiveAttribute()), map);

		assertThat(map).isEmpty();
	}

	@Test
	public void shouldPopulateDate()
	{
		final Date date = new Date();
		givenPropertyIs(date);
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(primitiveAttribute()), map);

		assertThat(map).containsOnly(entry(ATTR_NAME, "/Date("+date.getTime()+")/"));
	}

	@Test
	public void shouldPopulateDouble()
	{
		givenPropertyIs(1.0D);
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(primitiveAttribute()), map);

		assertThat(map).containsOnly(entry(ATTR_NAME, 1.0D));
	}

	@Test
	public void shouldPopulateInt()
	{
		givenPropertyIs(1);
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(primitiveAttribute()), map);

		assertThat(map).containsOnly(entry(ATTR_NAME, 1));
	}

	@Test
	public void shouldPopulateLong()
	{
		givenPropertyIs(1L);
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(primitiveAttribute()), map);

		assertThat(map).containsOnly(entry(ATTR_NAME, "1"));
	}

	@Test
	public void shouldPopulateBigDecimal()
	{
		givenPropertyIs(BigDecimal.valueOf(123456));
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(primitiveAttribute()), map);

		assertThat(map).containsOnly(entry(ATTR_NAME, "123.46E3"));
	}

	@Test
	public void shouldPopulateBigDecimalDifferentLocale()
	{
		givenPropertyIs(BigDecimal.valueOf(123456));
		Locale.setDefault(Locale.GERMAN);  // 12.55 is written 12,55
		final Map<String, Object> map = Maps.newHashMap();

		populator.populate(conversionContext(primitiveAttribute()), map);

		assertThat(map).containsOnly(entry(ATTR_NAME, "123.46E3"));
	}

	private ItemToMapConversionContext conversionContext(final TypeAttributeDescriptor attribute)
	{
		final TypeDescriptor type = mock(TypeDescriptor.class);
		doReturn(Collections.singleton(attribute)).when(type).getAttributes();

		final ItemToMapConversionContext context = mock(ItemToMapConversionContext.class);
		doReturn(itemModel).when(context).getItemModel();
		doReturn(type).when(context).getTypeDescriptor();
		return context;
	}

	private void givenPropertyIs(final Object value)
	{
		doReturn(value).when(modelService).getAttributeValue(itemModel, QUALIFIER);
	}

	private static TypeAttributeDescriptor primitiveAttribute() {
		final TypeAttributeDescriptor descriptor = attribute();
		doReturn(true).when(descriptor).isPrimitive();
		return descriptor;
	}

	private static TypeAttributeDescriptor nonPrimitiveAttribute() {
		final TypeAttributeDescriptor descriptor = attribute();
		doReturn(false).when(descriptor).isPrimitive();
		return descriptor;
	}

	private static TypeAttributeDescriptor attribute()
	{
		final TypeAttributeDescriptor attr = mock(TypeAttributeDescriptor.class);
		doReturn(ATTR_NAME).when(attr).getAttributeName();
		doReturn(QUALIFIER).when(attr).getQualifier();
		return attr;
	}
}
