/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.oci.jalo.interfaces;

/**
 * This interface stores a list of SAPProducts and provide methods to access a SAPProduct from the list.
 * 
 * 
 * 
 */
public interface SAPProductList
{
	/**
	 * get a stored item/product with the given index
	 * 
	 * @param index
	 *           index of the SAPProduct in the SAPProductList
	 * @return the SAPProduct
	 */
	public SAPProduct getProduct(int index);

	/**
	 * size of the SAPProductList
	 * 
	 * @return number of SAPProducts in this SAPProductList
	 */
	public int size();


}
