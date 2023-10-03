package ar.edu.unsam.algo2.grupo1.destinations

import ar.edu.unsam.algo2.grupo1.repositories.BaseData
import ar.edu.unsam.algo2.grupo1.users.User

class Destination(var country: String, var city: String, var baseCost: Float) : BaseData {
    override var id: Int = 0
    companion object{
        val LOCAL = "Argentina"
        val MAX_ANTIQUITY = 15
    }

    fun isLocal(): Boolean = this.country == LOCAL

    fun totalCostFor(user: User): Float = this.baseCost + this.localAddedCost() - this.sameCountryDiscount(user)

    fun isValid(): Boolean = !this.countryIsNull() && !this.cityIsNull() && this.baseCostIsValid()

    private fun localAddedCost(): Float =
        if(!this.isLocal())
            (this.baseCost * 0.2).toFloat()
        else 0.toFloat();

    private fun isFromThisCountry(user: User): Boolean = this.country.equals(user.countryOfResidence, ignoreCase = true)

    private fun sameCountryDiscount(user: User): Float =
        if(this.isFromThisCountry(user))
            (this.baseCost * (0.01 * user.antiquityMax(MAX_ANTIQUITY))).toFloat()
        else 0.toFloat();

    private fun countryIsNull(): Boolean = this.country.isEmpty()

    private fun cityIsNull(): Boolean = this.city.isEmpty()

    private fun baseCostIsValid(): Boolean = this.baseCost > 0

}