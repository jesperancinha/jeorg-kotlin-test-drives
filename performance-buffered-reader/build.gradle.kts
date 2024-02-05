plugins {
    kotlin("jvm") version "1.9.22"
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
    implementation("commons-io:commons-io:2.15.1")
    implementation("org.slf4j:slf4j-api:2.0.11")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.platform:junit-platform-suite-engine:1.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("com.google.truth:truth:1.4.0")
}
