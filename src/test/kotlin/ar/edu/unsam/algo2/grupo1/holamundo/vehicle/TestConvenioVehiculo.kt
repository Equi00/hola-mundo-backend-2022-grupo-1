package ar.edu.unsam.algo2.grupo1.vehicle

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.floats.shouldBeExactly
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class TestConvenioVehiculo : DescribeSpec({
    describe("Testeo de convenio sobre los vehiculos.") {
        // ARRANGE:

        val crV: Truck = Truck("Honda",
            "cr-v", 2014, 510.0F, false, false
        )

        val ranger: Truck = Truck("Ford",
            "Ranger", 2020, 300.0F, true, true
        )

        it("El vehiculo cuyo valor por dia es de 510 " +
                "que tiene convenio con Honda debe tener un costo total de 23085.") {

            // ASSERT:
            crV.totalCost(15).shouldBeExactly(23085.0F)
        }

        it("El vehiculo cuyo valor por dia es de 300 " +
                "que no tiene convenio con Honda debe tener un costo total de 22500.") {

            // ASSERT:
            ranger.totalCost(10).shouldBeExactly(22500.0F)
        }
    }
})