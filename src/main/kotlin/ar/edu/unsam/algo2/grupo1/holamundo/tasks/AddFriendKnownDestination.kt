package ar.edu.unsam.algo2.grupo1.tasks
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.users.User

class AddFriendKnownDestination(var itinerary: Itinerary) : Task() {
    override fun performInternalTask(user: User) {
        user.addAllFriends(this.userRepository.searchUsersByDestination(itinerary.destination))
    }
}