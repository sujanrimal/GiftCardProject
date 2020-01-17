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
package de.hybris.platform.integrationservices.service.impl

import de.hybris.bootstrap.annotations.UnitTest
import de.hybris.platform.core.model.ItemModel
import de.hybris.platform.core.model.product.ProductModel
import de.hybris.platform.core.model.type.ComposedTypeModel
import de.hybris.platform.integrationservices.model.IntegrationObjectItemModel
import de.hybris.platform.integrationservices.model.IntegrationObjectModel
import de.hybris.platform.integrationservices.model.TypeDescriptor
import de.hybris.platform.integrationservices.populator.ItemToMapConversionContext
import de.hybris.platform.integrationservices.service.IntegrationObjectNotFoundException
import de.hybris.platform.integrationservices.service.IntegrationObjectService
import de.hybris.platform.servicelayer.dto.converter.Converter
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException
import org.junit.Test
import spock.lang.Specification
import spock.lang.Unroll

@UnitTest
class DefaultIntegrationObjectConversionServiceUnitTest extends Specification {
    private static final String INTEGRATION_OBJECT = "ProductIntegrationObject"
    private static final def CONVERSION_RESULT = [:]

    def conversionService = new DefaultIntegrationObjectConversionService()
    def integrationObjectService = Stub(IntegrationObjectService)
    def itemToIntegrationObjectMapConverter = Stub(Converter) {
        convert(_, _) >> CONVERSION_RESULT
    }

    def setup() {
        conversionService.setIntegrationObjectService(integrationObjectService)
        conversionService.setItemToIntegrationObjectMapConverter(itemToIntegrationObjectMapConverter)
    }

    @Test
    def "populate result map when integration object exists"() {
        given:
        integrationObjectService.findIntegrationObject(_) >> Stub(IntegrationObjectModel)
        integrationObjectService.findIntegrationObjectItemByTypeCode(_, _) >> Stub(IntegrationObjectItemModel) {
            getType() >> Stub(ComposedTypeModel)
        }

        when:
        def map = conversionService.convert(Stub(ProductModel), INTEGRATION_OBJECT)

        then:
        map == CONVERSION_RESULT
    }

    @Test
    @Unroll
    def "throws #expectedException when integration object code is '#code'"() {
        given:
        integrationObjectService.findIntegrationObject(_ as String) >> { throw Stub(exceptionToThrow) }

        when:
        conversionService.convert(new ItemModel(), code)

        then:
        thrown expectedException

        where:
        code                            | exceptionToThrow         | expectedException
        "${INTEGRATION_OBJECT}notFound" | ModelNotFoundException   | IntegrationObjectNotFoundException
        ""                              | IllegalArgumentException | IllegalArgumentException
    }

    @Test
    def "throws exception when item model is null"() {
        when:
        conversionService.convert(null, INTEGRATION_OBJECT)

        then:
        thrown IllegalArgumentException
    }

    @Test
    def "context conversion places the conversion result into the context"() {
        ItemToMapConversionContext context = Mock() {
            getTypeDescriptor() >> Stub(TypeDescriptor)
        }

        when:
        def result = conversionService.convert(context)

        then:
        result.is CONVERSION_RESULT
        1 * context.setConversionResult([:])
    }

    @Test
    def "context conversion returns matching conversion result in the context"() {
        given:
        def contextConversionResult = [source: 'context']
        def context = Stub(ItemToMapConversionContext) {
            getConversionResult() >> contextConversionResult
            getTypeDescriptor() >> Stub(IntegrationObjectItemModel)
        }

        when:
        def result = conversionService.convert(context)

        then:
        result == contextConversionResult
    }
}
