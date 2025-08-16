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

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.koin.core.jvm)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.platform.launcher)
    testImplementation(libs.junit.platform.engine)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(22))
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
