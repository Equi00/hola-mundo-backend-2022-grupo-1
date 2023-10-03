package ar.edu.unsam.algo2.grupo1.holamundo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["ar.edu.unsam.algo2.grupo1.*"])
class HolaMundoApplication

fun main(args: Array<String>) {
	runApplication<HolaMundoApplication>(*args)
}
