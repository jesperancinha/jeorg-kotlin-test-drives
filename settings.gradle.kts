pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "string-array-paradigms"
include(":string-paradigm-api")
include(":string-paradigm-expression-api")
include(":string-paradigm-dependency_2")
include(":string-paradigm-expression-no-wrapper")
include(":string-paradigm-expression-original")
include(":string-paradigm-expression_1")
include(":string-paradigm-expression_2")
include(":string-paradigm-no-wrapper")
include(":string-paradigm-test-generator")
include(":string-paradigm_1")
include(":string-paradigm_original")
