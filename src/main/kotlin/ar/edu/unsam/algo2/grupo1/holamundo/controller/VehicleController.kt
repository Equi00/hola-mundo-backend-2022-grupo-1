package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.holamundo.dto.CreateVehicleDTO
import ar.edu.unsam.algo2.grupo1.holamundo.dto.UpdateVehicleDTO
import ar.edu.unsam.algo2.grupo1.holamundo.dto.toEntity
import ar.edu.unsam.algo2.grupo1.repositories.VehicleRepository
import ar.edu.unsam.algo2.grupo1.service.VehicleService
import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle
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
@RequestMapping("api/vehicle")
@CrossOrigin("*")
class VehicleController {

    @Autowired
    lateinit var vehicleService: VehicleService

    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    @GetMapping("/search")
    @Operation(summary = "Devuelve todos los vehiculos")
    fun getAll() = vehicleRepository.getAll()

    @GetMapping("/{id}")
    @Operation(summary = "Devuelve una sola tarea por ID")
    fun getOne(@PathVariable id: Int) = vehicleService.getById(id)

    @PostMapping()
    fun create(@RequestBody vehicle: CreateVehicleDTO) = vehicleRepository.create(vehicle.toEntity())

    @PutMapping()
    fun update(@RequestBody vehicle: UpdateVehicleDTO) = vehicleRepository.update(vehicle.toEntity())

    @GetMapping("/search/{toSearch}")
    @Operation(summary = "Devuelve todos los vehiculos que coincidan con la busqueda")
    fun search(@PathVariable toSearch: String) = vehicleRepository.search(toSearch)

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar un vehiculo por Id y devuelve el objeto borrado")
    fun delete(@PathVariable id: Int): Vehicle = vehicleRepository.deleteById(id)
}