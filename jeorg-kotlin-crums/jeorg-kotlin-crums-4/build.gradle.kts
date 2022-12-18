allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.8.0-RC-1.0.8"
    kotlin("jvm") version "1.7.21"
    application
    idea
    id("jacoco")
    id("org.jesperancinha.plugins.omni") version "0.3.0"
}

idea {
    module {
        sourceDirs = sourceDirs + file("build/generated/ksp/main/kotlin")
        testSourceDirs = testSourceDirs + file("build/generated/ksp/test/kotlin")
        generatedSourceDirs =
            generatedSourceDirs + file("build/generated/ksp/main/kotlin") + file("build/generated/ksp/test/kotlin")
    }
}
val arrowVersion = "1.1.4-rc.2"

dependencies {
    implementation(platform("io.arrow-kt:arrow-stack:$arrowVersion"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-optics")
    implementation("org.jesperancinha.console:consolerizer:2.0.12")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")
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


