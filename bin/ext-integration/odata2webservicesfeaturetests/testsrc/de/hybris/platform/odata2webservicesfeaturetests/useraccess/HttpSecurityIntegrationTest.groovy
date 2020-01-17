/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.odata2webservicesfeaturetests.useraccess

import de.hybris.bootstrap.annotations.IntegrationTest
import de.hybris.platform.catalog.model.CatalogModel
import de.hybris.platform.core.model.user.EmployeeModel
import de.hybris.platform.integrationservices.model.IntegrationObjectModel
import de.hybris.platform.integrationservices.util.IntegrationTestUtil
import de.hybris.platform.odata2webservices.constants.Odata2webservicesConstants
import de.hybris.platform.servicelayer.ServicelayerSpockSpecification
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer
import org.apache.olingo.odata2.api.commons.HttpStatusCodes
import org.junit.Test
import org.springframework.http.MediaType
import spock.lang.Unroll

import javax.ws.rs.client.Entity

import static de.hybris.platform.integrationservices.util.JsonBuilder.json
import static de.hybris.platform.odata2webservicesfeaturetests.useraccess.UserAccessTestUtils.ADMIN_USER
import static de.hybris.platform.odata2webservicesfeaturetests.useraccess.UserAccessTestUtils.NO_GROUP_USER
import static de.hybris.platform.odata2webservicesfeaturetests.useraccess.UserAccessTestUtils.PASSWORD
import static de.hybris.platform.odata2webservicesfeaturetests.useraccess.UserAccessTestUtils.SERVICE

@NeedsEmbeddedServer(webExtensions = [Odata2webservicesConstants.EXTENSIONNAME])
@IntegrationTest
class HttpSecurityIntegrationTest extends ServicelayerSpockSpecification {
	private static final String CATALOG_ID = "testNonRestrictedCatalog"

	def setupSpec() {
		importCsv '/impex/essentialdata-odata2services.impex', 'UTF-8'

		UserAccessTestUtils.givenCatalogIOExists()
		IntegrationTestUtil.createCatalogWithId(CATALOG_ID)
	}

	def cleanupSpec() {
		IntegrationTestUtil.removeAll IntegrationObjectModel
		IntegrationTestUtil.findAny(CatalogModel, { it.id == CATALOG_ID }).ifPresent { IntegrationTestUtil.remove it }
	}

	@Test
	@Unroll
	def "User must be authenticated in order to GET #path"() {
		when:
		def response = UserAccessTestUtils.basicAuthRequest()
				.path(path)
				.build()
				.get()

		then:
		response.status == HttpStatusCodes.UNAUTHORIZED.statusCode

		where:
		path << [SERVICE + '/$metadata', "$SERVICE/Catalogs('${CATALOG_ID}')"]
	}

	@Test
	@Unroll
	def "User must be authenticated in order to POST to #path"() {
		when:
		def response = UserAccessTestUtils.basicAuthRequest()
				.path(path)
				.build()
				.post(Entity.json('{}'))

		then:
		response.status == HttpStatusCodes.UNAUTHORIZED.statusCode

		where:
		path << ["$SERVICE/Catalogs", SERVICE + '/$batch']
	}


	@Test
	@Unroll
	def "Wrong credentials for GET"() {
		given:
		final String password = "password"
		final String userId = "testAdminUser"
		IntegrationTestUtil.importImpEx(
				'$password=@password[translator = de.hybris.platform.impex.jalo.translators.UserPasswordTranslator]',
				'INSERT_UPDATE Employee; UID[unique = true] ; groups(uid)[mode = append] ;$password',
				"                      ; $userId            ; integrationadmingroup      ;*:$password"
		)

		when:
		def response = UserAccessTestUtils.basicAuthRequest(path)
				.credentials(userId, "wrong-${password}")
				.build()
				.get()

		then:
		response.status == HttpStatusCodes.UNAUTHORIZED.statusCode

		cleanup:
		IntegrationTestUtil.findAny(EmployeeModel, { it.uid == userId }).ifPresent { IntegrationTestUtil.remove it }

		where:
		path << [SERVICE + '/$metadata', "$SERVICE/Catalogs('${CATALOG_ID}')"]
	}

	@Test
	@Unroll
	def "Wrong credentials for POST"() {
		given:
		final String password = "password"
		final String userId = "testAdminUser"
		IntegrationTestUtil.importImpEx(
				'$password=@password[translator = de.hybris.platform.impex.jalo.translators.UserPasswordTranslator]',
				'INSERT_UPDATE Employee; UID[unique = true] ; groups(uid)[mode = append] ;$password',
				"                      ; $userId            ; integrationadmingroup      ;*:$password"
		)

		when:
		def response = UserAccessTestUtils.basicAuthRequest(path)
				.credentials(userId, "wrong-${password}")
				.build()
				.post(Entity.json(Entity.json(json().withField("id", "testNewCatalog").build())))

		then:
		response.status == HttpStatusCodes.UNAUTHORIZED.statusCode

		cleanup:
		IntegrationTestUtil.findAny(EmployeeModel, { it.uid == userId }).ifPresent { IntegrationTestUtil.remove it }

		where:
		path << ["$SERVICE/Catalogs", SERVICE + '/$batch']
	}

	@Test
	@Unroll
	def "User #user gets #status for GET #path"() {
		given:
		UserAccessTestUtils.givenUserExistsForEachEssentialIntegrationApiUserGroup()

		when:
		def response = UserAccessTestUtils.basicAuthRequest(path)
				.credentials(user, PASSWORD)
				.build()
				.get()

		then:
		response.status == status.statusCode

		where:
		user          | status                    | path
		NO_GROUP_USER | HttpStatusCodes.FORBIDDEN | SERVICE + '/$metadata'
		ADMIN_USER    | HttpStatusCodes.OK        | SERVICE + '/$metadata'
		NO_GROUP_USER | HttpStatusCodes.FORBIDDEN | "$SERVICE/Catalogs('${CATALOG_ID}')"
		ADMIN_USER    | HttpStatusCodes.OK        | "$SERVICE/Catalogs('${CATALOG_ID}')"
	}

	@Test
	@Unroll
	def "User #user gets #status for POST TestCatalog/Catalogs"() {
		given:
		UserAccessTestUtils.givenUserExistsForEachEssentialIntegrationApiUserGroup()

		when:
		def response = UserAccessTestUtils.basicAuthRequest("$SERVICE/Catalogs")
				.credentials(user, PASSWORD)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.build()
				.post(Entity.json(json().withField("id", "testNewCatalog").build()))

		then:
		response.status == status.statusCode

		where:
		user          | status
		NO_GROUP_USER | HttpStatusCodes.FORBIDDEN
		ADMIN_USER    | HttpStatusCodes.CREATED
	}
}
