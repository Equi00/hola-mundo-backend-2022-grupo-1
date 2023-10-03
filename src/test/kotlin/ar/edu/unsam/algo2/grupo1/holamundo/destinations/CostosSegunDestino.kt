package ar.edu.unsam.algo2.grupo1.destinations//package ar.edu.unsam.algo2.grupo1.destinations

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.users.User
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class CostosSegunDestino : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Dado una persona alemana y dos destinos que ninguno es su propio pais, uno local y otro no"){
        val usuarioAleman = User("Roberto","Alvarez","robealvarez005", LocalDate.now().minusYears(3),"Alemania")
        val destinoArgentina = Destination("Argentina","Buenos Aires",30000.toFloat())  // Destino Local
        val destinoItalia = Destination("Italia","Roma",30000.toFloat())                // Destino no Local

        it("Si su destino es argentina entonces el costo sera el base, es decir $30000"){
            destinoArgentina.totalCostFor(usuarioAleman) shouldBe 30000.toFloat()
        }
        it("Si su destino es Italia entonces el costo tendra un aumento del 20%, es decir $36000"){
            destinoItalia.totalCostFor(usuarioAleman) shouldBe 36000.toFloat()
        }
    }

})