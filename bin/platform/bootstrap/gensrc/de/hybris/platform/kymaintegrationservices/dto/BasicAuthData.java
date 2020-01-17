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
package de.hybris.platform.kymaintegrationservices.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public  class BasicAuthData  implements Serializable 
{

 	/** Default serialVersionUID value. */
 
 	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>BasicAuthData.username</code> property defined at extension <code>kymaintegrationservices</code>. */
	@JsonProperty("username") 	
	private String username;

	/** <i>Generated property</i> for <code>BasicAuthData.password</code> property defined at extension <code>kymaintegrationservices</code>. */
	@JsonProperty("password") 	
	private String password;
	
	public BasicAuthData()
	{
		// default constructor
	}
	
		
	@JsonProperty("username") 
	public void setUsername(final String username)
	{
		this.username = username;
	}

		
	@JsonProperty("username") 
	public String getUsername() 
	{
		return username;
	}
	
		
	@JsonProperty("password") 
	public void setPassword(final String password)
	{
		this.password = password;
	}

		
	@JsonProperty("password") 
	public String getPassword() 
	{
		return password;
	}
	


}
