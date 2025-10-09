plugins {
    jacoco
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jesperancinha.omni)
}

group = "jesperancinha"
version = "1.0-SNAPSHOT"

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}

repositories {
    mavenCentral()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

dependencies {
    api(libs.kotlin.stdlib)
    api(libs.google.gson)
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.framework.engine.jvm)
    testImplementation(libs.kotest.runner.junit5.jvm)
}

kotlin {
    jvmToolchain(25)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}