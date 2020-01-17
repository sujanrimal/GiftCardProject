/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.odata2webservicesfeaturetests.useraccess

import de.hybris.bootstrap.annotations.IntegrationTest
import de.hybris.platform.integrationservices.util.IntegrationTestUtil
import de.hybris.platform.odata2webservices.constants.Odata2webservicesConstants
import de.hybris.platform.odata2webservicesfeaturetests.ws.BasicAuthRequestBuilder
import de.hybris.platform.servicelayer.ServicelayerSpockSpecification
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer
import org.apache.olingo.odata2.api.commons.HttpStatusCodes
import org.junit.Test
import spock.lang.Unroll

import javax.ws.rs.client.Entity

@NeedsEmbeddedServer(webExtensions = [Odata2webservicesConstants.EXTENSIONNAME])
@IntegrationTest
class IntegrationMonitoringSecurityIntegrationTest extends ServicelayerSpockSpecification {
	private static final String PASSWORD = 'password'
	private static final String USER_ADMIN = 'testAdmin'
	private static final String USER_MONITORING = 'testMonitoring'
	private static final String USER_MONITORING_ADMIN = 'testMonitoringAdmin'
	private static final String USER_SERVICE = 'testService'
	private static final String USER_CREATE = 'testCreate'
	private static final String USER_VIEW = 'testView'

	def setupSpec() {
        importCsv '/impex/essentialdata-odata2services.impex', 'UTF-8'
        importCsv '/impex/essentialdata-odata2webservices.impex', 'UTF-8'
		importCsv '/impex/essentialdata-inboundservices.impex', 'UTF-8'
		importCsv '/impex/essentialdata-outboundservices.impex', 'UTF-8'
		IntegrationTestUtil.importImpEx(
				'$password=@password[translator = de.hybris.platform.impex.jalo.translators.UserPasswordTranslator]',
				'INSERT_UPDATE Employee; UID[unique = true]    ; groups(uid)                                      ;$password',
				"                      ; $USER_ADMIN           ; integrationadmingroup                            ;*:$PASSWORD",
				"                      ; $USER_MONITORING      ; integrationmonitoringgroup                       ;*:$PASSWORD",
				"                      ; $USER_MONITORING_ADMIN; integrationmonitoringgroup, integrationadmingroup;*:$PASSWORD",
				"                      ; $USER_SERVICE         ; integrationservicegroup, integrationadmingroup   ;*:$PASSWORD",
				"                      ; $USER_CREATE          ; integrationcreategroup                           ;*:$PASSWORD",
				"                      ; $USER_VIEW            ; integrationviewgroup                             ;*:$PASSWORD")
	}

    @Test
	@Unroll
    def "User must be authenticated in order to GET /#service"() {
        when:
        def response = basicAuthRequest(service)
                .build()
                .get()

        then:
        response.status == HttpStatusCodes.UNAUTHORIZED.statusCode

		where:
		service << ['InboundIntegrationMonitoring', 'OutboundIntegrationMonitoring']
    }

    @Test
    @Unroll
    def "User must be authenticated in order to GET /#service/#feed"() {
        when:
        def response = basicAuthRequest(service)
                .path(feed)
                .build()
                .get()

        then:
        response.status == HttpStatusCodes.UNAUTHORIZED.statusCode

        where:
        service                         | feed
        'InboundIntegrationMonitoring'  | 'InboundRequests'
        'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses'
        'InboundIntegrationMonitoring'  | 'InboundRequestErrors'
        'OutboundIntegrationMonitoring' | 'OutboundRequests'
        'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses'
    }

