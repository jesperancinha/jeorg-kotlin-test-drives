plugins {
    kotlin("jvm") version "1.9.20"
    idea
    java
    jacoco
    `maven-publish`
}

publishing {

}
repositories {
    mavenLocal()
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

kotlin {
    jvmToolchain(17)
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
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.jesperancinha:string-paradigm-expression-api:0.0.0-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.7.2")
}
