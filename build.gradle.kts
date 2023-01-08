buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.8.0"
    id("java-gradle-plugin")
    id("maven-publish")
    jacoco
}

repositories {
    mavenCentral()
    mavenLocal()
}

group "jesperancinha"
version "0.0.0-SNAPSHOT"

