package ar.edu.unsam.algo2.grupo1.itinerary

import ar.edu.unsam.algo2.grupo1.holamundo.activity.Activity

class ItinearyDay {
    var activities: MutableList<Activity> = mutableListOf()

    fun totalActivitiesCost(): Float = activities.sumOf{it.cost.toDouble()}.toFloat()

    fun avarageDuration(): Int = (activities.sumOf { it.durationInMinutes().toDouble() } / activities.size ).toInt()

    fun isAlmostOneActivity(): Boolean = activities.isNotEmpty()

    fun areOverlapedAcitivities(): Boolean = activities.any { act1 -> activities.any { act2 -> act1.initialTime.isBefore(act2.endTime) && act1.endTime.isAfter(act2.initialTime) && act1 !== act2 }}

    fun addActivity(activity: Activity) { activities.add(activity) }
    fun addActivities(_activities: List<Activity>) { activities.addAll(_activities)  }
    fun removeActivity(activity: Activity) { activities.remove(activity) }
    fun removeActivities(_activities: List<Activity>) { activities.removeAll(_activities)  }
}