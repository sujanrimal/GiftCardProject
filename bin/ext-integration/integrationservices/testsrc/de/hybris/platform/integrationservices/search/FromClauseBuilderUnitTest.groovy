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

package de.hybris.platform.integrationservices.search

import de.hybris.bootstrap.annotations.UnitTest
import de.hybris.platform.core.enums.RelationEndCardinalityEnum
import de.hybris.platform.core.model.enumeration.EnumerationMetaTypeModel
import de.hybris.platform.core.model.type.ComposedTypeModel
import de.hybris.platform.core.model.type.RelationDescriptorModel
import de.hybris.platform.core.model.type.RelationMetaTypeModel
import de.hybris.platform.integrationservices.model.IntegrationObjectItemModel
import org.junit.Test
import spock.lang.Specification
import spock.lang.Unroll

@UnitTest
class FromClauseBuilderUnitTest extends Specification {

	def builder = FromClauseBuilder.builder()

	@Test
	@Unroll
	def "from clause for many-to-many relationship with JOIN for #end"() {
		given:
		def condition = new WhereClauseCondition('{supercategories} = 1234')
		def filter = new WhereClauseConditions(condition)

		when:
		def query = builder
				.withFilter(filter)
				.withIntegrationObjectItem(item)
				.build()

		then:
		query == "SELECT {product:pk} FROM {Product* AS product JOIN RelationName AS relationname ON {product:pk} = {relationname:" + end + "}}"

		where:
		end      | item
		'target' | sourceItem()
		'source' | targetItem()
	}

	@Test
	def "from clause for enum type item"()
	{
		given:
		def condition = new WhereClauseCondition('{code} = enum2')
		def filter = new WhereClauseConditions(condition)

		when:
		def query = builder
				.withFilter(filter)
				.withIntegrationObjectItem(enumItem())
				.build()

		then:
		query == "SELECT {myenum:pk} FROM {MyEnum AS myenum}"
	}

	@Test
	def "from clause for multiple relations item"()
	{
		given:
		def condition1 = new WhereClauseCondition('{supercategories} = cat1')
		def condition2 = new WhereClauseCondition('{vendors} = vendor1')
		def filter = new WhereClauseConditions(condition1, condition2)

		when:
		def query = builder
				.withFilter(filter)
				.withIntegrationObjectItem(multiRelationItem())
				.build()

		then:
		query == "SELECT {product:pk} FROM {Product* AS product JOIN CategoryProductRelation AS categoryproductrelation ON {product:pk} = {categoryproductrelation:target} JOIN ProductVendorRelation AS productvendorrelation ON {product:pk} = {productvendorrelation:source}}"
	}

	@Test
	@Unroll
	def "from clause with type hierarchy restriction"() {
		given:
		def condition = new WhereClauseCondition('{code} = mycode')
		def filter = new WhereClauseConditions(condition)

		when:
		def query = builder
				.withFilter(filter)
				.withIntegrationObjectItem(item())
				.withTypeHierarchyRestriction(itemTypeMatch)
				.build()

		then:
		query == "SELECT {mycode:pk} FROM {MyCode" + end + " AS mycode}"

		where:
		end | itemTypeMatch
		'*' | ItemTypeMatch.ALL_SUB_AND_SUPER_TYPES
		''  | ItemTypeMatch.ALL_SUBTYPES
		'!' | ItemTypeMatch.RESTRICT_TO_ITEM_TYPE
	}

	private IntegrationObjectItemModel sourceItem() {
		def relationTypeModel = manyToManyRelationTypeModel()
		relationTypeModel.sourceTypeRole >> "supercategories"

		Stub(IntegrationObjectItemModel) {
			getType() >> Stub(ComposedTypeModel) {
				def relation = Stub(RelationDescriptorModel) {
					getRelationType() >> relationTypeModel
					getRelationName() >> "RelationName"
				}
				getDeclaredattributedescriptors() >> [relation]
				getInheritedattributedescriptors() >> []
				getCode() >> "Product"
				getAbstract() >> false
			}
		}
	}

	private IntegrationObjectItemModel targetItem() {
		def relationTypeModel = manyToManyRelationTypeModel()
		relationTypeModel.targetTypeRole >> "supercategories"

		Stub(IntegrationObjectItemModel) {
			getType() >> Stub(ComposedTypeModel) {
				def relation = Stub(RelationDescriptorModel) {
					getRelationType() >> relationTypeModel
					getRelationName() >> "RelationName"
				}
				getDeclaredattributedescriptors() >> [relation]
				getInheritedattributedescriptors() >> []
				getCode() >> "Product"
				getAbstract() >> false
			}
		}
	}

	private manyToManyRelationTypeModel() {
		Stub(RelationMetaTypeModel) {
			getSourceTypeCardinality() >> RelationEndCardinalityEnum.MANY
			getTargetTypeCardinality() >> RelationEndCardinalityEnum.MANY
		}
	}

	private IntegrationObjectItemModel enumItem() {
		Stub(IntegrationObjectItemModel) {
			getType() >> Stub(EnumerationMetaTypeModel) {
				getDeclaredattributedescriptors() >> []
				getInheritedattributedescriptors() >> []
				getCode() >> "MyEnum"
			}
		}
	}

	private IntegrationObjectItemModel item() {
		Stub(IntegrationObjectItemModel) {
			getType() >> Stub(ComposedTypeModel) {
				getDeclaredattributedescriptors() >> []
				getInheritedattributedescriptors() >> []
				getCode() >> "MyCode"
			}
		}
	}

	private IntegrationObjectItemModel multiRelationItem() {
		Stub(IntegrationObjectItemModel) {
			getType() >> Stub(ComposedTypeModel) {
				def relation1 = Stub(RelationDescriptorModel) {
					def sourceTypeModel = manyToManyRelationTypeModel()
					sourceTypeModel.sourceTypeRole >> "supercategories"

					getRelationType() >> sourceTypeModel
					getRelationName() >> "CategoryProductRelation"
				}

				def relation2 = Stub(RelationDescriptorModel) {
					def targetTypeModel = manyToManyRelationTypeModel()
					targetTypeModel.targetTypeRole >> "vendors"

					getRelationType() >> targetTypeModel
					getRelationName() >> "ProductVendorRelation"
				}
				getDeclaredattributedescriptors() >> [relation1, relation2]
				getInheritedattributedescriptors() >> []
				getCode() >> "Product"
				getAbstract() >> false
			}
		}
	}
}
