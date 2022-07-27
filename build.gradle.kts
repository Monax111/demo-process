import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.2"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    jacoco
    id("ru.tim.demo.ci.gradle.plugin") version "1.0.0"
    id("org.liquibase.gradle") version "2.1.0"
}

group = "ru.tim.demo.ci.gradle"
version = "1.1"

repositories {
    mavenCentral()
}

java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    api(platform("org.springframework.cloud:spring-cloud-dependencies:2021.0.3"))
    api(platform("org.springframework.boot:spring-boot-dependencies:2.7.2"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")
    implementation(group = "org.postgresql", name = "postgresql", version = "42.4.0")
    implementation(group = "org.testcontainers", name = "postgresql", version = "1.17.2")

    liquibaseRuntime("org.liquibase:liquibase-core:4.11.0")
    liquibaseRuntime("info.picocli:picocli:4.6.3")
    liquibaseRuntime(group = "org.postgresql", name = "postgresql", version = "42.4.0")
    liquibaseRuntime("org.yaml:snakeyaml:1.29")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
    implementation(group = "org.springdoc", name = "springdoc-openapi-kotlin", version = "1.6.9")

    implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks {

	wrapper{
		gradleVersion = "7.4"
	}

    test {
        useJUnitPlatform {
            excludeTags.add("service")
        }
    }

    check{
        dependsOn(verifyLogic)
    }

    jacocoTestReport {
        reports {
            html.required.set(true)
        }
    }

    val serviceTest = register<Test>("serviceTest"){
        useJUnitPlatform {
            includeTags.add("service")
        }
    }

    check{
        dependsOn(serviceTest)
    }

}

liquibase{
    activities.create("main"){
        arguments = mapOf(
            "logLevel" to "info",
            "changeLogFile" to "liquidbase/db.changelog-master.yaml",
            "url" to "jdbc:postgresql://localhost:52765/test",
            "username" to "test",
            "password" to "test",
            "driver" to "org.postgresql.Driver"
        )
    }
}
