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
class IntegrationServiceSecurityIntegrationTest extends ServicelayerSpockSpecification {
    private static final String SERVICE_NAME = "IntegrationService"
    private static final String PASSWORD = 'password'
    private static final String USER_ADMIN = 'integrationAdminTest'
    private static final String USER_SERVICE = 'integrationServiceTest'
    private static final String USER_SERVICE_ADMIN = 'integrationServiceAdminTest'
    private static final String USER_MONITORING = 'integrationMonitoringTest'
    private static final String USER_CREATE = 'integrationCreateTest'
    private static final String USER_VIEW = 'integrationViewTest'

    def setupSpec() {
        importCsv '/impex/essentialdata-integrationservices.impex', 'UTF-8'
        importCsv '/impex/essentialdata-odata2services.impex', 'UTF-8'
        importCsv '/impex/essentialdata-odata2webservices.impex', 'UTF-8'
        IntegrationTestUtil.importImpEx(
                '$password=@password[translator = de.hybris.platform.impex.jalo.translators.UserPasswordTranslator]',
                'INSERT_UPDATE Employee; UID[unique = true] ; $password  ; groups(uid)',
                "                      ; $USER_ADMIN        ; *:$PASSWORD; integrationadmingroup",
                "                      ; $USER_SERVICE      ; *:$PASSWORD; integrationservicegroup",
                "                      ; $USER_SERVICE_ADMIN; *:$PASSWORD; integrationadmingroup,integrationservicegroup",
                "                      ; $USER_MONITORING   ; *:$PASSWORD; integrationadmingroup,integrationmonitoringgroup",
                "                      ; $USER_CREATE       ; *:$PASSWORD; integrationcreategroup",
                "                      ; $USER_VIEW         ; *:$PASSWORD; integrationviewgroup")
    }

    @Test
    def "User must be authenticated in order to GET /IntegrationService"() {
        when:
        def response = basicAuthRequest()
                .path(SERVICE_NAME)
                .build()
                .get()

        then:
        response.status == HttpStatusCodes.UNAUTHORIZED.statusCode
    }

    @Test
    @Unroll
    def "User must be authenticated in order to GET /IntegrationService/#feed"() {
        when:
        def response = basicAuthRequest()
                .path(SERVICE_NAME)
                .path(feed)
                .build()
                .get()

        then:
        response.status == HttpStatusCodes.UNAUTHORIZED.statusCode

        where:
        feed << ['IntegrationObjects', 'IntegrationObjectItems', 'IntegrationObjectItemAttributes']
    }

    @Test
    @Unroll
    def "User must be authenticated in order to POST to /IntegrationService/#feed"() {
        when:
        def response = basicAuthRequest()
                .path(SERVICE_NAME)
                .path(feed)
                .build()
                .post(null)

        then:
        response.status == HttpStatusCodes.UNAUTHORIZED.statusCode

        where:
        feed << ['IntegrationObjects', 'IntegrationObjectItems', 'IntegrationObjectItemAttributes']
    }

    @Test
    @Unroll
    def "User #user gets #status for GET /IntegrationService"() {
        when:
        def response = basicAuthRequest()
                .path(SERVICE_NAME)
                .credentials(user, PASSWORD)
                .build()
                .get()

        then:
        response.status == status.statusCode

        where:
        user               | status
        USER_ADMIN         | HttpStatusCodes.NOT_FOUND
        USER_SERVICE       | HttpStatusCodes.FORBIDDEN
        USER_SERVICE_ADMIN | HttpStatusCodes.OK
        USER_MONITORING    | HttpStatusCodes.NOT_FOUND
        USER_CREATE        | HttpStatusCodes.FORBIDDEN
        USER_VIEW          | HttpStatusCodes.FORBIDDEN
    }

