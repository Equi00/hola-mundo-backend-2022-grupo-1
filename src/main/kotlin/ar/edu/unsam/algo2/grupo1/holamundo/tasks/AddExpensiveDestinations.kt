package ar.edu.unsam.algo2.grupo1.tasks
import ar.edu.unsam.algo2.grupo1.users.User

class AddExpensiveDestinations() : Task() {
    override fun performInternalTask(user: User) {
        user.addAllDestinations(user.friends.map { it.mostExpensiveDestination()!! })
    }
}