package ar.edu.unsam.algo2.grupo1.holamundo.service

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.holamundo.dto.itineraryDTO.GetActivityDTO
import ar.edu.unsam.algo2.grupo1.holamundo.repositories.ActivityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActivityService {

    @Autowired
    lateinit var activityRepository: ActivityRepository

    fun getAll(): List<GetActivityDTO> = activityRepository.getAll().map { it.toGetActivityDTO() }

    fun getById(activityId: Int): GetActivityDTO? = activityRepository.getById(activityId)?.toGetActivityDTO()

    fun create(activity: Activity) = activityRepository.create(activity)

    fun update(activity: Activity) = activityRepository.update(activity)
}
