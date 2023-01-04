allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("js") version "1.8.0"
    id("jacoco")
    id("org.jesperancinha.plugins.omni") version "0.3.0"
}

group = "org.jesperancinha.ktd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.468")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.468")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.10.5-pre.467-compat")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.3.0-pre.467-compat")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.2-pre.467-compat")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux:7.2.6-pre.468")
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {  }
            }
        }
    }
}
