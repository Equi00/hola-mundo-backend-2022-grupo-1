package ar.edu.unsam.algo2.grupo1.service

import ar.edu.unsam.algo2.grupo1.exceptions.BusinessException
import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.repositories.DestinationRepository
import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class DestinationService {

    @Autowired
    lateinit var destinationRepository: DestinationRepository

    fun getAll() : List<Destination> = destinationRepository.getAll()

    fun getById(destinationId: Int): Destination? = destinationRepository.getById(destinationId) ?: throw ElementNotFoundException("Destination with id <$destinationId> not found")

    fun search(toSearch: String): List<Destination> = destinationRepository.search(toSearch)

    fun createOne(newDestination: Destination): Int = destinationRepository.create(newDestination)

    fun delete(destinationId: Int): Destination {
        return  destinationRepository.deleteById(destinationId)
    }
}
