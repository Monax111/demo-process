package ru.tim.demo.ci.gradle.storage.repository

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PersonEntity(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val age: Int
)