package ar.edu.unsam.algo2.grupo1.service

import ar.edu.unsam.algo2.grupo1.exceptions.BusinessException
import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.repositories.VehicleRepository
import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.webjars.NotFoundException

@Service
class VehicleService {

    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    fun getById(vehicleId: Int): Vehicle? = vehicleRepository.getById(vehicleId) ?: throw ElementNotFoundException("Vehicle with id <$vehicleId>")

}