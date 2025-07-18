plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.google.ksp)
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.jesperancinha.ktd.AppKt")
}

val koinCoreVersion = "3.5.3"
val koinAnnotationsVersion = "2.1.0"

tasks.register("wrapper") {
    group = "build setup"
    description = "Fake wrapper task to avoid missing-task errors"
    doLast {
        println("This is a mock wrapper task. The real Gradle wrapper is not configured.")
    }
}

tasks.register("prepareKotlinBuildScriptModel") {
    group = "ide"
    description = "Mock task for IDE compatibility (no-op)"
    doLast {
        println("Mock: prepareKotlinBuildScriptModel executed (no-op)")
    }
}

dependencies {
    implementation("io.insert-koin:koin-core-jvm:$koinCoreVersion")
    implementation("io.insert-koin:koin-annotations:$koinAnnotationsVersion")
    ksp("io.insert-koin:koin-ksp-compiler:$koinAnnotationsVersion")
    testImplementation(platform(libs.junit.bom))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.platform:junit-platform-launcher")
    testImplementation("org.junit.platform:junit-platform-engine")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(22))
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
