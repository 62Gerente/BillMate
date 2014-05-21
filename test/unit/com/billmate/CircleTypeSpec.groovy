package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CircleType)
class CircleTypeSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(CircleType, [new CircleType()])
    }

    @Unroll("test circleType all constraints #field is #error")
    def "test circleType all constraints"() {
        when:
        def obj = new CircleType("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field        | val
        'nullable'             | 'identifier' | null
        'nullable'             | 'name'       | null
        'blank'                | 'name'       | ''
    }
}
