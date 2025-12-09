plugins {
    java
    jacoco
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jesperancinha.omni)
}

group = "org.jesperancinha.ktd"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}