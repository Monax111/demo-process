package ru.tim.demo.ci.gradle.storage.model

import ru.tim.demo.ci.gradle.storage.exception.PersonNotValid

data class Person(
    val name: String,
    val age: Int
) {
    init {
        if (age < 1) {
            throw PersonNotValid("Возраст должен быть больше 0")
        }
    }

}
