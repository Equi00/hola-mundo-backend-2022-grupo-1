package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.holamundo.service.ItineraryService
import ar.edu.unsam.algo2.grupo1.itinerary.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ar.edu.unsam.algo2.grupo1.holamundo.dto.itineraryDTO.ItineraryUpdateRequest
import ar.edu.unsam.algo2.grupo1.holamundo.dto.itineraryDTO.RateDataDTO
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("api/itinerary")
@CrossOrigin("*")
class ItineraryController {
    @Autowired
    lateinit var itineraryService: ItineraryService

    @GetMapping("/search")
    fun getAll() = itineraryService.getAll()

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int) = itineraryService.getById(id)

    @GetMapping("/search/{toSearch}")
    fun search(@PathVariable toSearch: String) = itineraryService.search(toSearch)

    @GetMapping("/{id}/destination")
    fun getItineraryDestination(@PathVariable id: Int) = itineraryService.getItineraryDestination(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody itineraryUpdateRequest: ItineraryUpdateRequest): ResponseEntity<Boolean> {
        itineraryService.update(id, itineraryUpdateRequest)
        return ResponseEntity<Boolean>(HttpStatus.OK)
    }

    @PutMapping("/{id}/rate")
    fun update(@PathVariable id: Int, @RequestBody rateDataDTO: RateDataDTO): ResponseEntity<Boolean> {
        itineraryService.addScore(id, rateDataDTO)
        return ResponseEntity<Boolean>(HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Itinerary = itineraryService.delete(id)
}
