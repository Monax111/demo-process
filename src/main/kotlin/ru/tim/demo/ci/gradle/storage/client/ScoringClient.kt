package ru.tim.demo.ci.gradle.storage.client

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.tim.demo.ci.gradle.storage.model.PersonDto

@Component
class ScoringClient(
    @Value("\${rest.scoring.url}")
    val url: String
) : RestTemplate() {

    fun score(personDto: PersonDto): String {
        val request: HttpEntity<PersonDto> = HttpEntity<PersonDto>(personDto)
        logger.info("Send request $request to url $url")
        return postForObject(url, request, String::class.java)!!
    }


}