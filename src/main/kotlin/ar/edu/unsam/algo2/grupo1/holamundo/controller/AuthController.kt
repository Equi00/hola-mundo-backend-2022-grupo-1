package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.holamundo.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
class AuthController {
    @Autowired
    lateinit var authService: AuthService

    @GetMapping("/{username}/{password}")
    fun authenticateUser(@PathVariable username: String, @PathVariable password: String): Boolean {
        return authService.authenticateUser(username,password)
    }

    @GetMapping("/{username}")
    fun authenticateUserWithoutPassword(@PathVariable username: String): Boolean {
        return this.authenticateUser(username,"")
    }
}