package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DefaultExpenseType)
class DefaultExpenseTypeSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(User, [new DefaultExpenseType()])
    }

    @Unroll("test defaultExpenseType all constraints #field is #error")
    def "test defaultExpenseType all constraints"() {
        when:
        def obj = new DefaultExpenseType("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field          | val
        'nullable'             | 'expenseType'  | null
    }
}
