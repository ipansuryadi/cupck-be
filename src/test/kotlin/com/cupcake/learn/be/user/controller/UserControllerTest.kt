import com.cupcake.learn.be.user.controller.UserController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.client.MockMvcWebTestClient

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
class UserControllerTest {
    @Test
    fun `should ok`() {
        val controller = UserController()
        val client = MockMvcWebTestClient.bindToController(controller).build()
        client
            .get()
            .uri("/users")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(String::class.java)
            .isEqualTo("Hello")
    }
}
