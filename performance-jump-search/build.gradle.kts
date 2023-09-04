plugins {
    kotlin("jvm") version "1.9.10"
    idea
    java
    jacoco
}

repositories {
    mavenLocal()
    mavenCentral()
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

group = "jesperancinha"
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
    implementation("org.slf4j:slf4j-api:2.0.9")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.platform:junit-platform-suite-engine:1.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.7.1")
}


