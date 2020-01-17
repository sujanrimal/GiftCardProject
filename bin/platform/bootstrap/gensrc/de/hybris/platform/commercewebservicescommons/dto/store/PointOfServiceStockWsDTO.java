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
package de.hybris.platform.commercewebservicescommons.dto.store;

import de.hybris.platform.commercewebservicescommons.dto.product.StockWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.store.PointOfServiceWsDTO;

public  class PointOfServiceStockWsDTO extends PointOfServiceWsDTO 
{

 

	/** <i>Generated property</i> for <code>PointOfServiceStockWsDTO.stockInfo</code> property defined at extension <code>commercewebservicescommons</code>. */
		
	private StockWsDTO stockInfo;
	
	public PointOfServiceStockWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setStockInfo(final StockWsDTO stockInfo)
	{
		this.stockInfo = stockInfo;
	}

		
	
	public StockWsDTO getStockInfo() 
	{
		return stockInfo;
	}
	


}