plugins {
    alias(libs.plugins.kotlin.jvm)
    idea
    java
    jacoco
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.jesperancinha"
            artifactId = "string-paradigm-api"
            version = "0.0.0-SNAPSHOT"
            from(components["java"])
        }
    }
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

group = "org.jesperancinha"
version = "0.0.0-SNAPSHOT"

tasks.jar {
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports{
        xml.required.set(true)
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    api(libs.slf4j.api)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.kotest.assertions.core)
}
