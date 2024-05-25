buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    jacoco
    java
    id( "org.jesperancinha.plugins.omni") version "0.3.1"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
