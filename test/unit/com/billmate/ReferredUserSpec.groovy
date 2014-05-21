package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReferredUser)
class ReferredUserSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(ReferredUser, [new ReferredUser()])
    }

    @Unroll("test referredUser all constraints #field is #error")
    def "test referredUser all constraints"() {
        when:
        def obj = new ReferredUser("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field          | val
        'nullable'             | 'user'        | null
    }
}
