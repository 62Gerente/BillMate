package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Circle)
class CircleSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(Circle, [new Circle()])
    }

    @Unroll("test circle all constraints #field is #error")
    def "test circle all constraints"() {
        when:
        def obj = new Circle("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field        | val
        'nullable'             | 'name'       | null
        'nullable'             | 'createdAt'  | null
    }

}
