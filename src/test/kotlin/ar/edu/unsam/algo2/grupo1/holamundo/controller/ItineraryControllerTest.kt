package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.repositories.ItineraryRepository
import ar.edu.unsam.algo2.grupo1.users.User
import ar.edu.unsam.algo2.grupo1.vehicle.Car
//import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test de itinerary controller")
class ItineraryControllerTest(@Autowired val mockMvc: MockMvc) {
    @Autowired
    lateinit var itineraryRepository: ItineraryRepository
    lateinit var itinerary: Itinerary

    @BeforeEach
    fun init() {
        itinerary = itineraryBuilder()
    }

    @Test
    fun `Obtener todos los itinerarios de forma exitosa`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/itinerary/search"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.length()").value(2))
    }

    @Test
    fun `Filtrar itinerario que contenga el id = 0`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/itinerary/" + itinerary.id))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(itinerary.id))
    }

    @Test
    fun `No se encontro itinerario con id = 777`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/itinerary/777"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `Filtrar itinerarios por pais`() {
        val toSearch: String = "Argentina"
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/itinerary/search/${toSearch}"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
    }

    @Test
    fun `Filtrar itinerarios por ciudad`() {
        val toSearch: String = "Tokyo"
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/itinerary/search/${toSearch}"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
    }

//    @Test
//    fun `Eliminar un itinerario`() {
//        println(itineraryRepository.getAll().map{it.id})
//
//        mockMvc.delete("/api/itinerary/1").andExpect { status { isOk() } }
//
//        assertEquals(1, itineraryRepository.getAll().size)
//    }

    @Test
    fun `No se puede eliminar un itinerario`() {
        mockMvc
            .perform(MockMvcRequestBuilders.delete("/api/itinerary/777"))
            .andExpect(status().isNotFound)
    }

    fun itineraryBuilder(): Itinerary {
        return Itinerary(
            User("Mateo", "Pastorini", "MatePasto", LocalDate.now().minusYears(7), "Argentina")
                .apply { this.email = "mateopastorini@gmail.com" },
            Destination("Argentina", "Buenos Aires", 15000.toFloat())
        )
            .apply { this.vehicle = Car("Audi", "R8", 2015, 750.toFloat(), false, false ) }
    }
}
