/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package de.hybris.platform.odata2webservicesfeaturetests

import de.hybris.bootstrap.annotations.IntegrationTest
import de.hybris.platform.inboundservices.util.InboundMonitoringRule
import de.hybris.platform.integrationservices.util.IntegrationTestUtil
import de.hybris.platform.integrationservices.util.JsonObject
import de.hybris.platform.odata2webservices.constants.Odata2webservicesConstants
import de.hybris.platform.odata2webservicesfeaturetests.ws.BasicAuthRequestBuilder
import de.hybris.platform.servicelayer.ServicelayerSpockSpecification
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer
import org.junit.Rule
import org.junit.Test

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Tests for STOUT-1258 Expand Support feature
 */
@NeedsEmbeddedServer(webExtensions = Odata2webservicesConstants.EXTENSIONNAME)
@IntegrationTest
class GetExpandSupportIntegrationTest extends ServicelayerSpockSpecification {
	private static def TEST_EXPAND_2_LEVEL = 'TestItemForEnumExpand'

	@Rule
	InboundMonitoringRule monitoring = InboundMonitoringRule.disabled()

    def setup() {
        importCsv("/impex/essentialdata-odata2services.impex", "UTF-8") // For the integrationadmingroup (from odata2services)
        IntegrationTestUtil.importImpEx(
                '$catalog = Default',
                '$version = Staged',
                '$catalogVersion = $catalog:$version',
                'INSERT_UPDATE Employee; UID[unique = true]; groups(uid); @password[translator = de.hybris.platform.impex.jalo.translators.UserPasswordTranslator]',
                '; tester ; integrationadmingroup; *:retset',
                'INSERT_UPDATE IntegrationObject; code[unique = true]; integrationType(code)',
                '; ExpandSupport; INBOUND',
                'INSERT_UPDATE IntegrationObjectItem; integrationObject(code)[unique = true]; code[unique = true]; type(code)',
                '; ExpandSupport  ; Product         ; Product',
                '; ExpandSupport  ; Catalog         ; Catalog',
                '; ExpandSupport  ; CatalogVersion  ; CatalogVersion',
                'INSERT_UPDATE IntegrationObjectItemAttribute; integrationObjectItem(integrationObject(code), code)[unique = true]; attributeName[unique = true]; attributeDescriptor(enclosingType(code), qualifier); returnIntegrationObjectItem(integrationObject(code), code); unique[default = false]',
                '; ExpandSupport:Catalog        ; id              ; Catalog:id              ;',
                '; ExpandSupport:CatalogVersion ; catalog         ; CatalogVersion:catalog  ; ExpandSupport:Catalog',
                '; ExpandSupport:CatalogVersion ; version         ; CatalogVersion:version  ;',
                '; ExpandSupport:Product        ; code            ; Product:code            ;',
                '; ExpandSupport:Product        ; catalogVersion  ; Product:catalogVersion  ; ExpandSupport:CatalogVersion',
                'INSERT_UPDATE Catalog; id[unique = true]; name[lang = en]; defaultCatalog;',
                '; $catalog ; $catalog ; true',
                'INSERT_UPDATE CatalogVersion; catalog(id)[unique = true]; version[unique = true]; active;',
                '; $catalog ; $version ; true',
                'INSERT_UPDATE Product; code[unique = true]; catalogVersion(catalog(id), version)',
                '; product1 ; $catalogVersion',
                '; product2 ; $catalogVersion')
    }

    @Test
    def "entities not expanded when \$expand is not present"() {
        when:
        def response = basicAuthRequest('ExpandSupport')
                .path('Products')
                .build()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get()

        then:
        response.status == 200
        def json = extractBody response
        json.exists "\$.d.results[?(@.code == 'product1')].catalogVersion.__deferred"
        json.exists "\$.d.results[?(@.code == 'product2')].catalogVersion.__deferred"
    }

    @Test
    def "entities are expanded when \$expand is present"() {
        when:
        def response = basicAuthRequest('ExpandSupport')
                .path('Products')
                .queryParam('$expand', 'catalogVersion')
                .build()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get()

        then:
        response.status == 200
        def json = extractBody(response)
        json.getCollectionOfObjects("\$.d.results[*].catalogVersion.version") == ["Staged", "Staged"]
    }

