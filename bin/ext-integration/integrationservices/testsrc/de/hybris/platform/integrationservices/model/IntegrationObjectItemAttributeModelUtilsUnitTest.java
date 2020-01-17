/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.integrationservices.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Test;

@UnitTest
public class IntegrationObjectItemAttributeModelUtilsUnitTest
{
	@Test
	public void testFalseIfNullWhenNull()
	{
		assertFalse(IntegrationObjectItemAttributeModelUtils.falseIfNull(null));
	}

	@Test
	public void testFalseIfNullWhenFalse()
	{
		assertFalse(IntegrationObjectItemAttributeModelUtils.falseIfNull(false));
	}

	@Test
	public void testFalseIfNullWhenTrue()
	{
		assertTrue(IntegrationObjectItemAttributeModelUtils.falseIfNull(true));
	}
}
