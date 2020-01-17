/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.sap.sapcpiadapter.decorator.impl;

import de.hybris.platform.apiregistryservices.model.ConsumedDestinationModel;
import de.hybris.platform.outboundservices.client.IntegrationRestTemplateFactory;
import de.hybris.platform.outboundservices.decorator.DecoratorContext;
import de.hybris.platform.outboundservices.decorator.DecoratorExecution;
import de.hybris.platform.outboundservices.decorator.OutboundRequestDecorator;
import de.hybris.platform.sap.sapcpiadapter.decorator.CSRFTokenFetchingException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import com.google.common.base.Strings;

/**
 * Decorates SAP CPI outbound requests with CSRF tokens.
 */
public class SapCpiOutboundRequestDecorator implements OutboundRequestDecorator
{

	private static final String X_CSRF_TOKEN = "X-CSRF-Token";
	private static final String X_CSRF_TOKEN_FETCH = "Fetch";
	private static final String X_CSRF_SET_COOKIES = "Set-Cookie";
	private static final String X_CSRF_COOKIE = "Cookie";
	private static final Logger LOG = LoggerFactory.getLogger(SapCpiOutboundRequestDecorator.class);
	private static final String COOKIE_SEPARATOR = "; ";

	private IntegrationRestTemplateFactory integrationRestTemplateFactory;
	private ResponseEntity<Map> csrfTokenExchange;

	@Override
	public HttpEntity<Map<String, Object>> decorate(final HttpHeaders httpHeaders, final Map<String, Object> payload,
			final DecoratorContext context, final DecoratorExecution execution)
	{

		httpHeaders.set(X_CSRF_TOKEN, fetchCsrfTokenFromSCPI(context.getDestinationModel()));
		httpHeaders.set(X_CSRF_COOKIE, fetchCsrfCookieHeader(context.getDestinationModel()));

		return execution.createHttpEntity(httpHeaders, payload, context);
	}

	protected String fetchCsrfTokenFromSCPI(final ConsumedDestinationModel destinationModel)
	{

		final HttpHeaders headers = new HttpHeaders();
		headers.set(X_CSRF_TOKEN, X_CSRF_TOKEN_FETCH);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		final RestOperations restOperations = getIntegrationRestTemplateFactory().create(destinationModel);
		csrfTokenExchange = restOperations.exchange(getBaseUrl(destinationModel), HttpMethod.GET, new HttpEntity(headers), Map.class);
		final String csrfTokenHeader = csrfTokenExchange.getHeaders().getFirst(X_CSRF_TOKEN);


		if (!Strings.isNullOrEmpty(csrfTokenHeader))
		{
			LOG.debug("The CSRF token fetched from {} is: {}", destinationModel.getId(), csrfTokenHeader);
		}
		else
		{
			throw new CSRFTokenFetchingException("Failed to fetch the CSRF token from " + destinationModel.getId());
		}

		return csrfTokenHeader;
	}

	protected String fetchCsrfCookieHeader(final ConsumedDestinationModel destinationModel)
	{
		final List<String> csrfCookies = csrfTokenExchange.getHeaders().get(X_CSRF_SET_COOKIES);
		final String csrfCookieHeader = StringUtils.join(csrfCookies, COOKIE_SEPARATOR);

		if (!Strings.isNullOrEmpty(csrfCookieHeader))
		{
			LOG.debug("The CSRF token fetched from {} has the cookies: {}", destinationModel.getId(), csrfCookieHeader);
		}
		else
		{
			throw new CSRFTokenFetchingException("Failed to fetch the CSRF cookies from " + destinationModel.getId());
		}
		return csrfCookieHeader;
	}


	protected String getBaseUrl(ConsumedDestinationModel destinationModel)
	{

		String url = destinationModel.getUrl();
		return url.substring(0, (url.lastIndexOf('/') + 1));
	}

	protected IntegrationRestTemplateFactory getIntegrationRestTemplateFactory()
	{
		return integrationRestTemplateFactory;
	}

	@Required
	public void setIntegrationRestTemplateFactory(final IntegrationRestTemplateFactory integrationRestTemplateFactory)
	{
		this.integrationRestTemplateFactory = integrationRestTemplateFactory;
	}
}
