package ar.edu.unsam.algo2.grupo1.users

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate

class UsuarioYAmigos : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Usuarios y amigos") {
        val julian = User(
            "Julian",
            "Carnillo",
            "juli_carne",
            LocalDate.now().minusYears(5),
            "Rusia")
        val sebastian = User(
            "Sebastian",
            "Bauer",
            "sebasbau03",
            LocalDate.now().minusYears(10),
            "Argentina")
        val roberto = User(
            "Roberto",
            "Sanchez",
            "robisanchez",
            LocalDate.now().minusYears(3),
            "Argentina").apply {
            this.addFriend(sebastian)
        }

        it("El usuario Roberto tiene al usuario Sebastian como amigo") {
            roberto.isFriend(sebastian).shouldBeTrue()
        }
        it("El usuario Roberto no tiene al usuario Julian como amigo") {
            roberto.isFriend(julian).shouldBeFalse()
        }
    }
})