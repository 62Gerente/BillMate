package com.billmate

import grails.test.mixin.TestFor
import org.junit.After
import org.junit.Before
import org.junit.Test
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def myUser

    @Before
    def setup() {
        myUser = new User(name: "John Doe", email: "johndoe@mail.com",createdAt: new Date())
    }

    @After
    def cleanup() {
    }

    @Test
    void "Test that email is an valid format"() {

        when: 'mail format is valid'
        then: 'validation should pass'
        myUser.validate(['email'])

        when: 'mail format is invalid'
        myUser.setEmail("notanemail")

        then: 'validation should fail'
        !myUser.validate(['email'])

    }
}
