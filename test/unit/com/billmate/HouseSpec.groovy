package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(House)
class HouseSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(House, [new House()])
    }

    @Unroll("test house all constraints #field is #error")
    def "test house all constraints"() {
        when:
        def obj = new House("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field          | val
        'nullable'             | 'circle'         | null
    }
}
