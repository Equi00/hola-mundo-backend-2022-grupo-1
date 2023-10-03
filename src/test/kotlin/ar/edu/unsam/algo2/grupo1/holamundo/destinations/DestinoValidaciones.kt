package ar.edu.unsam.algo2.grupo1.destinations

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class DestinoValidaciones : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("Test de validacion de destino"){

        val argentina = Destination("Argentina", "Buenos Aires", 30000.toFloat())
        val italia = Destination("Italia", "Roma", 0.toFloat())
        val chile = Destination("Chile", "Santiago", (-100).toFloat())
        val espania = Destination("España", "",15000.toFloat())
        val brasil = Destination("", "Brasilia", 20000.toFloat())

        it("Si el destino tiene pais, ciudad y costo base mayor a cero entonces es valido"){
            argentina.isValid().shouldBeTrue()
        }
        it("Si el destino tiene un costo base igual a cero entonces es invalido"){
            italia.isValid().shouldBeFalse()
        }
        it("Si el destino tiene un costo base menor a cero entonces es invalido"){
            chile.isValid().shouldBeFalse()
        }
        it("Si el destino no tiene ciudad entonces es invalido"){
            espania.isValid().shouldBeFalse()
        }
        it("Si el destino no tiene pais entonces es invalido"){
            brasil.isValid().shouldBeFalse()
        }
    }
})