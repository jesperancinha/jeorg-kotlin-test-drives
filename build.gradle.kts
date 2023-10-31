buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.9.20"
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
    jvmToolchain(18)
}

group = "org.jesperancinha"
version = "0.0.0-SNAPSHOT"

