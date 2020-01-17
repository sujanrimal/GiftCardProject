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

import de.hybris.platform.ruleengineservices.rao.AbstractActionedRAO;
import de.hybris.platform.ruleengineservices.rao.CategoryRAO;
import java.util.Set;

import java.util.Objects;

public  class ProductRAO extends AbstractActionedRAO 
{

 	/** <i>Generated property</i> for <code>ProductRAO.code</code> property defined at extension <code>ruleengineservices</code>. */
		private String code;
	/** <i>Generated property</i> for <code>ProductRAO.categories</code> property defined at extension <code>ruleengineservices</code>. */
		private Set<CategoryRAO> categories;
	/** <i>Generated property</i> for <code>ProductRAO.baseProductCodes</code> property defined at extension <code>ruleengineservices</code>. */
		private Set<String> baseProductCodes;
		
	public ProductRAO()
	{
		// default constructor
	}
	
			
		public void setCode(final String code)
	{
		this.code = code;
	}
	
				
	public String getCode() 
	{
		return code;
	}
		
			
		public void setCategories(final Set<CategoryRAO> categories)
	{
		this.categories = categories;
	}
	
				
	public Set<CategoryRAO> getCategories() 
	{
		return categories;
	}
		
			
		public void setBaseProductCodes(final Set<String> baseProductCodes)
	{
		this.baseProductCodes = baseProductCodes;
	}
	
				
	public Set<String> getBaseProductCodes() 
	{
		return baseProductCodes;
	}
		
			@Override
	public boolean equals(final Object o)
	{
		
	if (o == null) return false;
	if (o == this) return true;

	if (getClass() != o.getClass()) return false;

	final ProductRAO other = (ProductRAO) o;
	return	Objects.equals(getCode(), other.getCode())
  ;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		Object attribute;

				attribute = code;
		result = 31 * result + (attribute == null ? 0 : attribute.hashCode());
		
		return result;
	}
	}