package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CustomDebt)
class CustomDebtSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(CustomDebt, [new CustomDebt()])
    }

    @Unroll("test customDebt all constraints #field is #error")
    def "test customDebt all constraints"() {
        when:
        def obj = new CustomDebt("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field        | val
        'nullable'             | 'expense'    | null
        'nullable'             | 'user'       | null
        'min'                  | 'percentage' | -1
        'nullable'             | 'percentage' | null
        'nullable'             | 'createdAt'  | null
        'min'                  | 'createdAt'  | new Date() - 1
    }
}
