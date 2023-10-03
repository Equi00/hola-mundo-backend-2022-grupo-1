package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.vehicle.Car
import ar.edu.unsam.algo2.grupo1.vehicle.Motocicle
import ar.edu.unsam.algo2.grupo1.vehicle.Truck
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class PreferenciaDeVehiculo : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test personalidades de usuario les gusta o no un vehiculo ") {

        //Arrange

        //Users
        val ezequiel = User(
            "Ezequiel",
            "Iozzia",
            "Eze91", LocalDate.now(),
            "Argentina")

        //Vehicle Preference

        val neophyte = Neophyte()
        val superstitious = Superstitious()
        val capricious = Capricious()
        val selective = Selective("Honda")
        val noLimit = NoLimit()
        val combined = Combined().apply {
            this.vehiclePersonalities = mutableListOf(noLimit, neophyte)
        }

        //Vehiculos
        val motoHonda = Motocicle(
            "Honda",
            "PCX125",
            LocalDate.now().minusYears(1).year,
            500.0F,
            true,
            350)
        val autoAudi = Car(
            "Audi",
            "R8",
            LocalDate.now().minusYears(5).year,
            1000.0F,
            true,
            true)
        val camionPolenta = Truck(
            "Volkswagen",
            "2000",
            LocalDate.now().minusYears(2).year,
            5000.0F,
            false,
            false)
        val motoYamaha = Motocicle(
            "Yamaha",
            "YZF-R6",
            LocalDate.now().minusYears(1).year,
            300.0F,
            true,
            500)
        val autoBmw = Car(
            "Bmw",
            "Serie1",
            LocalDate.now().minusYears(1).year,
            10.0F,
            false,
            false)

        it("Preferencia Neofilo le gusta vehiculo con antiguedad menor a 2 años") {
            neophyte.userLikes(ezequiel, motoHonda).shouldBeTrue()
        }

        it("Preferencia Neofilo NO le gusta vehiculo con antiguedad mayor a 2 años") {
            neophyte.userLikes(ezequiel, autoAudi).shouldBeFalse()
        }

        it("Preferencia Supersticioso le gusta vehiculo fabricado en años pares") {
            superstitious.userLikes(ezequiel, camionPolenta).shouldBeTrue()
        }

        it("Preferencia Supersticioso NO le gusta vehiculo fabricado en años impares") {
            superstitious.userLikes(ezequiel, motoHonda).shouldBeFalse()
        }

        it("Preferencia Caprichoso " +
                "le gusta vehiculo cuya inicial de la marca, coincida con incial de modelo") {
            capricious.userLikes(ezequiel, motoYamaha).shouldBeTrue()
        }

        it("Preferencia Caprichoso " +
                "NO le gusta vehiculo cuya inicial de la marca, no coincida con incial de modelo") {
            capricious.userLikes(ezequiel, motoHonda).shouldBeFalse()
        }

        it("Preferencia Selectivo le gusta vehiculo que sea de una marca especifica") {
            selective.userLikes(ezequiel, motoHonda).shouldBeTrue()
        }

        it("Preferencia Selectivo NO le gusta vehiculo que no sea de una marca especifica") {
            selective.userLikes(ezequiel, autoAudi).shouldBeFalse()
        }

        it("Preferencia Sin Limite le gusta vehiculo que tenga kilometraje libre") {
            noLimit.userLikes(ezequiel, motoYamaha).shouldBeTrue()
        }

        it("Preferencia Sin Limite NO le gusta vehiculo que no tenga kilometraje libre") {
            noLimit.userLikes(ezequiel, camionPolenta).shouldBeFalse()
        }

        it("Preferencia Combinado le gusta vehiculo " +
                "cuando se cumple las condiciones impuestas por distintas formas") {
            combined.userLikes(ezequiel, motoYamaha).shouldBeTrue()
        }

        it("Preferencia Combinado NO le gusta vehiculo " +
                "cuando no se cumple las condiciones impuestas por distintas formas") {
            combined.userLikes(ezequiel, autoBmw).shouldBeFalse()
        }

    }


})