    @Test
    @Unroll
    def "User #user gets #status for GET /IntegrationService/#feed"() {
        when:
        def response = basicAuthRequest()
                .path(SERVICE_NAME)
                .path(feed)
                .credentials(user, PASSWORD)
                .build()
                .get()

        then:
        response.status == status.statusCode

        where:
        feed                              | user               | status
        'IntegrationObjects'              | USER_ADMIN         | HttpStatusCodes.NOT_FOUND
        'IntegrationObjects'              | USER_SERVICE       | HttpStatusCodes.FORBIDDEN
        'IntegrationObjects'              | USER_SERVICE_ADMIN | HttpStatusCodes.OK
        'IntegrationObjects'              | USER_MONITORING    | HttpStatusCodes.NOT_FOUND
        'IntegrationObjects'              | USER_CREATE        | HttpStatusCodes.FORBIDDEN
        'IntegrationObjects'              | USER_VIEW          | HttpStatusCodes.FORBIDDEN
        'IntegrationObjectItems'          | USER_ADMIN         | HttpStatusCodes.NOT_FOUND
        'IntegrationObjectItems'          | USER_SERVICE       | HttpStatusCodes.FORBIDDEN
        'IntegrationObjectItems'          | USER_SERVICE_ADMIN | HttpStatusCodes.OK
        'IntegrationObjectItems'          | USER_MONITORING    | HttpStatusCodes.NOT_FOUND
        'IntegrationObjectItems'          | USER_CREATE        | HttpStatusCodes.FORBIDDEN
        'IntegrationObjectItems'          | USER_VIEW          | HttpStatusCodes.FORBIDDEN
        'IntegrationObjectItemAttributes' | USER_ADMIN         | HttpStatusCodes.NOT_FOUND
        'IntegrationObjectItemAttributes' | USER_SERVICE       | HttpStatusCodes.FORBIDDEN
        'IntegrationObjectItemAttributes' | USER_SERVICE_ADMIN | HttpStatusCodes.OK
        'IntegrationObjectItemAttributes' | USER_MONITORING    | HttpStatusCodes.NOT_FOUND
        'IntegrationObjectItemAttributes' | USER_CREATE        | HttpStatusCodes.FORBIDDEN
        'IntegrationObjectItemAttributes' | USER_VIEW          | HttpStatusCodes.FORBIDDEN
    }

    @Test
    @Unroll
    def "User #user is Forbidden to POST to /IntegrationService"() {
        when:
        def response = basicAuthRequest()
                .path(SERVICE_NAME)
                .credentials(user, PASSWORD)
                .build()
                .post(Entity.json('{}'))

        then:
        response.status == HttpStatusCodes.FORBIDDEN.statusCode

        where:
        user << [USER_ADMIN, USER_CREATE, USER_VIEW, USER_SERVICE, USER_SERVICE_ADMIN, USER_MONITORING]
    }

