/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices.model.impl

import de.hybris.bootstrap.annotations.UnitTest
import de.hybris.platform.core.model.type.AtomicTypeModel
import de.hybris.platform.core.model.type.AttributeDescriptorModel
import de.hybris.platform.core.model.type.ComposedTypeModel
import de.hybris.platform.integrationservices.model.*
import org.junit.Test
import spock.lang.Specification
import spock.lang.Unroll

import static de.hybris.platform.integrationservices.model.BaseMockItemAttributeModelBuilder.simpleAttributeBuilder
import static de.hybris.platform.integrationservices.model.MockIntegrationObjectItemModelBuilder.*

@UnitTest
class ItemTypeDescriptorUnitTest extends Specification {
    private static final String DEFAULT_IO_CODE = 'IO'

    @Test
    def "creates new ItemTypeDescriptor"() {
        expect:
        ItemTypeDescriptor.create(Stub(IntegrationObjectItemModel)) instanceof ItemTypeDescriptor
    }

    @Test
    def "create(null) throws exception"() {
        when:
        ItemTypeDescriptor.create(null)

        then:
        thrown(IllegalArgumentException)
    }

    @Test
    def "reads integration object code"() {
        given:
        def descriptor = typeDescriptor('TestObject', 'TestItem')

        expect:
        descriptor.integrationObjectCode == 'TestObject'
    }

    @Test
    def "reads integration object item code"() {
        given:
        def descriptor = typeDescriptor(itemModelBuilder()
                .withCode("MyProduct")
                .withItemModelCode("Product"))

        expect:
        descriptor.itemCode == "MyProduct"
    }

    @Test
    def "read integration object item type code"() {
        given:
        def descriptor = typeDescriptor(itemModelBuilder()
                .withCode("MyProduct")
                .withItemModelCode("Product"))

        expect:
        descriptor.typeCode == "Product"
    }

    @Test
    def "isPrimitive() is always false"() {
        expect:
        !typeDescriptor().primitive
    }

    @Test
    def "getAttributes() is empty when item does not have attributes defined"() {
        expect:
        typeDescriptor().attributes.empty
    }

    @Test
    def "getAttributes() returns attributes defined in the item model"() {
        given:
        def descriptor = typeDescriptor(itemModelBuilder()
                .withAttribute(simpleAttributeBuilder().withName("Attribute 1"))
                .withAttribute(simpleAttributeBuilder().withName("Attribute 2")))

        expect:
        descriptor.attributes.collect { it.attributeName } == ["Attribute 2", "Attribute 1"]
    }

    @Test
    def "getAttributes() does not leak immutability"() {
        given:
        def descriptor = typeDescriptor(itemModelBuilder().withAttribute(simpleAttributeBuilder()))

        when:
        descriptor.attributes.clear()

        then:
        descriptor.attributes.size() == 1
    }

    @Test
    def "getAttribute() returns empty result when attribute does not exist"() {
        expect:
        !typeDescriptor().getAttribute('someAttribute').present
    }

    @Test
    def "getAttribute() returns matching attribute descriptor when the attribute exists"() {
        given:
        def descriptor = typeDescriptor(itemModelBuilder()
                .withAttribute(simpleAttributeBuilder().withName("One"))
                .withAttribute(simpleAttributeBuilder().withName("Two")))

        when:
        def attribute = descriptor.getAttribute("Two").orElse(null)

        then:
        attribute.attributeName == 'Two'
    }

    @Test
    def "isEnumeration() is true for enumeration types in the type system"() {
        given:
        def descriptor = typeDescriptor(itemModelBuilder().withItemModel(enumerationModel("ChannelType")))

        expect:
        descriptor.enumeration
    }

    @Test
    def "isEnumeration() is false for composite types in the type system"() {
        given:
        def descriptor = typeDescriptor(itemModelBuilder().withItemModel(composedTypeModel("Channel")))

        expect:
        !descriptor.enumeration
    }

