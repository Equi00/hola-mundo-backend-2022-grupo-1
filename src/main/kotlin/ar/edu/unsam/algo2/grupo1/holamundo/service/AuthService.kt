package ar.edu.unsam.algo2.grupo1.holamundo.service

import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun authenticateUser(username: String, password: String): Boolean {
        val user = userRepository.getByUsername(username)
        return if(user != null) user.password == password
        else false
    }
}