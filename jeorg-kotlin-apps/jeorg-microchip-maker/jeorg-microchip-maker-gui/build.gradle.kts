allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    jacoco
    alias(libs.plugins.jesperancinha.omni)
    alias(libs.plugins.kotlin.js)
}

group = "org.jesperancinha.ktd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:19.0.0-pre.852")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:19.0.0-pre.852")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.14.0-pre.852")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.28.0-pre.852")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.2-pre.785")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux:7.2.6-pre.785")
}

kotlin {
    jvmToolchain(21)
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport { }
            }
        }
    }
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}
