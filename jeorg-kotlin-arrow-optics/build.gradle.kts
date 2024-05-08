buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    jacoco
    alias(libs.plugins.jesperancinha.omni)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
