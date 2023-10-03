package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.users.User
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class AntiguedadUsuario : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    //Arrange

    //Users
    val userE = User(
        "Ezequiel",
        "Iozzia",
        "Eze91",
        LocalDate.now().minusYears(12),
        "Argentina")
    val userM = User(
        "Matias",
        "Lomonaco",
        "Mati87",
        LocalDate.now().minusYears(22),
        "Argentina")

    it("Fecha de alta del usuario antes de los 15 años") {

        //Assert
        userE.antiquity() shouldBe 12
    }

    it("Fecha de alta del usuario después de los 15 años") {

        //Assert
        userM.antiquity() shouldBe 22
    }

})