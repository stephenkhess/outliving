package com.stupidplebs.outliving

import grails.converters.JSON

class MainController {

    def index() { }

    def search() {
		if (!params.year || !params.month || !params.day) {
			flash.message = "required fields: year, month, day"
			redirect action: "index"
			return
		}

		def today = new GregorianCalendar()
		def birthDate = new GregorianCalendar(
			params.year as Integer, 
			(params.month as Integer)-1, 
			params.day as Integer)

		def deadPeople = DeadPerson.findAllByDaysAlive(today - birthDate + 1)

		withFormat {
			html { [deadPeople: deadPeople] }
			json { render ([deadPeople: deadPeople] as JSON) }
		}

    }


}
