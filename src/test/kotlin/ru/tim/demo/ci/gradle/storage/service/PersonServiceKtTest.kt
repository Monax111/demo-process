package ru.tim.demo.ci.gradle.storage.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.tim.demo.ci.gradle.storage.model.PersonDto


internal class PersonServiceKtTest {

    @Test
    fun `когда возраст отрицательный человек не валидный`() {
        assertThrows<RuntimeException> {
            PersonDto(
                age = -1,
                name = ""
            ).validate()
        }

    }

    @Test
    fun `когда возраст положительный человек валидный`() {
        PersonDto(
            age = 1,
            name = ""
        ).validate()
    }
}