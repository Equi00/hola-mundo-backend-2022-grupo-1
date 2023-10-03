package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.service.DestinationService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/destination")
@CrossOrigin("*")
class DestinationController {

    @Autowired
    lateinit var destinationService: DestinationService

    @GetMapping("/search")
    @Operation(summary = "Returns all destinations")
    fun getAll() = destinationService.getAll()

    @GetMapping("/{id}")
    @Operation(summary = "Returns one destination by Id")
    fun getOne(@PathVariable id: Int) = destinationService.getById(id)

    @GetMapping("/search/{toSearch}")
    @Operation(summary = "Returns filtered destinations")
    fun search(@PathVariable toSearch: String) = destinationService.search(toSearch)

    @PostMapping
    @Operation(summary = "Creates one destination")
    fun create(@RequestBody newDestination: Destination) = destinationService.createOne(newDestination)

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes one destination by Id")
    fun delete(@PathVariable id: Int): Destination = destinationService.delete(id)
}