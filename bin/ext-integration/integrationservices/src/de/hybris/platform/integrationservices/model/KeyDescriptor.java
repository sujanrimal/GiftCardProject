/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices.model;

import de.hybris.platform.integrationservices.integrationkey.KeyValue;

import java.util.Map;

/**
 * Descriptor of an {@link IntegrationObjectItemModel} key. Each integration object item must have at least one (a simple key) or
 * several (composite key) attributes, which identify instances of the item type and therefore define its key.
 */
public interface KeyDescriptor
{
	/**
	 * Calculates key value for the specified data item.
	 * @param item Map presentation of a data item, for which the key value needs to be calculated.
	 * @return the calculated key value.
	 */
	KeyValue calculateKey(Map<String, Object> item);

	/**
	 * Queries whether the specified attribute name is a name of the key attribute.
	 * @param attr name of the attribute to enquire about
	 * @return {@code true}, if the specified attribute is a key attribute in this key descriptor; {@code false} otherwise.
	 */
	boolean isKeyAttribute(String attr);
}
