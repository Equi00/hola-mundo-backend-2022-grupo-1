package ar.edu.unsam.algo2.grupo1.holamundo.dto.itineraryDTO

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.holamundo.dto.DifficultyDTO
import ar.edu.unsam.algo2.grupo1.itinerary.Rate
import ar.edu.unsam.algo2.grupo1.itinerary.ItinearyDay
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.holamundo.dto.UserUpdateRequest
import ar.edu.unsam.algo2.grupo1.holamundo.repositories.ActivityRepository
import ar.edu.unsam.algo2.grupo1.holamundo.service.ActivityService
import ar.edu.unsam.algo2.grupo1.itinerary.RateData
import ar.edu.unsam.algo2.grupo1.repositories.UserRepository
import ar.edu.unsam.algo2.grupo1.repositories.VehicleRepository
import ar.edu.unsam.algo2.grupo1.users.User
import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


//Itinerary data required to update it
data class ItineraryUpdateRequest(
    val id: Int,
    val destination: Destination? = null,
    val days: MutableList<ItinearyDayDTO>? = mutableListOf(),
)

data class RateDataDTO( var rateScore: Int, var idUserRate: Int )

data class ActivityDTO(
    val id: Int,
    val cost: Int,
    val description: String,
    val initialTime: String,
    val endTime: String
)

data class GetActivityDTO(
    val id: Int,
    val cost: Float,
    val description: String,
    val initialTime: String,
    val endTime: String,
    val difficulty: DifficultyDTO,
    val duration: Int,
    val valid: Boolean
)

data class ItinearyDayDTO(val id: Int,val activities: MutableList<ActivityDTO>)

//fun convertRateDataDTO(rateDataDTO: RateDataDTO): RateData{
//    return  RateData(   rateDataDTO.rateScore,
 //                       userRepository.getById(rateDataDTO.userRate.id) as User)
//}

//User data response when update is success
data class ItineraryUpdateResponse(
    val id: Int,
    val creator: String
)