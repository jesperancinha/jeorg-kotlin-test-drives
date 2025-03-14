import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

group = "org.jesperancinha"

plugins {
    kotlin("jvm") version "2.1.10"
    id("java-gradle-plugin")
    id("maven-publish")
    id("org.jesperancinha.plugins.omni") version "0.3.1"
    jacoco
}


repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.opencsv:opencsv:5.10")
    implementation("org.apache.commons:commons-io:1.3.2")
    testImplementation(platform("org.junit:junit-bom:5.12.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.platform:junit-platform-engine")
    testImplementation("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}


tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

kotlin {
    jvmToolchain(21)
}