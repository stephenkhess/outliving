package com.stupidplebs.outliving

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MainController)
@Mock(DeadPerson) // adds .save() and other domain methods to DeadPerson
class MainControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "there is an index"() {
    	expect:
    	!controller.index()
    }

    def "search with no params redirects to index"() {
    	when:
    	controller.search()

    	then:
    	controller.response.redirectUrl == "/main/index"
    	flash.message == "required fields: year, month, day"

    }

    def "search with params "() {
    	given: "people that exist only in memory"
    	def goodDeadPerson1 = new DeadPerson(name: "name 1", description: "description 1", birthDate: new Date()-100, deathDate: new Date()-200, daysAlive: 5).save()
    	def goodDeadPerson2 = new DeadPerson(name: "name 2", description: "description 2", birthDate: new Date()-300, deathDate: new Date()-400, daysAlive: 5).save()

    	and:
    	def badDeadPerson1 = new DeadPerson(name: "name 3", description: "description 3", birthDate: new Date()-500, deathDate: new Date()-600, daysAlive: 4).save()
    	def badDeadPerson2 = new DeadPerson(name: "name 3", description: "description 3", birthDate: new Date()-700, deathDate: new Date()-800, daysAlive: 6).save()

    	and:
    	def today = new GregorianCalendar().time
    	def birthDate = new GregorianCalendar().time - 4

		and:
    	params.day = birthDate.date as String
    	params.month = (birthDate.month+1) as String
    	params.year = (birthDate.year+1900) as String

    	when:
    	def ret = controller.search()

    	then:
    	ret.deadPeople == [goodDeadPerson1, goodDeadPerson2]

    }

    def "search with params in JSON"() {
        given: "people that exist only in memory"
        def goodDeadPerson1 = new DeadPerson(name: "name 1", description: "description 1", birthDate: new Date()-100, deathDate: new Date()-200, daysAlive: 5).save()
        def goodDeadPerson2 = new DeadPerson(name: "name 2", description: "description 2", birthDate: new Date()-300, deathDate: new Date()-400, daysAlive: 5).save()

        and:
        def badDeadPerson1 = new DeadPerson(name: "name 3", description: "description 3", birthDate: new Date()-500, deathDate: new Date()-600, daysAlive: 4).save()
        def badDeadPerson2 = new DeadPerson(name: "name 3", description: "description 3", birthDate: new Date()-700, deathDate: new Date()-800, daysAlive: 6).save()

        and:
        def today = new GregorianCalendar().time
        def birthDate = new GregorianCalendar().time - 4

        and:
        params.format = "json"
        params.day = birthDate.date as String
        params.month = (birthDate.month+1) as String
        params.year = (birthDate.year+1900) as String

        when:
        controller.search()

        then:
        response.json.deadPeople.size() == 2
        response.json.deadPeople.name == ["name 1", "name 2"]

    }

    def "year parameter not found should flash message"() {
    	given:
    	params.day = day
    	params.month = month
    	params.year = year

    	when:
    	controller.search()

    	then:
    	controller.response.redirectUrl == "/main/index"
    	flash.message == "required fields: year, month, day"

    	where:
    	day | month | year
    	"1" | "1" | null
    	"1" | null | "1"
    	null| "1" | "1" 

    }

}
