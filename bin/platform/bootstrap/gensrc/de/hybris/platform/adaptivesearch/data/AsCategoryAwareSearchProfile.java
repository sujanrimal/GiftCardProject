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
package de.hybris.platform.adaptivesearch.data;

import de.hybris.platform.adaptivesearch.data.AbstractAsSearchProfile;
import de.hybris.platform.adaptivesearch.data.AsConfigurableSearchConfiguration;
import de.hybris.platform.core.PK;
import java.util.Map;

public  class AsCategoryAwareSearchProfile extends AbstractAsSearchProfile 
{

 

	/** <i>Generated property</i> for <code>AsCategoryAwareSearchProfile.searchConfigurations</code> property defined at extension <code>adaptivesearch</code>. */
		
	private Map<PK,AsConfigurableSearchConfiguration> searchConfigurations;
	
	public AsCategoryAwareSearchProfile()
	{
		// default constructor
	}
	
		
	
	public void setSearchConfigurations(final Map<PK,AsConfigurableSearchConfiguration> searchConfigurations)
	{
		this.searchConfigurations = searchConfigurations;
	}

		
	
	public Map<PK,AsConfigurableSearchConfiguration> getSearchConfigurations() 
	{
		return searchConfigurations;
	}
	


}
