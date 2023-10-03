package ar.edu.unsam.algo2.grupo1.repositories

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import org.springframework.stereotype.Repository

@Repository
class DestinationRepository : BaseRepository<Destination>() {

    init {
        this.setDefaultValues()
    }
    override fun setDefaultValues() {
        var count = 0
        this.elements = mutableSetOf(
            Destination("Argentina","Buenos Aires",3000.toFloat()),
            Destination("Argentina","Mar del Plata",10000.toFloat()),
            Destination("Italia","Roma",10000.toFloat()),
            Destination("Chile", "Santiago", 100.toFloat()),
            Destination("España", "Barcelona", 15000.toFloat()),
            Destination("Brasil", "Brasilia", 20000.toFloat()),
            Destination("Indonesia", "Bali", 30000.toFloat())

        )
        this.elements.forEach{ it.id = count++}
        this.autoId = count
    }
    override fun search(value: String): List<Destination> =
        this.elements.filter { it.country.contains(value, ignoreCase = true)
                    || it.city.contains(value, ignoreCase = true) }
}