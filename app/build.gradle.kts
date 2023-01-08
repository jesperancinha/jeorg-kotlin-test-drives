import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
    }
}


plugins {
    kotlin("jvm") version "1.8.0" // Kotlin version to use
    application // Application plugin. Also see 1️⃣ below the code
    id("java-gradle-plugin")
    id("maven-publish")
    id("org.jesperancinha.plugins.omni")
    jacoco
}


repositories {
    mavenCentral()
    mavenLocal()
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation ("org.kohsuke.args4j:args4j-maven-plugin:2.33")
    implementation ("com.opencsv:opencsv:5.7.1")
    implementation ("org.apache.groovy:groovy:4.0.7")
    implementation ("org.spockframework:spock-core:2.3-groovy-4.0")
    implementation ("org.apache.commons:commons-io:1.3.2")
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.jmock:jmock-junit4:2.12.0")
    testImplementation ("org.jmock:jmock-legacy:2.12.0")
}

tasks.test { // See 5️⃣
    useJUnitPlatform() // JUnitPlatform for tests. See 6️⃣
    finalizedBy(tasks.jacocoTestReport)
}


tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports{
        xml.required.set(true)
        csv.required.set(true)
    }
}