  	@Test
    @Unroll
	def "User #user gets #status for GET /#service"() {
		when:
		def response = basicAuthRequest(service)
				.credentials(user, PASSWORD)
				.build()
				.get()

		then:
		response.status == status.statusCode

		where:
		service                         | user                  | status
		'InboundIntegrationMonitoring'  | USER_ADMIN            | HttpStatusCodes.NOT_FOUND
		'InboundIntegrationMonitoring'  | USER_MONITORING       | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | USER_MONITORING_ADMIN | HttpStatusCodes.OK
		'InboundIntegrationMonitoring'  | USER_SERVICE          | HttpStatusCodes.NOT_FOUND
		'InboundIntegrationMonitoring'  | USER_CREATE           | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | USER_VIEW             | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | USER_ADMIN            | HttpStatusCodes.NOT_FOUND
		'OutboundIntegrationMonitoring' | USER_MONITORING       | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | USER_MONITORING_ADMIN | HttpStatusCodes.OK
		'OutboundIntegrationMonitoring' | USER_SERVICE          | HttpStatusCodes.NOT_FOUND
		'OutboundIntegrationMonitoring' | USER_CREATE           | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | USER_VIEW             | HttpStatusCodes.FORBIDDEN
	}

	@Test
	@Unroll
	def "User #user gets #status for GET /#service/#feed"()	{
		when:
		def response = basicAuthRequest(service)
				.path(feed)
				.credentials(user, PASSWORD)
				.build()
				.get()

		then:
		response.status == status.statusCode

		where:
		service                         | feed                         | user                  | status
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_ADMIN            | HttpStatusCodes.NOT_FOUND
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_MONITORING       | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_MONITORING_ADMIN | HttpStatusCodes.OK
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_SERVICE          | HttpStatusCodes.NOT_FOUND
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_CREATE           | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_VIEW             | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_ADMIN            | HttpStatusCodes.NOT_FOUND
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_MONITORING       | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_MONITORING_ADMIN | HttpStatusCodes.OK
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_SERVICE          | HttpStatusCodes.NOT_FOUND
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_CREATE           | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_VIEW             | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_ADMIN            | HttpStatusCodes.NOT_FOUND
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_MONITORING       | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_MONITORING_ADMIN | HttpStatusCodes.OK
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_SERVICE          | HttpStatusCodes.NOT_FOUND
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_CREATE           | HttpStatusCodes.FORBIDDEN
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_VIEW             | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_ADMIN            | HttpStatusCodes.NOT_FOUND
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_MONITORING       | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_MONITORING_ADMIN | HttpStatusCodes.OK
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_SERVICE          | HttpStatusCodes.NOT_FOUND
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_CREATE           | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_VIEW             | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_ADMIN            | HttpStatusCodes.NOT_FOUND
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_MONITORING       | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_MONITORING_ADMIN | HttpStatusCodes.OK
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_SERVICE          | HttpStatusCodes.NOT_FOUND
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_CREATE           | HttpStatusCodes.FORBIDDEN
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_VIEW             | HttpStatusCodes.FORBIDDEN
	}

	@Test
    @Unroll
	def "User #user is Forbidden to POST to /#service/#feed"() {
		when:
		def response = basicAuthRequest(service)
				.path(feed)
				.credentials(user, PASSWORD)
				.build()
				.post(Entity.json('{}'))

		then:
		response.status == HttpStatusCodes.FORBIDDEN.statusCode

		where:
		service                         | feed                         | user
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_ADMIN
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_MONITORING
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_SERVICE
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_CREATE
		'InboundIntegrationMonitoring'  | 'InboundRequests'            | USER_VIEW
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_ADMIN
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_MONITORING
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_SERVICE
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_CREATE
		'InboundIntegrationMonitoring'  | 'InboundRequestErrors'       | USER_VIEW
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_ADMIN
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_MONITORING
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_SERVICE
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_CREATE
		'InboundIntegrationMonitoring'  | 'IntegrationRequestStatuses' | USER_VIEW
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_ADMIN
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_MONITORING
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_SERVICE
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_CREATE
		'OutboundIntegrationMonitoring' | 'OutboundRequests'           | USER_VIEW
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_ADMIN
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_MONITORING
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_SERVICE
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_CREATE
		'OutboundIntegrationMonitoring' | 'IntegrationRequestStatuses' | USER_VIEW
	}

    def basicAuthRequest(String path) {
        new BasicAuthRequestBuilder()
                .extensionName(Odata2webservicesConstants.EXTENSIONNAME)
				.accept("application/json")
				.path(path)
    }
}
