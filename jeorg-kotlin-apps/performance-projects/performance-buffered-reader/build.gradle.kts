plugins {
    alias(libs.plugins.kotlin.jvm)
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
    jvmToolchain(21)
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
    implementation("commons-io:commons-io:2.19.0")
    implementation("org.slf4j:slf4j-api:2.0.17")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.0")
    testImplementation("org.junit.platform:junit-platform-suite-engine:1.13.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.13.0")
    testImplementation("com.google.truth:truth:1.4.4")
}
