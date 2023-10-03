package ar.edu.unsam.algo2.grupo1.activity

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDateTime

class ActividadValidaciones : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test de validacion de actividades"){
        val trekking = Activity(500.toFloat(), "Trekking en sendero al lago", LocalDateTime.of(2022,4,5,11,0,0),LocalDateTime.of(2022,4,5,12,0,0), Difficulty.LOW)
        val rafting = Activity(0.toFloat(), "rafting en el rio", LocalDateTime.of(2022,4,5,11,0,0),
            LocalDateTime.of(2022,4,5,12,0,0), Difficulty.MEDIUM)
        val biciturismo = Activity(500.toFloat(), "", LocalDateTime.of(2022,4,5,11,0,0),
            LocalDateTime.of(2022,4,5,12,0,0), Difficulty.HIGH)
        val natacion = Activity((-500).toFloat(), "Clase de natacion en el hotel", LocalDateTime.of(2022,4,5,11,0,0),
            LocalDateTime.of(2022,4,5,12,0,0), Difficulty.MEDIUM)
        val escalada = Activity(500.toFloat(), "Escalada de montaña", LocalDateTime.of(2022,4,5,15,0,0),
            LocalDateTime.of(2022,4,5,8,0,0), Difficulty.HIGH)
        val visitaBosque = Activity(500.toFloat(), "Visita al bosque alto", LocalDateTime.of(2022,4,5,13,0,0),
            LocalDateTime.of(2022,4,5,13,0,0), Difficulty.LOW)

        it("Si la actividad tiene descripcion, dificultad, un costo mayor cero y el horario de inicio es menor al de fin entonces es valido"){
            trekking.isValid().shouldBeTrue()
        }
        it("Si la actividad tiene todos los requisitos pero el costo es igual a cero entonces es valida"){
            rafting.isValid().shouldBeTrue()
        }
        it("Si la actividad no tiene descripcion entonces es invalida"){
            biciturismo.isValid().shouldBeFalse()
        }
        it("Si la actividad tiene un costo menor a cero entonces es invalida"){
            natacion.isValid().shouldBeFalse()
        }
        it("Si la actividad tiene un horario de inicio mayor al de fin entonces es invalida"){
            escalada.isValid().shouldBeFalse()
        }
        it("Si la actividad tiene un horario de inicio igual al de fin entonces es invalida"){
            visitaBosque.isValid().shouldBeFalse()
        }
    }

})