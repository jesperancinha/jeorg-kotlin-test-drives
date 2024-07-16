allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.kotlin.jvm)
    application
    idea
    jacoco
    alias(libs.plugins.jesperancinha.omni)
}

idea {
    module {
        sourceDirs = sourceDirs + file("build/generated/ksp/main/kotlin")
        testSourceDirs = testSourceDirs + file("build/generated/ksp/test/kotlin")
        generatedSourceDirs =
            generatedSourceDirs + file("build/generated/ksp/main/kotlin") + file("build/generated/ksp/test/kotlin")
    }
}
dependencies {
    implementation(platform(libs.arrow.stack))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-optics")
    implementation("io.arrow-kt:arrow-fx-coroutines")
    implementation(libs.arrow.analysis.types.jvm)
    implementation(libs.consolerizer)
    implementation(libs.kotest.assertions.core.jvm)
    ksp(libs.arrow.optics.ksp.plugin)
    testImplementation (libs.junit.jupiter.api)
    testImplementation (libs.junit.jupiter.engine)
    testImplementation(libs.kotlin.test)
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}
