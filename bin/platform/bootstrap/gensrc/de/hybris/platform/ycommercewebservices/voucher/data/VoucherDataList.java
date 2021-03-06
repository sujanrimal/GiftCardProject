/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Jan 17, 2020 10:18:06 AM
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
package de.hybris.platform.ycommercewebservices.voucher.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.voucher.data.VoucherData;
import java.util.List;

public  class VoucherDataList  implements Serializable 
{

 	/** Default serialVersionUID value. */
 
 	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>VoucherDataList.vouchers</code> property defined at extension <code>ycommercewebservices</code>. */
		
	private List<VoucherData> vouchers;
	
	public VoucherDataList()
	{
		// default constructor
	}
	
		
	
	public void setVouchers(final List<VoucherData> vouchers)
	{
		this.vouchers = vouchers;
	}

		
	
	public List<VoucherData> getVouchers() 
	{
		return vouchers;
	}
	


}
