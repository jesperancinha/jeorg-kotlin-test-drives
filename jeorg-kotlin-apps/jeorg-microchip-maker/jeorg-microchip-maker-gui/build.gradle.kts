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
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.3.1-pre.766")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.3.1-pre.766")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.11.4-pre.766")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.23.1-pre.766")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.2-pre.766")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux:7.2.6-pre.766")
}

kotlin {
    jvmToolchain(19)
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {  }
            }
        }
    }
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}
