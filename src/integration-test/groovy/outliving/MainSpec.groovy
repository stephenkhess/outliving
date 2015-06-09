package outliving

import grails.test.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Integration
@Rollback
class MainSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }

    def "test something"() {
        when:"The home page is visited"
            go '/'

        then:"The title is correct"
        	$('title').text() == "Welcome to Grails"
    }

    def "input fields exist"() {
        when:
        go '/main/index'

        then:
        title == "Outliving"

        and:
        year().find("option", text:"Year")
        month().find("option", text:"Month")
        day().find("option", text:"Day")

        and:
        submit()
        submit == "Submit"

    }

    def "form submission without year should redirect back with flash message"() {
        when:
        go '/main/index'

        and:
        submit().click()

        then:
        title == "Outliving"

        and:
        year().find("option", text:"Year")
        month().find("option", text:"Month")
        day().find("option", text:"Day")

        and:
        $('.flash').text() == "required fields: year, month, day"

    }

    def "form submission with all values should show dead people"() {
        when:
        go '/main/index'

        and:
        def birthDate = new GregorianCalendar().time - 4

        year = (birthDate.year+1900) as String
        month = (birthDate.month+1) as String
        day = birthDate.date as String

        and:
        submit().click()

        then:
        title == "By surviving to midnight, you've managed to outlive these people"

        and:
        $('ul li')*.text() == ["dead person 1", "dead person 2"]

    }

}
