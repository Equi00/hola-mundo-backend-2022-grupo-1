package ar.edu.unsam.algo2.grupo1.holamundo.service

import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.holamundo.dto.getDifficultyByPriority
import ar.edu.unsam.algo2.grupo1.holamundo.dto.itineraryDTO.*
import ar.edu.unsam.algo2.grupo1.itinerary.ItinearyDay
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.repositories.ItineraryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
class ItineraryService {
    @Autowired
    lateinit var itineraryRepository: ItineraryRepository

    @Autowired
    lateinit var activityService: ActivityService

    @Autowired
    lateinit var userService: UserService

    fun getAll(): List<Itinerary> = itineraryRepository.getAll()

    fun search(toSearch: String): List<Itinerary> = itineraryRepository.search(toSearch)

    fun getById(itineraryId: Int): Itinerary? = itineraryRepository.getById(itineraryId) ?: throw ElementNotFoundException("Vehicle with id <$itineraryId>")

    fun getItineraryDestination(id: Int) = itineraryRepository.getItineraryDestination(id)

    fun createOne(newItinerary: Itinerary): Int = itineraryRepository.create(newItinerary)

    fun createMany(itineraries: List<Itinerary>) {
        itineraryRepository.createMany(itineraries)
    }

    fun update(itineraryId: Int, itineraryNewData: ItineraryUpdateRequest) {
        val itinerary = this.getById(itineraryId)!!

        if (itineraryNewData.days!!.isNotEmpty())
            itinerary.days = convertItineraryDaysDTO(itineraryNewData.days)
        if (itineraryNewData.destination != null)
            itinerary.destination = itineraryNewData.destination

        itineraryRepository.update(itinerary)
    }

    fun addScore(itineraryId: Int, rateDataDTO: RateDataDTO){
        val itinerary = this.getById(itineraryId)!!
        itinerary.addScore(rateDataDTO.rateScore, userService.getById(rateDataDTO.idUserRate) )
    }

    fun delete(itineraryId: Int): Itinerary{
        return itineraryRepository.deleteById(itineraryId)
    }

    private fun convertItineraryDaysDTO(daysDTO: MutableList<ItinearyDayDTO>): MutableList<ItinearyDay>{
        return daysDTO.map { day -> ItinearyDay().apply { activities = convertActivitiesDTOList(day.activities) } }.toMutableList()
    }

    private fun convertActivitiesDTOList(activitiesListDTO: MutableList<ActivityDTO>): MutableList<Activity>{
        return  activitiesListDTO.map { act ->
            fun toActivity(): Activity {
                val activityRecived = activityService.getById(act.id)!!
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'");
                return Activity(
                    cost = activityRecived.cost,
                    description = activityRecived.description,
                    initialTime = LocalDateTime.parse(activityRecived.initialTime, formatter),
                    endTime = LocalDateTime.parse(activityRecived.endTime, formatter),
                    difficulty = getDifficultyByPriority(activityRecived.difficulty.priority)
                ).also {
                    it.id = activityRecived.id
                }
            }
            toActivity()
        }.toMutableList()
    }
}
