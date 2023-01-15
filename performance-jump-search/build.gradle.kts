//apply plugin: 'idea'
//apply plugin: 'java'
//apply plugin: 'jacoco'


plugins {
    kotlin("jvm") version "1.8.0"
    idea
    java
    jacoco
}

repositories {
    mavenLocal()
    mavenCentral()
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
//group "jesperancinha"
//version "0.0.0-SNAPSHOT"

//sourceCompatibility = 17
//targetCompatibility = 17

//task fatJar(type: Jar) {
//    baseName = project.name + '-all'
//    from { configurations.compile.collect { it.isDirectory() ? it : zipTree(it) } }
//    with jar
//}
//
//test {
//    finalizedBy jacocoTestReport
//}

//jacocoTestReport {
//    dependsOn test
//}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.platform:junit-platform-suite-engine:1.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}


