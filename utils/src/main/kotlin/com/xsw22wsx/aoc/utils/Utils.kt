import io.ktor.client.*
import io.ktor.client.engine.cio.*

private class Utils
val input: String
    get() = Utils::class.java.getResource("input")?.readText()?: throw IllegalStateException("No input found")