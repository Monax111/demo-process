package ru.tim.demo.ci.gradle.storage.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.tim.demo.ci.gradle.storage.controller.Controller
import ru.tim.demo.ci.gradle.storage.exception.PersonNotValid
import ru.tim.demo.ci.gradle.storage.model.Person
import ru.tim.demo.ci.gradle.storage.repository.PersonEntity

@SpringBootTest
class PersonServiceKtTest {

    @Autowired
    lateinit var controller: Controller

    @Test
    fun `когда добавляем человека нет ошибок`() {
        val person = Person(
            name = "Tim",
            age = 26
        )

        val personEntity = PersonEntity(
            name = "Tim",
            age = 26
        )
        val response = controller.addPeople(person)
        Assertions.assertEquals(personEntity.name, response.name)
        Assertions.assertEquals(personEntity.age, response.age)
    }

    @Test
    fun `когда добавляем человека с отрицательным возрастом происходит исключение`() {

        assertThrows<PersonNotValid> {
            val person = Person(
                name = "Tim",
                age = -26
            )
            controller.addPeople(person)
        }
    }

}