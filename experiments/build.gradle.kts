buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

group = "org.jesperancinha.ktd"
version = "0.0.0"

tasks.withType<Test> {
    useJUnitPlatform()
}
