plugins {
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.kotlin.jvm)
    application
    idea
    jacoco
    alias(libs.plugins.jesperancinha.omni)
}

group = "org.jesperancinha"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.arrow.stack))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-core-jvm")
    implementation("io.arrow-kt:arrow-optics")
    implementation("io.arrow-kt:arrow-fx-coroutines")
    implementation(libs.arrow.analysis.types.jvm)
    implementation(libs.consolerizer)
    implementation(libs.kotest.assertions.core.jvm)
    ksp(libs.arrow.optics.ksp.plugin)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.assertj.core)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(25)
}