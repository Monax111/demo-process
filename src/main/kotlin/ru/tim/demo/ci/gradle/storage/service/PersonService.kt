package ru.tim.demo.ci.gradle.storage.service

import org.springframework.stereotype.Component
import ru.tim.demo.ci.gradle.storage.client.ScoringClient
import ru.tim.demo.ci.gradle.storage.model.PersonDto
import ru.tim.demo.ci.gradle.storage.repository.PersonEntity
import ru.tim.demo.ci.gradle.storage.repository.PersonRepository

@Component
class PersonService(
    val personRepository: PersonRepository,
    val scoringClient: ScoringClient
) {
    fun save(personDto: PersonDto): PersonEntity {

        personDto.validate()

        val personEntity = PersonEntity(
            name = personDto.name,
            age = personDto.age
        )
        return personRepository.save(personEntity)
    }

    fun score(personId: String): String {
        val person = personRepository.findById(personId).orElseThrow()
        return scoringClient.score(person.toModel())
    }
}

fun PersonEntity.toModel() = PersonDto(
    name = name,
    age = age
)

fun PersonDto.validate() {
    if (age < 0) {
        throw RuntimeException("Возраст не может быть меньше 0. $age")
    }
}
