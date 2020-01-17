/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.odata2services.odata.persistence.populator.processor

import de.hybris.bootstrap.annotations.UnitTest
import de.hybris.platform.core.model.ItemModel
import de.hybris.platform.core.model.type.AttributeDescriptorModel
import de.hybris.platform.core.model.type.CollectionTypeModel
import de.hybris.platform.integrationservices.model.TypeAttributeDescriptor
import de.hybris.platform.integrationservices.service.IntegrationObjectService
import de.hybris.platform.odata2services.odata.persistence.ItemConversionRequest
import de.hybris.platform.odata2services.odata.persistence.StorageRequest
import de.hybris.platform.servicelayer.model.ModelService
import de.hybris.platform.servicelayer.type.TypeService
import org.apache.olingo.odata2.api.ep.entry.ODataEntry
import org.apache.olingo.odata2.api.ep.feed.ODataFeed
import org.apache.olingo.odata2.core.ep.entry.EntryMetadataImpl
import org.apache.olingo.odata2.core.ep.entry.MediaMetadataImpl
import org.apache.olingo.odata2.core.ep.entry.ODataEntryImpl
import org.apache.olingo.odata2.core.uri.ExpandSelectTreeNodeImpl
import org.assertj.core.util.Maps
import org.junit.Test
import spock.lang.Specification
import spock.lang.Unroll

import static de.hybris.platform.odata2services.constants.Odata2servicesConstants.PRIMITIVE_ENTITY_PROPERTY_NAME

@UnitTest
class PrimitiveCollectionPropertyProcessorUnitTest extends Specification {
	def TEST_PROPERTY = "testProperty"
	def INTEGRATION_OBJECT_CODE = "TestIntegrationObject"
	def TEST_TYPE = "TestType"
    private ItemModel ITEM_MODEL = Stub(ItemModel) {
        getItemtype() >> TEST_TYPE
    }

	def modelService = Mock(ModelService) {
		isNew(ITEM_MODEL) >> Boolean.TRUE
	}

    def processor = Spy PrimitiveCollectionPropertyProcessor

    def setup() {
        processor.modelService = modelService
        processor.integrationObjectService = Stub(IntegrationObjectService) {
            findItemAttributeName(_ as String, _ as String, _ as String) >> TEST_PROPERTY
        }
        processor.typeService = Stub(TypeService) {
            getAttributeDescriptor(_ as String, _ as String) >> Stub(AttributeDescriptorModel) {
                getAttributeType() >> Stub(CollectionTypeModel)
                getQualifier() >> TEST_PROPERTY
            }
        }
    }

    @Test
    @Unroll
    def "Processor supported=#supported when the attribute collection=#collection and primitives=#primitive"() {
        given:
        def attributeDescriptor = Stub(TypeAttributeDescriptor) {
            isPrimitive() >> primitive
            isCollection() >> collection
        }

        expect:
        processor.isPropertySupported(Optional.of(attributeDescriptor), TEST_PROPERTY) == supported

        where:
        collection | primitive | supported
        true       | true      | true
        true       | false     | false
        false      | true      | false
        false      | false     | false
    }

    @Test
    def "Process item with no previously existing values"() {
        given: 'model service does not find previous values for the property'
        def newValues = ["/this/url", "/another/url"]
        modelService.getAttributeValue(ITEM_MODEL, TEST_PROPERTY) >> null
		givenIsPropertySupported()

        when:
        processor.processItem ITEM_MODEL, storageRequest(oDataFeedFor(newValues))

        then:
        1 * modelService.setAttributeValue(ITEM_MODEL, TEST_PROPERTY, newValues)
    }

    @Test
    def "Process item with previously existing values and no duplicates"() {
        given: 'model service finds existing values for the property'
        def existingValues = ["/this/url", "/another/url"]
        def newValues = ["/new/url", "/more/url"]
        modelService.getAttributeValue(ITEM_MODEL, TEST_PROPERTY) >> existingValues
        def expectedCollection = existingValues + newValues
        and:
        givenIsPropertySupported()

        when:
        processor.processItem ITEM_MODEL, storageRequest(oDataFeedFor(newValues))

        then:
        1 * modelService.setAttributeValue(ITEM_MODEL, TEST_PROPERTY, expectedCollection)
    }

