allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    jacoco
    alias(libs.plugins.jesperancinha.omni)
    alias(libs.plugins.kotlin.multiplatform)
}

group = "org.jesperancinha.ktd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


kotlin {
    jvmToolchain(25)
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport { }
            }
        }
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(libs.kotlin.react)
                implementation(libs.kotlin.react.dom)
                implementation(libs.kotlin.emotion)
                implementation(libs.kotlin.react.router.dom)
                implementation(libs.kotlin.redux)
                implementation(libs.kotlin.react.redux)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}
