package ar.edu.unsam.algo2.grupo1.observers

import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.users.User

interface ObserverBehaviour {
    fun doBehaviour(user: User, itinerary: Itinerary)
}