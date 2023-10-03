package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.holamundo.dto.UserUpdateResponse
import ar.edu.unsam.algo2.grupo1.holamundo.dto.VehiclePreferenceDTO
import ar.edu.unsam.algo2.grupo1.holamundo.dto.VehiclePreferenceUnique
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.repositories.BaseData
import ar.edu.unsam.algo2.grupo1.observers.ObserverBehaviour
import ar.edu.unsam.algo2.grupo1.users.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.properties.Delegates
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.format.DateTimeFormatter
import java.util.Arrays

class User(
        var name: String,
        var surname: String,
        var userName: String,
        @JsonIgnore
        var initialDate: LocalDate,
        var countryOfResidence: String
        ): BaseData {
    override var id: Int = 0
    var friends: MutableList<User> = mutableListOf<User>()
    var desiredDestinations: MutableList<Destination> = mutableListOf<Destination>()
    var travelDays: Int = 0
    var visitedDestinations: MutableList<Destination> = mutableListOf<Destination>()
    @JsonIgnore
    var vehiclePreference: VehiclePreference = Neophyte()
    var itineraries: MutableList<Itinerary> = mutableListOf<Itinerary>()
    var personality: Personality = PersonalityRelaxed()
    var email: String = ""
    var itinerariesToRate: MutableList<Itinerary> = mutableListOf<Itinerary>()
    @JsonIgnore
    var password: String = ""

    @JsonIgnore
    private var observers: MutableList<ObserverBehaviour> = mutableListOf<ObserverBehaviour>()

    @JsonProperty("vehiclePreference")
    fun getVehiclePreferenceJSON(): VehiclePreferenceDTO =
        VehiclePreferenceDTO(
            name = this.vehiclePreference.name,
            specifiedBrand = this.vehiclePreference.getSpecifiedBrand(),
            vehiclePreferences = this.vehiclePreference.getCombinedVehiclePersonalities().map {
                convertVehiclePersonalitiesToJSON(it)
            }
        )

    fun convertVehiclePersonalitiesToJSON(vehiclePreference: VehiclePreference ): VehiclePreferenceUnique =
        VehiclePreferenceUnique(
            name = vehiclePreference.name,
            specifiedBrand = vehiclePreference.getSpecifiedBrand()
        )

    @JsonProperty("initialDate")
    fun getInitialDateAsString(): String {
        val d = this.initialDate
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return d.format(formatter)
    }

    @JsonProperty
    fun antiquity(): Int = ChronoUnit.YEARS.between(initialDate,LocalDate.now()).toInt()

    fun antiquityMax(topValue: Int): Int = minOf(this.antiquity(), topValue)

    fun isValid(): Boolean =
        this.isNotEmptyData() && this.initialDateIsValid() && this.travelDaysIsValid() && this.thereIsDesiredDestination()

    fun canRateItinerary(itinerary: Itinerary): Boolean =
        !itinerary.isTheCreatorUser(this) && this.isVisitedDestination(itinerary.destination)

    fun rateItinerary(itinerary: Itinerary, score: Int){
        if (this.canRateItinerary(itinerary)){
            itinerary.addScore(score,this)
        }else{
            throw IllegalAccessException("El itinerario no puede ser puntuado por este usuario!!")
        }
    }

    fun canDoItinerary(itinerary: Itinerary): Boolean =
        this.enoughtDaysToTravel(itinerary) && this.personality.canDoItinerary(this, itinerary)

    fun createItinerary(destination: Destination, activities: ArrayList<Activity>){
        this.addItinerary(Itinerary(this, destination))
    }

    fun canEditItinerary(itinerary: Itinerary): Boolean =
        (itinerary.isTheCreatorUser(this) || ((itinerary.creator.isFriend(this))
                && this.isVisitedDestination(itinerary.destination)))

    fun isFriend(user: User): Boolean = friends.contains(user)

    fun addFriend(user: User) { friends.add(user) }

    fun removeFriend(user: User){ friends.remove(user) }

    fun knowDestination(destination: Destination): Boolean = visitedDestinations.contains(destination)

    fun changePersonality(personality: Personality){
        this.personality = personality
    }

    fun isDesiredDestination(destination: Destination): Boolean = desiredDestinations.contains(destination)

    fun addVisitDestination(destination: Destination) {
        visitedDestinations.add(destination)
    }

    fun addDesiredDestination(destination: Destination){
        desiredDestinations.add(destination)
    }

    fun removeDesiredDestination(destination: Destination){
        desiredDestinations.remove(destination)
    }

    fun changeTravelDays(newDays : Int){
        this.travelDays = newDays
    }

    fun changeVehiclePreference(vehiclePreference: VehiclePreference) {
        this.vehiclePreference = vehiclePreference
    }

    fun doTrip(itinerary: Itinerary) {
        this.addVisitDestination(itinerary.destination)
        this.observers.forEach { it.doBehaviour(this, itinerary) }
    }

    fun addBehaviour(behaviour: ObserverBehaviour) {
        this.observers.add(behaviour)
    }

    fun removeBehaviour(behaviour: ObserverBehaviour) {
        this.observers.remove(behaviour)
    }

    fun addItineraryToRate(itinerary: Itinerary) {
        this.itinerariesToRate.add(itinerary)
    }

    fun clearItineraryToRate(){
        itinerariesToRate.clear()
    }

    fun friendWithLessVisitedDestination(): User? =  this.friends.minByOrNull{ it.visitedDestinations.size }

    fun addAllItineraries(itineraries: MutableList<Itinerary>) { this.itineraries.addAll(itineraries) }

    fun addAllFriends(newFriends: List<User>) { this.friends.addAll(newFriends) }

    fun addAllDestinations(destinations: List<Destination>) { this.desiredDestinations.addAll(destinations) }

    fun mostExpensiveDestination(): Destination? =
        this.desiredDestinations.maxByOrNull { it.totalCostFor(this) }



    private fun isNotEmptyData(): Boolean =
        this.name.isNotBlank() &&
        this.surname.isNotBlank() &&
        this.userName.isNotBlank() &&
        this.countryOfResidence.isNotBlank()

    private fun initialDateIsValid(): Boolean = this.initialDate.isBefore(LocalDate.now())

    private fun travelDaysIsValid(): Boolean = this.travelDays > 0

    private fun thereIsDesiredDestination(): Boolean = desiredDestinations.size > 0

    private fun isVisitedDestination(destination: Destination): Boolean = visitedDestinations.contains(destination)

    private fun enoughtDaysToTravel(itinerary: Itinerary): Boolean = this.travelDays >= itinerary.numberOfDays()

    private fun addItinerary(itinerary: Itinerary){ this.itineraries.add(itinerary) }

    fun toResponse() = UserUpdateResponse(
        id = id,
        name = name
    )

    @JsonProperty("homeInfo")
    fun homeData(): HomeInfo {
        return HomeInfo(
            itinerariosPuntuados = this.itinerariesToRate.size,
            itinerariosCreados = this.itineraries.size,
            destinosVisitados = this.visitedDestinations.size,
            amigos = this.friends.size
        )
    }
}

data class HomeInfo(
    var itinerariosPuntuados: Int,
    var itinerariosCreados: Int,
    var destinosVisitados: Int,
    var amigos: Int
)






