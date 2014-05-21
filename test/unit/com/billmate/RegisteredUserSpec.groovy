package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RegisteredUser)
class RegisteredUserSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(RegisteredUser, [new RegisteredUser(phoneNumber: "123456789")])
    }

    @Unroll("test registeredUser all constraints #field is #error")
    def "test registeredUser all constraints"() {
        when:
        def obj = new RegisteredUser("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field          | val
        'nullable'             | 'user'         | null
        'matches'              | 'phoneNumber'  | "123"
        'unique'               | 'phoneNumber'  | "123456789"
        'size'                 | 'password'     | "1234"
        'size'                 | 'password'     | "a"*21
        'nullable'             | 'password'     | null
        'blank'                | 'password'     | ''
    }
}
