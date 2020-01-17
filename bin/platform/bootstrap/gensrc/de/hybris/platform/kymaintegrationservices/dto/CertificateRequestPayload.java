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
package de.hybris.platform.kymaintegrationservices.dto;

import java.io.Serializable;

public  class CertificateRequestPayload  implements Serializable 
{

 	/** Default serialVersionUID value. */
 
 	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CertificateRequestPayload.csr</code> property defined at extension <code>kymaintegrationservices</code>. */
		
	private String csr;
	
	public CertificateRequestPayload()
	{
		// default constructor
	}
	
		
	
	public void setCsr(final String csr)
	{
		this.csr = csr;
	}

		
	
	public String getCsr() 
	{
		return csr;
	}
	


}