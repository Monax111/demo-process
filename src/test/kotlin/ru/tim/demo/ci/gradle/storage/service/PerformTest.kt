package ru.tim.demo.ci.gradle.storage.service

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import kotlin.time.DurationUnit
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PerformTest {


    @Test
    fun go() {

        val request = HttpRequest
            .newBuilder(URI("http://localhost:8080/best-process/people/add"))
            .PUT(HttpRequest.BodyPublishers.ofString("""{"name": "string","age": 12}"""))
            .timeout(Duration.ofMillis(10))
            .build()
        val client = HttpClient.newHttpClient()

        repeat(10000) {
            kotlin.runCatching {
                client.send(request, HttpResponse.BodyHandlers.ofString())
            }
        }
    }

}