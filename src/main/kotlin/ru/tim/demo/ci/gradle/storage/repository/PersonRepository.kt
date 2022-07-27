package ru.tim.demo.ci.gradle.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface PersonRepository : JpaRepository<PersonEntity, String>