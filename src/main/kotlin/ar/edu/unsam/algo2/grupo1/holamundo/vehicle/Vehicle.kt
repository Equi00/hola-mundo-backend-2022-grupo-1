package ar.edu.unsam.algo2.grupo1.vehicle

import ar.edu.unsam.algo2.grupo1.repositories.BaseData
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import kotlin.properties.Delegates

abstract class Vehicle(var brand: String,
                       var model: String,
                       var fabricationYear: Int,
                       var dayCost: Float,
                       var freeMileage: Boolean
                       ) : BaseData {

    override var id: Int = 0

    open var cylinderCapacity = 2
    open var hatchback = true
    open var is4x4 = true
    lateinit var type: String

    var agreements: MutableList<String> = mutableListOf("Honda")

    fun baseCost(rentalDays : Int): Float = dayCost * rentalDays.toFloat()

    abstract fun rechargeDays(rentalDays: Int): Float

    fun discountForAgreement(): Float =
        if(this.hasAnAgreement()) 0.9.toFloat()
        else 1.toFloat()

    fun totalCost(rentalDays: Int): Float =
        (this.baseCost(rentalDays)  + this.rechargeDays(rentalDays)) * this.discountForAgreement()

    fun hasAnAgreement() = agreements.contains(brand)

    fun firstBrandWithAgreement(): String = agreements.first()

    fun antiquity(): Int = LocalDate.now().year - fabricationYear

    fun addBrandForAgreement(brand: String) {
        agreements.add(brand)
    }

    @JsonProperty("type")
    abstract fun typeOfVehicle(): String
}

class Motocicle(brand: String, model: String, fabricationYear: Int, dayCost: Float, freeMileage: Boolean,
    override var cylinderCapacity: Int) : Vehicle(brand, model, fabricationYear, dayCost, freeMileage ){

    override fun rechargeDays(rentalDays: Int) =
        if(cylinderCapacity > 250) (rentalDays * 500).toFloat() else 0.toFloat()

    override fun typeOfVehicle() = "Motocicle"
}

class Car(brand: String, model: String, fabricationYear: Int, dayCost: Float, freeMileage: Boolean,
    override var hatchback: Boolean) : Vehicle(brand, model, fabricationYear, dayCost, freeMileage) {

    override fun rechargeDays(rentalDays: Int): Float =
        if(hatchback) this.baseCost(rentalDays) * 0.1.toFloat()
        else this.baseCost(rentalDays) * 0.25.toFloat()

    override fun typeOfVehicle() = "Car"
}

class Truck(brand: String, model: String, fabricationYear: Int, dayCost: Float, freeMileage: Boolean,
    override var is4x4: Boolean) : Vehicle(brand, model, fabricationYear, dayCost, freeMileage) {

    private val rentalRecharge: Float = 1000.toFloat()
    private val truckRecharge: Float = 10000.toFloat()

    override fun rechargeDays(rentalDays: Int): Float =
        (truckRecharge + this.rechargeForExcessDays(rentalDays)) * this.percentageRechargeFor4x4()

    private fun percentageRechargeFor4x4() = if(is4x4) 1.5.toFloat() else 1.toFloat()

    private fun rechargeForExcessDays(rentalDays: Int): Float {
        return maxOf(rentalDays - 7, 0).toFloat() * rentalRecharge
    }

    override fun typeOfVehicle() = "Truck"
}