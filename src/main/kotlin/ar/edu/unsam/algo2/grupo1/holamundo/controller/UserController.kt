package ar.edu.unsam.algo2.grupo1.holamundo.controller
import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.holamundo.dto.UserUpdateRequest
import ar.edu.unsam.algo2.grupo1.holamundo.dto.UserUpdateResponse
import ar.edu.unsam.algo2.grupo1.holamundo.dto.toEntity
import ar.edu.unsam.algo2.grupo1.holamundo.service.UserService
import ar.edu.unsam.algo2.grupo1.repositories.UserRepository
import ar.edu.unsam.algo2.grupo1.users.User
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping
    @Operation(summary = "Devuelve todos los usuarios")
    fun getAll(): List<User> = userService.getAll()

    @GetMapping("/{id}")
    @Operation(summary = "Devuelve un usuario por ID")
    fun getOne(@PathVariable id: Int) = userService.getById(id)

    @GetMapping("/username/{username}")
    @Operation(summary = "Devuelve un usuario por ID")
    fun getOneByUsername(@PathVariable username: String) = userService.getByUsername(username)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody userRequestUpdate: UserUpdateRequest): ResponseEntity<UserUpdateResponse> {
        userRepository.getById(id) ?: ElementNotFoundException("User with id ${id}, ${userRequestUpdate.friends}")
        val user = userService.update(id, userRequestUpdate.toEntity(id, userRepository))
        return ResponseEntity.ok(user.toResponse())
    }

    @PostMapping("/{id}/add-destination/{destinationId}")
    fun createDestinationVisited(@PathVariable id: Int, @PathVariable destinationId: Int) = userService.createDestinationVisited(id, destinationId)
}