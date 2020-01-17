/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices.integrationkey

import de.hybris.bootstrap.annotations.UnitTest
import de.hybris.platform.integrationservices.model.IntegrationObjectItemAttributeModel
import de.hybris.platform.integrationservices.model.IntegrationObjectItemModel
import de.hybris.platform.integrationservices.model.IntegrationObjectModel
import de.hybris.platform.integrationservices.model.KeyAttribute
import org.junit.Test
import spock.lang.Specification
import spock.lang.Unroll

@UnitTest
class KeyAttributeValueUnitTest extends Specification {
    @Test
    def "throws exception when instantiated with null attribute"() {
        when:
        new KeyAttributeValue(null, 10)

        then:
        thrown(IllegalArgumentException)
    }

    @Test
    def "reads back key attribute"() {
        given:
        def keyAttribute = attribute('key')
        def keyAttributeValue = new KeyAttributeValue(keyAttribute, null)

        expect:
        keyAttributeValue.getAttribute() == keyAttribute
    }

    @Test
    def "reads back value"() {
        given:
        def keyAttribute = attribute('key')
        def keyAttributeValue = new KeyAttributeValue(keyAttribute, true)

        expect:
        keyAttributeValue.getValue()
    }

    @Test
    @Unroll
    def "is not equal when the other value #condition"() {
        given:
        def value = new KeyAttributeValue(attribute('one'), '1')

        expect:
        value != other

        where:
        condition                  | other
        'has different attribute'  | new KeyAttributeValue(attribute('two'), '1')
        'has different value'      | new KeyAttributeValue(attribute('one'), 'eins')
        'has different value type' | new KeyAttributeValue(attribute('one'), 1)
        'had different type'       | attributeModel('one')
        'is null'                  | null
    }

    @Test
    def "is equal when the other object has same attribute and value"() {
        expect:
        new KeyAttributeValue(attribute('good'), true) == new KeyAttributeValue(attribute('good'), true)
    }

    @Test
    @Unroll
    def "hashCode is not equal when the other value #condition"() {
        given:
        def value = new KeyAttributeValue(attribute('one'), '1')

        expect:
        value.hashCode() != other.hashCode()

        where:
        condition                  | other
        'has different attribute'  | new KeyAttributeValue(attribute('two'), '1')
        'has different value'      | new KeyAttributeValue(attribute('one'), 'eins')
        'has different value type' | new KeyAttributeValue(attribute('one'), 1)
    }

    @Test
    def "hashCode is equal when the other object has same attribute and value"() {
        expect:
        new KeyAttributeValue(attribute('good'), true).hashCode() == new KeyAttributeValue(attribute('good'), true).hashCode()
    }

    def attribute(String name) {
        new KeyAttribute(attributeModel(name))
    }

    def attributeModel(String name) {
        Stub(IntegrationObjectItemAttributeModel) {
            getAttributeName() >> name
            getIntegrationObjectItem() >> Stub(IntegrationObjectItemModel) {
                getIntegrationObject() >> Stub(IntegrationObjectModel)
            }
        }
    }
}
