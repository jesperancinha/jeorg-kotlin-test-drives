buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "2.0.20"
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
    jvmToolchain(21)
}

group = "org.jesperancinha"
version = "0.0.0-SNAPSHOT"

