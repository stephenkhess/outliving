package com.stupidplebs.outliving

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DeadPeopleLoaderService)
@Mock(DeadPerson)
class DeadPeopleLoaderServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "there is a load method that takes a inputStream"() {
    	given:
    	def input1 = "dead person 1|description 1|1900-01-01|1900-12-31|365"
    	def input2 = "dead person 2|description 2|1901-01-01|1901-12-31|365"

    	and:
    	def inputStream = new ByteArrayInputStream([input1, input2].join("\n").bytes)

        when:
        service.load(inputStream)

        then:
        DeadPerson.list()*.name == ["dead person 1", "dead person 2"]
        DeadPerson.list()*.description == ["description 1", "description 2"]

    }
}
