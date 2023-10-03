package ar.edu.unsam.algo2.grupo1.repositories

import ar.edu.unsam.algo2.grupo1.vehicle.Car
import ar.edu.unsam.algo2.grupo1.vehicle.Motocicle
import ar.edu.unsam.algo2.grupo1.vehicle.Truck
import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle
import org.springframework.stereotype.Repository

@Repository
class VehicleRepository() : BaseRepository<Vehicle>() {

    init {
        this.setDefaultValues()
    }

    override fun setDefaultValues() {
        var idCount = 0

        this.elements = mutableSetOf(
            Car("Audi","A4",2008,1500.toFloat(),true,false),
            Motocicle("Yamaha","WR125X",2015, 600.toFloat(),false, 5),
            Truck("Scania","R20",1990, 5000.toFloat(), true, false),
            Car("Peugeot","206",2009,960.toFloat(),false,true),
            Car("Fiat","500",1998,1500.toFloat(),false,false),
            Truck("Volvo","H2",2015, 8000.toFloat(), true, true),
            Car("Audi","R8",2008,3000.toFloat(),true,true),
            Motocicle("Yamaha","WR125X",2015, 800.toFloat(),true, 2)
        )
        this.elements.forEach{ it.id = idCount++ }
        this.autoId = idCount
    }

    override fun search(value: String) : List<Vehicle> =
        this.elements.filter { value.equals(it.brand, ignoreCase = true) || it.model.startsWith(value, ignoreCase = true)}
}