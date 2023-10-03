package ar.edu.unsam.algo2.grupo1.itinerary

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.users.User
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalDateTime

class DificultadDeItinerario: DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    describe("Tests de dificultad del itinerario"){
    //Arrange

    //Activities
         val activity1 = Activity(3000.0f, "Futbol", LocalDateTime.of(2022,4,5,8,30,0),LocalDateTime.of(2022,4,5,10,30,0), Difficulty.LOW)
         val activity2 = Activity(3000.0f, "Tennis",LocalDateTime.of(2022,4,5,11,0 ,0), LocalDateTime.of(2022,4,5,13,0,0), Difficulty.MEDIUM)
         val activity3 = Activity(3000.0f, "Calistenia", LocalDateTime.of(2022,4,6,8,0,0),LocalDateTime.of(2022,4,6,10,0,0), Difficulty.MEDIUM)
         val activity4 = Activity(3000.0f, "Boxeo", LocalDateTime.of(2022,4,6,12,30,0),LocalDateTime.of(2022,4,6,14,0,0), Difficulty.HIGH)
         val activity5 = Activity(3000.0f, "Voley", LocalDateTime.of(2022,4,6,15,0,0), LocalDateTime.of(2022,4,6,17,0,0), Difficulty.HIGH)
         val activity6 = Activity(3000.0f, "Calistenia", LocalDateTime.of(2022,4,7,10,30,0), LocalDateTime.of(2022,4,7,12,30,0), Difficulty.MEDIUM)
    //Aux user
        val raul = User("Raul","Mendez","raulmendez", LocalDate.now().minusYears(3), "Argentina")
    //List
        val activities1 = arrayListOf<Activity>(activity1,activity2,activity3,activity4,activity5,activity6)
        val activities2 = arrayListOf<Activity>(activity1,activity2)

    //Destination
        val destination1 = Destination("Argentina","Buenos Aires",3000.0F)
        val destination2 = Destination("Italia","Roma",10000.0F)

    //Itineraries
         val itinerary1 = Itinerary(raul, destination1).apply {
             this.addDay(ItinearyDay().apply {
                 this.addActivities(activities1)
             })
             this.addEmptyDays(3)
         }
         val itinerary2 = Itinerary(raul, destination2).apply {
             this.addDay(ItinearyDay().apply {
                 this.addActivities(activities2)
             })
             this.addEmptyDays(4)
         }


        it("El itinerario tiene más actividades nivel medio"){
            //Act

            //Arrange
            itinerary1.difficulty() shouldBe Difficulty.MEDIUM
        }

        it("El itinerario tiene cantidad igual de actividades de nivel bajo y nivel medio"){
            //Act

            //Arrange
            itinerary2.difficulty() shouldBe Difficulty.MEDIUM
        }
    }

})