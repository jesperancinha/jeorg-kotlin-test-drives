allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
//    id("io.arrow-kt.analysis.kotlin") version "2.0.2"
    kotlin("jvm") version "1.9.0"
    application
    idea
    id("jacoco")
    id("org.jesperancinha.plugins.omni") version "0.3.1"
}

idea {
    module {
        sourceDirs = sourceDirs + file("build/generated/ksp/main/kotlin")
        testSourceDirs = testSourceDirs + file("build/generated/ksp/test/kotlin")
        generatedSourceDirs =
            generatedSourceDirs + file("build/generated/ksp/main/kotlin") + file("build/generated/ksp/test/kotlin")
    }
}
val arrowVersion = "1.2.0"

dependencies {
    implementation(platform("io.arrow-kt:arrow-stack:$arrowVersion"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-optics")
    implementation("org.jesperancinha.console:consolerizer:2.2.2")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    implementation("org.jetbrains.kotlin:kotlin-test:1.9.0")
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
    jvmToolchain(19)
}

