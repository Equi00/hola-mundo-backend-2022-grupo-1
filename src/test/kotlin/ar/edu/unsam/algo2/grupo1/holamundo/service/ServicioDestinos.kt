//package ar.edu.unsam.algo2.grupo1.service
//
//import ar.edu.unsam.algo2.grupo1.destinations.Destination
//import ar.edu.unsam.algo2.grupo1.repositories.DestinationRepository
//import ar.edu.unsam.algo2.grupo1.updaters.DestinationUpdater
//import io.kotest.core.spec.IsolationMode
//import io.kotest.core.spec.style.DescribeSpec
//import io.kotest.matchers.equality.shouldBeEqualToUsingFields
//import io.kotest.matchers.shouldBe
//import io.mockk.every
//import io.mockk.mockk
//
//
//class ServicioDestinos : DescribeSpec({
//    isolationMode = IsolationMode.InstancePerTest
//
//    describe("Test de servicio de destino") {
//
//        val mockkedDestinationService = mockDestinationService()
//
//        val italia = Destination("Italia", "Roma", 200.toFloat()).apply {
//            this.id = 9
//        }
//        val chile = Destination("Chile", "Santiago", 100.toFloat()).apply {
//            this.id = 12
//        }
//
//        val argentina = Destination("Argentina", "Mar del Plata", 10000.0f).apply {
//            this.id = 9
//        }
//        val brasil = Destination("Brasil", "Rio de Janeiro", 20000.0f).apply {
//            this.id = 12
//        }
//
//        val repositorioDestinos = DestinationRepository()
//
//        val res = mockkedDestinationService.getDestinations()
//        val destinationUpdater = DestinationUpdater(repositorioDestinos, res)
//
//        it(
//            "Al realizar una peticion get al servicio de destinos debe retornar una lista en formato Json String el" +
//                    " cual convertimos a una lista de objetos con el updater y actualizaremos el repositorio de destinos," +
//                    "al actualizarse, los destinos sin id en el Json se deberan crear, en este caso tendremos el repositorio" +
//                    "con 2 destinos cuyo id es identico a los destinos del Json, en el Json hay un destino sin id, al actualizarse" +
//                    "el repositorio debe haber un total de 3 destinos"
//        ) {
//
//            repositorioDestinos.create(italia)
//            repositorioDestinos.create(chile)
//
//            italia.id = 9
//            chile.id = 12
//
//            destinationUpdater.jsonToDestinations()
//
//            repositorioDestinos.elementsSize().shouldBe(3)
//            repositorioDestinos.getById(9)?.shouldBeEqualToUsingFields(argentina)
//            repositorioDestinos.getById(12)?.shouldBeEqualToUsingFields(brasil)
//        }
//    }
//
//
//})
//
//fun mockDestinationService(): DestinationService {
//    val service = mockk<DestinationService>(relaxUnitFun = true)
//
//    every { service.getDestinations() } answers { "[{\"id\": 9,\"country\": \"Argentina\",\"city\": \"Mar del Plata\",\"baseCost\": 10000},{\"id\": 12,\"country\": \"Brasil\",\"city\": \"Rio de Janeiro\",\"baseCost\": 20000},{\"country\": \"Indonesia\",\"city\": \"Bali\",\"baseCost\": 30000}]" }
//
//    return service
//}