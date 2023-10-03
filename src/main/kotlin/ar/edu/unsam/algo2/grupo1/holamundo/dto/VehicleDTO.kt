package ar.edu.unsam.algo2.grupo1.holamundo.dto

import ar.edu.unsam.algo2.grupo1.vehicle.Car
import ar.edu.unsam.algo2.grupo1.vehicle.Motocicle
import ar.edu.unsam.algo2.grupo1.vehicle.Truck
import ar.edu.unsam.algo2.grupo1.vehicle.Vehicle

data class CreateVehicleDTO (
        val type: String,
        val brand: String,
        val model: String,
        val fabricationYear: Int,
        val dayCost: Float,
        val freeMileage: Boolean,
        val cylinderCapacity: Int?,
        val hatchback: Boolean?,
        val is4x4: Boolean?
        ){}

fun CreateVehicleDTO.toEntity(): Vehicle {
        if(type.equals("Car", ignoreCase = true)){
                return Car(
                        brand = brand,
                        model = model,
                        fabricationYear = fabricationYear,
                        dayCost = dayCost,
                        freeMileage = freeMileage,
                        hatchback = hatchback!!
                )
        }else if(type.equals("Motocicle", ignoreCase = true)) {
                return Motocicle(
                        brand = brand,
                        model = model,
                        fabricationYear = fabricationYear,
                        dayCost = dayCost,
                        freeMileage = freeMileage,
                        cylinderCapacity = cylinderCapacity!!
                )
        }
        return Truck(
                brand = brand,
                model = model,
                fabricationYear = fabricationYear,
                dayCost = dayCost,
                freeMileage = freeMileage,
                is4x4 = is4x4!!
        )
}

data class UpdateVehicleDTO(
        val id: Int,
        val type: String,
        val brand: String,
        val model: String,
        val fabricationYear: Int,
        val dayCost: Float,
        val freeMileage: Boolean,
        val cylinderCapacity: Int?,
        val hatchback: Boolean?,
        val is4x4: Boolean?
){}

fun UpdateVehicleDTO.toEntity(): Vehicle{
        if(type.equals("Car", ignoreCase = true)){
                return Car(
                        brand = brand,
                        model = model,
                        fabricationYear = fabricationYear,
                        dayCost = dayCost,
                        freeMileage = freeMileage,
                        hatchback = hatchback!!
                ).also {
                        it.id = this.id
                }
        }else if(type.equals("Motocicle", ignoreCase = true)) {
                return Motocicle(
                        brand = brand,
                        model = model,
                        fabricationYear = fabricationYear,
                        dayCost = dayCost,
                        freeMileage = freeMileage,
                        cylinderCapacity = cylinderCapacity!!
                ).also {
                        it.id = this.id
                }
        }
        return Truck(
                brand = brand,
                model = model,
                fabricationYear = fabricationYear,
                dayCost = dayCost,
                freeMileage = freeMileage,
                is4x4 = is4x4!!
        ).also {
                it.id = this.id
        }
}