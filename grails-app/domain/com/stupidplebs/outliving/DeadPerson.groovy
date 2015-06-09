package com.stupidplebs.outliving

class DeadPerson {
	String name
	String description
	Date birthDate
	Date deathDate
	Integer daysAlive 

    static constraints = {
    	description(nullable: true)
    	daysAlive(min: 0, max: 365*125)
    }
}
