/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.odata2services.odata.persistence;

import java.util.Locale;

import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.exception.ODataRuntimeApplicationException;

/**
 * Exception to throw for error scenarios produced by invalid Data.
 * Will result in HttpStatus 400
 */
public class InvalidDataException extends ODataRuntimeApplicationException
{
	private static final HttpStatusCodes STATUS_CODE = HttpStatusCodes.BAD_REQUEST;

	/**
	 * Constructor to create InvalidDataException
	 *
	 * @param message error message
	 * @param errorCode error code
	 */
	public InvalidDataException(final String errorCode, final String message)
	{
		super(message, Locale.ENGLISH, STATUS_CODE, errorCode);
	}

	/**
	 * Constructor to create InvalidDataException
	 *
	 * @param errorCode error code
	 * @param e exception to get Message from
	 */
	public InvalidDataException(final String errorCode, final String message, final Throwable e)
	{
		super(message, Locale.ENGLISH, STATUS_CODE, errorCode, e);
	}
}