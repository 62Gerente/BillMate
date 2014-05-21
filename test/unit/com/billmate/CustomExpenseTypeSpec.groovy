package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CustomExpenseType)
class CustomExpenseTypeSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(User, [new CustomExpenseType()])
    }

    @Unroll("test customExpenseType all constraints #field is #error")
    def "test customExpenseType all constraints"() {
        when:
        def obj = new CustomExpenseType("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field          | val
        'nullable'             | 'expenseType'  | null
    }
}
