package ar.edu.unsam.algo2.grupo1.holamundo.dto

import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity
import ar.edu.unsam.algo2.grupo1.holamundo.repositories.ActivityRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class CreateActivityDTO(
    val cost: Float,
    val description: String,
    val initialTime: String,
    val endTime: String,
    val difficulty: DifficultyDTO
){}

fun CreateActivityDTO.toEntity() = Activity(
    cost = cost,
    description = description,
    initialTime = LocalDateTime.parse(initialTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'")),
    endTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'")),
    difficulty = getDifficultyByPriority(difficulty.priority)
)


fun getDifficultyByPriority(priority: Number): Difficulty {
    val diff = listOf<Difficulty>(Difficulty.LOW, Difficulty.MEDIUM, Difficulty.HIGH).find {
        it.priority == priority
    }
    return if(diff != null) diff else Difficulty.NULL_DIFFICULTY
}

data class UpdateActivityDTO(
    val id: Int,
    val cost: Float,
    val description: String,
    val initialTime: String,
    val endTime: String,
    val difficulty: DifficultyDTO
){}

fun UpdateActivityDTO.toEntity() = Activity(
    cost = cost,
    description = description,
    initialTime = LocalDateTime.parse(initialTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'")),
    endTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'")),
    difficulty = getDifficultyByPriority(difficulty.priority)
).also {
    it.id = this.id
}