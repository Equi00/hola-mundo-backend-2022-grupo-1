package ar.edu.unsam.algo2.grupo1.users

import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle

interface VehiclePreference {
    var name: String
    fun userLikes(user: User, vehicle: Vehicle): Boolean

    fun getSpecifiedBrand(): String = ""
    fun getCombinedVehiclePersonalities(): List<VehiclePreference> = listOf()
}

class Neophyte(): VehiclePreference {
    override var name = "Neophyte"
    override fun userLikes(user: User, vehicle: Vehicle) = vehicle.antiquity() <= 2
}

class Superstitious(): VehiclePreference {
    override var name = "Superstitious"
    override fun userLikes(user: User, vehicle: Vehicle) = vehicle.fabricationYear % 2 == 0
}

class Capricious(): VehiclePreference {
    override var name = "Capricious"
    override fun userLikes(user: User, vehicle: Vehicle) = vehicle.brand.first() == vehicle.model.first()
}

class Selective(private val specifiedBrand: String): VehiclePreference {
    override var name = "Selective"
    override fun userLikes(user: User, vehicle: Vehicle) = vehicle.brand == specifiedBrand

    override fun getSpecifiedBrand(): String = specifiedBrand
}

class NoLimit(): VehiclePreference {
    override var name = "NoLimit"
    override fun userLikes(user: User, vehicle: Vehicle) = vehicle.freeMileage
}

class Combined(): VehiclePreference {
    override var name = "Combined"
    var vehiclePersonalities = mutableListOf<VehiclePreference>()
    override fun userLikes(user: User, vehicle: Vehicle) = vehiclePersonalities.all {it.userLikes(user, vehicle)}
    override fun getCombinedVehiclePersonalities(): List<VehiclePreference> = vehiclePersonalities
}
