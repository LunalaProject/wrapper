import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.User
import com.gabriel.lunala.project.entity.UserCreateDTO
import com.gabriel.lunala.project.entity.UserUpdateDTO
import com.gabriel.lunala.project.service.UserService
import com.gabriel.lunala.project.util.prepareClient
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import org.junit.jupiter.api.Test

class OperationsTest {

    @Test
    suspend fun `test operations`() {
        operate(UserService(LunalaWrapper("http://localhost:8080/api", "key", client = HttpClient(CIO.create()) {
            prepareClient()
        })))
    }

}

suspend fun operate(service: UserService) {
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

suspend fun create(service: UserService, block: User.() -> Unit) =
    service.create(UserCreateDTO(1, 0, "Earth")).also(block)

suspend fun update(service: UserService, block: User.() -> Unit) =
    service.update(1, UserUpdateDTO(20, null)).also(block)

suspend fun get(service: UserService, block: User.() -> Unit) =
    service.retrieve(1).also(block)

suspend fun delete(service: UserService, id: Long = 1, block: Long.() -> Unit) =
    service.delete(id).also { block(id) }