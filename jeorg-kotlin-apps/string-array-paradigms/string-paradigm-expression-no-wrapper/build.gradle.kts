plugins {
    alias(libs.plugins.kotlin.jvm)
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
    jvmToolchain(25)
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
    reports {
        xml.required.set(true)
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    api(libs.slf4j.api)
    implementation(project(mapOf("path" to ":jeorg-kotlin-apps:string-array-paradigms::string-paradigm-api")))
    implementation("org.jesperancinha:string-paradigm-expression-api:0.0.0-SNAPSHOT")
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.platform.launcher)
    testImplementation(libs.junit.platform.engine)
    testImplementation(libs.kotest.assertions.core)
}
