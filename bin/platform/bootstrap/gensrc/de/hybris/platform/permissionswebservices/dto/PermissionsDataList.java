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
package de.hybris.platform.permissionswebservices.dto;

import java.io.Serializable;
import de.hybris.platform.permissionsfacades.data.PermissionsData;
import java.util.List;

public  class PermissionsDataList  implements Serializable 
{

 	/** Default serialVersionUID value. */
 
 	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>PermissionsDataList.permissionsList</code> property defined at extension <code>permissionswebservices</code>. */
		
	private List<PermissionsData> permissionsList;
	
	public PermissionsDataList()
	{
		// default constructor
	}
	
		
	
	public void setPermissionsList(final List<PermissionsData> permissionsList)
	{
		this.permissionsList = permissionsList;
	}

		
	
	public List<PermissionsData> getPermissionsList() 
	{
		return permissionsList;
	}
	


}
