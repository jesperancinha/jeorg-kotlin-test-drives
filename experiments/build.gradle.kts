buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

group = "org.jesperancinha.ktd"
version = "0.0.0"

plugins {
    jacoco
    id( "org.jesperancinha.plugins.omni") version "0.3.1"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
