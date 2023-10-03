package ar.edu.unsam.algo2.grupo1.vehicle

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class AntiguedadVehiculo: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Tests de antiguedad de vehiculo"){
        val peugeot = Car(
            "Peugeot",
            "206",
            LocalDate.now().minusYears(5).year,
            5000.0f,
            false,
            false)

        it("La antiguedad del auto de marca Peugeot debe ser de 5 años"){
            peugeot.antiquity().shouldBeExactly(5)
        }
    }
})