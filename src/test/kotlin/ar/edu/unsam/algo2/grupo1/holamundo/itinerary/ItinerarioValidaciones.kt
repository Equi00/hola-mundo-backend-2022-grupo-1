package ar.edu.unsam.algo2.grupo1.itinerary

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.users.User
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate
import java.time.LocalDateTime

class ItinerarioValidaciones : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test de validacion de itinerario"){
        val argentina = Destination("Argentina", "Buenos Aires", 30000.toFloat())
        val trekking = Activity(500.toFloat(), "Trekking en sendero al lago", LocalDateTime.of(2022,4,5,11,0,0),LocalDateTime.of(2022,4,5,12,0,0), Difficulty.LOW)
        val caminata = Activity(500.toFloat(), "Caminata por el bosque", LocalDateTime.of(2022,4,5,14,0,0),
            LocalDateTime.of(2022,4,5,15,0,0), Difficulty.LOW)
        val carrera = Activity(500.toFloat(), "Carrera en autopista", LocalDateTime.of(2022,4,5,14,20,0),
            LocalDateTime.of(2022,4,5,14,30,0), Difficulty.LOW)
        val moncho = User("Moncho", "Criollo","Chicho", LocalDate.now().minusYears(5), "Afganistan").apply {
            this.addDesiredDestination(argentina)
        }
        val esteban = User("Esteban", "Kotillo","Est321", LocalDate.now().minusYears(5), "Argentina").apply {
            this.addDesiredDestination(argentina)
            this.travelDays = 5
        }
        val micaela = User("Micaela", "Zosa","Miki", LocalDate.now().minusYears(3), "China").apply {
            this.addDesiredDestination(argentina)
        }
        val itinerario = Itinerary(esteban, argentina).apply{
            this.addDay(ItinearyDay().apply {
                this.addActivities(mutableListOf<Activity>(trekking, caminata))
            })
        }
        val itinerario2 = Itinerary(esteban, argentina)
        val itinerario3 = Itinerary(esteban, argentina).apply{
            this.addDay(ItinearyDay().apply {
                this.addActivities(mutableListOf<Activity>(trekking, caminata, carrera))
            })
        }

        it("Si el itinerario tiene creador, destino, 1 o mas dias con actividades, las actividades del dia no se solapan, puntuaciones del 1 al 10 de distintos usuarios entonces es valida"){
            moncho.addVisitDestination(argentina)
            micaela.addVisitDestination(argentina)
            moncho.rateItinerary(itinerario, 9)
            micaela.rateItinerary(itinerario, 5)
            itinerario.isValid().shouldBeTrue()
        }
        it("Si el itinerario no tiene dias con actividades entonces no es valido"){
            itinerario2.isValid().shouldBeFalse()
        }
        it("Si el itinerario tiene actividades del dia que se solapan entonces no es valido"){
            itinerario3.isValid().shouldBeFalse()
        }
        it("Si el itinerario tiene mas de una puntuacion de un mismo usuario entonces no es valido"){
            moncho.addVisitDestination(argentina)
            moncho.rateItinerary(itinerario, 9)
            moncho.rateItinerary(itinerario, 5)
            itinerario.isValid().shouldBeFalse()
        }
        it("Si el itinerario tiene alguna puntuacion menor a 1 entonces no es valido"){
            moncho.addVisitDestination(argentina)
            moncho.rateItinerary(itinerario, 0)
            itinerario.isValid().shouldBeFalse()
        }
        it("Si el itinerario tiene alguna puntuacion mayor a 10 entonces no es valido"){
            moncho.addVisitDestination(argentina)
            moncho.rateItinerary(itinerario, 20)
            itinerario.isValid().shouldBeFalse()
        }
    }

})