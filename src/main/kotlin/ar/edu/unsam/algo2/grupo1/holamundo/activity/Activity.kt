package ar.edu.unsam.algo2.grupo1.holamundo.activity

import ar.edu.unsam.algo2.grupo1.activity.Difficulty
import ar.edu.unsam.algo2.grupo1.holamundo.dto.DifficultyDTO
import ar.edu.unsam.algo2.grupo1.holamundo.dto.itineraryDTO.GetActivityDTO
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import ar.edu.unsam.algo2.grupo1.repositories.BaseData
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.format.DateTimeFormatter

class Activity(var cost: Float, var description: String,
               @JsonIgnore
               var initialTime: LocalDateTime,
               @JsonIgnore
               var endTime: LocalDateTime,
               @JsonIgnore
               var difficulty: Difficulty) : BaseData {
    override var id: Int = 0

    @JsonProperty("duration")
    fun durationInMinutes() = ChronoUnit.MINUTES.between(this.initialTime, this.endTime)

    fun isValid() = this.haveDescription() and this.isTimeValid() and this.isCostValid()

    private fun haveDescription() = this.description.isNotBlank()
    private fun isTimeValid() = this.initialTime.isBefore(this.endTime)
    private fun isCostValid() = this.cost >= 0

    @JsonProperty("difficulty")
    fun getDifficulty(): DifficultyDTO {
        return DifficultyDTO(difficulty.name, difficulty.priority)
    }

    @JsonProperty("initialTime")
    fun getInitialTimeAsString(): String {
        val d = this.initialTime
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'")
        return d.format(formatter)
    }

    @JsonProperty("endTime")
    fun getEndTimeAsString(): String {
        val d = this.endTime
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'")
        return d.format(formatter)
    }

    fun toGetActivityDTO(): GetActivityDTO {
        return GetActivityDTO(
            id = this.id,
            cost = this.cost,
            description = this.description,
            initialTime = this.getInitialTimeAsString(),
            endTime = this.getEndTimeAsString(),
            difficulty = DifficultyDTO(this.difficulty.name, this.difficulty.priority),
            duration = this.durationInMinutes().toInt(),
            valid = this.isValid()
        )
    }

}