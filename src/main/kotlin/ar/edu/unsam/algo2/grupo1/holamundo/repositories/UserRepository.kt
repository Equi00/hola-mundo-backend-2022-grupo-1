package ar.edu.unsam.algo2.grupo1.repositories


import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.users.*
import org.springframework.stereotype.Repository
import java.time.LocalDate
import ar.edu.unsam.algo2.grupo1.repositories.*

@Repository
class UserRepository : BaseRepository<User>() {

    init {
        this.setDefaultValues()
    }

    override fun setDefaultValues() {
        var count = 0
        val destinationRepository = DestinationRepository()
        var vehicleRepository = VehicleRepository()

        this.elements = mutableSetOf(
            User(
                "Nicolas",
                "Villamonte",
                "nicovillamonte",
                LocalDate.of(2020,2, 1),
                "Uruguay")
                .apply {
                    email = "nicovillamonte@gmail.com"
                    personality = PersonalityLocalist()
                    vehiclePreference = Combined().apply {
                        vehiclePersonalities.addAll(mutableListOf(Neophyte(),NoLimit(),Capricious()))
                    }
                    destinationRepository.getById(4)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(2)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(1)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(0)?.let { desiredDestinations.add(it) }
                    destinationRepository.getById(6)?.let { desiredDestinations.add(it) }
                    itineraries = mutableListOf(
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(5)!!).apply {
                            vehicleRepository.getById(0)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        }
                    )
                    friends = mutableListOf(
                        User("","","", LocalDate.now(),""),
                        User("","","", LocalDate.now(),"")
                    )
                    itinerariesToRate = mutableListOf(
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(5)!!).apply {
                            vehicleRepository.getById(0)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        }
                    )
                    this.password = "nico123"
                },
            User(
                "Mateo",
                "Pastorini",
                "matepasto",
                LocalDate.of(2015,2, 1),
                "Argentina")
                .apply {
                    email = "matepasto@gmail.com"
                    personality = PersonalityActive()
                    vehiclePreference = NoLimit()
                    destinationRepository.getById(4)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(2)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(3)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(1)?.let { desiredDestinations.add(it) }
                    destinationRepository.getById(5)?.let { desiredDestinations.add(it) }
                    itineraries = mutableListOf(
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(5)!!).apply {
                            vehicleRepository.getById(0)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        }
                    )
                    friends = mutableListOf(
                        User("","","", LocalDate.now(),""),
                        User("","","", LocalDate.now(),""),
                    )
                    itinerariesToRate = mutableListOf(
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(5)!!).apply {
                            vehicleRepository.getById(0)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        }
                    )
                    this.password = "mate123"
                },
            User(
                "Ezequiel",
                "Oyola",
                "ezeoyola",
                LocalDate.of(2011,1, 1),
                "Argentina")
                .apply {
                    email = "ezeoyola@gmail.com"
                    personality = PersonalityCautios()
                    vehiclePreference = Combined().apply {
                        vehiclePersonalities.addAll(mutableListOf(Superstitious(),NoLimit(),Capricious()))
                    }
                    destinationRepository.getById(3)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(5)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(4)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(2)?.let { visitedDestinations.add(it) }
                    destinationRepository.getById(2)?.let { desiredDestinations.add(it) }
                    destinationRepository.getById(6)?.let { desiredDestinations.add(it) }
                    itineraries = mutableListOf(
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(5)!!).apply {
                            vehicleRepository.getById(0)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        }
                    )
                    friends = mutableListOf(
                        User("","","", LocalDate.now(),""),
                        User("","","", LocalDate.now(),""),
                        User("","","", LocalDate.now(),""),
                    )
                    itinerariesToRate = mutableListOf(
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(5)!!).apply {
                            vehicleRepository.getById(0)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        },
                        Itinerary(  User("","","", LocalDate.now(),""),
                            destinationRepository.getById(3)!!).apply {
                            vehicleRepository.getById(2)?.let { vehicle = it }
                        }
                    )
                    this.password = "eze123"
                },
            User(
                "Alejandro",
                "Nava",
                "alenava",
                LocalDate.of(2014,1,1),
                "Islandia" ).apply {
                email = "anava@gmail.com"
                personality = PersonalityDreamer()
                vehiclePreference = Combined().apply {
                    vehiclePersonalities.addAll(mutableListOf(Selective("Honda"),NoLimit()))
                }
                destinationRepository.getById(2)?.let { visitedDestinations.add(it) }
                destinationRepository.getById(5)?.let { visitedDestinations.add(it) }
                destinationRepository.getById(4)?.let { visitedDestinations.add(it) }
                destinationRepository.getById(3)?.let { visitedDestinations.add(it) }
                destinationRepository.getById(4)?.let { desiredDestinations.add(it) }
                itineraries = mutableListOf(
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(5)!!).apply {
                        vehicleRepository.getById(0)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(3)!!).apply {
                        vehicleRepository.getById(2)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(3)!!).apply {
                        vehicleRepository.getById(2)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(1)!!).apply {
                        vehicleRepository.getById(1)?.let { vehicle = it }
                    }
                )
                friends = mutableListOf(
                    User("","","", LocalDate.now(),""),
                    User("","","", LocalDate.now(),""),
                    User("","","", LocalDate.now(),""),
                    User("","","", LocalDate.now(),""),
                    User("","","", LocalDate.now(),""),
                )
                itinerariesToRate = mutableListOf(
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(5)!!).apply {
                        vehicleRepository.getById(0)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(3)!!).apply {
                        vehicleRepository.getById(2)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(3)!!).apply {
                        vehicleRepository.getById(2)?.let { vehicle = it }
                    }
                )
                this.password = "ale123"
            },
            User(
                "Denise",
                "Martin",
                "dmartin",
                LocalDate.of(2012,5,1),
                "Islandia" ).apply {
                email = "dmartin@gmail.com"
                personality = PersonalityActive()
                vehiclePreference = Combined().apply {
                    vehiclePersonalities.addAll(mutableListOf(Neophyte(),NoLimit()))
                }
                destinationRepository.getById(5)?.let { visitedDestinations.add(it) }
                destinationRepository.getById(3)?.let { desiredDestinations.add(it) }
                destinationRepository.getById(1)?.let { desiredDestinations.add(it) }
                itineraries = mutableListOf(
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(5)!!).apply {
                        vehicleRepository.getById(0)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(2)!!).apply {
                        vehicleRepository.getById(1)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(3)!!).apply {
                        vehicleRepository.getById(2)?.let { vehicle = it }
                    }
                )
                friends = mutableListOf(
                    User("","","", LocalDate.now(),""),
                    User("","","", LocalDate.now(),""),
                    User("","","", LocalDate.now(),""),
                    User("","","", LocalDate.now(),""),
                )
                itinerariesToRate = mutableListOf(
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(5)!!).apply {
                        vehicleRepository.getById(0)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                        destinationRepository.getById(3)!!).apply {
                        vehicleRepository.getById(2)?.let { vehicle = it }
                    },
                    Itinerary(  User("","","", LocalDate.now(),""),
                    destinationRepository.getById(5)!!).apply {
                    vehicleRepository.getById(4)?.let { vehicle = it }
                },
                    Itinerary(  User("","","", LocalDate.now(),""),
                    destinationRepository.getById(3)!!).apply {
                    vehicleRepository.getById(1)?.let { vehicle = it }
                }
                )
                this.password = "denise123"
            },
        )
        this.elements.forEach{ it.id = count++ }
    }



    override fun search(value: String): List<User> =
        this.elements.filter { value.equals(it.name, ignoreCase = true) ||
                value.equals(it.surname, ignoreCase = true) || value.equals(it.userName) }

    fun searchUsersByDestination(destination: Destination): List<User> {
        return elements.filter { it.knowDestination(destination) }
    }

    fun getByUsername(username: String) = this.elements.find { it.userName.equals(username, ignoreCase = true) }

    fun createDestinationVisited(id: Int, destination: Destination): User {
        val user = this.getById(id) ?: throw ElementNotFoundException("User with id <$id>")
        if(!user.knowDestination(destination))
            user.visitedDestinations.add(destination)
        return user
    }
}