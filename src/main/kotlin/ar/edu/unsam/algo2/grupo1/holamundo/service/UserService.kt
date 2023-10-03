package ar.edu.unsam.algo2.grupo1.holamundo.service

import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ar.edu.unsam.algo2.grupo1.service.DestinationService
import ar.edu.unsam.algo2.grupo1.users.HomeInfo
import ar.edu.unsam.algo2.grupo1.users.User

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var destinationService: DestinationService

    fun getAll() = userRepository.getAll()

    fun getById(userId: Int) = userRepository.getById(userId) ?: throw ElementNotFoundException("User with id <$userId>")

    fun update(id: Int, user: User) =
        if (user.id == id) userRepository.update(user)
        else throw ElementNotFoundException("No se encontro el usuario especificado")

    fun getByUsername(username: String) = userRepository.getByUsername(username)

    fun createDestinationVisited(id: Int, destinationId: Int): User {
        val destintation = destinationService.getById(destinationId) ?: throw ElementNotFoundException("Destination with id <$destinationId>")
        return userRepository.createDestinationVisited(id, destintation)
    }
}