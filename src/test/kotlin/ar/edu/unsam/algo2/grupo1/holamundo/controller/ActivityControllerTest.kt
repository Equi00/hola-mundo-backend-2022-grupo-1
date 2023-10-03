package ar.edu.unsam.algo2.grupo1.holamundo.controller

import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.holamundo.repositories.ActivityRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class ActivityControllerTest(@Autowired val mockMvc: MockMvc) {

    @Autowired
    lateinit var activityRepository: ActivityRepository

    lateinit var activity1: Activity
    lateinit var activity2: Activity

    @BeforeEach
    fun init(){
        activityRepository.clear()
        activity1 = Activity(
            2000.toFloat(),
            "Senderismo / Trekking en Cerro Campanario",
            LocalDateTime.of(2022, 4, 5, 9, 0, 0),
            LocalDateTime.of(2022, 4, 5, 11, 0, 0),
            Difficulty.MEDIUM
        )
        activity2 = Activity(
            16000.toFloat(),
            "Ruta de los 7 Lagos, Villa La Angostura - San Martin de los Andes",
            LocalDateTime.of(2022, 4, 5, 8, 0, 0),
            LocalDateTime.of(2022, 4, 5, 16, 0, 0),
            Difficulty.LOW
        )
        activityRepository.createMany(listOf(activity1, activity2))
    }

    @Test
    fun `Se obtienen todas las actividades`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/activity/search"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.length()").value(2))
    }

    @Test
    fun `Se obtiene la actividad segun su descripcion`() {
        val toSearch = "RUT"
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/activity/search/${toSearch}"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$.[0].description").value(
                "Ruta de los 7 Lagos, Villa La Angostura - San Martin de los Andes"))
    }

    @Test
    fun `Al pedir una actividad con un id que no existe, se produce un error`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/activity/4345"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `Se elimina una actividad existente exitosamente`() {
        val newActivity = Activity(
            15000.toFloat(),
            "Circuito chico desde la rotonda Km 18 de Av. Exequiel Bustillo",
            LocalDateTime.of(2022, 4, 5, 9, 0, 0),
            LocalDateTime.of(2022, 4, 5, 10, 0, 0),
            Difficulty.LOW
        )

        val id = activityRepository.create(newActivity)

        mockMvc.delete("/api/activity/${id}")
            .andExpect { status { isOk() } }

        assertEquals(0, activityRepository.search(newActivity.description).size)
    }

    @Test
    fun `Al intentar eliminar una actividad que no existe sale un error`() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/activity/3454"))
            .andExpect { status().isNotFound }
    }

    @Test
    fun `Se obtiene una actividad por su id`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/activity/" + activity1.id))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(activity1.id))
            .andExpect(jsonPath("$.description").value(activity1.description))
    }

}