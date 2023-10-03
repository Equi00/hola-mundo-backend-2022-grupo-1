//package ar.edu.unsam.algo2.grupo1.destinations

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.users.User
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class CostosAntiguedadNoLocal : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test de destinos no locales segun antiguedad de 15 años o menos") {
        val userJapones = User(
            "Agustín",
            "Fernandez",
            "Fernan777",
            LocalDate.now().minusYears(7),
            "Japon")
        val userCanadiense = User(
            "Marcos",
            "Sanchez",
            "MSanchez",
            LocalDate.now().minusYears(16),
            "Canada")

        val viajeJapon = Destination(
            "Japon",
            "Tokyo",
            50000.toFloat()
        )                             // Destino no Local - Pais de Residencia.
        val viajeCanada = Destination(
            "Canada",
            "Sherbrooke",
            50000.toFloat()
        )                      // Destino no Local - Pais de Residencia.

        it("Si el usuario tiene 7 años de antiguedad y el viaje es no local, entonces se le resta el %7") {
            viajeJapon.totalCostFor(userJapones) shouldBe 56500.toFloat()
        }
        it("Si el usuario tiene mas de 15 años de antiguedad " +
                "y el viaje es no local, entonces se le resta hasta el %15") {
            viajeCanada.totalCostFor(userCanadiense) shouldBe 52500.toFloat()
        }
    }
})
