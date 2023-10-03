package ar.edu.unsam.algo2.grupo1.observers

import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.users.PersonalityLocalist
import ar.edu.unsam.algo2.grupo1.users.User

class TripLocalObserver : ObserverBehaviour {
    override fun doBehaviour(user: User, itinerary: Itinerary) {
        if(!itinerary.isLocal()) {
            user.changePersonality(PersonalityLocalist())
        }
    }
}