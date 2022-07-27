package ru.tim.demo.ci.gradle.storage.controller

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.tim.demo.ci.gradle.storage.model.Person
import ru.tim.demo.ci.gradle.storage.service.PersonService

@RestController
class Controller(
    val personService: PersonService
) {

    @PutMapping("people/add")
    fun addPeople(
        @RequestBody person: Person
    ) = personService.addPeople(person)


}