    @Test
    def "expands collection of enums"()
    {
        given:
        final String itemKey = "testItem1Code"
        final String enumKey1 = "string"
        final String enumKey2 = "bool"
        IntegrationTestUtil.importImpEx(
                "INSERT_UPDATE IntegrationObjectItem; integrationObject(code)[unique = true]; code[unique = true]                         ; type(code)",
                "                                   ; ExpandSupport                         ; TestIntegrationItem                         ; TestIntegrationItem",
                "                                   ; ExpandSupport                         ; OData2webservicesFeatureTestPropertiesTypes ; OData2webservicesFeatureTestPropertiesTypes",
                "INSERT_UPDATE IntegrationObjectItemAttribute ; integrationObjectItem(integrationObject(code), code)[unique = true] ; attributeName[unique = true] ; attributeDescriptor(enclosingType(code), qualifier) ; returnIntegrationObjectItem(integrationObject(code), code)  ; unique[default = false]",
                "                                             ; ExpandSupport:TestIntegrationItem                                   ; code                         ; TestIntegrationItem:code",
                "                                             ; ExpandSupport:TestIntegrationItem                                   ; testEnums                    ; TestIntegrationItem:testEnums                       ; ExpandSupport:OData2webservicesFeatureTestPropertiesTypes   ; false",
                "                                             ; ExpandSupport:OData2webservicesFeatureTestPropertiesTypes           ; code                         ; OData2webservicesFeatureTestPropertiesTypes:code",

                "INSERT_UPDATE TestIntegrationItem ; code[unique = true] ; testEnums(code)",
                "                                  ; $itemKey            ; $enumKey1,$enumKey2"
        )

        when:
        def response = basicAuthRequest('ExpandSupport')
                .path('TestIntegrationItems')
                .queryParam('$expand', 'testEnums')
                .build()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get()

        then:
        response.status == 200
        def json = extractBody(response)
        json.getCollectionOfObjects('d.results[?(@.code == "testItem1Code")].testEnums.results[*].code').containsAll([enumKey1, enumKey2])
    }

    @Test
    def "not found error returned when invalid \$expand option is specified"() {
        when:
        def response = basicAuthRequest('ExpandSupport')
                .path('Products')
                .queryParam('$expand', 'units')
                .build()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get()

        then:
        response.status == 404
        def json = extractBody(response)
        json.getString("\$.error.message.value").contains "units"
    }

    @Test
    def "entities are expanded when \$expand option of an enum is provided at the second level"() {
        given:
        IntegrationTestUtil.importImpEx(
                "INSERT_UPDATE IntegrationObject; code[unique = true]",
                "; $TEST_EXPAND_2_LEVEL",
                "INSERT_UPDATE IntegrationObjectItem; integrationObject(code)[unique = true]; code[unique = true] ; type(code)",
                "; $TEST_EXPAND_2_LEVEL ; TestIntegrationItem       ; TestIntegrationItem",
                "; $TEST_EXPAND_2_LEVEL ; TestIntegrationItemDetail ; TestIntegrationItemDetail",
                "; $TEST_EXPAND_2_LEVEL ; TestSampleEnum            ; TestSampleEnum",
                "INSERT_UPDATE IntegrationObjectItemAttribute; integrationObjectItem(integrationObject(code), code)[unique = true]; attributeName[unique = true]; attributeDescriptor(enclosingType(code), qualifier); returnIntegrationObjectItem(integrationObject(code), code)",
                "; $TEST_EXPAND_2_LEVEL:TestIntegrationItem   	    ; code      ; TestIntegrationItem:code",
                "; $TEST_EXPAND_2_LEVEL:TestIntegrationItem   	    ; detail    ; TestIntegrationItem:detail         ; $TEST_EXPAND_2_LEVEL:TestIntegrationItemDetail",
                "; $TEST_EXPAND_2_LEVEL:TestIntegrationItemDetail   ; code      ; TestIntegrationItemDetail:code",
                "; $TEST_EXPAND_2_LEVEL:TestIntegrationItemDetail   ; testEnum  ; TestIntegrationItemDetail:testEnum ; $TEST_EXPAND_2_LEVEL:TestSampleEnum",
                "; $TEST_EXPAND_2_LEVEL:TestSampleEnum              ; code      ; TestSampleEnum:code",
                "INSERT_UPDATE TestIntegrationItemDetail; code[unique=true]; testEnum(code)",
                "; detail1 ; two",
                "INSERT_UPDATE TestIntegrationItem; code[unique=true]; detail(code)",
                "; testItem1 ; detail1")
        when:
        def response = basicAuthRequest(TEST_EXPAND_2_LEVEL)
                .path('TestIntegrationItems')
                .queryParam('$expand', 'detail/testEnum')
                .build()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get()

        then:
        response.status == 200
        def json = extractBody(response)
        json.getCollectionOfObjects("\$.d.results[*].detail.code").contains('detail1')
        json.getCollectionOfObjects("\$.d.results[*].detail.testEnum.code").contains('two')
    }

    BasicAuthRequestBuilder basicAuthRequest(String serviceName)
    {
        new BasicAuthRequestBuilder()
                .extensionName(Odata2webservicesConstants.EXTENSIONNAME)
                .credentials('tester', 'retset') // defined inside setup()
                .path(serviceName)
    }

    JsonObject extractBody(final Response response)
    {
        JsonObject.createFrom((InputStream) response.getEntity())
    }
}
