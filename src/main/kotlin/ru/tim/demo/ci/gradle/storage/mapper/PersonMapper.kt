package ru.tim.demo.ci.gradle.storage.mapper

import org.springframework.stereotype.Component
import ru.tim.demo.ci.gradle.storage.model.Person
import ru.tim.demo.ci.gradle.storage.repository.PersonEntity

@Component
class PersonMapper {

    fun mapToEntity(person: Person) = PersonEntity(
        name = person.name,
        age = person.age
    )

}