plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.jesperancinha"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.kotest:kotest-assertions-core-jvm:5.8.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}