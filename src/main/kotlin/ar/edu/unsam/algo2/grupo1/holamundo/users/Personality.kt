package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.users.User

interface Personality {
    var name: String
    fun canDoItinerary(user: User, itinerary: Itinerary): Boolean
}

class PersonalityRelaxed() : Personality {
    override var name: String = "Relajado"

    override fun canDoItinerary(user: User, itinerary: Itinerary): Boolean = true
}

class PersonalityLocalist() : Personality {
    override var name: String = "Localista"

    override fun canDoItinerary(user: User, itinerary: Itinerary): Boolean = itinerary.destination.isLocal()
}

class PersonalityCautios() : Personality {
    override var name: String = "Cauteloso"
    override fun canDoItinerary(user: User, itinerary: Itinerary): Boolean =
        knowDestination(user, itinerary) or someFriendKnowDestination(user, itinerary)

    private fun knowDestination(user: User, itinerary: Itinerary): Boolean = user.knowDestination(itinerary.destination)
    private fun someFriendKnowDestination(user: User, itinerary: Itinerary): Boolean =
        user.friends.any { it.knowDestination(itinerary.destination) }

}

class PersonalityDreamer() : Personality {
    override var name: String = "Soñador"

    override fun canDoItinerary(user: User, itinerary: Itinerary): Boolean =
        user.isDesiredDestination(itinerary.destination) or isMoreExpensive(user, itinerary)

    private fun maxCostDesiredDestination(user: User): Float = user.desiredDestinations.maxOf { it.totalCostFor(user) }
    private fun isMoreExpensive(user: User, itinerary: Itinerary): Boolean =
        itinerary.destination.totalCostFor(user) > maxCostDesiredDestination(user)

}

class PersonalityActive() : Personality {
    override var name: String = "Activo"

    override fun canDoItinerary(user: User, itinerary: Itinerary): Boolean = itinerary.isAlmostOneActivityPerDay()
}

class PersonalityExigent(val difficulty: Difficulty, val percentage: Int) : Personality {
    override var name: String = "Exigente"

    override fun canDoItinerary(user: User, itinerary: Itinerary): Boolean =
        percentageDifficulty(itinerary) >= percentage

    private fun countDifficultyActivities(itinerary: Itinerary): Int =
        itinerary.getAllActivitiesAsList().count { it.difficulty!!.equals(difficulty) }

    private fun percentageDifficulty(itinerary: Itinerary): Float =
        ((countDifficultyActivities(itinerary) * 100) / itinerary.totalActivities()).toFloat()

}