    @Test
    def "Process item with previously existing values and duplicates"() {
        given: 'model service returns the value of the existing property'
		givenIsPropertySupported()
        def existingValues = Arrays.asList("/this/url", "/another/url")
        modelService.getAttributeValue(ITEM_MODEL, TEST_PROPERTY) >> existingValues

        when:
        processor.processItem ITEM_MODEL as ItemModel, storageRequest(oDataFeedFor(existingValues))

        then:
        1 * modelService.setAttributeValue(ITEM_MODEL, TEST_PROPERTY, existingValues)
    }

    @Test
    def "processEntity() returns collection of primitives as feed with a list of ODataEntry"() {
        given: 'an empty ODataEntry to be populated'
        def entry = oDataEntry()
        and:
        givenIsPropertySupported()
        and: 'attribute values exist'
        modelService.getAttributeValue(ITEM_MODEL, TEST_PROPERTY) >> ["/this/url", "/another/url"]
        and: 'request should be converted'
        def request = conversionRequest()
        request.isPropertyValueShouldBeConverted(TEST_PROPERTY) >> true

        when:
        processor.processEntity(entry, request)

        then:
        entry.properties[TEST_PROPERTY].entries[0].properties["value"] == "/this/url"
        entry.properties[TEST_PROPERTY].entries[1].properties["value"] == "/another/url"
    }

    @Test
    def "processEntity() does not process an unsupported property"() {
        given:
        givenIsPropertySupported()
        and:
        def request = conversionRequest()
        request.isPropertyValueShouldBeConverted(TEST_PROPERTY) >> false
        def entry = oDataEntry()

        when:
        processor.processEntity entry, request

        then:
        entry.properties.isEmpty()
    }

    @Test
    @Unroll
    def "processEntity() does not process an attribute when it has collection=#col and primitive=#prim"() {
        given: 'attribute descriptor is not applicable'
        def notApplicableAttribute = Stub(TypeAttributeDescriptor) {
                isCollection() >> col
                isPrimitive() >> prim
        }
        processor.findTypeDescriptorAttributeForItem(_, _) >> Optional.of(notApplicableAttribute)
        and:
        def request = conversionRequest()
        request.isPropertyValueShouldBeConverted(TEST_PROPERTY) >> true
        def entry = oDataEntry()

        when:
        processor.processEntity entry, request

        then:
        entry.properties.isEmpty()

        where:
        col   | prim
        false | false
        true  | false
        false | true
    }

    StorageRequest storageRequest(def feed) {
        Stub(StorageRequest) {
            getIntegrationObjectCode() >> INTEGRATION_OBJECT_CODE
            getODataEntry() >> Stub(ODataEntry) {
                getProperties() >> [(TEST_PROPERTY): feed]
            }
        }
    }

    def givenIsPropertySupported() {
		processor.findTypeDescriptorAttributeForItem(_, _) >> Optional.of(applicableAttribute())
	}

	def createUrlPatternFeed(List<Object> urlPatterns) {
		def urlPatternEntries = new ArrayList<>()
		urlPatterns.each { u -> urlPatternEntries.add(createEntry(u)) }
		def feed = Mock(ODataFeed) {
			getEntries() >> urlPatternEntries
		}
		feed
	}

    def createEntry(Object o) {
		def entry = Mock(ODataEntry) {
			getProperties() >> Maps.newHashMap(PRIMITIVE_ENTITY_PROPERTY_NAME, o)
		}
		entry
	}

    def applicableAttribute() {
        Stub(TypeAttributeDescriptor) {
            isPrimitive() >> true
            isCollection() >> true
        }
    }

    def oDataFeedFor(List<Object> values) {
        Stub(ODataFeed) {
            getEntries() >> values.collect { oDataEntry([(PRIMITIVE_ENTITY_PROPERTY_NAME):it]) }
        }
    }

    def conversionRequest() {
        Stub(ItemConversionRequest) {
            getIntegrationObjectCode() >> INTEGRATION_OBJECT_CODE
            getValue() >> ITEM_MODEL
            getAllPropertyNames() >> [TEST_PROPERTY]
        }
    }

    private static ODataEntryImpl oDataEntry(def values = [:]) {
        new ODataEntryImpl(values, new MediaMetadataImpl(), new EntryMetadataImpl(), new ExpandSelectTreeNodeImpl())
    }
}
