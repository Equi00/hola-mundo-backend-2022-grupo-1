package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.repositories.VehicleRepository
import ar.edu.unsam.algo2.grupo1.vehicle.Car
import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de vehiculos")
class VehicleControllerTest(@Autowired val mockMvc: MockMvc) {
    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    lateinit var vehicle: Vehicle

    @BeforeEach
    fun init() {
        vehicleRepository.clear()
        vehicle = buildVehicle()
        vehicleRepository.createMany(listOf(
            vehicle,
            buildVehicle().also {
                it.brand = "Peugeot"
                it.model = "206"
            },
            buildVehicle().also {
                it.brand = "Peugeot"
                it.model = "207"
            },
        ))

    }

    @Test
    fun `Se pueden obtener todos los vehiculos`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/vehicle/search"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.length()").value(3))
    }

    @Test
    fun `Se pueden obtener los vehiculos que contengan exactamente una marca`() {
        val toSearch: String = "peugeot"
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/vehicle/search/${toSearch}"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$.[0].brand").value("Peugeot"))
    }

    @Test
    fun `Se pueden obtener los vehiculos que comiencen con el criterio de busqueda`() {
        val toSearch: String = "20"
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/vehicle/search/${toSearch}"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$.[0].model").value("206"))
    }

    @Test
    fun `Se puede obtener un vehiculo por su id`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/vehicle/" + vehicle.id))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(vehicle.id))
            .andExpect(jsonPath("$.brand").value(vehicle.brand))
    }

    @Test
    fun `Si se pide un vehiculo con un id que no existe se produce un error`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/vehicle/20000"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `Se puede eliminar una vehiculo existente en forma exitosa`() {
        val newVehicle = buildVehicle().also {
            it.brand = "Volvo"
            it.model = "T107"
        }
        val id = vehicleRepository.create(newVehicle)

        println(id)
        println(vehicleRepository.getAll().map{it.brand})
        println(vehicleRepository.getAll().map{it.id})

        mockMvc.delete("/api/vehicle/${id}")
            .andExpect { status { isOk() } }

        println(vehicleRepository.getAll().map{it.brand})

        assertEquals(0, vehicleRepository.search(newVehicle.brand).size)
    }

    @Test
    fun `Si pasamos vehiculo que no existen no se pueden borrar`() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/vehicle/20000"))
            .andExpect { status().isNotFound }
    }

    fun buildVehicle(): Vehicle {
        return Car("Honda", "CRV",2000,500.toFloat(),true, true).apply {
            type = "Car"
            agreements = mutableListOf("Honda")
        }
    }
}