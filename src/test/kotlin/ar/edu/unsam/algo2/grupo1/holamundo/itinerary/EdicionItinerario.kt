package ar.edu.unsam.algo2.grupo1.itinerary

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.users.User
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate

class EdicionItinerario: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test Edicion de itinerarios"){
        // ARRANGE:

        // Destinos:
        val argentina: Destination = Destination(
            "Argentina",
            "CABA",
            30000.0F
        )

        // Usuarios:
        val pedro: User = User(
            "Pedro",
            "Martinez",
            "PeMartin",
            LocalDate.now().minusYears(12),
            "Uruguay"
        )

        val juan: User = User(
            "Juan",
            "Fernandez",
            "Ferna123",
            LocalDate.now().minusYears(12),
            "Argentina"
        )

        // Itinerarios:
        val itinerario = Itinerary(juan, argentina).apply {
            this.addEmptyDays(4)
        }

        it("Usuario puede editar itinerario porque es el creador.") {
            // ASSERT:
            juan.canEditItinerary(itinerario).shouldBeTrue()
        }

        it("Usuario puede editar el itinerario porque es amigo del creador y conoce el destino.") {
            // ACT:
            juan.addFriend(pedro)
            pedro.addVisitDestination(argentina)

            // ASSERT:
            pedro.canEditItinerary(itinerario).shouldBeTrue()
        }

        it("Usuario no puede editar el itinerario porque es amigo del creador pero no conoce el destino.") {
            // ACT:
            juan.addFriend(pedro)

            // ASSERT:
            pedro.canEditItinerary(itinerario).shouldBeFalse()
        }

        it("Usuario no puede editar el itinerario porque no es amigo del creador.") {
            // ASSERT:
            pedro.canEditItinerary(itinerario).shouldBeFalse()
        }
    }
})