package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Action)
class ActionSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(Action, [new Action()])
    }

    @Unroll("test action all constraints #field is #error")
    def "test action all constraints"() {
        when:
        def obj = new User("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field        | val
        'nullable'             | 'actionType' | null
        'nullable'             | 'actionDate' | null
        'min'                  | 'actionDate' | new Date() - 1

    }
}