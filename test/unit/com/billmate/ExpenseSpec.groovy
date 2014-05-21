package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Expense)
class ExpenseSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(Expense, [new Expense()])
    }

    @Unroll("test expense all constraints #field is #error")
    def "test expense all constraints"() {
        when:
        def obj = new Expense("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field          | val
        'nullable'             | 'responsible'  | null
        'nullable'             | 'title'        | null
        'blank'                | 'title'        | ""
        'maxSize'              | 'description'  | 'a'*2001
        'min'                  | 'value'        | -1
        'nullable'             | 'value'        | null
        'nullable'             | 'beginDate'    | null
        'nullable'             | 'createdAt'    | null
    }
}
