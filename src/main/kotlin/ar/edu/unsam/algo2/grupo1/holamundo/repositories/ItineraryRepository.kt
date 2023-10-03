package ar.edu.unsam.algo2.grupo1.repositories

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.holamundo.repositories.ActivityRepository
import ar.edu.unsam.algo2.grupo1.itinerary.ItinearyDay
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.users.User
import ar.edu.unsam.algo2.grupo1.vehicle.Car
import ar.edu.unsam.algo2.grupo1.vehicle.Motocicle
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class ItineraryRepository : BaseRepository<Itinerary>() {
    init { this.setDefaultValues() }

    override fun setDefaultValues() {
        var userRepository = UserRepository()
        var destinationRepository = DestinationRepository()
        var vehicleRepository = VehicleRepository()
        var activityRepository = ActivityRepository()
        var count = 0
        this.elements = mutableSetOf(
            Itinerary(
                userRepository.getById(0)!!,
                destinationRepository.getById(0)!!
            ).apply {
                this.vehicle = vehicleRepository.getById(0)!!
                addDay(ItinearyDay()
                    .apply {
                        addActivity(activityRepository.getById(0)!!)
                        addActivity((activityRepository.getById(1)!!))
                    })
                addScore(7,userRepository.getById(0)!!)
                addScore(8,userRepository.getById(1)!!)
            },

            Itinerary(
                userRepository.getById(1)!!,
                destinationRepository.getById(1)!!
            ).apply {
                this.vehicle = vehicleRepository.getById(1)!!
                addDay(ItinearyDay()
                    .apply {
                        addActivity(activityRepository.getById(1)!!)
                        addActivity((activityRepository.getById(2)!!))
                    })
                addScore(4,userRepository.getById(0)!!)
                addScore(6,userRepository.getById(1)!!)
            }
        )
        this.elements.forEach{ it.id = count++ }
    }

    override fun search(value: String): List<Itinerary> =
        this.elements.filter { it.destination.country.equals(value, ignoreCase = true) ||
                                it.destination.city.equals(value, ignoreCase = true) ||
                                it.getAllActivitiesAsList().any { it.description.contains(value, ignoreCase = true)} }

    fun getItineraryDestination(id: Int) = this.getById(id)?.destination

    fun addActivityToItinerary(id: Int, dayId: Int, activity: Activity): Itinerary{
        val itinerary = this.getById(id)
        itinerary?.days?.get(dayId-1)?.addActivity(activity)
        return itinerary!!
    }
}
