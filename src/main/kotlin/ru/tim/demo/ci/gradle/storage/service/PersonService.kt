package ru.tim.demo.ci.gradle.storage.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.tim.demo.ci.gradle.storage.mapper.PersonMapper
import ru.tim.demo.ci.gradle.storage.model.Person
import ru.tim.demo.ci.gradle.storage.repository.PersonEntity
import ru.tim.demo.ci.gradle.storage.repository.PersonRepository


@Service
class PersonService(
    val repository: PersonRepository,
    val personMapper: PersonMapper
) {

    private val logger = KotlinLogging.logger {}

    fun addPeople(person:Person): PersonEntity {
        Thread.sleep(10000)
        logger.info("А это уже с логгером")
        val personEntity = personMapper.mapToEntity(person)
        return repository.save(personEntity)
    }
}
