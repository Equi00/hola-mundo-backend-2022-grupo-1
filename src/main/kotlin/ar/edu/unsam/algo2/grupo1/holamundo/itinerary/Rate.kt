package ar.edu.unsam.algo2.grupo1.itinerary

import ar.edu.unsam.algo2.grupo1.users.User

class Rate {
    var rateInfo: MutableList<RateData> = mutableListOf()

    fun hadUserRated(user: User): Boolean = rateInfo.any{ it.userRate === user}

    fun addRate(score: Int, user: User){ rateInfo.add(RateData(score,user)) }

    fun isMultipleRatesByOneUser(): Boolean = rateInfo.map{ it.userRate }.distinct().count() != rateInfo.map{ it.userRate }.size

    fun isValid(): Boolean = !rateInfo.any{ it.rateScore < 1 || it.rateScore > 10}
}