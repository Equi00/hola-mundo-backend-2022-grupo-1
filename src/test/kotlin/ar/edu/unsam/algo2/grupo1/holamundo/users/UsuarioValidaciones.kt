package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate

class UsuarioValidaciones : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test de validacion de usuario"){
        val argentina = Destination("Argentina", "Buenos Aires", 30000.toFloat())
        val esteban = User(
            "Esteban",
            "Kotillo",
            "Est321",
            LocalDate.now().minusYears(5),
            "Argentina").apply {
            this.addDesiredDestination(argentina)
            this.travelDays = 5
        }
        val jorge = User(
            "Jorge",
            "Mianta",
            "",
            LocalDate.now().minusYears(2),
            "Italia").apply {
            this.desiredDestinations = mutableListOf<Destination>(argentina)
        }
        val marco = User(
            "Marcos",
            "Bicho",
            "Bichito9",
            LocalDate.now().plusYears(2),
            "España").apply {
            this.desiredDestinations = mutableListOf<Destination>(argentina)
        }
        val mariano = User(
            "Mariano",
            "Acosta",
            "MA3",
            LocalDate.now().minusYears(1),
            "Italia").apply {
            this.desiredDestinations = mutableListOf<Destination>(argentina)
        }
        val sofia = User(
            "Sofia",
            "jedry",
            "sj1999",
            LocalDate.now().minusYears(3),
            "Argentina")

        it("Si tiene todos los campos Strings, la fecha de alta es anterior a la del dia, " +
                "tiene al menos un dia para viajar y tiene al menos un destino deseado entonces es valido"){
            esteban.addDesiredDestination(argentina)
            esteban.isValid().shouldBeTrue()
        }
        it("Si tiene algun campo String vacio entonces no es valido"){
            jorge.isValid().shouldBeFalse()
        }
        it("Si la fecha de alta es posterior a la del dia entonces no es valido"){
            marco.isValid().shouldBeFalse()
        }
        it("Si los dias para viajar son cero entonces no es valido"){
            mariano.isValid().shouldBeFalse()
        }
        it("Si no tiene un destino deseado entonces no es valido"){
            sofia.isValid().shouldBeFalse()
        }
    }

})