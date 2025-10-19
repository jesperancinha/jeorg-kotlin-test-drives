buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.kotlin.jvm)
    id("java-gradle-plugin")
    id("maven-publish")
    jacoco
}

repositories {
    mavenCentral()
    mavenLocal()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

kotlin {
    jvmToolchain(25)
}

group = "org.jesperancinha"
version = "0.0.0-SNAPSHOT"

