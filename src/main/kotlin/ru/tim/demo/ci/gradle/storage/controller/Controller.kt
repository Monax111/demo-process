package ru.tim.demo.ci.gradle.storage.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.tim.demo.ci.gradle.storage.model.PersonDto
import ru.tim.demo.ci.gradle.storage.service.PersonService

@RestController
class Controller(
    val personService: PersonService
) {

    @GetMapping("hello")
    fun sayHello() = "hello"

    @PostMapping("person/save")
    fun save(person: PersonDto) = personService.save(person)

    @GetMapping("start/{personId}")
    fun start(@PathVariable personId: String) = personService.score(personId)

}