    @Unroll
    @Test
    def "isAbstract() is #result when getAbstract() is #isAbstract"() {
        given:
        final ComposedTypeModel model = Stub(ComposedTypeModel)
        model.getAbstract() >> isAbstract

        def descriptor = typeDescriptor(itemModelBuilder().withItemModel(model))

        expect:
        descriptor.abstract == result

        where:
        isAbstract | result
        true       | true
        false      | false
        null       | false
    }

    @Test
    def "retrieves KeyDescriptor"() {
        expect:
        typeDescriptor().getKeyDescriptor() != null
    }

    @Test
    @Unroll
    def "not equal to another type descriptor when the other type descriptor #condition"() {
        given:
        def sample = typeDescriptor("Inbound", "Item")

        expect:
        sample != other

        where:
        condition                               | other
        'is null'                               | null
        'is not instance of ItemTypeDescriptor' | Stub(TypeDescriptor)
        'has different integration object code' | typeDescriptor("Outbound", "Item")
        'had different item code'               | typeDescriptor("Inbound", "Primitive")
    }

    @Test
    def "equals to another type descriptor when it has same object and item codes"() {
        expect:
        typeDescriptor("Inbound", "Item") == typeDescriptor("Inbound", "Item")
    }

    @Test
    @Unroll
    def "hashCode not equal when type descriptors #condition"() {
        given:
        def sample = typeDescriptor("Inbound", "Item")

        expect:
        sample.hashCode() != other.hashCode()

        where:
        condition                                 | other
        'have different class'                    | Stub(TypeDescriptor)
        'have different integration object codes' | typeDescriptor("Outbound", "Item")
        'have different item codes'               | typeDescriptor("Inbound", "Primitive")
    }

    @Test
    def "hashCode is equal when type descriptors have same integration object and item codes"() {
        expect:
        typeDescriptor("Inbound", "Item").hashCode() == typeDescriptor("Inbound", "Item").hashCode()
    }

    private ComposedTypeModel composedType(String code, ComposedTypeModel... subtypes) {
        Stub(ComposedTypeModel) {
            getCode() >> code
            getAllSubTypes() >> subtypes.toList()
        }
    }

    private ItemTypeDescriptor typeDescriptor(final String object, final String item) {
        def itemModel = Stub(IntegrationObjectItemModel) {
            getIntegrationObject() >> Stub(IntegrationObjectModel) {
                getCode() >> object
            }
            getCode() >> item
        }
        typeDescriptor(itemModel)
    }

    private ItemTypeDescriptor typeDescriptor() {
        typeDescriptor(Stub(IntegrationObjectItemModel))
    }

    private static ItemTypeDescriptor typeDescriptor(MockIntegrationObjectItemModelBuilder modelBuilder) {
        typeDescriptor(modelBuilder.build())
    }

    private static ItemTypeDescriptor typeDescriptor(IntegrationObjectItemModel model) {
        new ItemTypeDescriptor(model)
    }

    private IntegrationObjectItemAttributeModel attributeModel(String name, IntegrationObjectItemModel item=null) {
        Stub(IntegrationObjectItemAttributeModel) {
            getAttributeName() >> name
            getIntegrationObjectItem() >> itemModel()
            getReturnIntegrationObjectItem() >> (item ?: itemModel())
        }
    }

    private IntegrationObjectItemAttributeModel primitiveAttributeModel(String name) {
        Stub(IntegrationObjectItemAttributeModel) {
            getIntegrationObjectItem() >> itemModel()
            getReturnIntegrationObjectItem() >> null
            getAttributeName() >> name
            getAttributeDescriptor() >> Stub(AttributeDescriptorModel) {
                getAttributeType() >> Stub(AtomicTypeModel)
            }
        }
    }

    private IntegrationObjectItemModel itemModel(List<IntegrationObjectItemAttributeModel> attributes=[]) {
        itemModel('', attributes)
    }

    private IntegrationObjectItemModel itemModel(String type, List<IntegrationObjectItemAttributeModel> attributes=[]) {
        Stub(IntegrationObjectItemModel) {
            getCode() >> type
            getIntegrationObject() >> Stub(IntegrationObjectModel) {
                getCode() >> DEFAULT_IO_CODE
            }
            getAttributes() >> attributes
        }
    }
}