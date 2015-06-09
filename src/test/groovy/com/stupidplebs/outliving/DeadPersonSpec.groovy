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

    void "domain has fields"() {
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
        
    }
}