    @Test
    @Unroll
    def "User #user gets #status for POST to /IntegrationService/#feed" () {
        given: "an integration object defines the payload"
        IntegrationTestUtil.importImpEx(
                'INSERT_UPDATE IntegrationObject; code[unique = true]',
                '; Order',
                'INSERT_UPDATE IntegrationObjectItem; integrationObject(code)[unique = true]; code[unique = true]; type(code)',
                '; Order ; Order      ; Order',
                'INSERT_UPDATE IntegrationObjectItemAttribute; integrationObjectItem(integrationObject(code), code)[unique = true]; attributeName[unique = true]; attributeDescriptor(enclosingType(code), qualifier); returnIntegrationObjectItem(integrationObject(code), code); unique[default = false]',
                '; Order:Order      ; code     ; Order:code          ;                  ; true')

        when:
        def response = basicAuthRequest()
                .path(SERVICE_NAME)
                .path(feed)
                .credentials(user, PASSWORD)
                .build()
                .post(Entity.json(json))

        then:
        response.status == status.statusCode

        where:
        feed                              | user               | status                    | json
        'IntegrationObjects'              | USER_ADMIN         | HttpStatusCodes.NOT_FOUND | '{"code": "Order"}'
        'IntegrationObjects'              | USER_SERVICE       | HttpStatusCodes.FORBIDDEN | '{"code": "Order"}'
        'IntegrationObjects'              | USER_SERVICE_ADMIN | HttpStatusCodes.CREATED   | '{"code": "Order"}'
        'IntegrationObjects'              | USER_MONITORING    | HttpStatusCodes.NOT_FOUND | '{"code": "Order"}'
        'IntegrationObjects'              | USER_CREATE        | HttpStatusCodes.FORBIDDEN | '{"code": "Order"}'
        'IntegrationObjects'              | USER_VIEW          | HttpStatusCodes.FORBIDDEN | '{"code": "Order"}'
        'IntegrationObjectItems'          | USER_ADMIN         | HttpStatusCodes.NOT_FOUND | '{"code": "Order", "integrationObject": {"code": "Order"}}'
        'IntegrationObjectItems'          | USER_SERVICE       | HttpStatusCodes.FORBIDDEN | '{"code": "Order", "integrationObject": {"code": "Order"}}'
        'IntegrationObjectItems'          | USER_SERVICE_ADMIN | HttpStatusCodes.CREATED   | '{"code": "Order", "integrationObject": {"code": "Order"}}'
        'IntegrationObjectItems'          | USER_MONITORING    | HttpStatusCodes.NOT_FOUND | '{"code": "Order", "integrationObject": {"code": "Order"}}'
        'IntegrationObjectItems'          | USER_CREATE        | HttpStatusCodes.FORBIDDEN | '{"code": "Order", "integrationObject": {"code": "Order"}}'
        'IntegrationObjectItems'          | USER_VIEW          | HttpStatusCodes.FORBIDDEN | '{"code": "Order", "integrationObject": {"code": "Order"}}'
        'IntegrationObjectItemAttributes' | USER_ADMIN         | HttpStatusCodes.NOT_FOUND | '{"attributeName": "code", "attributeDescriptor": {"qualifier": "code", "enclosingType": {"code": "Order"}}, "integrationObjectItem": {"code": "Order", "integrationObject": {"code": "Order"}}}'
        'IntegrationObjectItemAttributes' | USER_SERVICE       | HttpStatusCodes.FORBIDDEN | '{"attributeName": "code", "attributeDescriptor": {"qualifier": "code", "enclosingType": {"code": "Order"}}, "integrationObjectItem": {"code": "Order", "integrationObject": {"code": "Order"}}}'
        'IntegrationObjectItemAttributes' | USER_SERVICE_ADMIN | HttpStatusCodes.CREATED   | '{"attributeName": "code", "attributeDescriptor": {"qualifier": "code", "enclosingType": {"code": "Order"}}, "integrationObjectItem": {"code": "Order", "integrationObject": {"code": "Order"}}}'
        'IntegrationObjectItemAttributes' | USER_MONITORING    | HttpStatusCodes.NOT_FOUND | '{"attributeName": "code", "attributeDescriptor": {"qualifier": "code", "enclosingType": {"code": "Order"}}, "integrationObjectItem": {"code": "Order", "integrationObject": {"code": "Order"}}}'
        'IntegrationObjectItemAttributes' | USER_CREATE        | HttpStatusCodes.FORBIDDEN | '{"attributeName": "code", "attributeDescriptor": {"qualifier": "code", "enclosingType": {"code": "Order"}}}'
        'IntegrationObjectItemAttributes' | USER_VIEW          | HttpStatusCodes.FORBIDDEN | '{"attributeName": "code", "attributeDescriptor": {"qualifier": "code", "enclosingType": {"code": "Order"}}}'
    }

    def basicAuthRequest() {
        new BasicAuthRequestBuilder()
                .extensionName(Odata2webservicesConstants.EXTENSIONNAME)
                .accept('application/json')
    }
}
