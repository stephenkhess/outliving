import com.stupidplebs.outliving.DeadPerson

class BootStrap {

    def init = { servletContext ->
    	assert new DeadPerson(name: "dead person 1", description: "dead guy 1", birthDate: new Date(), deathDate: new Date()-100, daysAlive: 5).save()
    	assert new DeadPerson(name: "dead person 2", description: "dead guy 2", birthDate: new Date(), deathDate: new Date()-100, daysAlive: 5).save()
    }
    def destroy = {
    }
}
