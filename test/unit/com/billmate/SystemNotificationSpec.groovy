package com.billmate

import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(SystemNotification)
class SystemNotificationSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(SystemNotification, [new SystemNotification()])
    }

    @Unroll("test systemNotification all constraints #field is #error")
    def "test systemNotification all constraints"() {
        when:
        def obj = new SystemNotification("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field            | val
        'nullable'             | 'action'         | null
        'nullable'             | 'registeredUser' | null
        'nullable'             | 'notification'   | null
        'nullable'             | 'isRead'         | null
    }
}
