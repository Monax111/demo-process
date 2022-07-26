package ru.tim.demo.ci.gradle.storage.repository

import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<PersonEntity, String>