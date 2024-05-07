plugins {
    kotlin("jvm") version "1.9.24"
    idea
    java
    jacoco
    id("org.jesperancinha.plugins.omni") version "0.3.1"
}
group = "jesperancinha"
version = "0.0.0-SNAPSHOT"


repositories {
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}
