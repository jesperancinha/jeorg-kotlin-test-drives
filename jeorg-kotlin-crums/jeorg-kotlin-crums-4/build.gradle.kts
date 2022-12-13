allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.6.10-1.0.4"
    kotlin("jvm") version "1.7.21"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

val arrowVersion = "1.1.4-rc.2"

dependencies {
    implementation(platform("io.arrow-kt:arrow-stack:$arrowVersion"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-optics")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
        kotlinOptions.jvmTarget = "17"
    }
}