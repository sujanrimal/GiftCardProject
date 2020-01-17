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

import java.util.Objects;

public  class CategoryRAO  implements Serializable 
{

 	/** Default serialVersionUID value. */
 	private static final long serialVersionUID = 1L;	/** <i>Generated property</i> for <code>CategoryRAO.code</code> property defined at extension <code>ruleengineservices</code>. */
		private String code;
		
	public CategoryRAO()
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
		
			@Override
	public boolean equals(final Object o)
	{
		
	if (o == null) return false;
	if (o == this) return true;

	if (getClass() != o.getClass()) return false;

	final CategoryRAO other = (CategoryRAO) o;
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