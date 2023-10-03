package ar.edu.unsam.algo2.grupo1.holamundo.repositories

import ar.edu.unsam.algo2.grupo1.repositories.BaseRepository
import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class ActivityRepository : BaseRepository<Activity>() {

    init {
        this.setDefaultValues()
    }
    override fun setDefaultValues(){
        var idCount = 0

        this.elements = mutableSetOf(
            Activity(
                2000.toFloat(),
                "Senderismo / Trekking en Cerro Campanario",
                LocalDateTime.of(2022, 4, 5, 9, 0, 0),
                LocalDateTime.of(2022, 4, 5, 11, 0, 0),
                Difficulty.LOW
            ),
            Activity(
                16000.toFloat(),
                "Ruta de los 7 Lagos, Villa La Angostura - San Martin de los Andes",
                LocalDateTime.of(2022, 4, 5, 8, 0, 0),
                LocalDateTime.of(2022, 4, 5, 16, 0, 0),
                Difficulty.HIGH
            ),
            Activity(
                15000.toFloat(),
                "Circuito chico desde la rotonda Km 18 de Av. Exequiel Bustillo",
                LocalDateTime.of(2022, 4, 5, 9, 0, 0),
                LocalDateTime.of(2022, 4, 5, 10, 0, 0),
                Difficulty.LOW
            ),
            Activity(
                22000.toFloat(),
                "Cerro Tronador y Glaciar Ventisquero Negro",
                LocalDateTime.of(2022, 4, 5, 10, 0, 0),
                LocalDateTime.of(2022, 4, 5, 13, 50, 0),
                Difficulty.MEDIUM
            ),
            Activity(
                15000.toFloat(),
                "Cerveceria Patagonia",
                LocalDateTime.of(2022, 4, 5, 9, 0, 0),
                LocalDateTime.of(2022, 4, 5, 10, 0, 0),
                Difficulty.MEDIUM
            )
        )
        this.elements.forEach{ it.id = idCount++ }
        this.autoId = idCount
    }

    override fun search(value: String): List<Activity> =
        this.elements.filter { this.checkIfItContainsWord(it, value) || value.equals(it.description, ignoreCase = true)}

    private fun checkIfItContainsWord(activity: Activity, search: String): Boolean{
        val words = activity.description.split("\\s+")
        return words.any{ it.contains(search, ignoreCase = true) }
    }
}