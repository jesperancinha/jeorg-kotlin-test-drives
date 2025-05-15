plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "org.jesperancinha.asnsei.modularity"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":jeorg-kotlin-crums:module-common")))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}