pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}

buildscript {
    repositories{
        mavenCentral()
    }

    dependencies{
        classpath(group = "org.testcontainers", name = "postgresql", version = "1.17.2")
    }
}