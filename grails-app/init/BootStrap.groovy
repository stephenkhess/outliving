import com.stupidplebs.outliving.DeadPerson
import grails.util.Environment

class BootStrap {
	def deadPeopleLoaderService

    def init = { servletContext ->
    	if (Environment.current  == Environment.TEST) {
	    	assert new DeadPerson(name: "dead person 1", description: "description 1", birthDate: new Date(), deathDate: new Date()-100, daysAlive: 5).save()
	    	assert new DeadPerson(name: "dead person 2", description: "description 2", birthDate: new Date(), deathDate: new Date()-100, daysAlive: 5).save()
    	}
    	else {
			deadPeopleLoaderService.load(this.class.classLoader.getResourceAsStream('dead_people.psv'))
    	}

    }
    def destroy = {
    }
}
