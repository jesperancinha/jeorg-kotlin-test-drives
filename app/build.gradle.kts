import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

group = "org.jesperancinha"

plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    id("maven-publish")
    id("org.jesperancinha.plugins.omni") version "0.3.0"
    jacoco
}


repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.kohsuke.args4j:args4j-maven-plugin:2.33")
    implementation("com.opencsv:opencsv:5.7.1")
    implementation("org.apache.commons:commons-io:1.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.4")
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
    jvmToolchain(18)
}