package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ActionType)
class ActionTypeSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(ActionType, [new ActionType()])
    }

    @Unroll("test actionType all constraints #field is #error")
    def "test actionType all constraints"() {
        when:
        def obj = new ActionType("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field        | val
        'blank'                | 'type'       | ''
        'nullable'             | 'type'       | null
    }
}
