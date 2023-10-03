package ar.edu.unsam.algo2.grupo1.observers

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import ar.edu.unsam.algo2.grupo1.itinerary.Itinerary
import ar.edu.unsam.algo2.grupo1.mailSender.Mail
import ar.edu.unsam.algo2.grupo1.mailSender.MailSender
import ar.edu.unsam.algo2.grupo1.users.User

class tellFriendAboutTripObserver(var mailSender: MailSender) : ObserverBehaviour {
    override fun doBehaviour(user: User, itinerary: Itinerary) {

        this.friendsWithDesiredDestination(user, itinerary.destination).forEach {
            mailSender.sendMail(Mail(
                "app@holamundo.com",
                it.email,
                "Visitaron un destino que te puede interesar",
                "Hola ${it.name}, ${user.name} ${user.surname}" +
                        "visitó ${itinerary.country()} ${itinerary.city()}"
            ))
        }
    }

    private fun friendsWithDesiredDestination(user: User, destination: Destination): List<User> {
        return user.friends.filter { it.desiredDestinations.contains(destination) }
    }
}