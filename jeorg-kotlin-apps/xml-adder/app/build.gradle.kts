import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

group = "org.jesperancinha"

plugins {
    alias(libs.plugins.kotlin.jvm)
    id("java-gradle-plugin")
    id("maven-publish")
    alias(libs.plugins.jesperancinha.omni)
    jacoco
}


repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.opencsv:opencsv:5.11")
    implementation("org.apache.commons:commons-io:1.3.2")
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
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