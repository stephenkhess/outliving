package com.stupidplebs.outliving

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DeadPerson)
class DeadPersonSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "domain has fields"() {
    	given:
    	def birthDate = new Date()-100
		def deathDate = new Date()-200

		and: "value"
		def deadPerson = new DeadPerson(
    		name: "Name", 
    		description: "Description",
    		birthDate: birthDate,
    		deathDate: deathDate,
    		daysAlive: 17
    		)

        expect:
        deadPerson.name == "Name"
        deadPerson.description == "Description"
        deadPerson.birthDate == birthDate
        deadPerson.deathDate == deathDate
        deadPerson.daysAlive == 17
        
        and:
        deadPerson.validate()

    }

    def "fields are required"() {
    	given:
    	def deadPerson = new DeadPerson()

    	expect:
    	!deadPerson.validate()

    	and:
    	deadPerson.hasErrors()

    	and:
    	deadPerson.errors.getFieldError("name").code == "nullable"
    	!deadPerson.errors.getFieldError("description")
    	deadPerson.errors.getFieldError("birthDate").code == "nullable"
    	deadPerson.errors.getFieldError("deathDate").code == "nullable"
    	deadPerson.errors.getFieldError("daysAlive").code == "nullable"

    }

    def "empty name should trigger error"() {
    	given:
		def deadPerson = new DeadPerson(name: " \t ")

		expect:
		!deadPerson.validate()

		and:
		deadPerson.errors.getFieldError("name").code == "nullable"

    }

    def "daysAlive must be >= 0"() {
    	given:
		def deadPerson = new DeadPerson(daysAlive: -1)

		expect:
		!deadPerson.validate()

		and:
		deadPerson.errors.getFieldError("daysAlive").code == "min.notmet"

		when:
		deadPerson.daysAlive = 0
		deadPerson.validate()

		then:
		!deadPerson.errors.getFieldError("daysAlive")

    }

    def "daysAlive must be <= 45625"() {
    	given:
		def deadPerson = new DeadPerson(daysAlive: 45626)

		expect:
		!deadPerson.validate()

		and:
		deadPerson.errors.getFieldError("daysAlive").code == "max.exceeded"

		when:
		deadPerson.daysAlive = 45625
		deadPerson.validate()

		then:
		!deadPerson.errors.getFieldError("daysAlive")

    }

}
