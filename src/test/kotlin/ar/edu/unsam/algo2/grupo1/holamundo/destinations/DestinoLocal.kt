package ar.edu.unsam.algo2.grupo1.destinations

import ar.edu.unsam.algo2.grupo1.destinations.Destination
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class DestinoLocal : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test Destino Local"){
        val argentina = Destination("Argentina", "Buenos Aires", 30000.toFloat())
        val italia = Destination("Italia", "Roma", 35000.toFloat())
        it("Si el pais de destino es Argentina entonces el destino es local"){
            argentina.isLocal().shouldBeTrue()
        }
        it("Si el pais de destino no es Argentina entonces el destino no es local"){
            italia.isLocal().shouldBeFalse()
        }
    }
})