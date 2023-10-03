package ar.edu.unsam.algo2.grupo1.vehicle

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class CostoBaseCostoTotal : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Costo base y costo total de vehiculos") {

        val car = Car(
            "lamborghini",
            "avenger",
            LocalDate.now().minusYears(5).year,
            3500.toFloat(),
            true,
            true)
        val car2 = Car(
            "lamborghini",
            "avenger",
            LocalDate.now().minusYears(5).year,
            3500.toFloat(),
            true,
            false)

        val truck = Truck(
            "Mercedes-Benz",
            "Accelo",
            LocalDate.now().minusYears(15).year,
            750.toFloat(),
            false,
            true)
        val truck2 = Truck(
            "Mercedes-Benz",
            "Accelo",
            LocalDate.now().minusYears(15).year,
            750.toFloat(),
            false,
            true)
        val truck3 = Truck(
            "Mercedes-Benz",
            "Accelo",
            LocalDate.now().minusYears(15).year, 750.toFloat(),
            false,
            false)

        val motocicle = Motocicle(
            "Yamaha",
            "YZF R6",
            LocalDate.now().minusYears(7).year,
            1500.toFloat(),
            true,
            175)
        val motocicle2 = Motocicle(
            "Yamaha",
            "YZF R6",
            LocalDate.now().minusYears(7).year,
            1500.toFloat(),
            true,
            290)

        it("Costo base de cualquier vehiculo") { car.baseCost(7) shouldBe 24500.toFloat() }

        it("Costo total del auto a 3500 por dia con hatchback siendo este de 26950") {
            car.totalCost(7) shouldBe 26950.toFloat()
        }
        it("Costo total del auto a 3500 por dia sin hatchback siendo este de 30625") {
            car2.totalCost(7) shouldBe 30625.toFloat()
        }

        it("Costo total del camion a 750 por dia sin recargo por exeso de dias deberia ser de 20250") {
            truck.totalCost(7) shouldBe 20250.toFloat()
        }
        it("Costo total del camion a 750 por dia con recargo por exeso de dias deberia ser de 38250") {
            truck2.totalCost(15) shouldBe 38250.toFloat()
        }
        it("Costo del camion a 750 por dia sin recargo por exeso de dias y es 4x4 deberia ser de 15250") {
            truck3.totalCost(7) shouldBe 15250.toFloat()
        }

        it("Costo total de la motocicleta a 1500 por dia y con cylinderCapacity bajo debe de ser 13500") {
            motocicle.totalCost(9) shouldBe 13500.toFloat()
        }
        it("Costo total de la motocicleta a 1500 por dia y con cylinderCapacity alto debe de ser 18000") {
            motocicle2.totalCost(9) shouldBe 18000.toFloat()
        }
    }
})
