package ar.edu.unsam.algo2.grupo1.tasks
import ar.edu.unsam.algo2.grupo1.users.User

class TransferItinerariesToFriend() : Task() {
    override fun performInternalTask(user: User) {
        user.friendWithLessVisitedDestination()?.addAllItineraries(user.itineraries)
    }
}