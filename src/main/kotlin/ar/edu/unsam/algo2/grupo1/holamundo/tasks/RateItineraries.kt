package ar.edu.unsam.algo2.grupo1.tasks
import ar.edu.unsam.algo2.grupo1.users.User

class RateItineraries(var score: Int) : Task(){
    override fun performInternalTask(user: User) {
        user.itinerariesToRate.forEach {
            it.addScore(score, user)
        }
        user.clearItineraryToRate()
    }
}