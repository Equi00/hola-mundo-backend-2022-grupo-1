package ar.edu.unsam.algo2.grupo1.tasks
import ar.edu.unsam.algo2.grupo1.mailSender.Mail
import ar.edu.unsam.algo2.grupo1.mailSender.MailSender
import ar.edu.unsam.algo2.grupo1.repositories.UserRepository
import ar.edu.unsam.algo2.grupo1.users.User

abstract class Task() {
    var name: String = ""
    var fromMail: String = "app@holamundo.com"
    var toMail: String = ""
    var subjectMail: String = "Task ${this.name} has been done."
    var contentMail: String = "Task ${this.name} has been done."
    lateinit var mailSender: MailSender
    lateinit var userRepository: UserRepository

    fun doTask(user: User){
        this.performInternalTask(user)
        if(user.email.isNotEmpty()){
            this.sendMail(user)
        }
    }

     private fun sendMail(user: User){
        this.toMail = user.email
        this.mailSender.sendMail(Mail(
            this.fromMail,
            this.toMail,
            this.subjectMail,
            this.contentMail
        ))
    }

    abstract fun performInternalTask(user: User)
}