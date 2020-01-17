/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Jan 17, 2020 11:49:29 AM
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

import java.util.Objects;

public  class CustomerSupportRAO  implements Serializable 
{

 	/** Default serialVersionUID value. */
 	private static final long serialVersionUID = 1L;	/** <i>Generated property</i> for <code>CustomerSupportRAO.customerSupportAgentActive</code> property defined at extension <code>ruleengineservices</code>. */
		private Boolean customerSupportAgentActive;
	/** <i>Generated property</i> for <code>CustomerSupportRAO.customerEmulationActive</code> property defined at extension <code>ruleengineservices</code>. */
		private Boolean customerEmulationActive;
		
	public CustomerSupportRAO()
	{
		// default constructor
	}
	
			
		public void setCustomerSupportAgentActive(final Boolean customerSupportAgentActive)
	{
		this.customerSupportAgentActive = customerSupportAgentActive;
	}
	
				
	public Boolean getCustomerSupportAgentActive() 
	{
		return customerSupportAgentActive;
	}
		
			
		public void setCustomerEmulationActive(final Boolean customerEmulationActive)
	{
		this.customerEmulationActive = customerEmulationActive;
	}
	
				
	public Boolean getCustomerEmulationActive() 
	{
		return customerEmulationActive;
	}
		
			@Override
	public boolean equals(final Object o)
	{
		
	if (o == null) return false;
	if (o == this) return true;

	if (getClass() != o.getClass()) return false;

	final CustomerSupportRAO other = (CustomerSupportRAO) o;
	return	Objects.equals(getCustomerSupportAgentActive(), other.getCustomerSupportAgentActive())
 &&  Objects.equals(getCustomerEmulationActive(), other.getCustomerEmulationActive())
  ;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		Object attribute;

				attribute = customerSupportAgentActive;
		result = 31 * result + (attribute == null ? 0 : attribute.hashCode());
				attribute = customerEmulationActive;
		result = 31 * result + (attribute == null ? 0 : attribute.hashCode());
		
		return result;
	}
	}