package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.itinerary.ItinearyDay
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.mailSender.MailSender
import ar.edu.unsam.algo2.grupo1.repositories.UserRepository
import ar.edu.unsam.algo2.grupo1.tasks.*
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate
import java.time.LocalDateTime


class TestEnvioEmailTarea : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    //destinos
    val argentina = Destination("Argentina", "Buenos Aires", 30000.toFloat())
    val italia = Destination("Italia", "Roma", 50000.toFloat())
    val alemania = Destination("Alemania", "Berlin", 100000.toFloat())

    //actividades
    val trekking = Activity(
        500.toFloat(), "Trekking en sendero al lago", LocalDateTime.of(2022, 4, 5, 11, 0, 0),
        LocalDateTime.of(2022, 4, 5, 12, 0, 0), Difficulty.LOW
    )
    val caminata = Activity(
        500.toFloat(), "Caminata por el bosque", LocalDateTime.of(2022, 4, 5, 14, 0, 0),
        LocalDateTime.of(2022, 4, 5, 15, 0, 0), Difficulty.LOW
    )

    //repositorios
    val repositorioDeUsuarios = UserRepository()

    //usuarios
    val esteban = User("Esteban", "Kotillo", "Est321", LocalDate.now().minusYears(5), "Argentina").apply {
        this.addDesiredDestination(argentina)
        this.travelDays = 5
        this.email = ""
    }
    val jorge = User("Jorge", "Jusua", "J300", LocalDate.now().minusYears(10), "Argentina").apply {
        this.addDesiredDestination(argentina)
        this.addDesiredDestination(italia)
        this.addDesiredDestination(alemania)
        this.travelDays = 4
        this.email = ""
    }
    val marcos = User("Marcos", "Jusua", "M300", LocalDate.now().minusYears(9), "Argentina").apply {
        this.addDesiredDestination(argentina)
        this.addDesiredDestination(italia)
        this.travelDays = 4
        this.email = ""
    }

    //itinerarios
    val itinerario1 = Itinerary(esteban, argentina).apply {
        this.addDay(ItinearyDay().apply {
            this.addActivities(mutableListOf<Activity>(trekking, caminata))
        })
    }
    val itinerario2 = Itinerary(esteban, argentina).apply {
        this.addDay(ItinearyDay().apply {
            this.addActivities(mutableListOf<Activity>(trekking, caminata))
        })
    }
    val itinerario3 = Itinerary(esteban, argentina).apply {
        this.addDay(ItinearyDay().apply {
            this.addActivities(mutableListOf<Activity>(trekking, caminata))
        })
    }

    //tareas
    val puntuarItinerarios = RateItineraries(10).apply {
        this.userRepository = repositorioDeUsuarios
        this.mailSender = mockkMailSender()
    }
    val transferirItinerariosAAmigo = TransferItinerariesToFriend().apply {
        this.userRepository = repositorioDeUsuarios
        this.mailSender = mockkMailSender()
    }
    val agregarAmigosQueConozcanDestino = AddFriendKnownDestination(itinerario1).apply {
        this.userRepository = repositorioDeUsuarios
        this.mailSender = mockkMailSender()
    }
    val agregarDestinosDeseadosMasCaros = AddExpensiveDestinations().apply {
        this.userRepository = repositorioDeUsuarios
        this.mailSender = mockkMailSender()
    }

    describe("Se preestablecen tareas para simplificar trabajos a los usuarios") {
        it(
            "Se realiza la tarea de darle puntaje a los itinerarios a puntuar y se debe vaciar la lista" +
                    "de itinerarios a puntuar del usuario"
        ) {
            jorge.addItineraryToRate(itinerario1)
            jorge.addItineraryToRate(itinerario2)

            puntuarItinerarios.doTask(jorge)

            jorge.itinerariesToRate.shouldBeEmpty()
        }

        it(
            "Un usuario transfiere todos sus itinerarios al amigo con menos destinos visitados y " +
                    "el amigo debe tener sus itinerarios mas los que les fueron transferidos"
        ) {
            marcos.apply {
                this.itineraries = mutableListOf(itinerario2, itinerario3)
            }
            esteban.apply {
                this.itineraries = mutableListOf(itinerario1)
            }

            marcos.addFriend(jorge)
            marcos.addFriend(esteban)

            jorge.addVisitDestination(argentina)
            jorge.addVisitDestination(italia)
            esteban.addVisitDestination(argentina)

            transferirItinerariosAAmigo.doTask(marcos)

            esteban.itineraries.shouldBe(mutableListOf(itinerario1, itinerario2, itinerario3))
        }

        it("El usuario se hace amigo de todos los usuarios que conozcan un destino especifico correctamente") {
            val viki = User("Viktoria", "Iutus", "kikikaka", LocalDate.now().minusYears(5), "Argentina").apply {
                this.addDesiredDestination(argentina)
                this.travelDays = 5
                this.email = ""
            }

            repositorioDeUsuarios.create(jorge)
            repositorioDeUsuarios.create(marcos)
            repositorioDeUsuarios.create(viki)

            jorge.addVisitDestination(argentina)
            viki.addVisitDestination(argentina)
            marcos.addVisitDestination(italia)

            agregarAmigosQueConozcanDestino.doTask(esteban)

            esteban.friends.shouldBe(mutableListOf(jorge, viki))
        }

        it("se agrega a la lista de destinos deseados el destino mas caro de cada uno de los amigos del usuario") {
            esteban.addFriend(jorge)
            esteban.addFriend(marcos)

            agregarDestinosDeseadosMasCaros.doTask(esteban)

            esteban.desiredDestinations.shouldBe(mutableListOf(argentina, alemania, italia))
        }
    }

    describe("Prueba de envio de mails a los usuarios al realizar las tareas.") {

        it(
            "Se realiza la tarea de darle puntaje a los itinerarios a puntuar y se debe vaciar la lista" +
                    "de itinerarios a puntuar del usuario"
        ) {
            // ACT:
            jorge.addItineraryToRate(itinerario1)
            jorge.addItineraryToRate(itinerario2)

            // ASSERT:
            assertDoesNotThrow { puntuarItinerarios.doTask(jorge) }
        }

        it(
            "Un usuario transfiere todos sus itinerarios al amigo con menos destinos visitados y " +
                    "el amigo debe tener sus itinerarios mas los que les fueron transferidos"
        ) {
            // ACT:
            marcos.apply {
                this.itineraries = mutableListOf(itinerario2, itinerario3)
            }
            esteban.apply {
                this.itineraries = mutableListOf(itinerario1)
            }
            marcos.addFriend(jorge)
            marcos.addFriend(esteban)
            jorge.addVisitDestination(argentina)
            jorge.addVisitDestination(italia)
            esteban.addVisitDestination(argentina)

            // ASSERT:
            assertDoesNotThrow { transferirItinerariosAAmigo.doTask(marcos) }
        }

        it("El usuario se hace amigo de todos los usuarios que conozcan un destino especifico correctamente") {
            // ACT:
            val viki = User("Viktoria", "Iutus", "kikikaka", LocalDate.now().minusYears(5), "Argentina").apply {
                this.addDesiredDestination(argentina)
                this.travelDays = 5
                this.email = ""
            }
            repositorioDeUsuarios.create(jorge)
            repositorioDeUsuarios.create(marcos)
            repositorioDeUsuarios.create(viki)
            jorge.addVisitDestination(argentina)
            viki.addVisitDestination(argentina)
            marcos.addVisitDestination(italia)

            // ASSERT:
            assertDoesNotThrow { agregarAmigosQueConozcanDestino.doTask(esteban) }
        }

        it("se agrega a la lista de destinos deseados el destino mas caro de cada uno de los amigos del usuario") {
            // ACT:
            esteban.addFriend(jorge)
            esteban.addFriend(marcos)

            // ASSERT:
            assertDoesNotThrow { agregarDestinosDeseadosMasCaros.doTask(esteban) }
        }
    }
})

fun mockkMailSender() : MailSender {
    val mailSender = mockk<MailSender>(relaxUnitFun = true)
    every { mailSender.sendMail(any()) } answers { 200 }
    return mailSender
}