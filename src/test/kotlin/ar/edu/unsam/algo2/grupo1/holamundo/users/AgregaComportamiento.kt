package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.mailSender.MailSender
import ar.edu.unsam.algo2.grupo1.observers.ItineraryRateObserver
import ar.edu.unsam.algo2.grupo1.observers.TripLocalObserver
import ar.edu.unsam.algo2.grupo1.observers.VehicleAgreementObserver
import ar.edu.unsam.algo2.grupo1.observers.tellFriendAboutTripObserver
import ar.edu.unsam.algo2.grupo1.vehicle.Car
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate

class AgregaComportamiento: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Se le agrega comportamiento al usuario en funcion de las caracteristicas del viaje.") {
        // ARRANGE:

        //Destinations:
        val viajeArgentina = Destination("Argentina","Buenos Aires", 15000.0F)
        val viajeCanada = Destination("Canada","Quebec", 55000.0F)

        // Users:
        val user1 = User(
            "Mateo",
            "Pastorini",
            "MatePasto",
                        LocalDate.now().minusYears(7),
            "Argentina"
        ).apply {
                this.email = "mateopastorini@gmail.com"
                addBehaviour(TripLocalObserver())
                addBehaviour(VehicleAgreementObserver())
                addDesiredDestination(viajeCanada)
        }

        val user2 = User(
            "Fernando",
            "Albarellos",
            "Fernan777",
                        LocalDate.now().minusYears(7),
            "España"
        ).apply{
                this.email = "fernan777@hotmail.com"
                addBehaviour(ItineraryRateObserver())
                addFriend(user1)
        }

        // Personallities:
        val personalityRelaxed = PersonalityRelaxed()
        val personalityLocalist = PersonalityLocalist()

        // Vehicle Preferences:
        val neophyte = Neophyte()
        val selective = Selective("Honda")

        // Vehicles:
        val car1 = Car(
            "Peugeot",
            "206",
                    LocalDate.now().minusYears(5).year,
            750.0F,
            true,
            true
        )

        val car2 = Car(
            "Honda",
            "HR-V",
                    LocalDate.now().minusYears(3).year,
            650.0F,
            true,
            true
        )

        // Itineraries:
        val itinerary1 = Itinerary(user1, viajeCanada).apply{
            vehicle = car1
        }
        val itinerary2 = Itinerary(user2, viajeArgentina).apply{
            vehicle = car2
        }

        it("Se le envia un mail a sus amigos cuando visita un destino que ellos desean visitar.") {
            // ACT:
            user2.addBehaviour(tellFriendAboutTripObserver(mockMailSender()))

            // ASSERT:
            assertDoesNotThrow{ user2.doTrip(itinerary1) }
            user2.visitedDestinations.shouldBe(listOf<Destination>(viajeCanada))
        }

        it("Modifica el criterio localista si no el viaje es local.") {
            // ACT:
            user1.doTrip(itinerary1)

            // ASSERT:
            user1.personality.shouldBeTypeOf<PersonalityLocalist>()
        }

        it("No modifica el criterio localista si el viaje es local.") {
            // ACT:
            user1.doTrip(itinerary2)

            // ASSERT:
            user1.personality.shouldBeTypeOf<PersonalityRelaxed>()
        }

        it("Se le agrega un itinerario a su lista de itinerarios a puntuar.") {
            // ACT:
            user2.doTrip(itinerary2)

            //ASSERT:
            user2.itinerariesToRate.shouldBe(listOf<Itinerary>(itinerary2))
        }

        it("Modifica la preferencia de vehiculos según si el viaje tiene un vehiculo con convenio.") {
            // ACT:
            user1.doTrip(itinerary1)

            // ASSERT:
            user1.vehiclePreference.shouldBeTypeOf<Selective>()
        }

        it("No modifica la preferencia de vehiculos según si el viaje no tiene un vehiculo con convenio.") {
            // ACT:
            user1.doTrip(itinerary2)

            // ASSERT:
            user1.vehiclePreference.shouldBeTypeOf<Neophyte>()
        }
    }
})

fun mockMailSender() : MailSender{
    val mailSender: MailSender = mockk<MailSender>(relaxUnitFun = true)
    every {
        mailSender.sendMail(any()) } answers { 200 }
    return mailSender
}
