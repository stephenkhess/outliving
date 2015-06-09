package com.stupidplebs.outliving

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

		[deadPeople: DeadPerson.findAllByDaysAlive(today - birthDate + 1)]

    }

}
