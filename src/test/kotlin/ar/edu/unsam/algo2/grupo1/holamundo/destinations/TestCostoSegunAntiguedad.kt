package ar.edu.unsam.algo2.grupo1.destinations

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.users.User
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class testCostoSegunAntiguedad: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    // ARRANGE:

    // Destinos:
    val italia = Destination("Italia", "Roma", 30000F)
    val argentina = Destination("Argentina", "Buenos Aires", 25000F)

    // Usuarios:
    val userA = User("Adrian", "Ibarra", "Adri97", LocalDate.now().minusYears(16), "Italia")
    val userB = User("Jorge", "Ibarra", "Jor97", LocalDate.now().minusYears(12), "Italia")

    it("Si el usuario no es del mismo pais que el destino.") {
        // ASSERT:
        argentina.totalCostFor(userA) shouldBe 25000
    }

    it("Si el usuario es del mismo pais que el destino y no supera los 15 años de antiguedad") {
        // ASSERT:
        italia.totalCostFor(userB) shouldBe 32400
    }

    it("Si el usuario es del mismo pais que el destino y supera los 15 años de antiguedad") {
        // ASSERT:
        italia.totalCostFor(userA) shouldBe 31500
    }
})