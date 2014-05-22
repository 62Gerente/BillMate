package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(User, [new User(email: "johndoe@mail.com")])
    }

    @Unroll("test user all constraints #field is #error")
    def "test user all constraints"() {
        when:
        def obj = new User("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field        | val
        'nullable'             | 'email'      | null
        'email'                | 'email'      | "john"
        'unique'               | 'email'      | "johndoe@mail.com"
        'nullable'             | 'createdAt'  | null
        'min'                  | 'createdAt'  | new Date() - 1

    }

}
