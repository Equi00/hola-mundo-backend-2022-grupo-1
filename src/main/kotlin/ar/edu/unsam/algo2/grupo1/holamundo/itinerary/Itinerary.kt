package ar.edu.unsam.algo2.grupo1.itinerary

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.holamundo.dto.itineraryDTO.ItineraryUpdateResponse
import ar.edu.unsam.algo2.grupo1.repositories.BaseData
import ar.edu.unsam.algo2.grupo1.users.User
import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle


class Itinerary(var creator: User, var destination: Destination): BaseData {
    override var id: Int = 0
    //Attributes
    var days: MutableList<ItinearyDay> = mutableListOf()
    var rate = Rate()
    lateinit var vehicle: Vehicle

    //Public methods
    fun numberOfDays(): Int = days.size

    fun addEmptyDay() { days.add(ItinearyDay()) }
    fun addEmptyDays(numberOfDays: Int) { for (i in 1..numberOfDays) this.addEmptyDay() }

    fun totalCost(): Float = days.sumOf { it.totalActivitiesCost().toDouble() }.toFloat()

    fun totalActivities(): Int = days.sumOf { it.activities.size }

    fun avarageDurationDay(): Float = this.totalActivitiesDuration() / this.totalActivities()

    fun avarageDurationInDay(activityDay: Int): Int = days[activityDay].avarageDuration()

    fun difficulty(): Difficulty?  {
        val maxNumberOfActivities = this.getAllActivitiesDifficultiesAsMap()
        return (getAllActivitiesAsList().groupingBy { it.difficulty }.eachCount()).filter {it.value == maxNumberOfActivities}.maxByOrNull{it.key!!.priority}?.key
    }

    fun addScore(score: Int, user: User) { rate.addRate(score,user) }

    fun isAlmostOneActivityPerDay(): Boolean = days.all { it.isAlmostOneActivity() }

    fun isValid() =
        isAlmostOneActivityPerDay() && !isMoreThanOneActivityAtSameTime() && !hasBeenScoredMultipleTimesBySameUser() && isAllScoresValid() && haveAlmostADay()

    fun isTheCreatorUser(user: User): Boolean = user.equals(creator)

    fun getAllActivitiesAsList(): List<Activity>{
        val activities: MutableList<Activity> = mutableListOf()
        days.forEach { activities.addAll(it.activities) }
        return activities
    }

    fun addDay(day: ItinearyDay){ days.add(day) }

    fun country(): String = destination.country

    fun city(): String = destination.city

    fun isLocal(): Boolean = destination.isLocal()

    fun brandForAgreement(): String = vehicle.firstBrandWithAgreement()

    //Private methods
    private fun totalActivitiesDuration(): Float = days.sumOf { it.activities.sumOf { it.durationInMinutes() } }.toFloat()
    private fun getAllActivitiesDifficultiesAsMap(): Int = (getAllActivitiesAsList().groupingBy { it.difficulty }.eachCount()).maxOf { it.value }
    private fun isMoreThanOneActivityAtSameTime(): Boolean = days.any{ it.areOverlapedAcitivities() }
    private fun hasBeenScoredMultipleTimesBySameUser(): Boolean = rate.isMultipleRatesByOneUser()
    private fun isAllScoresValid(): Boolean = rate.isValid()
    private fun haveAlmostADay(): Boolean = days.isNotEmpty()

    fun toResponse() = ItineraryUpdateResponse(
        id = id,
        creator = creator.userName
    )
}
