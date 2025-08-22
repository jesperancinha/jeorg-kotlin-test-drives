buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    alias(libs.plugins.kotlin.jvm)
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
    jvmToolchain(21)
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
