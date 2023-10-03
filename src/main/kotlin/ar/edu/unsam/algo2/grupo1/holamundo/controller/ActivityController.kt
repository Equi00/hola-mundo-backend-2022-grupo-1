package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.holamundo.dto.CreateActivityDTO
import ar.edu.unsam.algo2.grupo1.holamundo.dto.UpdateActivityDTO
import ar.edu.unsam.algo2.grupo1.holamundo.dto.itineraryDTO.GetActivityDTO
import ar.edu.unsam.algo2.grupo1.holamundo.dto.toEntity
import ar.edu.unsam.algo2.grupo1.holamundo.repositories.ActivityRepository
import ar.edu.unsam.algo2.grupo1.holamundo.service.ActivityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/activity")
@CrossOrigin("*")
class ActivityController{

    @Autowired
    lateinit var activityService: ActivityService
    @Autowired
    lateinit var activityRepository: ActivityRepository

    @GetMapping("/search")
    fun getAll() = activityService.getAll()

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int): GetActivityDTO = activityService.getById(id) ?: throw ElementNotFoundException("Actividad con id <$id>")

    //Make a create activity
    @PostMapping()
    fun create(@RequestBody activity: CreateActivityDTO) = activityService.create(activity.toEntity())

    //Make a update activity
    @PutMapping()
    fun update(@RequestBody activity: UpdateActivityDTO) = activityService.update(activity.toEntity())

    @GetMapping("/search/{toSearch}")
    fun search(@PathVariable toSearch: String) = activityRepository.search(toSearch)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Activity = activityRepository.deleteById(id)
}