package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.users.User
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate

class UsuariosYDestinos: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Usuario y destinos"){
        val italia = Destination("Italia","Roma",5000.toFloat())
        val espania = Destination("España","Barcelona",10000.toFloat())
        val roberto = User(
            "Roberto",
            "Sanchez",
            "robisanchez",
            LocalDate.now().minusYears(3),
            "Argentina")

        it("El usuario conoce el destino"){
            roberto.addVisitDestination(italia)
            roberto.knowDestination(italia).shouldBeTrue()
        }
        it("El usuario no conoce el destino"){
            roberto.knowDestination(espania).shouldBeFalse()
        }
        it("El usuario tiene el destino 'españa' en su lista de deseados"){
            roberto.addDesiredDestination(espania)
            roberto.isDesiredDestination(espania).shouldBeTrue()
        }
        it("El usuario no tiene el destino 'italia' en su lista de deseados"){
            roberto.isDesiredDestination(italia).shouldBeFalse()
        }
    }
})