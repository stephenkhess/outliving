package com.stupidplebs.outliving

import grails.transaction.Transactional
import java.text.SimpleDateFormat

@Transactional
class DeadPeopleLoaderService {
	def simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")

    def load(inputStream) {
    	def count = 0
   		inputStream.splitEachLine("\\|") {
   			count++
   			if (count % 50000 == 0) {
   				println count + " dead people loaded"
   			}

        try {
          def birthDate = simpleDateFormat.parse(it[2])
          def deathDate = simpleDateFormat.parse(it[3])

          def deadPerson = new DeadPerson(name: it[0], description: it[1], birthDate: birthDate, deathDate: deathDate, daysAlive: it[4])

          if (deadPerson.validate()) {
            deadPerson.save()
          }

        }
        catch (Exception e) {
          println it
        }
   		}

    }

}
