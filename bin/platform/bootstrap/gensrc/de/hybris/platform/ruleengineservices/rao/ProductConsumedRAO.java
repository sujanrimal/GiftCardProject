/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Jan 17, 2020 11:49:28 AM
 * ----------------------------------------------------------------
 *
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.ruleengineservices.rao;

import java.io.Serializable;
import de.hybris.platform.ruleengineservices.rao.OrderEntryRAO;

import java.util.Objects;

public  class ProductConsumedRAO  implements Serializable 
{

 	/** Default serialVersionUID value. */
 	private static final long serialVersionUID = 1L;	/** <i>Generated property</i> for <code>ProductConsumedRAO.orderEntry</code> property defined at extension <code>ruleengineservices</code>. */
		private OrderEntryRAO orderEntry;
	/** <i>Generated property</i> for <code>ProductConsumedRAO.availableQuantity</code> property defined at extension <code>ruleengineservices</code>. */
		private int availableQuantity;
		
	public ProductConsumedRAO()
	{
		// default constructor
	}
	
			
		public void setOrderEntry(final OrderEntryRAO orderEntry)
	{
		this.orderEntry = orderEntry;
	}
	
				
	public OrderEntryRAO getOrderEntry() 
	{
		return orderEntry;
	}
		
			
		public void setAvailableQuantity(final int availableQuantity)
	{
		this.availableQuantity = availableQuantity;
	}
	
				
	public int getAvailableQuantity() 
	{
		return availableQuantity;
	}
		
			@Override
	public boolean equals(final Object o)
	{
		
	if (o == null) return false;
	if (o == this) return true;

	if (getClass() != o.getClass()) return false;

	final ProductConsumedRAO other = (ProductConsumedRAO) o;
	return	Objects.equals(getOrderEntry(), other.getOrderEntry())
  ;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		Object attribute;

				attribute = orderEntry;
		result = 31 * result + (attribute == null ? 0 : attribute.hashCode());
		
		return result;
	}
	}