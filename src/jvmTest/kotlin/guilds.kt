import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.Guild
import com.gabriel.lunala.project.entity.GuildCreateDTO
import com.gabriel.lunala.project.entity.GuildUpdateDTO
import com.gabriel.lunala.project.service.GuildService
import com.gabriel.lunala.project.util.prepareClient
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.util.*
import org.junit.jupiter.api.Test

class GuildOperationsTest {

    @Test
    @KtorExperimentalAPI
    suspend fun `test operations`() {
        operate(GuildService(LunalaWrapper("http://localhost:8080/api", "key", client = HttpClient(CIO.create()) {
            prepareClient()
        })))
    }

}

private suspend fun operate(service: GuildService) {
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

suspend fun create(service: GuildService, block: Guild.() -> Unit) =
    service.create(GuildCreateDTO(1, "en-us", false)).also(block)

suspend fun update(service: GuildService, block: Guild.() -> Unit) =
    service.update(1, GuildUpdateDTO(locale = "pt-br")).also(block)

suspend fun get(service: GuildService, block: Guild.() -> Unit) =
    service.retrieve(1).also(block)

suspend fun delete(service: GuildService, id: Long = 1, block: Long.() -> Unit) =
    service.delete(id).also { block(id) }