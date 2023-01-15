pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "performance-projects"
include(":performance-input-test-generator")
include(":performance-buffered-reader")
include(":performance-jump-search")
