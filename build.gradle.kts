buildscript {
    repositories {
        mavenLocal()
    }
}

plugins {
    kotlin("jvm") version "1.9.20"
    idea
    java
    jacoco
    `maven-publish`
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports{
        xml.required.set(true)
    }
}


group = "org.jesperancinha"
version = "0.0.0-SNAPSHOT"

kotlin {
    jvmToolchain(17)
}


repositories {
    mavenCentral()
    mavenLocal()
}


tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports{
        xml.required.set(true)
    }
}
