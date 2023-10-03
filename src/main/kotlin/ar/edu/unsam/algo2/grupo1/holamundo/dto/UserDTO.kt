package ar.edu.unsam.algo2.grupo1.holamundo.dto

import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.repositories.UserRepository
import ar.edu.unsam.algo2.grupo1.users.*
import java.time.LocalDate

fun stringToVehiclePreference(name: String, specifiedBrand: String = "", preferences: List<VehiclePreferenceUnique> = listOf()): VehiclePreference {
    val mapeo: Map<String, VehiclePreference> = mapOf(
        "Neophyte" to Neophyte(),
        "Superstitious" to Superstitious(),
        "Capricious" to Capricious(),
        "Selective" to Selective(specifiedBrand),
        "NoLimit" to NoLimit(),
        "Combined" to Combined().apply {
            this.vehiclePersonalities.addAll(
                preferences.map { stringToVehiclePreference(it.name, it.specifiedBrand) }
            )
        }
    )
    return mapeo[name]!!
}

fun stringToPersonality(name: String): Personality {
    val mapeo: Map<String, Personality> = mapOf(
        "Relajado" to PersonalityRelaxed(),
        "Localista" to PersonalityLocalist(),
        "Cauteloso" to PersonalityCautios(),
        "Soñador" to PersonalityDreamer(),
        "Activo" to PersonalityActive(),
        "Exigente" to PersonalityExigent(Difficulty.HIGH,50)
    )
    return mapeo[name]!!
}

//User data required to add a friend
data class UserAsFriend(
    val id: Int,
    val name: String
) {}

//User data required to add a vehicle preference in Combined vehicle preference
data class VehiclePreferenceUnique(
    val name: String,
    val specifiedBrand: String
)

//User data required to add a vehicle personality
data class VehiclePreferenceDTO(
    var name: String,
    var specifiedBrand: String,
    var vehiclePreferences: List<VehiclePreferenceUnique>
)

data class PersonalityDTO(
    var name: String,
    var difficulty: DifficultyDTO
)

data class DifficultyDTO(
    var name: String,
    var priority: Int,
)

//User data required to update it
data class UserUpdateRequest(
    val id: Int,
    val name: String,
    val surname: String,
    val userName: String,
    val countryOfResidence: String,
    val travelDays: Int,
    val email: String,
    val friends: List<UserAsFriend>,
    val desiredDestinations: List<Destination>,
    val visitedDestinations: List<Destination>,
    val personality: PersonalityDTO,
    val vehiclePreference: VehiclePreferenceDTO,
)


// Convertion from userUpdateRequest to a class instance or entity
fun UserUpdateRequest.toEntity(id: Int, userRepository: UserRepository) = User(
    name = name,
    surname = surname,
    userName = userName,
    countryOfResidence = countryOfResidence,
    initialDate = LocalDate.of(2022,12,25)
).also {
    it.id = id
    it.travelDays = travelDays
    it.email = email
    it.friends = friends.map { userRepository.getById(it.id) ?: throw ElementNotFoundException("User with id ${it}") }.toMutableList()
    it.desiredDestinations = desiredDestinations.toMutableList()
    it.visitedDestinations = visitedDestinations.toMutableList()
    it.personality = stringToPersonality(personality.name)
    it.vehiclePreference = stringToVehiclePreference(vehiclePreference.name, vehiclePreference.specifiedBrand, vehiclePreference.vehiclePreferences)
}



//User data response when update is success
data class UserUpdateResponse(
    val id: Int,
    val name: String
)
