import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.Planet
import com.gabriel.lunala.project.entity.PlanetCreateDTO
import com.gabriel.lunala.project.entity.PlanetUpdateDTO
import com.gabriel.lunala.project.service.PlanetService
import com.gabriel.lunala.project.util.prepareClient
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.util.*
import org.junit.jupiter.api.Test

class PlanetOperationsTest {

    @Test
    @KtorExperimentalAPI
    suspend fun `test operations`() {
        operate(PlanetService(LunalaWrapper("http://localhost:8080/api", "key", client = HttpClient(CIO.create()) {
            prepareClient()
        })))
    }

}

private suspend fun operate(service: PlanetService) {
    create(service) {
        println("Created $this")
    }
    update(service) {
        println("Updated $this")
    }
    get(service) {
        println("Got $this")
    }
    delete(service) {
        println("Deleted $this")
    }
}

suspend fun create(service: PlanetService, block: Planet.() -> Unit) =
    service.create(PlanetCreateDTO("Earth", 0, 100f, null, "Milky Way", false)).also(block)

suspend fun update(service: PlanetService, block: Planet.() -> Unit) =
    service.update("Earth", PlanetUpdateDTO(owner = 360162870069166080)).also(block)

suspend fun get(service: PlanetService, block: Planet?.() -> Unit) =
    service.retrieve("Earth").also(block)

suspend fun delete(service: PlanetService, id: String = "Earth", block: String.() -> Unit) =
    service.delete(id).also { block(id) }