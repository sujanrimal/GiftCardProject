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
package de.hybris.platform.ruleengineservices.compiler;

import java.io.Serializable;
import java.util.Map;

public abstract  class RuleIrCondition  implements Serializable 
{

 	/** Default serialVersionUID value. */
 
 	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>RuleIrCondition.metadata</code> property defined at extension <code>ruleengineservices</code>. */
		
	private Map<String,Object> metadata;
	
	public RuleIrCondition()
	{
		// default constructor
	}
	
		
	
	public void setMetadata(final Map<String,Object> metadata)
	{
		this.metadata = metadata;
	}

		
	
	public Map<String,Object> getMetadata() 
	{
		return metadata;
	}
	


}