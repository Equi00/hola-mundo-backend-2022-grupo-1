package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class UsuarioRealizaViaje: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Un usuario realiza un viaje"){
        val esteban = User("Estaban","Quito","sentateEnEsteBanquito", LocalDate.now(), "Argentina")
        val ezequiel = User("Ezquiel","Baez","ezebaez", LocalDate.now(), "Italia")

        val noruega = Destination("Noruega","Oslo",15000.0f)

        val itinerario = Itinerary(ezequiel, noruega)

        it("Se agrega a su lista destinos visitados el destino al que viajo"){
            esteban.doTrip(itinerario)

            esteban.visitedDestinations shouldBe listOf<Destination>(noruega)
        }
    }
}) {
}