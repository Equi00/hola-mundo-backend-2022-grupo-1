package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.service.VehicleService
import ar.edu.unsam.algo2.grupo1.holamundo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorldController {

    @Autowired
    lateinit var service: VehicleService

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun helloWorld(): String = "Hola pedazo de putos"
}