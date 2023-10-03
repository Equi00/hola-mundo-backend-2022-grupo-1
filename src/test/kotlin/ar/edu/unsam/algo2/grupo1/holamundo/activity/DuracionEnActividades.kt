package ar.edu.unsam.algo2.grupo1.activity

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class DuracionEnActividades : DescribeSpec( {
    isolationMode = IsolationMode.InstancePerTest

    // Arrange
    val activity1 = Activity(3000.0F, "Futbol", LocalDateTime.of(2022,4,5,11,30,0),LocalDateTime.of(2022,4,5,13,45,0), Difficulty.HIGH)

    describe("Duracion de actividades") {
        it("Duracion calculada en minutos segun inicio y fin de actividad") {
            // ACT

            // ASSERT
            activity1.durationInMinutes() shouldBe 135
        }
    }
})
