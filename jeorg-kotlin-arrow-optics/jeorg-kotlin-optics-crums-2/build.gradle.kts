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
val arrowVersion = "1.2.4"

dependencies {
    implementation(platform("io.arrow-kt:arrow-stack:$arrowVersion"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-optics")
    implementation("org.jesperancinha.console:consolerizer:2.2.3")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.3")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.10.3")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.10.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.0.0")
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
