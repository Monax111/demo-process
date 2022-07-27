package ru.tim.demo.ci.gradle.storage

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.tim.demo.ci.gradle.storage.client.ScoringClient
import ru.tim.demo.ci.gradle.storage.controller.Controller
import ru.tim.demo.ci.gradle.storage.model.PersonDto


@SpringBootTest
@Tag("service")
class DemoApplicationTest {

    @Autowired
    lateinit var controller: Controller

    @MockkBean
    lateinit var scoringClient: ScoringClient

    @Test
    fun contextUp() = Unit

    @Test
    fun score() {
        val person = PersonDto(
            name = "tim",
            age = 22
        )
        val personEntity = controller.save(person)

        val scoreResult = "Yes"
        every { scoringClient.score(person) }.returns(scoreResult)

        val response = controller.start(personEntity.id)
        Assertions.assertEquals(response, scoreResult)
    }
}