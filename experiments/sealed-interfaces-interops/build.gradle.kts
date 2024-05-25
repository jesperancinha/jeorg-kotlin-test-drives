plugins {
    java
    jacoco
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jesperancinha.omni)
}

group = "org.jesperancinha.ktd"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}