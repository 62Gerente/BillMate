package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Payment)
class PaymentSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(Payment, [new Payment()])
    }

    @Unroll("test payment all constraints #field is #error")
    def "test payment all constraints"() {
        when:
        def obj = new Payment("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field          | val
        'nullable'             | 'value'        | null
        'min'                  | 'value'        | -1
        'nullable'             | 'date'         | null
        'nullable'             | 'createdAt'    | null
        'min'                  | 'createdAt'    | new Date() - 1
    }
}
