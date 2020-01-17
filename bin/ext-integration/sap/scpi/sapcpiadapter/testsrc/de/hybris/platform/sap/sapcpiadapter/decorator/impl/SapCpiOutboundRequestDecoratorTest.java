/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.sap.sapcpiadapter.decorator.impl;

import static de.hybris.platform.outboundservices.decorator.DecoratorContext.decoratorContextBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.ACCEPT;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.apiregistryservices.model.ConsumedDestinationModel;
import de.hybris.platform.apiregistryservices.model.EndpointModel;
import de.hybris.platform.apiregistryservices.services.DestinationService;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.outboundservices.client.IntegrationRestTemplateFactory;
import de.hybris.platform.outboundservices.decorator.DecoratorContext;
import de.hybris.platform.outboundservices.decorator.DecoratorExecution;
import de.hybris.platform.sap.sapcpiadapter.decorator.CSRFTokenFetchingException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

@UnitTest
@RunWith(MockitoJUnitRunner.class)

public class SapCpiOutboundRequestDecoratorTest
{

    private static final String X_CSRF_TOKEN = "X-CSRF-Token";
    private static final String X_CSRF_SET_COOKIES = "Set-Cookie";
    private static final String X_CSRF_COOKIE = "Cookie";
    private static final String TOKEN_URL = "https://cd1n01-iflmap.hcisb.int.sap.hana.ondemand.com/gw/odata/SAP/REPLICATE-B2B-CUSTOMER-FROM-SAP-COMMERCE-CLOUD-TO-ERP;v=1/";
    private static final String CONSUMED_DESTINATION_URL = "https://cd1n01-iflmap.hcisb.int.sap.hana.ondemand.com/gw/odata/SAP/REPLICATE-B2B-CUSTOMER-FROM-SAP-COMMERCE-CLOUD-TO-ERP;v=1/SAPCpiOutboundB2BCustomers";

    @InjectMocks
    private SapCpiOutboundRequestDecorator sapCpiOutboundRequestDecorator;

    @Mock
    private DestinationService destinationService;
    @Mock
    private IntegrationRestTemplateFactory integrationRestTemplateFactory;
    @Mock
    private DecoratorExecution execution;

    private ItemModel itemModel;
    private ConsumedDestinationModel consumedDestination;
    private EndpointModel endpoint;
    private HttpHeaders headers;
    private ResponseEntity<Map> csrfResponse;
    private RestOperations restOperation;

    @Captor
    private ArgumentCaptor<HttpEntity<Map>> httpEntityCaptor;

    @Before
    public void setUp()
    {
        itemModel = mock(ItemModel.class);
        consumedDestination = mock(ConsumedDestinationModel.class);
        endpoint = mock(EndpointModel.class);

        when(consumedDestination.getUrl()).thenReturn(CONSUMED_DESTINATION_URL);
        when(endpoint.getId()).thenReturn("scpiB2BCustomerEndpoint");
        when(destinationService.getDestinationById(eq("scpiCustomerDestination"))).thenReturn(consumedDestination);
        when(consumedDestination.getEndpoint()).thenReturn(endpoint);

        restOperation = mock(RestOperations.class);
        when(integrationRestTemplateFactory.create(eq(consumedDestination))).thenReturn(restOperation);


        csrfResponse = mock(ResponseEntity.class);
        when(restOperation.exchange(eq(TOKEN_URL), eq(HttpMethod.GET), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(csrfResponse);

        headers = mock(HttpHeaders.class);
        when(csrfResponse.getHeaders()).thenReturn(headers);
        when(headers.getFirst(X_CSRF_TOKEN)).thenReturn("322704B0EB3E980C8E752CB5748F754A");
    }

    @Test
    public void testDecorate()
    {
        when(headers.get(X_CSRF_SET_COOKIES)).thenReturn(Arrays.asList("cookie1, cookie2"));

        final DecoratorContext context = decoratorContextBuilder().withIntegrationObjectCode("integrationObjectCode")
                .withItemModel(itemModel)
                .withDestinationModel(consumedDestination)
                .withIntegrationObjectItemCode(null)
                .build();
        sapCpiOutboundRequestDecorator.decorate(headers, Collections.emptyMap(), context, execution);

        verify(restOperation).exchange(eq(TOKEN_URL), eq(HttpMethod.GET), httpEntityCaptor.capture(), eq(Map.class));
        verify(headers).set(X_CSRF_TOKEN, "322704B0EB3E980C8E752CB5748F754A");
        verify(headers).set(X_CSRF_COOKIE, "cookie1, cookie2");
        verify(execution).createHttpEntity(headers, Collections.emptyMap(), context);

        assertThat(httpEntityCaptor.getValue().getHeaders())
                .contains(
                        entry(X_CSRF_TOKEN, Collections.singletonList("Fetch")),
                        entry(ACCEPT, Collections.singletonList(MediaType.APPLICATION_JSON_VALUE)));
    }

    @Test (expected = CSRFTokenFetchingException.class)
    public void testDecorate_NoCookieHeaders()
    {
        when(headers.get(X_CSRF_SET_COOKIES)).thenReturn(null);

        final DecoratorContext context = decoratorContextBuilder().withIntegrationObjectCode("integrationObjectCode")
                .withItemModel(itemModel)
                .withDestinationModel(consumedDestination)
                .withIntegrationObjectItemCode(null)
                .build();

        sapCpiOutboundRequestDecorator.decorate(headers, Collections.emptyMap(), context, execution);
    }
}