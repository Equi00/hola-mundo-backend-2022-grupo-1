package ar.edu.unsam.algo2.grupo1.updaters

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.repositories.DestinationRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DestinationUpdater(val repository: DestinationRepository, val json: String) {
    fun jsonToDestinations() {
        val listType = object : TypeToken<List<Destination>>() {}.type
        val destinationsList = Gson().fromJson<List<Destination>>(json, listType)
        destinationsList.forEach {
            if (it.id == 0) repository.create(it) else repository.update(it)
        }
    }
}