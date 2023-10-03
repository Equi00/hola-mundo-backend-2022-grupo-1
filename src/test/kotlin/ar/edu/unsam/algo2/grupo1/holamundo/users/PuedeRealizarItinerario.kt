package ar.edu.unsam.algo2.grupo1.users//package ar.edu.unsam.algo2.grupo1.destinations

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.itinerary.ItinearyDay
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalDateTime

class PuedeRealizarItinerario : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test de si el usuario puede o no hacer un itenerario") {
        val userRelaxed = User(
            "Agustín",
            "Fernandez",
            "Fernan777",
            LocalDate.now().minusYears(7),
            "Japon").apply {
            this.travelDays = 6
            this.changePersonality(PersonalityRelaxed())
        }
        val userCautious = User(
            "Agustín",
            "Fernandez",
            "Fernan777",
            LocalDate.now().minusYears(7),
            "Japon").apply {
            this.travelDays = 6
            this.changePersonality(PersonalityCautios())
        }
        val userLocalist = User(
            "Agustín",
            "Fernandez",
            "Fernan777",
            LocalDate.now().minusYears(7),
            "Argentina").apply {
            this.travelDays = 6
            this.changePersonality(PersonalityLocalist())
        }
        val userDreamer = User(
            "Agustín",
            "Fernandez",
            "Fernan777",
            LocalDate.now().minusYears(7),
            "Argentina").apply {
            this.travelDays = 6
            this.changePersonality(PersonalityDreamer())
        }
        val userActive = User(
            "Agustín",
            "Fernandez",
            "Fernan777",
            LocalDate.now().minusYears(7),
            "Japon").apply {
            this.travelDays = 6
            this.changePersonality(PersonalityActive())
        }
        val userExigent = User(
            "Agustín",
            "Fernandez",
            "Fernan777",
            LocalDate.now().minusYears(7),
            "Japon").apply {
            this.travelDays = 6
            this.changePersonality(PersonalityExigent(Difficulty.HIGH, 50))
        }

        val actividad1 = Activity(
            200.0f,
            "Casino",
            LocalDateTime.of(2022, 4, 5, 11, 0, 0),
            LocalDateTime.of(2022, 4, 5, 12, 0, 0),
            Difficulty.LOW
        )
        val actividad2 = Activity(
            400.0f,
            "Tirolesa",
            LocalDateTime.of(2022, 4, 6, 11, 0, 0),
            LocalDateTime.of(2022, 4, 6, 12, 30, 0),
            Difficulty.MEDIUM
        )
        val actividad3 = Activity(
            500.0f,
            "Paracaidismo",
            LocalDateTime.of(2022, 4, 7, 11, 0, 0),
            LocalDateTime.of(2022, 4, 7, 13, 0, 0),
            Difficulty.HIGH
        )
        val actividadExtra = Activity(
            700.0f,
            "Caida libre",
            LocalDateTime.of(2022, 4, 7, 11, 0, 0),
            LocalDateTime.of(2022, 4, 7, 13, 0, 0),
            Difficulty.HIGH
        )

        val argentina = Destination("Argentina", "Buenos Aires", 3000.0f)
        val italia = Destination("Italia", "Roma", 5000.0f)
        val canada = Destination("Canada", "Sweeterland", 7000.0f)
        val suiza = Destination("Suiza", "Romisl", 4000.0f)


        val itinerary1 = Itinerary(userRelaxed, argentina).apply {
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad1)
            })
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad2)
                this.addActivity(actividad3)
            })
        }
        val itinerary2 = Itinerary(userRelaxed, italia).apply {
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad1)
            })
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad2)
                this.addActivity(actividad3)
            })
        }
        val itinerary3 = Itinerary(userRelaxed, canada).apply {
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad1)
            })
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad2)
            })
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad3)
            })
            this.addEmptyDays(1)
        }
        val itinerary4 = Itinerary(userRelaxed, suiza).apply {
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad1)
            })
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad2)
            })
            this.addDay(ItinearyDay().apply {
                this.addActivity(actividad3)
            })
        }
        val itineraryExtra = Itinerary(userRelaxed, suiza).apply {
            this.addDay(ItinearyDay().apply {
                this.addActivities(mutableListOf<Activity>(actividad1, actividad2, actividad3, actividadExtra))
            })
        }

        it("El usuario relajado acepta todos los itinerarios") {
            userRelaxed.canDoItinerary(itinerary1) shouldBe true
        }

        it("El usuario precavido solo acepta destinos que conozca el o un amigo") {
            userCautious.addVisitDestination(argentina)
            userCautious.canDoItinerary(itinerary1).shouldBeTrue()

            userCautious.addFriend(userRelaxed)
            userRelaxed.addVisitDestination(italia)
            userCautious.canDoItinerary(itinerary2).shouldBeTrue()

            userCautious.canDoItinerary(itinerary3).shouldBeFalse()
        }

        it("El usuario localista solo acepta destionos locales") {
            userLocalist.canDoItinerary(itinerary1).shouldBeTrue()
            userLocalist.canDoItinerary(itinerary2).shouldBeFalse()
        }

        it(
            "El usuario soñador solo acepta destinos deseados " +
                    "o destinos de mayor valor que el del destino deseado más caro"
        ) {
            userDreamer.addDesiredDestination(italia)
            userDreamer.addDesiredDestination(suiza)
            userDreamer.canDoItinerary(itinerary2).shouldBeTrue()

            userDreamer.canDoItinerary(itinerary3).shouldBeTrue()
            userDreamer.canDoItinerary(itinerary1).shouldBeFalse()
        }

        it("El usuario activo solo acepta aquellos que posean una actividad cada dia") {
            userActive.canDoItinerary(itinerary1).shouldBeTrue()
            userActive.canDoItinerary(itinerary3).shouldBeFalse()
        }

        it("El usuario exigente solo acepta que la mitad de los itinerarios sean de dificultad alta") {
            userExigent.canDoItinerary(itineraryExtra).shouldBeTrue()
            userExigent.canDoItinerary(itinerary4).shouldBeFalse()
        }
    }
})
